package org.ssk.domain.chatting.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssk.domain.chatting.dto.ChattingRoomDto;
import org.ssk.domain.chatting.service.ChattingService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChattingController.class)
class ChattingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChattingService chattingService;

    @Test
    @DisplayName("채팅방 생성하기")
    void createChattingRoom() throws Exception {
        given(chattingService.createChattingRoom(any(String.class)))
                .willReturn(1L);

        mockMvc.perform(post("/api/chat/room")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("roomName", "채팅방"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @DisplayName("채팅방 리스트 조회하기")
    void getChattingRoomList() throws Exception {
        List<ChattingRoomDto> list = Arrays.asList(
                ChattingRoomDto.of(1L, "첫번째 채팅방"),
                ChattingRoomDto.of(2L, "두번째 채팅방"),
                ChattingRoomDto.of(3L, "세번째 채팅방")
        );

        given(chattingService.getChattingRoomList()).willReturn(list);

        mockMvc.perform(get("/api/chat/room"))
                .andDo(print())
                .andExpect(view().name("chattingRoomList"))
                .andExpect(model().attribute("list", list));
    }

    @Test
    @DisplayName("유효한 채팅방 입장하기")
    void enterRoomWithValidRoomId() throws Exception {
        given(chattingService.getRoomName(1L))
                .willReturn("채팅방");

        mockMvc.perform(
                get("/api/chat/room/enter/{roomId}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("chatting"))
                .andExpect(model().attribute("roomName", "채팅방"))
                .andExpect(model().attribute("roomId", 1L));
    }

    @Test
    @DisplayName("유효하지 않은 채팅방 입장하기")
    void enterRoomWithInvalidRoomId() throws Exception {
        given(chattingService.getRoomName(100L)).willThrow(
                new RuntimeException("room is not exists"));

        mockMvc.perform(
                get("/api/chat/room/enter/{roomId}", 100L))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("room is not exists"));
    }
}