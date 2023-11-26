package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.ssk.domain.chatting.record.ChattingRecord;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final static String TOPIC = "chatting";
    private final KafkaTemplate<String, ChattingRecord> customKafkaTemplate;

    public void sendChattingRecord(ChattingRecord chattingRecord){
        ListenableFuture<SendResult<String, ChattingRecord>> future = customKafkaTemplate.send(TOPIC, chattingRecord);

        future.addCallback(new KafkaSendCallback<>() {

            @Override
            public void onSuccess(SendResult<String, ChattingRecord> result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(KafkaProducerException ex) {
                ex.printStackTrace();
            }
        });
    }

}
