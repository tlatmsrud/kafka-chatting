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
    private String sessionId;
    private String message;
    private String time;

    private ChattingDto(String sessionId, String message, String time){
        this.sessionId = sessionId;
        this.message = message;
        this.time = time;
    }

    public static ChattingDto of(String sessionId, String message, String time){
        return new ChattingDto(sessionId, message, time);
    }
}
