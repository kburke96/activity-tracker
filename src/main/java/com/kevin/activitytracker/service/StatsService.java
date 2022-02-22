package com.kevin.activitytracker.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.repository.ActivityRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsService {
    
    private final ActivityRepository activityRepo;

    public Long getActivityHoursInMonth(int year, String month) {
        int[] monthNumberAndDays = getMonthNumberAndDays(month, year);
        int monthNumber = monthNumberAndDays[0];
        int daysInMonth = monthNumberAndDays[1];

        List<Activity> activitiesInMonth = activityRepo.findActivitiesByMonth(year, monthNumber, daysInMonth);

        Long totalMinutes = activitiesInMonth.stream().map(a -> Duration.between(LocalTime.MIN, LocalTime.parse(a.getTime()))).mapToLong(Duration::toMinutes).sum();
        return Math.floorDiv(totalMinutes, 60);
    }

    private int[] getMonthNumberAndDays(String month, int year) {
        int monthNumber = 0;
        int daysInMonth = 0;
        switch (month) {
            case "jan":
                monthNumber=1;
                daysInMonth=31;
                break;
            case "feb":
                monthNumber=2;
                if(year%4==0)
                    daysInMonth=29;
                else
                    daysInMonth=28;
                break;
            case "mar":
                monthNumber=3;
                daysInMonth=31;
                break;
            case "apr":
                monthNumber=4;
                daysInMonth=30;
                break;
            case "may":
                monthNumber=5;
                daysInMonth=31;
                break;
            case "jun":
                monthNumber=6;
                daysInMonth=30;
                break;
            case "jul":
                monthNumber=7;
                daysInMonth=31;
                break;
            case "aug":
                monthNumber=8;
                daysInMonth=31;
                break;
            case "sep":
                monthNumber=9;
                daysInMonth=30;
                break;
            case "oct":
                monthNumber=10;
                daysInMonth=31;
                break;
            case "nov":
                monthNumber=11;
                daysInMonth=30;
                break;
            case "dec":
                monthNumber=12;
                daysInMonth=31;
                break;
        }

        return new int[]{monthNumber, daysInMonth};
    }

}
