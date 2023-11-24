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
    private ChattingRoom roomId;
    private String sessionId;
    private String message;
    private String time;
}
