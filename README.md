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

> 로컬에서 Apache Zookeeper과 함께 Kafka 사용하였습니다. 카프카 브로커는 테스트 목적에 맞게 하나만 구성하였습니다.

## 3.4. Polling 방식으로 실시간 채팅 기능을 구현하라
<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/b1ae55b5-e9a2-469b-a7ee-e7f65f804acf" width="80%" height="80%"/>

> 채팅방에 입장하면 클라이언트가 1초마다 DB에 적재된 채팅내역을 불러오고 화면을 갱신합니다.
> 동일 채팅방에 있는 다른 클라이언트가 채팅을 입력하면 서버의 API를 호출하고, 내부 채팅 DB에 데이터를 Insert 합니다.
> Web Socket 방식과의 호환성을 위해 내부적으로 Kafka Producer를 생성하여 Broker에 레코드를 보냅니다.

## 3.5. WebSocket 방식으로 실시간 채팅 기능을 구현하라



## 5. 회고
> 채팅은 실시간 처리가 매우 중요하고, 다수의 클라이언트가 존재하는 성질을 띕니다. 클라이언트가 많아질수록 서버에 무리가 가고, 많은 HTTP 오버헤드가 발생하므로 Polling 방식은 실시간 채팅에 적합하지 않습니다.


