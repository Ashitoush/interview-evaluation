package com.ashitoush.interviewevaluation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Builder
public class ApiResponse {
    @NotNull
    private boolean status;
    @NotBlank
    private String message;
    private Object data;
    private List<String> errors;
}
