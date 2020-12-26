// PuRple's Second Project : Smart_Flower_Garden.Servo.ver
// WeMos D1 R1 Board

#include <Arduino.h>
#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <SocketIoClient.h>
#include <Servo.h>

// ESP8266 && Socket.Io init
ESP8266WiFiMulti WiFiMulti;
SocketIoClient webSocket;

// Servo init
Servo servo;

// Socket.on 응답시 동작 함수
void clearUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(180);
}

void cloudsUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(90);
}

void rainUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(0);
}

void snowUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(0);
}

void openUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(180);
}

void midUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(90);
}

void closeUpdate(const char * payload, size_t length) {
  Serial.printf("got message: %s\n", payload);
  servo.write(0);
}

void setup() {
  servo.attach(14);
  servo.write(0);
  
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

  WiFiMulti.addAP("SSID", "Password");
  
  while(WiFiMulti.run() != WL_CONNECTED) {
    delay(100);
  }

  webSocket.on("clearUpdate", clearUpdate);
  webSocket.on("cloudsUpdate", cloudsUpdate);
  webSocket.on("rainUpdate", rainUpdate);
  webSocket.on("snowUpdate", snowUpdate);
  webSocket.on("openUpdate", openUpdate);
  webSocket.on("midUpdate", midUpdate);
  webSocket.on("closeUpdate", closeUpdate);
  webSocket.begin("IP_Address", "port");
}

void loop() {
  webSocket.loop();
}
