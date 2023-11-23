package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping("/send")
    public void send(@RequestBody SendDto sendDto){
        chattingService.send(sendDto);
    }
}
