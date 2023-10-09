package com.ashitoush.interviewevaluation.service;

import com.ashitoush.interviewevaluation.dto.LoginDto;
import com.ashitoush.interviewevaluation.response.AuthResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthResponse authenticate(LoginDto loginDto);
    AuthResponse generateRefreshToken(HttpServletRequest httpServletRequest);
}
