package org.ssk.domain.chatting.dto;

import lombok.Getter;

import java.time.LocalDateTime;

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
    private LocalDateTime localDateTime;
}
