package com.ashitoush.interviewevaluation.controller;

import com.ashitoush.interviewevaluation.dto.LoginDto;
import com.ashitoush.interviewevaluation.dto.UserDto;
import com.ashitoush.interviewevaluation.response.AuthResponse;
import com.ashitoush.interviewevaluation.service.AuthService;
import com.ashitoush.interviewevaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
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
}
