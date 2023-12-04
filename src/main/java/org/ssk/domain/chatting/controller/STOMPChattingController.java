package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;

import java.util.List;


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
public class STOMPChattingController {

    private final ChattingService chattingService;

    /**
     * STOMP 기반의 채팅방 입장
     * View로 채팅방 ID와 채팅방 이름 전달
     * @param model - 모델
     * @param roomId - 채팅방 ID
     * @return model
     */
    @GetMapping("/enterChattingRoom/{roomId}")
    public String enterChattingRoom(Model model, @PathVariable(value = "roomId") Long roomId){
        List<ChattingDto> list = chattingService.getChattingListByRoomId(roomId);
        String roomName = chattingService.getRoomName(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", roomName);
        model.addAttribute("list", list);
        return "chatting_stomp";
    }

    /**
     * 메시지 브로커에 대한 어플리케이션 API 메서드
     * 채팅방에 입력한 정보를 Kafka Broker 로 전달한다.
     * @param sendDto - 전달할 채팅 Dto
     */
    @MessageMapping("/sendMessage")
    public void sendMessage(SendDto sendDto){
        log.info("chattingDto : "+ sendDto.toString());
        chattingService.send(sendDto);
    }
}
