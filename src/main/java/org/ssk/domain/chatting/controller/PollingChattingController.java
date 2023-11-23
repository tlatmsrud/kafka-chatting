package org.ssk.domain.chatting.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssk.domain.chatting.dto.ChattingDto;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@RestController
@RequestMapping("/polling")
public class PollingChattingController {

    @GetMapping("/chatting/{roomId}")
    public ChattingDto getChatting(@PathVariable String roomId){
        return null;
    }
}

