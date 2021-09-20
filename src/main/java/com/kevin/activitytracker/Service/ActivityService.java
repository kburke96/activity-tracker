package com.kevin.activitytracker.Service;

import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {

    // Use constructor-based injection instead of field-based
    // @RequiredArgsContructor from Lombok removes some boilerplate
    private final ActivityRepository repository;

    public Page<Activity> getAllActivities(Pageable pageable) {
        Page<Activity> pageResult = repository.findAll(pageable);

        if (pageResult.hasContent()) {
            return pageResult;
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
