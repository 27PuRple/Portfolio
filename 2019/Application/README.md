# 배전반 감시 제어용 애플리케이션
## 프로젝트 개요
(주)벽진테크와 같이 산학과제 프로젝트 진행.   
자사의 배전반을 스마트폰으로 모니터링하고 제어신호를 보낼 수 있는 데모 애플리케이션 개발.   
외부에서 데이터를 받아들이면 서버를 통해 애플리케이션과 데이터 통신 진행.   
애플리케이션 실행 시 이중보안(PIN + 지문인식)을 진행.

## 이미지
| <img src="https://user-images.githubusercontent.com/44526808/103400629-20edf300-4b89-11eb-9c46-8e4c67505451.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103400641-2ea37880-4b89-11eb-8766-4ceff1ddf221.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103400650-3a8f3a80-4b89-11eb-922b-faed2b0ed605.png"></img> | <img src="https://user-images.githubusercontent.com/44526808/103400666-467afc80-4b89-11eb-9fc0-2f416dbac582.png"></img> |
|:---:|:---:|:---:|:---:|
| PIN 비밀번호 | 지문인식 | 메인 화면 | 데이터 통신 화면 | 

## 프로젝트 설명
+ Java 언어를 사용하여 Eclipse에서 서버 개발, 안드로이드 스튜디오에서 애플리케이션을 개발 진행.   
+ 서버
  + Eclipse IDE를 사용하여 서버 개발.
  + 서버 개발 컴퓨터의 IP주소 확인 후 소켓통신 초기설정 진행.
  + 애플리케이션에서 신호를 보내주면 해당 신호에 맞추어 컴퓨터에서 파일 전송.
+ 이중보안 (PIN + 지문인식)
  + 지원받은 스마트폰(Samsung Galaxy S7)의 OS버전에 알맞는 방법으로 지문인식 기능 개발.
  + 애플리케이션 최초실행 시 PIN 비밀번호 생성 기능, 이후 접속 시 'PIN + 지문인식'으로 이중보안 기능 진행.
+ 데이터 통신
  + 서버 컴퓨터의 IP주소를 확인 후, 서버와 애플리케이션간의 소켓통신을 준비.
  + 해당 메뉴 화면으로 이동하면 서버와 신호를 보내고 데이터를 수신하여 나타냄.
  + 응급 상황일 때 버튼을 터치하여 `Emergency` 메시지를 서버로 전송.
+ TTA 인증 시험
  + 평가 항목에 대하여 사전 테스트 진행.
  + 시험을 받기위한 해당 서류 작성.

## 개발 환경
+ 언어 : Java
+ OS : Windows 10, Android 8.0 (Oreo)
+ 개발 도구 : 스마트폰 (Samsung Galaxy S7)
+ 개발 툴 : Eclipse IDE, Android Studio
