package org.ssk.domain.chatting.record;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChattingRecord {

    private String nickname;
    private String message;
    private String time;
    private Long roomId;

    private ChattingRecord(String nickname, String message, Long roomId, String time){
        this.nickname = nickname;
        this.message = message;
        this.roomId = roomId;
        this.time = time;
    }

    public static ChattingRecord of(String nickname, String message, Long roomId, String time){
        return new ChattingRecord(nickname, message, roomId, time);
    }
}
