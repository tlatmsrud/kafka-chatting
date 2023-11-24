package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(
            topics = TOPIC, groupId = "test-01", containerFactory = "customContainerFactory")
    public void customListener(ChattingRecord chattingRecord){
        System.out.println("recordListener");
        System.out.println(chattingRecord.getSessionId());
    }


}
