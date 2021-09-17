package com.kevin.activitytracker.Controllers;

import java.util.Optional;

import com.kevin.activitytracker.Exception.ActivityNotFoundException;
import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HomeController {
    
    @Autowired
    private ActivityService service;

    // @Autowired
    // ActivityRepository activityRepository;

    
    @GetMapping("/home")
    public String home() {
        return "Hello world!";
    }

    @GetMapping("/activities")
    public Page<Activity> getAllActivities(@RequestParam(defaultValue = "0") int pageNo,
                                           @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(defaultValue = "id") String sortBy) {
        
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Activity> activities = service.getAllActivities(pageable);

        return activities;
    }

    @GetMapping(
        value = "/activities",
        params = "activityType")
    public Iterable<Activity> getActivity(@RequestParam("activityType") String type) {
        // return this.activityRepository.findByActivityType(type);
        return service.getByType(type);
    }

    @GetMapping(
        value = "/activities",
        params = "id")
    public Optional<Activity> getSingleActivity(@RequestParam("id") Long id) {
        // return this.activityRepository.findById(id);
        return service.getById(id);
    }

    @PutMapping("/activities")
    public ResponseEntity<Activity> newActivity(@RequestBody Activity inputActivity) {

        Activity newActivity = Activity.builder()
                        .activityType(inputActivity.getActivityType())
                        .activityName(inputActivity.getActivityName())
                        .activityDate(inputActivity.getActivityDate())
                        .time(inputActivity.getTime())
                        .distance(inputActivity.getDistance())
                        .build();

        final Activity updatedActivity = service.insertActivity(newActivity);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping(value="/activities/{id}")
    public ResponseEntity<Optional<Activity>> deleteActivity(@PathVariable Long id) throws ActivityNotFoundException {
        Optional<Activity> deletedActivity = service.deleteActivity(id);

        if (deletedActivity == null) {
            throw new ActivityNotFoundException("Activity ID: " + id + " not found.");
        } else {
            return ResponseEntity.ok(deletedActivity);
            
        }
        
        
    }
    
}
