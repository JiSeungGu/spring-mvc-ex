package com.doubles.ex05.controller;

import com.doubles.ex05.domain.LoginDTO;
import com.doubles.ex05.domain.UserVO;
import com.doubles.ex05.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserService userService;

    // 회원가입
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void registerGET() {

    }

    // 회원가입 처리
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPOST() {

    }

    // 로그인 페이지
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginGET(@ModelAttribute("loginDTO") LoginDTO loginDTO) {

    }

    // 로그인 처리
    @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
    public void loginPOST(LoginDTO loginDTO, HttpSession session, Model model) throws Exception {
        UserVO userVO = userService.login(loginDTO);
        if (userVO == null) {
            return;
        }
        model.addAttribute("userVO", userVO);
        if (loginDTO.isUseCookie()) {
            int amount = 60 * 60 * 24 * 7;
            Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
            userService.keepLogin(userVO.getUid(), session.getId(), sessionLimit);
        }
    }
}
