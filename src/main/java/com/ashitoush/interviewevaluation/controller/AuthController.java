package com.ashitoush.interviewevaluation.controller;

import com.ashitoush.interviewevaluation.dto.LoginDto;
import com.ashitoush.interviewevaluation.dto.UserDto;
import com.ashitoush.interviewevaluation.response.AuthResponse;
import com.ashitoush.interviewevaluation.service.AuthService;
import com.ashitoush.interviewevaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("create")
    public ResponseEntity<Integer> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.authenticate(loginDto), HttpStatus.OK);
    }

    @GetMapping("generate-token-from-refresh-token")
    public ResponseEntity<AuthResponse> generateTokenFromRefreshToken(HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(authService.generateRefreshToken(httpServletRequest), HttpStatus.OK);
    }
}
