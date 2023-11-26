package org.ssk.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssk.domain.user.domain.User;
import org.ssk.domain.user.repository.UserRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-27
 * description  :
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(String nickName){
        if(!userRepository.findById(nickName).isEmpty()){
            throw new RuntimeException("이미 존재하는 nickname 입니다.");
        }

        User user = User.from(nickName);
        userRepository.save(user);
    }
}
