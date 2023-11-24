package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChattingController {

    private final ChattingService chattingService;

    @PostMapping("/room")
    public void createChattingRoom(@RequestParam(name = "roomName") String roomName){
        chattingService.createChattingRoom(roomName);
    }

    @GetMapping("/room")
    public List<ChattingRoomDto> getChattingRoomList(){
        return chattingService.getChattingRoomList();
    }

    @PostMapping("/send")
    public void send(HttpServletRequest request, @RequestBody SendDto sendDto){
        chattingService.send(sendDto, request.getSession().getId());
    }

    @GetMapping("/polling/{roomId}")
    public List<ChattingDto> polling(@PathVariable("roomId") Long roomId){
        return chattingService.getChattingListByRoomId(roomId);
    }
}
