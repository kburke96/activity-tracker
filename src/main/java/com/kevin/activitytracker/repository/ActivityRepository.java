package com.kevin.activitytracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kevin.activitytracker.model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    

    List<Activity> findByActivityType(String activityType);
    Optional<Activity> findById(Long id);

    List<Activity> findByActivityDateBetween(LocalDate startDate, LocalDate endDate);

    List<Activity> findByActivityTypeAndActivityDateBetween(String activityType, LocalDate startDate, LocalDate endDate);

}
