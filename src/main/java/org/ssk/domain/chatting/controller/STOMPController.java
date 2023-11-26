package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;


/**
 * title        : STOMP Controller
 * author       : sim
 * date         : 2023-11-27
 * description  : WebSocket을 통해 Message를 받는 Controller 클래스
 */

@Slf4j
@Controller
@RequestMapping("/stomp")
@RequiredArgsConstructor
public class STOMPController {

    private final ChattingService chattingService;

    @GetMapping("/enterChattingRoom/{roomId}")
    public String enterChattingRoom(Model model, @PathVariable(value = "roomId") Long roomId){
        String roomName = chattingService.getRoomName(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", roomName);
        return "chatting_stomp";
    }

    @MessageMapping("/sendMessage")
    public void sendMessage(SendDto sendDto){
        log.info("chattingDto : "+ sendDto.toString());
        chattingService.send(sendDto, "test");
    }
}
