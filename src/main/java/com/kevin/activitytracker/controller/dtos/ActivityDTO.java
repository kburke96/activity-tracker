package com.kevin.activitytracker.controller.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ActivityDTO {
    private Long id;

    private String activityType;

    private String activityName;

    private String time;

    private double distance;

    private String activityDate;
}
