package com.kevin.activitytracker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

// import java.time.Duration;
@Entity
public class Activity {

    @Id
    private Long id;
    private String activityType;
    private String activityName;
    private String time;
    private double distance;
    private String activityDate;

    // public Activity() {
    // }

    // public Activity(String activityType, String activityName, String time) {
    //     this.activityType = activityType;
    //     this.activityName = activityName;
    //     this.time = time;
    // }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
    public String getActivityName() {
        return activityName;
    }
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Activity [activityName=" + activityName + ", activityType=" + activityType + ", distance=" + distance
                + ", id=" + id + ", time=" + time + "]";
    }
 
    

}
