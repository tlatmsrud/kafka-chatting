package org.ssk.domain.chatting.record;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChattingRecord {

    private String sessionId;
    private String message;
    private String time = LocalDateTime.now().toString();
    private Long roomId;

    private ChattingRecord(String sessionId, String message, Long roomId){
        this.sessionId = sessionId;
        this.message = message;
        this.roomId = roomId;
    }

    public static ChattingRecord of(String sessionId, String message, Long roomId){
        return new ChattingRecord(sessionId, message, roomId);
    }
}
