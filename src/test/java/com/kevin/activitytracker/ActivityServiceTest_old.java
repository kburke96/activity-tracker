// package com.kevin.activitytracker;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import java.util.ArrayList;
// import java.util.List;

// import com.kevin.activitytracker.Model.Activity;
// import com.kevin.activitytracker.Repository.ActivityRepository;
// import com.kevin.activitytracker.Service.ActivityService;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;

// @ExtendWith(MockitoExtension.class)
// public class ActivityServiceTest {
    
//     @Mock
//     private ActivityRepository repo;

//     @Autowired
//     @InjectMocks
//     private ActivityService service;

//     private Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running").activityDate("2021").time("15:00").distance(15.00).build();
//     private Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling").activityDate("2021").time("5:00").distance(5.00).build();
//     private Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking").activityDate("2021").time("125:00").distance(125.00).build();
//     List<Activity> activityList;
    
//     @BeforeEach
//     public void setUp() {
//         activityList = new ArrayList<Activity>();
//         activityList.add(activity1);
//         activityList.add(activity2);
//         activityList.add(activity3);
//     }

//     @AfterEach
//     public void tearDown() {
//         activity1 = activity2 = activity3 = null;
//         activityList = null;
//     }

//     @Test
//     public void givenActivityToAddShouldReturnAddedActivity() {
//         when(repo.save(any())).thenReturn(activity1);

//         service.insertActivity(activity1);

//         verify(repo,times(1)).save(any());
        
//     }

//     @Test
//     public void givenGetAllActivitiesShouldReturnAllActivities() {
//         PageRequest pageable = PageRequest.of(1, 10, Sort.by("activityName"));

//         Page<Activity> newPage = new PageImpl<>(activityList, pageable, 1);
//         repo.save(activity1);

//         when(repo.findAll()).thenReturn(activityList);

//         Page<Activity> returnedActivityPage = service.getAllActivities(pageable);

//         // List<Activity> returnedActivityList = returnedActivityPage.getContent();
//         // List<Activity> returnedActivityList = service.getAllActivities();

//         assertEquals(returnedActivityList, activityList);
//         verify(repo,times(1)).save(activity1);
//         verify(repo,times(1)).findAll();
//     }


    
// }
