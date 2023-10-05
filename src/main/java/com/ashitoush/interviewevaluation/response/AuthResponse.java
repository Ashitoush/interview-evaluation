package com.ashitoush.interviewevaluation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Boolean status;
    private String message;
    private String accessToken;
    private String refreshToken;
}
