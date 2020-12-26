// PuRple's Second Project : Smart_Flower_Garden.DC_motor.ver
// nodeMCU V2 ESP-12N Board

#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <SocketIoClient.h>

// ESP8266 && Socket.Io init
ESP8266WiFiMulti WiFiMulti;
SocketIoClient webSocket;

// UltraSonic init
#define trig 14
#define echo 12
unsigned long duration;
int distance;

// DC motor init
#define M1 4
#define M2 2
#define E1 5
#define E2 0

// Socket.on 응답시 동작 함수
void clearUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  Serial.println(distance);
  if(distance < 20) {
    digitalWrite(M1, LOW); digitalWrite(M2, HIGH); analogWrite(E1, 350); analogWrite(E2, 350);
    delay(2100);
    digitalWrite(M1, LOW); digitalWrite(M2, HIGH); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
  else {
    digitalWrite(M1, LOW); digitalWrite(M2, HIGH); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
}

void cloudsUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  digitalWrite(M1, HIGH); digitalWrite(M2, HIGH); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
}

void rainUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  Serial.println(distance);
  if(distance > 20) {
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); analogWrite(E1, 350); analogWrite(E2, 350);
    delay(2250);
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
  else {
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
}

void snowUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  Serial.println(distance);
  if(distance > 20) {
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); analogWrite(E1, 350); analogWrite(E2, 350);
    delay(2250);
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
  else {
    digitalWrite(M1, HIGH); digitalWrite(M2, LOW); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
  }
}

void goUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  digitalWrite(M1, HIGH); digitalWrite(M2, LOW); analogWrite(E1, 300); analogWrite(E2, 300);
  delay(2000);
  digitalWrite(M1, HIGH); digitalWrite(M2, LOW); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
}

void backUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  digitalWrite(M1, LOW); digitalWrite(M2, HIGH); analogWrite(E1, 300); analogWrite(E2, 300);
  delay(2000);
  digitalWrite(M1, LOW); digitalWrite(M2, HIGH); digitalWrite(E1, LOW); digitalWrite(E2, LOW);
}

void setup() {
  pinMode(trig, OUTPUT);
  pinMode(echo, INPUT);
  pinMode(M1, OUTPUT);
  pinMode(E1, OUTPUT);
  pinMode(M2, OUTPUT);
  pinMode(E2, OUTPUT);
  
  Serial.begin(115200);

  Serial.setDebugOutput(true);

  Serial.println();
  Serial.println();
  Serial.println();

  for(uint8_t t = 4; t > 0; t--) {
    Serial.printf("[SETUP] BOOT WAIT %d...\n", t);
    Serial.flush();
    delay(1000);
  }

  // 네트워크 설정
  WiFiMulti.addAP("SSID", "Password");

  while(WiFiMulti.run() != WL_CONNECTED) {
    delay(100);
  }

  webSocket.on("clearUpdate", clearUpdate);
  webSocket.on("cloudsUpdate", cloudsUpdate);
  webSocket.on("rainUpdate", rainUpdate);
  webSocket.on("snowUpdate", snowUpdate);
  webSocket.on("goUpdate", goUpdate);
  webSocket.on("backUpdate", backUpdate);
  // 소켓통신 주소 설정
  webSocket.begin("IP_Address", "port");
}

void loop() {
  digitalWrite(trig, LOW); digitalWrite(echo, LOW); delayMicroseconds(2);
  digitalWrite(trig, HIGH); delayMicroseconds(10); digitalWrite(trig, LOW);
  duration = pulseIn(echo, HIGH);
  distance = ((float)(340*duration)/10000)/2;
  webSocket.loop();
}
