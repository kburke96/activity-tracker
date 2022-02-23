package com.kevin.activitytracker.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.repository.ActivityRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final ActivityRepository activityRepo;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public Long getActivityHoursInMonth(int year, String month) {
        String[] monthNumberAndDays = getMonthNumberAndDays(month, year);

        List<Activity> activitiesInMonth = activityRepo.findByActivityDateBetween(LocalDate.parse(monthNumberAndDays[0], formatter), LocalDate.parse(monthNumberAndDays[1], formatter));

        Long totalMinutes = activitiesInMonth.stream()
                .map(a -> Duration.between(LocalTime.MIN, LocalTime.parse(a.getTime()))).mapToLong(Duration::toMinutes)
                .sum();
        return Math.floorDiv(totalMinutes, 60);
    }

    public List<Activity> getActivitiesByTypeInMonth(String type, int year, String month) {
        String[] monthNumberAndDays = getMonthNumberAndDays(month, year);
        
        List<Activity> typeActivitiesInMonth = activityRepo.findByActivityTypeAndActivityDateBetween(type, LocalDate.parse(monthNumberAndDays[0], formatter), LocalDate.parse(monthNumberAndDays[1], formatter));

        return typeActivitiesInMonth;
    }

    private String[] getMonthNumberAndDays(String month, int year) {
        String firstDay = "";
        String lastDay = "";
        switch (month) {
            case "jan":
                firstDay = "01/01/"+year;
                lastDay = "31/01/"+year;
                break;
            case "feb":
                firstDay = "01/02/"+year;
                if (year % 4 == 0)
                    lastDay = "29/02/"+year;
                else
                    lastDay = "28/02/"+year;
                break;
            case "mar":
                firstDay = "01/03/"+year;
                lastDay = "31/03/"+year;
                break;
            case "apr":
                firstDay = "01/04/"+year;
                lastDay = "30/04/"+year;
                break;
            case "may":
                firstDay = "01/05/"+year;
                lastDay = "31/05/"+year;
                break;
            case "jun":
                firstDay = "01/06/"+year;
                lastDay = "30/06/"+year;
                break;
            case "jul":
                firstDay = "01/07/"+year;
                lastDay = "31/07/"+year;
                break;
            case "aug":
                firstDay = "01/08/"+year;
                lastDay = "31/08/"+year;
                break;
            case "sep":
                firstDay = "01/09/"+year;
                lastDay = "30/09/"+year;
                break;
            case "oct":
                firstDay = "01/10/"+year;
                lastDay = "31/10/"+year;
                break;
            case "nov":
                firstDay = "01/11/"+year;
                lastDay = "30/11/"+year;
                break;
            case "dec":
                firstDay = "01/12/"+year;
                lastDay = "31/12/"+year;
                break;
        }
        return new String[] { firstDay, lastDay };
    }

}
