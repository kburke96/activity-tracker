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
        String[] monthNumberAndDays = getMonthNumberAndDays(month, year);
        String startDate = year + "/" + monthNumberAndDays[0];
        String endDate = year + "/" + monthNumberAndDays[1];

        List<Activity> activitiesInMonth = activityRepo.findByActivityDateBetween(startDate, endDate);

        Long totalMinutes = activitiesInMonth.stream()
                .map(a -> Duration.between(LocalTime.MIN, LocalTime.parse(a.getTime()))).mapToLong(Duration::toMinutes)
                .sum();
        return Math.floorDiv(totalMinutes, 60);
    }

    public List<Activity> getActivitiesByTypeInMonth(String type, int year, String month) {
        String[] monthNumberAndDays = getMonthNumberAndDays(month, year);
        String startDate = year + "/" + monthNumberAndDays[0];
        String endDate = year + "/" + monthNumberAndDays[1];

        List<Activity> typeActivitiesInMonth = activityRepo.findByActivityTypeAndActivityDateBetween(type, startDate, endDate);

        return typeActivitiesInMonth;
    }

    private String[] getMonthNumberAndDays(String month, int year) {
        String firstDay = "";
        String lastDay = "";
        switch (month) {
            case "jan":
                firstDay = "01/1";
                lastDay = "01/31";
                break;
            case "feb":
                firstDay = "01/1";
                if (year % 4 == 0)
                    lastDay = "02/29";
                else
                    lastDay = "02/28";
                break;
            case "mar":
                firstDay = "03/1";
                lastDay = "03/31";
                break;
            case "apr":
                firstDay = "04/1";
                lastDay = "04/30";
                break;
            case "may":
                firstDay = "05/1";
                lastDay = "05/31";
                break;
            case "jun":
                firstDay = "06/1";
                lastDay = "06/30";
                break;
            case "jul":
                firstDay = "07/1";
                lastDay = "07/31";;
                break;
            case "aug":
                firstDay = "08/1";
                lastDay = "08/31";
                break;
            case "sep":
                firstDay = "09/1";
                lastDay = "09/30";
                break;
            case "oct":
                firstDay = "10/1";
                lastDay = "10/31";
                break;
            case "nov":
                firstDay = "11/1";
                lastDay = "11/30";
                break;
            case "dec":
                firstDay = "12/1";
                lastDay = "12/31";
                break;
        }
        return new String[] { firstDay, lastDay };
    }

}
