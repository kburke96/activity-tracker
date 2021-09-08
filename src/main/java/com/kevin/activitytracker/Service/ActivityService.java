package com.kevin.activitytracker.Service;

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

    public Activity insertActivity(Activity activity) {
        return repository.save(activity);
    }
}
