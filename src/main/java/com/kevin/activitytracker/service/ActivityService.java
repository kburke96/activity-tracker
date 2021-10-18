package com.kevin.activitytracker.service;

import com.kevin.activitytracker.exception.ActivityNotFoundException;
import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.repository.ActivityRepository;

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

    public Activity getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ActivityNotFoundException("Activity ID " + id + " not found."));
    }

    public Activity insertActivity(Activity activity) {
        return repository.save(activity);
    }

    public Activity deleteActivity(Long id) {
        Activity toBeDeleted = getById(id);
        repository.delete(toBeDeleted);
        return toBeDeleted;
    }
}
