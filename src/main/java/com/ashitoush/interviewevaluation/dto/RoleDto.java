package com.ashitoush.interviewevaluation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDto {

    private Integer id;
    private String name;
    private String description;
    private boolean isActive;
}
