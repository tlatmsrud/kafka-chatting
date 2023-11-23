package org.ssk.domain.chatting.record;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Getter
public class ChattingRecord {

    private String sessionId;
    private String message;
    private String time = LocalDateTime.now().toString();

    private ChattingRecord(String sessionId, String message){
        this.sessionId = sessionId;
        this.message = message;
    }

    public static ChattingRecord of(String sessionId, String message){
        return new ChattingRecord(sessionId, message);
    }
}
