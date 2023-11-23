package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
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
public class KafkaService {

    private final String TOPIC = "chatting";
    private final KafkaTemplate<String, ChattingRecord> customKafkaTemplate;

    public void sendChattingRecord(String topic, ChattingRecord chattingRecord){
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

    @KafkaListener(topics = TOPIC, groupId = "test-01")
    public void recordListener(ConsumerRecord<String, ChattingRecord> record){
        System.out.println("recordListener");
        System.out.println(record);
    }

    @KafkaListener(topics = TOPIC, groupId = "test-02")
    public void singleTopicListener(String messageValue){
        System.out.println("singleTopicListener");
        System.out.println(messageValue);
    }

    @KafkaListener(topics = TOPIC, groupId = "test-03"
    ,properties = {
            "max.poll.interval.ms:60000",
            "auto.offset.reset:earliest"
    })
    public void singleTopicWithPropertiesListener(String messageValue){
        System.out.println("singleTopicWithPropertiesListener");
        System.out.println(messageValue);
    }

    @KafkaListener(topics = TOPIC, groupId = "test-04"
    ,concurrency = "2")
    public void concurrentTopicListener(String messageValue){
        System.out.println("concurrentTopicListener");
        System.out.println(messageValue);
    }

    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = TOPIC, partitions = {"0", "1"})}, groupId = "test-05")
    public void listenSpecificPartition(ConsumerRecord<String, ChattingRecord> record){
        System.out.println("listenSpecificPartition");
        System.out.println(record);
    }
}
