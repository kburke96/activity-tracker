package com.kevin.activitytracker.Repository;

import java.util.List;
import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, String> {
    

    List<Activity> findByActivityType(String activityType);

    Optional<Activity> findById(Long id);
}
