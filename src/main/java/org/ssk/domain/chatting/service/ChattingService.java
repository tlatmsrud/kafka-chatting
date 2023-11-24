package org.ssk.domain.chatting.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssk.domain.chatting.domain.Chatting;
import org.ssk.domain.chatting.domain.ChattingRoom;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.dto.ChattingRoomDto;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.record.ChattingRecord;
import org.ssk.domain.chatting.repository.ChattingRepository;
import org.ssk.domain.chatting.repository.ChattingRoomRepository;
import org.ssk.domain.chatting.strategy.ChattingSelectStrategy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ChattingService {

    private final KafkaProducerService kafkaProducerService;
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingSelectStrategy chattingSelectStrategy;
    private final ChattingRepository chattingRepository;

    public List<ChattingRoomDto> getChattingRoomList(){
        return chattingRoomRepository.findAll().stream()
                .map(entity -> ChattingRoomDto.of(entity.getRoomId(), entity.getRoomName()))
                .collect(Collectors.toList());
    }
    public void createChattingRoom(String chattingRoomName){
        ChattingRoom chattingRoom = ChattingRoom.builder().roomName(chattingRoomName).build();
        chattingRoomRepository.save(chattingRoom);
        log.info("created chatting room : "+ chattingRoomName);
    }
    public void send(SendDto sendDto, String sessionId){
        Long roomId = sendDto.getRoomId();

        ChattingRoom findChattingRoom = findChattingRoom(roomId);

        ChattingRecord chattingRecord = ChattingRecord.of(sessionId, sendDto.getMessage());
        kafkaProducerService.sendChattingRecord(roomId, chattingRecord);

        Chatting chatting = Chatting.builder()
                .sessionId(sessionId)
                .message(chattingRecord.getMessage())
                .time(chattingRecord.getTime())
                .roomId(findChattingRoom)
                .build();

        chattingRepository.save(chatting);
    }

    public ChattingRoom findChattingRoom(Long roomId){
        return chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("room is not exists"));
    }

    public List<ChattingDto> getChattingListByRoomId(Long roomId){
        return chattingSelectStrategy.selectChattingByRoomId(roomId);
    }
}
