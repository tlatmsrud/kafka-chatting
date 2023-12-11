package org.ssk.domain.chatting.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.ssk.domain.chatting.service.ChattingService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void getChattingRoomList() {
    }

    @Test
    void enterRoom() {
    }
}