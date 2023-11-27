package org.ssk.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.ssk.domain.user.service.UserService;

/**
 * title        : 메인 컨트롤러
 * author       : sim
 * date         : 2023-11-27
 * description  :
 */

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserService userService;

    /**
     * 메인 페이지 호출
     * @return - 메인페이지
     */
    @GetMapping
    public String getMainPage(){
        return "main";
    }

    /**
     * 회원 가입
     * @param nickName - 닉네임
     */
    @PostMapping("/join")
    @ResponseBody
    public void enter(@RequestParam(name = "nickname") String nickName){
        userService.join(nickName);
    }
}
