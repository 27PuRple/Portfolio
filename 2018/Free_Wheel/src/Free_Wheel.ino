// PuRple's First Project : Free_Wheel

#include <Wire.h>
#include <Servo.h>
#include <SoftwareSerial.h>

// 기본 핀모드 설정
int LED1 = 8, LED2 = 9, LED3 = 10, LED4 = 11, LED5 = 12;
int LED1s = LOW, LED2s = LOW, LED3s = LOW, LED4s = LOW, LED5s = LOW;
int buzzer = 13;
int cnt = 0, count = 0;         // 카운트 변수 선언

// BLDC_ESC 관련 변수 선언
Servo bldc;
int pos = 0;

// 블루투스 관련 변수 선언
char button;                    // 제어할 변수 button선언
#define BT_RX 4
#define BT_TX 5
SoftwareSerial BTSerial(BT_RX, BT_TX);

// 자이로센서 관련 변수 선언
#define mpu 0x68                // MPU6050 I2C 기본주소
long ac_x, ac_y, ac_z, gy_x, gy_y, gy_z;
double angle = 0, deg;
double dgy_x;

void setup() {
  pinMode(LED1, OUTPUT);
  pinMode(LED2, OUTPUT);
  pinMode(LED3, OUTPUT);
  pinMode(LED4, OUTPUT);
  pinMode(LED5, OUTPUT);
  pinMode(buzzer, OUTPUT);
  Serial.begin(9600);
  bldc.attach(6);               //  BLDC_ESC
  bldc.write(pos);              //  BLDC_ESC
  BTSerial.begin(9600);         //  HC-06
  Wire.begin();                 //  MPU6050 - Wire 라이브러리 초기화
  Wire.beginTransmission(mpu);  //  MPU6050 - mpu로 데이터 전송 시작
  Wire.write(0x6B);             //  MPU6050 - PWR_MGMT_1register
  Wire.write(0);                //  MPU6050 - mpu6050 시작모드로
  Wire.endTransmission(true);   //  MPU6050
}

