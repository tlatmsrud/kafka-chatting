package org.ssk.domain.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.domain.chatting.domain.Chatting;
import org.ssk.domain.chatting.domain.ChattingRoom;

import java.util.List;

/**
 * title        : ChattingRepository
 * author       : sim
 * date         : 2023-11-24
 * description  : ChattingRepository
 */
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    List<Chatting> findByChattingRoom(ChattingRoom chattingRoom);
}
