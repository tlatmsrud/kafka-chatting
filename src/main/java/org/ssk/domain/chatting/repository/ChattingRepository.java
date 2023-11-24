package org.ssk.domain.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.domain.chatting.domain.Chatting;
import org.ssk.domain.chatting.domain.ChattingRoom;

import java.util.List;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    List<Chatting> findByRoomId(ChattingRoom chattingRoom);
}
