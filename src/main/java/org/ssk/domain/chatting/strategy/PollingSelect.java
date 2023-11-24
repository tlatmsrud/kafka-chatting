package org.ssk.domain.chatting.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.repository.ChattingRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Component
@RequiredArgsConstructor
public class PollingSelect implements ChattingSelectStrategy{

    private final ChattingRepository chattingRepository;
    @Override
    public List<ChattingDto> selectChattingByRoomId(Long roomId) {
        return chattingRepository.findByRoomId(roomId)
                .stream().map(entity -> ChattingDto.of(entity.getSessionId(), entity.getMessage(), entity.getTime()))
                .collect(Collectors.toList());
    }
}
