package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ssk.domain.chatting.dto.ChattingRoomDto;
import org.ssk.domain.chatting.service.ChattingService;

import java.util.List;

/**
 * title        : 채팅 컨트롤러
 * author       : sim
 * date         : 2023-11-23
 * description  : 채팅 컨트롤러
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChattingController {

    private final ChattingService chattingService;

    /**
     * 채팅방 생성
     * @param roomName - 채팅방 이름
     * @return 생성된 채팅방 ID
     */
    @ResponseBody
    @PostMapping("/room")
    public Long createChattingRoom(@RequestParam(name = "roomName") String roomName){
        return chattingService.createChattingRoom(roomName);
    }

    /**
     * 채팅방 리스트 조회
     * @param model - 모델
     * @return 채팅방 리스트 View
     */
    @GetMapping("/room")
    public String getChattingRoomList(Model model){

        List<ChattingRoomDto> list = chattingService.getChattingRoomList();
        model.addAttribute("list", list);
        return "chattingRoomList";
    }

    /**
     * 채팅방 입장
     * @param model - 모델
     * @param roomId - 채팅방 ID
     * @return 채팅방 View
     */
    @GetMapping("/room/enter/{roomId}")
    public String enterRoom(Model model, @PathVariable("roomId") Long roomId){
        String roomName = chattingService.getRoomName(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("roomName", roomName);
        return "chatting";
    }
}
