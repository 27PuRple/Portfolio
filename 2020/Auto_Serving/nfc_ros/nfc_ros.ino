#include <ros.h>
#include <std_msgs/String.h>
#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9

#define UID0_0 0x47
#define UID0_1 0x57
#define UID0_2 0x85
#define UID0_3 0x7B

#define UID1_0 0xC7
#define UID1_1 0x12
#define UID1_2 0x74
#define UID1_3 0x7A

#define UID2_0 0x48
#define UID2_1 0x7B
#define UID2_2 0x23
#define UID2_3 0x8A

MFRC522 rfid(SS_PIN, RST_PIN);
MFRC522::MIFARE_Key key;

ros::NodeHandle nh;

std_msgs::String str;
ros::Publisher table_num("numtable", &str_msg);

void setup() {
  SPI.begin();
  rfid.PCD_Init();
  nh.initNode();
  nh.advertise(table_num);
}

void loop() {
  if ( ! rfid.PICC_IsNewCardPresent() || ! rfid.PICC_ReadCardSerial())
    return;

  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);

  if (rfid.uid.uidByte[0] == UID0_0 && 
    rfid.uid.uidByte[1] == UID0_1 && 
    rfid.uid.uidByte[2] == UID0_2 && 
    rfid.uid.uidByte[3] == UID0_3 ) {
      str_msg.data = "one";
      pub_test.publish(&str_msg);
  }

  else if (rfid.uid.uidByte[0] == UID1_0 && 
    rfid.uid.uidByte[1] == UID1_1 && 
    rfid.uid.uidByte[2] == UID1_2 && 
    rfid.uid.uidByte[3] == UID1_3 ) {
      str_msg.data = "two";
      pub_test.publish(&str_msg);
  }

  else if (rfid.uid.uidByte[0] == UID2_0 && 
    rfid.uid.uidByte[1] == UID2_1 && 
    rfid.uid.uidByte[2] == UID2_2 && 
    rfid.uid.uidByte[3] == UID2_3 ) {
      str_msg.data = "three";
      pub_test.publish(&str_msg);
  }
  
  nh.spinOnce();
}
