package com.kevin.activitytracker.Controllers;

import java.util.List;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    
    private ActivityRepository activityRepository;

    
    public HomeController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping("/home")
    public String home() {
        return "Hello world!";
    }

    @GetMapping("/activities") 
    public Iterable<Activity> getAllActivities() {
        return this.activityRepository.findAll();
    }

    // @GetMapping("/activities")
    // public Iterable<Activity> getActivities() {
    //     return this.activityRepository.findAll();
    // }

    
}
