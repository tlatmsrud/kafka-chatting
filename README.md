Kafka를 활용한 채팅 어플리케이션

# 1. 개발환경

- IDE : intelliJ
- Framework : Spring Boot 2.7.14
- JAVA : OPEN JDK 11
- SCM : Git
- DB : H2 1.4.200
- Build Tool : Gradle 7.5.1

# 2. 구현 기능
- [x] 사용자마다 닉네임을 입력받아 관리하라
- [x] 채팅방을 생성할 수 있도록 하라
- [x] 카프카를 활용하라
- [x] Polling 방식으로 실시간 채팅 기능을 구현하라
- [x] WebSocket 방식으로 실시간 채팅 기능을 구현하라
- [ ] 테스트 커버리지를 80% 이상으로 유지하라

# 3. 구현 상세

## 3.1. 사용자마다 닉네임을 입력받아 관리하라
<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/32083f68-d632-4320-8cc5-75972c4c27a1" width="80%" height="80%"/>

> 메인화면에서 사용자가 닉네임을 입력받도록 합니다.
> 닉네임이 중복될 경우 에러 메시지가 출력됩니다.
> Polling 방식화 WebSocket 방식을 선택할 수 있도록 합니다. 선택에 따라 View가 달라집니다.

## 3.2. 채팅방을 생성할 수 있도록 하라
<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/32083f68-d632-4320-8cc5-75972c4c27a1](https://github.com/tlatmsrud/kafka-chatting/assets/9374562/9d987164-3702-486f-9436-420dc263f289)" width="80%" height="80%"/>

> 사용자가 생성할 채팅방 이름을 입력하면 채팅방이 생성됩니다.
> 비동기 방식으로 진행되며 생성된 채팅방에 대한 정보는 동적으로 생성되어 리스트업 됩니다.

## 3.3. 카프카를 활용하라



# 4. 어플리케이션 테스트 방법

4.1. 어플리케이션 실행 전 환경변수에 Jasypt 키 값을 추가합니다.
``` java
    jasypt.encryptor.password=samosam_backend
``` 

4.2. 어플리케이션 실행 후 http://localhost:8080/swagger-ui/index.html#/ 로 접속하여 Swagger-UI 페이지를 호출합니다.

4.3. JWT 토큰은 로그인 성공 시 Http 메시지의 body로 응답받을 수 있습니다.

4.4. JWT 토큰을 발급받으면 Authorize 버튼을 클릭하여 Authorization 헤더를 추가할 수 있습니다.

![image](https://github.com/tlatmsrud/samosam/assets/9374562/7a9f60c3-270d-4e71-bb16-b86e84d04a3d)


4.5. Authorization 형식에 맞게 발급받은 JWT 토큰을 추가합니다. (ex - Bearer JWT_TOKEN)


![image](https://github.com/tlatmsrud/samosam/assets/9374562/250ef5a7-1b53-4076-b68e-f32fa178f9b1)

4.6. 그 후 유저정보 조회, 스크랩, 세액 계산 API를 테스트 해주시면 됩니다. 만약 JWT 토큰을 넣지 않으시면 JWT 관련 에러 및 안내 메시지가 출력됩니다.

4.7. H2 로 접속하실 경우 ID : sa, PASSWORD : 2023, URI : jdbc:h2:mem:samosam 입니다.

4.8. 특이사항이나 문의사항이 있으시다면 010-7936-9566(심승경) 으로 언제든 연락 부탁드립니다! 감사합니다!
