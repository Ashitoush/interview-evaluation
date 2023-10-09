package com.ashitoush.interviewevaluation.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GlobalApiResponse {
    private boolean status;
    private String message;
    private Object data;
}
