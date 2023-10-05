package com.ashitoush.interviewevaluation.service.impl;

import com.ashitoush.interviewevaluation.config.AuthUserDetails;
import com.ashitoush.interviewevaluation.config.AuthUserDetailsService;
import com.ashitoush.interviewevaluation.config.CustomMessageSource;
import com.ashitoush.interviewevaluation.config.JwtHelper;
import com.ashitoush.interviewevaluation.dto.LoginDto;
import com.ashitoush.interviewevaluation.entity.User;
import com.ashitoush.interviewevaluation.response.AuthResponse;
import com.ashitoush.interviewevaluation.service.AuthService;
import com.ashitoush.interviewevaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthUserDetailsService authUserDetailsService;
    private final CustomMessageSource customMessageSource;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Override
    public AuthResponse authenticate(LoginDto loginDto) {

        String username = loginDto.getEmail();
        String password = loginDto.getPassword();
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);
        User user = userService.getByUsername(userDetails.getUsername());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        AuthResponse authResponse = jwtHelper.generateToken(new AuthUserDetails(user));
        authResponse.setStatus(Boolean.TRUE);
        authResponse.setMessage("Token Generated Successfully");

        return authResponse;
    }

    @Override
    public AuthResponse generateRefreshToken(String refreshToken) {
        String username = jwtHelper.getUsernameFromToken(refreshToken);
        User user = userService.getByUsername(username);
        AuthResponse authResponse = jwtHelper.generateToken(new AuthUserDetails(user));
        authResponse.setStatus(Boolean.TRUE);
        authResponse.setMessage("New Access Token and Refresh Token Generated");
        return authResponse;
    }
}
