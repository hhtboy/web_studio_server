package com.example.webstudio.service;

import com.example.webstudio.Entity.Member;
import com.example.webstudio.dto.response.BoolResponseDto;

public interface LoginService {
    BoolResponseDto register(String userid, String userpw);
    Member login(String userid, String userpw) throws Exception;
}
