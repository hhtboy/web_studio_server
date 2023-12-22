package com.example.webstudio.Controller;

import com.example.webstudio.Entity.Member;
import com.example.webstudio.dto.request.AccountRequestDto;
import com.example.webstudio.dto.response.BoolResponseDto;
import com.example.webstudio.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    public static final String LOGIN_MEMBER = "loginMember";
    private final LoginService service;

    @PostMapping("/register")
    public BoolResponseDto register(@RequestBody AccountRequestDto request) {
        return service.register(request.getUserid(), request.getUserpw());
    }

    @PostMapping("/login")
    public BoolResponseDto login(@RequestBody AccountRequestDto request, HttpServletRequest httpRequest) {

        Member member = null;
        try {
            member = service.login(request.getUserid(), request.getUserpw());
            if(member == null) {
                return new BoolResponseDto(false);
            }
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute(LOGIN_MEMBER, member);

            log.info("새로운 멤버 로그인 : " + member.getUserid());
            return new BoolResponseDto(true);
        } catch (Exception e) {
            return new BoolResponseDto(false);
        }
    }

    @PostMapping("/logout")
    public BoolResponseDto logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return new BoolResponseDto(true);
        }
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        if(member == null) {
            return new BoolResponseDto(false);
        }

        else {
            request.getSession().invalidate();
            log.info("멤버 로그아웃 : " + member.getUserid());
            return new BoolResponseDto(true);

        }
    }
}
