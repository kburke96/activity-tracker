package com.kevin.activitytracker.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import java.time.Duration;
@Entity
@Table(name="activities", schema="public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="activity_type")
    private String activityType;

    @Column(name="title")
    private String activityName;

    @Column(name="time")
    private String time;

    @Column(name="distance")
    private double distance;

    @Column(name="date")
    private String activityDate;


    @Override
    public String toString() {
        return "Activity [activityName=" + activityName + ", activityType=" + activityType + ", distance=" + distance
                + ", id=" + id + ", time=" + time + "]";
    }
 
    

}
