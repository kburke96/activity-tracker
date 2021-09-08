package com.kevin.activitytracker.Controllers;

import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;
import com.kevin.activitytracker.Service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        Activity newActivity = new Activity();

        newActivity.setActivityDate(inputActivity.getActivityDate());
        newActivity.setActivityName(inputActivity.getActivityName());
        newActivity.setActivityType(inputActivity.getActivityType());
        newActivity.setDistance(inputActivity.getDistance());
        newActivity.setTime(inputActivity.getTime());
        final Activity updatedActivity = service.insertActivity(newActivity);
        return ResponseEntity.ok(updatedActivity);
    }
    
}
