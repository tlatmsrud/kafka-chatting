package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.dto.ChattingRoomDto;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-23
 * description  :
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChattingController {

    private final ChattingService chattingService;

    @GetMapping
    public String getMainPage(){
        return "main";
    }

    @ResponseBody
    @PostMapping("/room")
    public void createChattingRoom(@RequestParam(name = "roomName") String roomName){
        chattingService.createChattingRoom(roomName);
    }

    @GetMapping("/room")
    public String getChattingRoomList(Model model){

        List<ChattingRoomDto> list = chattingService.getChattingRoomList();
        model.addAttribute("list", list);
        return "chattingRoomList";
    }

    @ResponseBody
    @PostMapping("/send")
    public void send(HttpServletRequest request, @RequestBody SendDto sendDto){
        chattingService.send(sendDto, request.getSession().getId());
    }

    @GetMapping("/room/enter/{roomId}")
    public String enterRoom(Model model, @PathVariable("roomId") Long roomId){
        String roomName = chattingService.getRoomName(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", roomName);
        return "chatting";
    }
}
