package com.example.webstudio.service;

import com.example.webstudio.Entity.Member;
import com.example.webstudio.dto.response.BoolResponseDto;
import com.example.webstudio.repository.WebRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MainLoginService implements LoginService{
    private final WebRepository repository;
    @Override
    public BoolResponseDto register(String userid, String userpw) {
        try {
            repository.insertUser(userid, userpw);
            return new BoolResponseDto(true);
        } catch (SQLException e) {
            return new BoolResponseDto(false);
        }
    }

    @Override
    public Member login(String userid, String userpw) throws Exception {
        Member member = repository.getUser(userid, userpw);
        return member;
    }
}
