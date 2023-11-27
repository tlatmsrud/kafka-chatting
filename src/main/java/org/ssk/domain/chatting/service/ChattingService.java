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
import org.ssk.global.util.TimeUtil;

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

    /**
     * 채팅방 리스트 조회
     * @return 채팅방 리스트
     */
    public List<ChattingRoomDto> getChattingRoomList(){
        return chattingRoomRepository.findAll().stream()
                .map(entity -> ChattingRoomDto.of(entity.getRoomId(), entity.getRoomName()))
                .collect(Collectors.toList());
    }

    /**
     * 채팅방 생성
     * @param chattingRoomName - 채팅방 이름
     * @return 생성된 채팅방 ID
     */
    public Long createChattingRoom(String chattingRoomName){
        ChattingRoom chattingRoom = ChattingRoom.builder().roomName(chattingRoomName).build();
        return chattingRoomRepository.save(chattingRoom).getRoomId();
    }

    /**
     * 채팅 발송
     * @param sendDto - 발송할 채팅 Dto
     */
    public void send(SendDto sendDto){
        Long roomId = sendDto.getRoomId();
        String message = sendDto.getMessage();
        String nickname = sendDto.getNickname();
        String time = TimeUtil.getCurrentTime();

        ChattingRoom findChattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("room is not exists"));

        ChattingRecord chattingRecord = ChattingRecord.of(nickname, message, roomId, time);

        kafkaProducerService.sendChattingRecord(chattingRecord);

        Chatting chatting = Chatting.builder()
                .nickname(nickname)
                .message(message)
                .time(time)
                .chattingRoom(findChattingRoom)
                .build();

        chattingRepository.save(chatting);
    }


    public List<ChattingDto> getChattingListByRoomId(Long roomId){
        return chattingSelectStrategy.selectChattingByRoomId(roomId);
    }

    public String getRoomName(Long roomId) {
        return chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("room is not exists"))
                .getRoomName();
    }
}
