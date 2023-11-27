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
@Table(name="tbl_chatting")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChattingRoom chattingRoom;
    private String nickname;
    private String message;
    private String time;
}
