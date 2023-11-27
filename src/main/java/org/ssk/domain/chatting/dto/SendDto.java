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

    private String nickname;
    private Long roomId;
    private String message;
}
