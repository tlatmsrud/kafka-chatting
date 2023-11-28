# Kafka를 활용한 채팅 어플리케이션

# 1. 개발환경 🏃

- IDE : intelliJ
- Framework : Spring Boot 2.7.14
- JAVA : OPEN JDK 11
- Kafka : 2.13
- Zookeeper : Kafka 내장
- SCM : Git
- DB : H2 1.4.200
- Build Tool : Gradle 7.5.1
- SSR : Thymeleaf

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
<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/9d987164-3702-486f-9436-420dc263f289" width="80%" height="80%"/>

> 사용자가 생성할 채팅방 이름을 입력하면 채팅방이 생성됩니다.

> 비동기 방식으로 진행되며 생성된 채팅방에 대한 정보는 동적으로 생성되어 리스트업 됩니다.

## 3.3. 카프카를 활용하라

> 로컬에서 Apache Zookeeper과 함께 Kafka 사용하였습니다. 카프카 브로커는 테스트 목적에 맞게 하나만 구성하였습니다.

## 3.4. Polling 방식으로 실시간 채팅 기능을 구현하라
<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/b1ae55b5-e9a2-469b-a7ee-e7f65f804acf" width="80%" height="80%"/>

> 채팅방에 입장하면 클라이언트가 1초마다 DB에 적재된 채팅내역을 불러오고 화면을 갱신합니다.

> 동일 채팅방에 있는 다른 클라이언트가 채팅을 입력하면 서버의 API를 호출하고, 내부 채팅 DB에 데이터를 Insert 합니다.

> Web Socket 방식과의 호환성을 위해 내부적으로 Kafka Producer를 생성하여 Broker에 레코드를 보냅니다.

![Animation](https://github.com/tlatmsrud/kafka-chatting/assets/9374562/9fec0c69-c063-4da2-ba3e-801f78598f47)

> 1초마다 채팅 내역 조회 API를 호출하기 때문에 채팅을 입력한 후 상대방이 받기까지 최대 1초가 지체될 수 있습니다.

## 3.5. WebSocket 방식으로 실시간 채팅 기능을 구현하라

<img src="https://github.com/tlatmsrud/kafka-chatting/assets/9374562/37a0eb14-0ec3-4afe-8dc9-931274b6f05c" width="80%" height="80%"/>

> 어플리케이션 서버는 Kafka의 'Chatting' Topic을 구독합니다.

> 채팅방에 입장하면 어플리케이션 서버에서 구현한 메시지 브로커/STOMP 기반의 웹 소켓과 Connection을 맺습니다.

> Connection을 맺은 후 위 메시지 브로커를 Subscribe 합니다.

> 채팅 입력 시 STOMP 프로토콜을 통해 메시지 브로커측으로 데이터를 전달합니다.

> 메시지 브로커는 채팅 정보를 받고 어플리케이션 서버가 구독한 Chatting 토픽으로 채팅 레코드를 전달합니다.

> 채팅 레코드를 전달받은 카프카가 Chatting Topic을 구독한 컨슈머로 채팅 데이터를 전달합니다.

> 어플리케이션 서버에서 Chatting Topic을 구독하고 있으므로 해당 레코드를 받으며 메시지 브로커를 구독한 클라이언트들에게 STOMP 프로토콜로 채팅 데이터를 전달합니다.

![Animation](https://github.com/tlatmsrud/kafka-chatting/assets/9374562/ab6d15d2-84dd-4f8e-bcc1-2c94cfa7e464)

> Message 브로커 기반으로 데이터를 바로바로 받아오므로 지체 현상이 일어나지 않지만 DB 트랜잭션이 많아질 경우 느려질 가능성이 농후해 보입니다.

> 카프카를 억지로 끼워맞춘 느낌이 드는 상황입니다. 카프카 컨슈머 서버를 따로 만들고 DB 트랜잭션만을 처리하도록 구현해야겠습니다.

## 4. 회고
> 채팅은 실시간 처리가 매우 중요하고, 다수의 클라이언트가 존재하는 성질을 띕니다. 클라이언트가 많아질수록 서버에 무리가 가고, 많은 HTTP 오버헤드가 발생하므로 Polling 방식은 실시간 채팅에 적합하지 않았습니다.


