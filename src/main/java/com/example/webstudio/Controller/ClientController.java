package com.example.webstudio.Controller;

import com.example.webstudio.Entity.Member;
import com.example.webstudio.dto.request.BoardRequestDto;
import com.example.webstudio.dto.request.ReservationRequestDto;
import com.example.webstudio.dto.response.BoardResponseDto;
import com.example.webstudio.dto.response.BoolResponseDto;
import com.example.webstudio.dto.response.ProductResponseDto;
import com.example.webstudio.dto.response.ServiceResponseDto;
import com.example.webstudio.service.WebService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.example.webstudio.Controller.LoginController.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientController {

    private final WebService service;

    @GetMapping("/service")
    public ResponseEntity<ArrayList<ServiceResponseDto>> service(HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.getAllService(), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<ArrayList<ProductResponseDto>> product(HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<BoolResponseDto> reservation(@RequestBody ReservationRequestDto dto, HttpServletRequest request) {
        //세션 검증
        HttpSession session = request.getSession(false);
        if(session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(service.reservation(dto), HttpStatus.OK);
    }


    @GetMapping("/board")
    public ResponseEntity<ArrayList<BoardResponseDto>> board() {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//    }
        return service.getAllArticles();
    }

    @PostMapping("/board/write")
    public ResponseEntity<BoolResponseDto> write(@RequestBody BoardRequestDto dto, HttpServletRequest request) {
        //세션 검증
        HttpSession session = request.getSession(false);
        if(session == null) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Member member = (Member) request.getSession().getAttribute(LOGIN_MEMBER);
        String userid = member.getUserid();

        return new ResponseEntity<>(service.writeArticle(dto, userid), HttpStatus.OK);
    }
}
