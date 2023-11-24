package org.ssk.domain.chatting.domain;

import lombok.*;

import javax.persistence.*;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-24
 * description  :
 */

@Entity
@Table(name = "tbl_chatting_room")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ChattingRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomName;
}
