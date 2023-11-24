package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.ssk.domain.chatting.domain.Chatting;
import org.ssk.domain.chatting.record.ChattingRecord;
import org.ssk.domain.chatting.repository.ChattingRepository;

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
    private final ChattingRepository chattingRepository;

    @KafkaListener(
            topics = TOPIC, groupId = "test-01", containerFactory = "customContainerFactory")
    public void customListener(ChattingRecord chattingRecord){
        System.out.println("recordListener");
        System.out.println(chattingRecord.getSessionId());

        Chatting chatting = Chatting.builder()
                .sessionId(chattingRecord.getSessionId())
                .message(chattingRecord.getMessage())
                .time(chattingRecord.getTime())
                .build();

        chattingRepository.save(chatting);
    }


}