void loop() {
  Wire.beginTransmission(mpu);  // acc data 데이터 전송시작
  Wire.write(0x3B);             // register 0x3B 데이터 기록
  Wire.endTransmission(false);  // 연결유지
  Wire.requestFrom(mpu,6,true); // mpu에 데이터 요청
  // 데이터 한 바이트 씩 읽어서 반환
  ac_x = Wire.read() << 8|Wire.read();
  ac_y = Wire.read() << 8|Wire.read();
  ac_z = Wire.read() << 8|Wire.read();

  Wire.beginTransmission(mpu);  // gyro data
  Wire.write(0x43);             // register 0x43 데이터 기록
  Wire.endTransmission(false);  // 연결유지
  Wire.requestFrom(mpu,6,true); // mpu에 데이터 요청
  // 데이터 한 바이트 씩 읽어서 반환
  gy_x = Wire.read() << 8|Wire.read();
  gy_y = Wire.read() << 8|Wire.read();
  gy_z = Wire.read() << 8|Wire.read();

  deg = atan2(ac_x, ac_z) * 180/PI;  // radian to degree

  dgy_x = gy_y / 131.;  // 16bit data to 250 deg/sdc
  angle = (0.95 *(angle + (dgy_x * 0.001))) + (0.05 * deg);  // complem entary filter
  
  if(BTSerial.available()) {     // 값이 들어오면
    button = BTSerial.read();    // button에 값 저장
    count = 1;
  }
  
  switch(button)
  {
  case 'a' :
    {
      if(cnt < 50) {                    // LED 깜빡이기 시작
        if(LED1s==LOW && LED2s==LOW && LED3s==LOW && LED4s==LOW && LED5s==LOW) { LED1s = HIGH; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = HIGH; }
        else                                                                   { LED1s = LOW; LED2s = LOW; LED3s = LOW; LED4s = LOW; LED5s = LOW; }
      }
      else if(cnt < 100) {
        digitalWrite(LED1,LED1s); digitalWrite(LED2,LED2s); digitalWrite(LED3,LED3s); digitalWrite(LED4,LED4s); digitalWrite(LED5,LED5s);
        digitalWrite(buzzer, LOW);
      }
      else {
        cnt = 0;
      }                                 // LED 깜빡이기 끝
      pos = 99; bldc.write(pos);        // 1단
      if(angle < -7 || angle > 9) {     // 자이로 센서 like interrupt
        pos = 98; bldc.write(pos);
        digitalWrite(buzzer, HIGH);
      }
      cnt += 1;
      break;
    }
  case 'b' :
    {
      if(cnt < 20) {                    // LED 깜빡이기 시작
        if(LED1s==LOW && LED2s==LOW && LED3s==LOW && LED4s==LOW && LED5s==LOW) { LED1s = HIGH; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = HIGH; }
        else                                                                   { LED1s = LOW; LED2s = LOW; LED3s = LOW; LED4s = LOW; LED5s = LOW; }
      }
      else if(cnt < 40) {
        digitalWrite(LED1,LED1s); digitalWrite(LED2,LED2s); digitalWrite(LED3,LED3s); digitalWrite(LED4,LED4s); digitalWrite(LED5,LED5s);
        digitalWrite(buzzer, LOW);
      }
      else {
        cnt = 0;
      }                                 // LED 깜빡이기 끝
      pos = 101; bldc.write(pos);       // 2단
      if(angle < -7 || angle > 9) {     // 자이로 센서 like interrupt
        pos = 98; bldc.write(pos);
        digitalWrite(buzzer, HIGH);
      }
      cnt += 1;
      break;
    }
  case 'c' :
    {
      digitalWrite(LED1, HIGH); digitalWrite(LED2, HIGH); digitalWrite(LED3, HIGH); digitalWrite(LED4, HIGH); digitalWrite(LED5, HIGH);
      digitalWrite(buzzer, LOW);
      if(count == 1) {
        pos = 98; bldc.write(pos); delay(500);
        pos = 0; bldc.write(pos);
        count = 0;
      }
      cnt = 0;
      break;
    }
  case 'd' :
    {
      if(cnt < 35) {                    // LED 깜빡이기 시작
        LED1s = LOW; LED2s = LOW; LED3s = HIGH; LED4s = LOW; LED5s = LOW;
      }
      else if(cnt>=35 && cnt<70) {
        LED1s = LOW; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = LOW;
      }
      else if(cnt>=70 && cnt<105) {
        LED1s = HIGH; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = HIGH;
      }
      else if(cnt>=105 && cnt<140) {
        LED1s = LOW; LED2s = LOW; LED3s = LOW; LED4s = LOW; LED5s = LOW;
      }
      if(cnt<160) {
        digitalWrite(LED1,LED1s); digitalWrite(LED2,LED2s); digitalWrite(LED3,LED3s); digitalWrite(LED4,LED4s); digitalWrite(LED5,LED5s);
        digitalWrite(buzzer, LOW);
      }
      else {
        cnt = 0;
      }                                 // LED 깜빡이기 끝
      if(count == 1) {
        pos += 1; bldc.write(pos);      // 증가
        count = 0;
      }
      if(angle < -7 || angle > 9) {     // 자이로 센서 like interrupt
        pos = 98; bldc.write(pos);
        digitalWrite(buzzer, HIGH);
      }
      cnt += 1;
      break;
    }
  case 'e' :
    {
      if(cnt < 35) {                    // LED 깜빡이기 시작
        LED1s = HIGH; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = HIGH;
      }
      else if(cnt>=35 && cnt<70) {
        LED1s = LOW; LED2s = HIGH; LED3s = HIGH; LED4s = HIGH; LED5s = LOW;
      }
      else if(cnt>=70 && cnt<105) {
        LED1s = LOW; LED2s = LOW; LED3s = HIGH; LED4s = LOW; LED5s = LOW;
      }
      else if(cnt>=105 && cnt<140) {
        LED1s = LOW; LED2s = LOW; LED3s = LOW; LED4s = LOW; LED5s = LOW;
      }
      if(cnt<160) {
        digitalWrite(LED1,LED1s); digitalWrite(LED2,LED2s); digitalWrite(LED3,LED3s); digitalWrite(LED4,LED4s); digitalWrite(LED5,LED5s);
        digitalWrite(buzzer, LOW);
      }
      else {
        cnt = 0;
      }                                 // LED 깜빡이기 끝
      if(count == 1) {
        pos -= 1; bldc.write(pos);      // 감소        
        count = 0;
      }
      if(angle < -7 || angle > 9) {     // 자이로 센서 like interrupt
        pos = 98; bldc.write(pos);
        digitalWrite(buzzer, HIGH);
      }     
      cnt += 1;
      break;
    }
  }
  delay(5);
}
