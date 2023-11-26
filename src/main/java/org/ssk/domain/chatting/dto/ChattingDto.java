package org.ssk.domain.chatting.dto;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Getter
public class ChattingDto {
    private final String sessionId;
    private final String message;
    private final String time;
    private final Long roomId;

    private ChattingDto(String sessionId, String message, String time, Long roomId){
        this.sessionId = sessionId;
        this.message = message;
        this.time = time;
        this.roomId = roomId;
    }

    public static ChattingDto of(String sessionId, String message, String time, Long roomId){
        return new ChattingDto(sessionId, message, time, roomId);
    }

    @Override
    public String toString() {
        return "ChattingDto{" +
                "sessionId='" + sessionId + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
