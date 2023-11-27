package org.ssk.domain.chatting.dto;

import lombok.Getter;

/**
 * title        : 채팅 DTO
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Getter
public class ChattingDto {
    private final String nickname;
    private final String message;
    private final String time;
    private final Long roomId;

    private ChattingDto(String nickname, String message, String time, Long roomId){
        this.nickname = nickname;
        this.message = message;
        this.time = time;
        this.roomId = roomId;
    }

    public static ChattingDto of(String nickname, String message, String time, Long roomId){
        return new ChattingDto(nickname, message, time, roomId);
    }

    @Override
    public String toString() {
        return "ChattingDto{" +
                "sessionId='" + nickname + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
