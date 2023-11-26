package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.ssk.domain.chatting.record.ChattingRecord;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final String TOPIC = "chatting";

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(
            topics = TOPIC, groupId = "chatting", containerFactory = "customContainerFactory")
    public void customListener(ChattingRecord chattingRecord){
        System.out.println("recordListener");
        System.out.println(chattingRecord.getSessionId());
        System.out.println(chattingRecord.getRoomId());

        // 카프카가 TOPIC 데이터를 poll 받아온 후 messagingTemplate을 통해 /subscribe/{roomId}를 구독한 클라이언트로 메시지를 발송한다.
        messagingTemplate.convertAndSend("/subscribe/"+chattingRecord.getRoomId(),
                chattingRecord);
    }


}
