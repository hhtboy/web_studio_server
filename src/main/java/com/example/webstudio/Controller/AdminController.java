package com.example.webstudio.Controller;

import com.example.webstudio.Entity.Member;
import com.example.webstudio.dto.ProductDto;
import com.example.webstudio.dto.request.*;
import com.example.webstudio.dto.response.ServiceResponseDto;
import com.example.webstudio.dto.response.BoolResponseDto;
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
public class AdminController {

    private final WebService service;

    @GetMapping("/admin/reservation")
    public ResponseEntity<ArrayList<ReservationRequestDto>> reservation() {
        return new ResponseEntity<>(service.getAllReservation(), HttpStatus.OK);
    }

    @PostMapping("/admin/addService")
    public ResponseEntity<BoolResponseDto> addService(@RequestBody ServiceRequestDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.addService(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/updateService")
    public ResponseEntity<BoolResponseDto> updateService(@RequestBody ServiceRequestDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.updateService(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/deleteService")
    public ResponseEntity<BoolResponseDto> deleteService(@RequestBody ServiceIdRequestDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.deleteService(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/addProduct")
    public ResponseEntity<BoolResponseDto> addProduct(@RequestBody ProductRequestDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.addProduct(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/updateProduct")
    public ResponseEntity<BoolResponseDto> updateProduct(@RequestBody ProductDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.updateProduct(dto), HttpStatus.OK);
    }

    @PostMapping("/admin/deleteProduct")
    public ResponseEntity<BoolResponseDto> deleteProduct(@RequestBody ProductIdRequestDto dto, HttpServletRequest request) {
        //세션 검증
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
//        if(!validate(session)) {
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//        }
        return new ResponseEntity<>(service.deleteProduct(dto), HttpStatus.OK);
    }

    private boolean validate(HttpSession session) {
        Member member = (Member) session.getAttribute(LOGIN_MEMBER);
        String userid = member.getUserid();
        return userid.equals("admin");
    }
}
