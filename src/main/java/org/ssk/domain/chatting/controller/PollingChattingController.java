package org.ssk.domain.chatting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ssk.domain.chatting.dto.ChattingDto;
import org.ssk.domain.chatting.dto.SendDto;
import org.ssk.domain.chatting.service.ChattingService;

import java.util.List;

/**
 * title        : Polling 채팅 Controller
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@RestController
@RequestMapping("/polling")
@RequiredArgsConstructor
public class PollingChattingController {

    private final ChattingService chattingService;

    /**
     * 채팅 입력
     * Kafka Broker 로 전달
     * @param sendDto - 발송할 채팅 Dto
     */
    @ResponseBody
    @PostMapping("/send")
    public void send(@RequestBody SendDto sendDto){
        chattingService.send(sendDto);
    }

    /**
     * 채팅방에 대한 채팅내역 조회
     * @param roomId - 채팅방 ID
     * @return 채팅 내역 리스트
     */
    @GetMapping("/{roomId}")
    public List<ChattingDto> getChatting(@PathVariable Long roomId){
        return chattingService.getChattingListByRoomId(roomId);
    }
}

