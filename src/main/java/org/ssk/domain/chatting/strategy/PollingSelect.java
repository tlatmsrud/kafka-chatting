package org.ssk.domain.chatting.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.ssk.domain.chatting.domain.ChattingRoom;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.repository.ChattingRepository;
import org.ssk.domain.chatting.repository.ChattingRoomRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * title        : Polling 조회 전략 클래스
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Component
@RequiredArgsConstructor
public class PollingSelect implements ChattingSelectStrategy{

    private final ChattingRepository chattingRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    @Override
    public List<ChattingDto> selectChattingByRoomId(Long roomId) {

        ChattingRoom findChattingRoom = chattingRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("room is not exists"));

        return chattingRepository.findByChattingRoom(findChattingRoom)
                .stream().map(entity -> ChattingDto.of(entity.getNickname(), entity.getMessage(), entity.getTime(), entity.getChattingRoom().getRoomId()))
                .collect(Collectors.toList());
    }
}
