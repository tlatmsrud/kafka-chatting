package org.ssk.domain.chatting.dto;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Getter
public class SendDto {

    private String sessionId;
    private String roomId;
    private String message;
}
