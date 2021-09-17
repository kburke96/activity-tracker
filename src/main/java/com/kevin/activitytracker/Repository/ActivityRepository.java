package com.kevin.activitytracker.Repository;

import java.util.List;
import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    

    List<Activity> findByActivityType(String activityType);
    Optional<Activity> findById(Long id);
}
