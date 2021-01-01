# 스마트 원예 시스템
## 프로젝트 개요
사물인터넷(IoT : Internet of Things)이 발전하면서 스마트 팩토리, 스마트 팜 등 다양하게 적용됨.   
그 중 일반 가정에서 사용하여 관리할 수 있는 가정용 스마트 원예 시스템 구축을 계획하고 프로젝트를 진행함.   
Open API를 사용하여 날씨 데이터를 받아 자동으로 화단과 화분을 관리하며, 필요에 따라 애플리케이션으로 직접 제어를 함.

## 이미지
| <img src="https://user-images.githubusercontent.com/44526808/103440504-95a85680-4c89-11eb-968f-9af8e9667669.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103400087-319d6980-4b87-11eb-89a4-d1a6936acc2a.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103440559-10717180-4c8a-11eb-80e9-a013f703dea6.png"></img><img src="https://user-images.githubusercontent.com/44526808/103440569-1b2c0680-4c8a-11eb-9c92-4374f7bd153f.png"></img> |
|:---:|:---:|:---:|
| Open API 사용화면 | 시스템 외형 시뮬레이션 | 화단 및 화분을 제어하는 애플리케이션 화면 |

## 프로젝트 설명
+ OpenWeatherMap의 API를 사용하여 현재 위치의 날씨 데이터를 수집.
+ Wi-Fi 환경에서 소켓통신으로 데이터 통신, "Socket.io"를 사용.
+ "Socket.io" 라이브러리로 서버와 클라이언트(H/W, 애플리케이션)간의 양방향 통신.
+ Open API를 통해 일정시간마다 데이터를 수집하고 해당 데이터에 따라 자동으로 화단과 화분 관리.
+ 애플리케이션으로 사용자가 원하는대로 화단과 화분을 직접 관리할 수 있도록 제어.

## 개발 환경
+ 언어 : C, Java, JavaScript
+ OS : Windows 10, Raspbian(Linux)
+ 개발 보드 : nodeMCU, WeMos D1 R1
+ 개발 툴 : Arduino IDE, VS Code, Android Studio
+ 플랫폼 : Node.js
