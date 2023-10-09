package com.ashitoush.interviewevaluation.controller;

import com.ashitoush.interviewevaluation.config.CustomMessageSource;
import com.ashitoush.interviewevaluation.constants.FieldConstants;
import com.ashitoush.interviewevaluation.constants.MessageConstants;
import com.ashitoush.interviewevaluation.response.GlobalApiResponse;
import com.ashitoush.interviewevaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomMessageSource customMessageSource;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public GlobalApiResponse getById(@PathVariable Integer id) {
        return GlobalApiResponse.builder()
                .status(true)
                .message(customMessageSource.get(MessageConstants.RETRIEVE,
                        customMessageSource.get(FieldConstants.USER)))
                .data(userService.getById(id))
                .build();
    }
}
