package com.kevin.activitytracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.repository.ActivityRepository;
import com.kevin.activitytracker.service.StatsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTest {
    
    @Mock
    private ActivityRepository repo;

    private StatsService service;

    Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running")
            .activityDate("13/07/2021 16:37").time("00:15:00").distance(15.00).build();
    Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling")
            .activityDate("23/07/2021 14:23").time("00:55:00").distance(5.00).build();
    Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking")
            .activityDate("03/10/2021 20:56").time("00:54:00").distance(125.00).build();

    List<Activity> activityList;

    @BeforeEach
    void setUp() {
        service = new StatsService(repo);
        
        activityList = new ArrayList<Activity>();
        activityList.add(activity1);
        activityList.add(activity2);
        activityList.add(activity3);
    }

    @Test
    void findActivitiesByMonthTest() {

        when(repo.findActivitiesByMonth(anyInt(), anyInt(), anyInt())).thenReturn(activityList);

        Long hours = service.getActivityHoursInMonth(2021, "mar");

        assertEquals(2L, hours);
    }
}
