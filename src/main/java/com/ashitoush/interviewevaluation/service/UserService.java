package com.ashitoush.interviewevaluation.service;

import com.ashitoush.interviewevaluation.dto.UserDto;
import com.ashitoush.interviewevaluation.entity.User;

public interface UserService {
    Integer createUser(UserDto userDto);
    UserDto getById(Integer id);
    User getByUsername(String username);
}
