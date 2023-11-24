package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    private final KafkaProducerService kafkaProducerService;

    public void send(SendDto sendDto){

        String roomId = sendDto.getRoomId();
        kafkaProducerService.sendChattingRecord(roomId
                , ChattingRecord.of(sendDto.getSessionId(), sendDto.getMessage()));
    }
}
