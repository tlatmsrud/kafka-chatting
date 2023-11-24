package org.ssk.domain.chatting.strategy;

import org.ssk.domain.chatting.dto.ChattingDto;

import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */
public interface ChattingSelectStrategy {

    List<ChattingDto> selectChattingByRoomId(Long roomId);
}
