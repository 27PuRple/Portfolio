# QR코드를 통한 주문 시스템 & SLAM을 활용한 서빙로봇
## 프로젝트 개요
푸드테크(Food + Technology) + 언택트(Un + Contact) 문화가 발전하면서 무인 시스템이 꾸준히 증가함.   
   
팀 프로젝트 중 서빙로봇 개발 파트를 담당하여 해당 내용을 기재.

## 이미지
| <img src="https://user-images.githubusercontent.com/44526808/103449922-64b03c00-4cf2-11eb-8b2e-d51cc7a63666.png"></img><img src="https://user-images.githubusercontent.com/44526808/103449929-772a7580-4cf2-11eb-8ac4-bc6cc00a395d.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103449947-aa6d0480-4cf2-11eb-8993-818a9addf431.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103449906-23b82780-4cf2-11eb-9e1a-f85217ff70c1.png"></img> |
|:---:|:---:|:---:|
| LiDAR - SLAM & Depth Camera - Point Cloud | SLAM을 사용하여 생성한 지도 | 서빙로봇 외형 |

## 프로젝트 설명
+ ROS 공식 로봇 플랫폼 "터틀봇3" 사용.

### turtlebot3 오픈소스 - SLAM & Navigation 사용

https://github.com/ROBOTIS-GIT/turtlebot3

위의 오픈소스를 사용하여 launch 파일을 실행하여 SLAM & Navigation 기능 사용.

## 개발 환경
+ 언어 : C++, Python
+ OS : Ubuntu 16.04 LTS, ROS Kinetic, Raspbian (Linux)
+ 개발 보드 : Arduino Uno, Raspberry Pi 3 B+, OpenCR 1.0, NVIDIA Jetson TX2
+ 개발 툴 : Arduino IDE, QT Creator
+ 플랫폼 : Turtlebot3 - burger
