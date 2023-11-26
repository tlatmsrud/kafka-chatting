package org.ssk.domain.user.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-27
 * description  :
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String nickName;

    private User(String nickName){
        this.nickName = nickName;
    }
    public static User from(String nickName){
        return new User(nickName);
    }

}
