package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.record.ChattingRecord;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Service
@RequiredArgsConstructor
public class ChattingService {

    private final KafkaService kafkaService;

    public void send(SendDto sendDto){

        String roomId = sendDto.getRoomId();
        kafkaService.sendChattingRecord(roomId
                , ChattingRecord.of(sendDto.getSessionId(), sendDto.getMessage()));
    }
}
