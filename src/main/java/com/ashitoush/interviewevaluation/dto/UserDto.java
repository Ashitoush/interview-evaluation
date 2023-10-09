package com.ashitoush.interviewevaluation.dto;

import com.ashitoush.interviewevaluation.entity.Role;
import com.ashitoush.interviewevaluation.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String password;
    private String email;
    private List<Integer> roleList = new ArrayList<>();
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
