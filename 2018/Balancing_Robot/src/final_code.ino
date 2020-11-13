#include <TimerThree.h>
#include <SPI.h>
#include <RF24.h>
#include "Balance.h"

#define Kp 8
#define Kd 1.7
#define Ke 7
#define a 4

// 조종기 이름 값 저장
const byte ads1[6] = "DG03A", ads2[6] = "DG03B";
 
Balance BR;
RF24 radio(48,53);

// 각종 전역 변수 선언
bool cnt=0;
int Lc=0, Rc=0, vdif, count = 0, body = 1;
int16_t AcX, AcY, AcZ, Tmp, GyX, GyY, GyZ;
float deg_x=0, deg_p=0;
int8_t fb = 0, lr = 0, sw = 0, fb_p = 0;

void setup() {
  pinMode(5, OUTPUT);
  // 모터 & 자이로 Init
  BR.Motor_Init();  BR.Gyro_Init();
  // 타이머 인터럽트 Init
  Timer3.initialize(10000);
  Timer3.attachInterrupt(tim);
  // R_MOTOR interrupt
  attachInterrupt(digitalPinToInterrupt(2), CHA_A, CHANGE);
  attachInterrupt(digitalPinToInterrupt(3), CHA_B, CHANGE);
  // L_MOTOR interrupt
  attachInterrupt(digitalPinToInterrupt(18), CHB_B, CHANGE);
  attachInterrupt(digitalPinToInterrupt(19), CHB_A, CHANGE);
  // RF24 Init
  radio.begin();
  radio.setDataRate(RF24_250KBPS);
  radio.setRetries(0,0);
  radio.setChannel(94);
  radio.openWritingPipe(ads2);
  radio.openReadingPipe(1, ads1);
  radio.setPALevel(RF24_PA_MAX);
  radio.startListening();
}

void loop() {
  if (cnt==1) {
    // 타이머 카운트 초기화
    cnt=0;

    // 조종기 신호 수신 유무 확인
    char tp[3] = "";
    if(radio.available()) {
      radio.read(&tp, sizeof(tp));
      fb=-tp[0]; lr=tp[1]; sw=tp[2];
      digitalWrite(5, HIGH);
    }
    else {
      digitalWrite(5, LOW);
    }

    // 급가속, 급제동 방지
    if(fb>fb_p+a) {fb = fb_p + a;}
    if(fb<fb_p-a) {fb = fb_p - a;}
    fb_p = fb;

    // 자이로 변수 값 호출
    BR.Gyro_Get(&AcX, &AcY, &AcZ, &GyX, &GyY, &GyZ);

    // BR.Gfilter(가속도 각도, y축 각속도, k1,k2)
    float deg_a = atan2(AcX, sqrt(pow(AcY,2) + pow(AcZ,2))) * 57.29;
    deg_x = BR.Gfilter(deg_a, GyY, 3, 0.01);
    int deg = deg_x - 5;

    // 속도 값 저장
    int sl=Lc, sr=Rc; Lc=0; Rc=0;

    // P + D + encoder
    int vsum=sl+sr;
    int vel = deg*Kp + (-GyY)*0.03*Kd - (vsum+(fb*0.5))*Ke;
    deg_p=deg;

    // 좌우 조종
    float q;
    if(abs(fb) >= 15) {q=0.975;}
    else              {q=2;}
    vdif = 2*(sl-sr)-lr*q;

    // 모터 출력
    vel=constrain(vel,-255,255);
    BR.Motor(vel+vdif,vel-vdif);

    // 스위치로 세우기
    if(body==0) {
      BR.Motor(0,0);
      if(sw==2) {
        body = 2;
      }
    }
    if(body==1 && abs(deg)>58) {
      body = 0;
    }
    if(body==2 && abs(deg)<58) {
      body = 1;
    }
  }
}

void tim() {
 cnt = 1;
}

// (E1==1 && E2==1) || (E1==0 && E2==0)
void CHA_A() {
  boolean E1=digitalRead(2), E2=digitalRead(3);
  if(E1==E2) { Rc++; }
  else       { Rc--; }
}

// (E1==1 && E2==0) || (E1==0 && E2==1)
void CHA_B() {
  boolean E1=digitalRead(2), E2=digitalRead(3);
  if(E1!=E2) { Rc++; }
  else       { Rc--; }
}

// (E1==1 && E2==0) || (E1==0 && E2==1)
void CHB_B() {
  boolean E1=digitalRead(18), E2=digitalRead(19);
  if(E1!=E2) { Lc--; }
  else       { Lc++; }
}

// (E1==1 && E2==1) || (E1==0 && E2==0)
void CHB_A() {
  boolean E1=digitalRead(18), E2=digitalRead(19);
  if(E1==E2) { Lc--; }
  else       { Lc++; }
}
