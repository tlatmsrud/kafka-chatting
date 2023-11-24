package org.ssk.domain.chatting.dto;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Getter
public class ChattingRoomDto {

    private Long roomId;
    private String roomName;

    private ChattingRoomDto(Long roomId, String roomName){
        this.roomId = roomId;
        this.roomName = roomName;
    }

    public static ChattingRoomDto of(Long roomId, String roomName){
        return new ChattingRoomDto(roomId, roomName);
    }
}
