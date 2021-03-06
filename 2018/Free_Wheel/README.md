# 전동 스케이트보드 - Free Wheel
## 프로젝트 개요
평소 스케이트보드를 타는 취미를 가지고 있고, 만화에 나오는 전동 스케이트보드를   
만들어서 타고자 보유중인 스케이트보드를 가지고 프로젝트를 진행함.

## 이미지
| <img src="https://user-images.githubusercontent.com/44526808/103165263-e585b880-4858-11eb-847f-20c621ba6cd6.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103165256-c5ee9000-4858-11eb-8fbc-8a0c45dfa1ab.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103165272-f9311f00-4858-11eb-8c07-2e13904503c7.png"></img> |
|:---:|:---:|:---:|
| 전동 스케이트보드 외형 | 스케이트보드 제어부 | 애플리케이션 모습 |

## 프로젝트 설명
+ 아두이노와 BLDC 모터를 사용하여 기존 스케이트보드를 전동 스케이트보드로 제작.
+ 서보모터를 제어하는 라이브러리 `<Servo.h>`를 사용하여 BLDC 모터 제어.
+ 애플리케이션을 제작하여 블루투스 통신으로 스케이트보드 조작.
+ 가속도 센서를 사용하여 좌우로 기울어지는 각도를 측정하여 탑승자가 넘어질 것 같으면 속도를 줄이고 Buzzer로 경고음 알림.
+ 스케이트보드 후면에 LED를 부착하여 애플리케이션 버튼에 따라 작동 및 자동차 테일램프(후미등) 역할을 함.

## 개발 환경
+ 언어 : C
+ OS : Windows 10
+ 개발 보드 : Arduino Uno
+ 개발 툴 : Arduino IDE, App Inventor, Autodesk Inventor(3D Printing)
+ 사용 부품 : LED 적색, Buzzer, 가속도 센서(MPU-6050), 블루투스 모듈(HC-06), BLDC 모터, ESC 변속기, 22.2V LiPo 배터리
