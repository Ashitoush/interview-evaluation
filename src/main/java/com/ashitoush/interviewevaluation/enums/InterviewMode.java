package com.ashitoush.interviewevaluation.enums;

public enum InterviewMode {
    ONLINE(0, "Online"),
    OFFLINE(1, "Offline");

    private Integer key;
    private String value;

    InterviewMode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public  String getValue() {
        return value;
    }

    public static InterviewMode getByKey(Integer key) {
        if (key == null) {
            throw new RuntimeException("Key Cannot Be Null");
        }

        InterviewMode[] modes = values();

        for (InterviewMode mode : modes) {
            if (key.equals(mode.getKey())) {
                return mode;
            }
        }
        throw new RuntimeException("Interview Mode Not Found");
    }
}
