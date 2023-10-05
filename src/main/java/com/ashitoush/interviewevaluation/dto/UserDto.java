package com.ashitoush.interviewevaluation.dto;

import com.ashitoush.interviewevaluation.entity.Department;
import com.ashitoush.interviewevaluation.entity.Role;
import com.ashitoush.interviewevaluation.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String password;
    private String email;
    private List<Integer> roleList;
    private Boolean isActive;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        for (Role role : user.getRoles()) {
            this.roleList.add(role.getId());
        }
        this.isActive = user.getIsActive();
    }
}
