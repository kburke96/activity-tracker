package com.kevin.activitytracker.Service;

import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    
    @Autowired
    public ActivityRepository repository;

    public Page<Activity> getAllActivities(Pageable pageable) {
        Page<Activity> pagedResult = repository.findAll(pageable);

        if(pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return null;
        }
    }

    public Iterable<Activity> getByType(String activityType) {
        return repository.findByActivityType(activityType);
    }

    public Optional<Activity> getById(Long id) {
        return repository.findById(id);
    }

    public Activity insertActivity(Activity activity) {
        return repository.save(activity);
    }

    public Optional<Activity> deleteActivity(Long id) {
        if (getById(id).isEmpty()) {
            return null;
        } else {
            Optional<Activity> activity = getById(id);
            repository.deleteById(id);
            return activity;
        }
    }
}
