package org.ssk.domain.chatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssk.domain.chatting.domain.ChattingRoom;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
}
