package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 채팅 입력
     * @param request - HttpServletRequest
     * @param sendDto - 발송할 채팅 Dto
     */
    @ResponseBody
    @PostMapping("/send")
    public void send(HttpServletRequest request, @RequestBody SendDto sendDto){
        chattingService.send(sendDto, request.getSession().getId());
    }

    /**
     * 채팅 조회
     * @param roomId - 채팅방 ID
     * @return 채팅 리스트
     */
    @GetMapping("/{roomId}")
    public List<ChattingDto> getChatting(@PathVariable Long roomId){
        return chattingService.getChattingListByRoomId(roomId);
    }
}

