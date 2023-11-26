package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.service.ChattingService;

import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@RestController
@RequestMapping("/api/polling")
@RequiredArgsConstructor
public class PollingChattingController {

    private final ChattingService chattingService;

    @GetMapping("/{roomId}")
    public List<ChattingDto> getChatting(@PathVariable Long roomId){
        return chattingService.getChattingListByRoomId(roomId);
    }
}

