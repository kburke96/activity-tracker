package com.kevin.activitytracker.controller;

import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.service.StatsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin()
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {
    
    private final StatsService statsService;

    @GetMapping("/time/{year}/{month}")
    public ResponseEntity<Long> getTotalActivityTimeByMonth(@PathVariable int year, @PathVariable String month) {
        return ResponseEntity.ok(statsService.getActivityHoursInMonth(year, month));
    }

    @GetMapping(
        value = "/{year}/{month}",
        params = "activityType")
    public ResponseEntity<Iterable<Activity>> getActivitiesByTypeInMonth(@RequestParam("activityType") String type, @PathVariable int year, @PathVariable String month) {
        return ResponseEntity.ok(statsService.getActivitiesByTypeInMonth(type, year, month));
    }
}
