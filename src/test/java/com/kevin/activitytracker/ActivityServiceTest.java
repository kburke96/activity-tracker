package com.kevin.activitytracker;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;
import com.kevin.activitytracker.Service.ActivityService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalAnswers.*;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @Mock
    private ActivityRepository repo;

    private ActivityService service;

    Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running")
            .activityDate("2021").time("15:00").distance(15.00).build();
    Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling")
            .activityDate("2021").time("5:00").distance(5.00).build();
    Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking")
            .activityDate("2021").time("125:00").distance(125.00).build();

    List<Activity> activityList;

    @BeforeEach
    public void setUp() {
        service = new ActivityService(repo);

        activityList = new ArrayList<Activity>();
        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);
    }

    @Test
    public void savedActivityHasTitle() {
        Activity activity = Activity.builder().activityName("title").activityType("cycling").activityDate("2021")
                .distance(15.00).time("20:00").build();

        when(repo.save(any(Activity.class))).then(returnsFirstArg());

        Activity savedActivity = service.insertActivity(activity);

        assertThat(savedActivity.getActivityName()).isNotNull();
    }

    @Test
    public void givenGetAllActivitiesShouldReturnAllActivities() {

        //Create a new Page<Activity> from the List of activities
        PageRequest pageable = PageRequest.of(1, 3, Sort.by("activityName"));
        Page<Activity> newPage = new PageImpl<>(activityList, pageable, activityList.size());

        when(repo.findAll(pageable)).thenReturn(newPage);

        Page<Activity> returnedActivityPage = service.getAllActivities(pageable);

        assertEquals(returnedActivityPage.getContent(), activityList);

    }
}
