package org.ssk.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public String getMainPage(){
        return "main";
    }
    @PostMapping("/enter")
    public String enter(Model model, @RequestParam(name = "nickName") String nickName, @RequestParam(name = "method") String method){
        log.info("nickName : "+ nickName);
        log.info("method : "+ method);
        userService.join(nickName);
        model.addAttribute("nickName", nickName);
        model.addAttribute("method", method);

        return "chattingRoomList";
    }
}
