package com.kevin.activitytracker.repository;

import java.util.List;
import java.util.Optional;

import com.kevin.activitytracker.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    

    List<Activity> findByActivityType(String activityType);
    Optional<Activity> findById(Long id);

    @Query("select * from activities where date between '?1/?2/1' and '?1/?2/?3'")
    List<Activity> findActivitiesByMonth(int year, int month, int daysInMonth);
}
