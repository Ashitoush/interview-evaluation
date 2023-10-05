package com.ashitoush.interviewevaluation.enums;

public enum RatingScale {
    POOR(1, "Poor"),
    SATISFACTORY(2, "Satisfactory"),
    AVERAGE(3, "Average"),
    GOOD(4, "Good"),
    OUTSTANDING(5, "Outstanding");

    private Integer key;
    private String value;

    RatingScale(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static RatingScale getByKey(Integer key) {
        if (key == null) {
            throw new RuntimeException("Enum Key Cannot be Null");
        }
        RatingScale[] ratingScales = values();

        for (RatingScale ratingScale : ratingScales) {
            if (key.equals(ratingScale.getKey())) {
                return ratingScale;
            }
        }
        throw new RuntimeException("Rating Scale Not Found");
    }
}
