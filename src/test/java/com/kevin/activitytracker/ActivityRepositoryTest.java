package com.kevin.activitytracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import com.kevin.activitytracker.Model.Activity;
import com.kevin.activitytracker.Repository.ActivityRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


// @DataJpaTest
@ExtendWith(SpringExtension.class)
class ActivityRepositoryTest {
    //integration test
    @Mock
    private ActivityRepository activityRepository;
    
    private Activity activity;

    @BeforeEach
    public void setUp() {

        activity = Activity.builder().activityDate("2021").activityName("name").activityType("cycling").distance(15.00).time("20.00").build();
    }

    @AfterEach
    public void tearDown() {
        activityRepository.deleteAll();
        activity = null;
    }

    @Test
    public void givenActivityToAddShouldReturnAddedActivity(){

        activityRepository.save(activity);
        Activity fetchedActivity = activityRepository.findById(activity.getId()).get();
        //assertEquals(activity1.getName(),activity2.getName());
        assertEquals(1, fetchedActivity.getId());
    }

    @Test
    public void GivenGetAllActivityShouldReturnListOfAllActivitys(){
        Activity activity1 = Activity.builder().activityDate("2021").activityName("name").activityType("cycling").distance(15.00).time("20.00").build();
        Activity activity2 = Activity.builder().activityDate("2021").activityName("name two").activityType("cycling").distance(15.00).time("20.00").build();
        activityRepository.save(activity1);
        activityRepository.save(activity2);

        List<Activity> activityList = (List<Activity>) activityRepository.findAll();
        assertEquals("name two", activityList.get(1).getActivityName());

    }

    @Test
    public void givenIdThenShouldReturnActivityOfThatId() {
        Activity activity1 = Activity.builder().activityDate("2021").activityName("name").activityType("cycling").distance(15.00).time("20.00").build();
        Activity activity2 = activityRepository.save(activity1);

        Optional<Activity> optional =  activityRepository.findById(activity2.getId());
        assertEquals(activity2.getId(), optional.get().getId());
        assertEquals(activity2.getActivityName(), optional.get().getActivityName());
    }

    @Test
    public void givenIdToDeleteThenShouldDeleteTheActivity() {
        Activity activity = Activity.builder().activityDate("2021").activityName("name").activityType("cycling").distance(15.00).time("20.00").build();
        activityRepository.save(activity);
        activityRepository.deleteById(activity.getId());
        Optional optional = activityRepository.findById(activity.getId());
        assertEquals(Optional.empty(), optional);
    }


    
}
