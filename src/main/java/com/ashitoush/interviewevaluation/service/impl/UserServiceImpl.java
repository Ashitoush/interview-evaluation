package com.ashitoush.interviewevaluation.service.impl;

import com.ashitoush.interviewevaluation.config.AuthUserDetails;
import com.ashitoush.interviewevaluation.config.CustomMessageSource;
import com.ashitoush.interviewevaluation.constants.FieldConstants;
import com.ashitoush.interviewevaluation.constants.MessageConstants;
import com.ashitoush.interviewevaluation.dto.UserDto;
import com.ashitoush.interviewevaluation.entity.Role;
import com.ashitoush.interviewevaluation.entity.User;
import com.ashitoush.interviewevaluation.exception.AppException;
import com.ashitoush.interviewevaluation.repo.RoleRepo;
import com.ashitoush.interviewevaluation.repo.UserRepo;
import com.ashitoush.interviewevaluation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final CustomMessageSource customMessageSource;

    @Override
    public Integer createUser(UserDto userDto) {

        List<Role> roleList = roleRepo.findAllById(userDto.getRoleList());
        if (roleList.isEmpty()) {
            throw new AppException(customMessageSource.get(MessageConstants.NOT_FOUND,
                    customMessageSource.get(FieldConstants.ROLE)));
        }

        User user = User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .isActive(userDto.getIsActive())
                .roles(roleList)
                .build();
        return userRepo.save(user).getId();
    }

    @Override
    public UserDto getById(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new AppException(customMessageSource.get(MessageConstants.NOT_FOUND,
                        customMessageSource.get(FieldConstants.USER))));
        return new UserDto(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepo.findUserByEmail(username)
                .orElseThrow(() -> new AppException("Invalid Email"));
    }
}
