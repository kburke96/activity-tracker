// package com.kevin.activitytracker;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.kevin.activitytracker.Controllers.HomeController;
// import com.kevin.activitytracker.Model.Activity;
// import com.kevin.activitytracker.Repository.ActivityRepository;
// import com.kevin.activitytracker.Service.ActivityService;

// import org.junit.Before;
// // import org.assertj.core.util.Arrays;
// import org.junit.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.context.TestConfiguration;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Sort;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.hamcrest.Matchers.*;

// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

// import org.junit.runner.RunWith;

// @RunWith(SpringRunner.class)
// @WebMvcTest(HomeController.class)
// public class HomeControllerTest {

//     // @TestConfiguration
//     // static class ActivityServiceTestContextConfiguration {
 
//     //     @Bean
//     //     public ActivityService activityService() {
//     //         return new ActivityService();
//     //     }
//     // }

//     @Autowired
//     MockMvc mockMvc;

//     @Autowired
//     ObjectMapper objectMapper;

//     // @Autowired
//     // ActivityService activityService;

//     @MockBean
//     HomeController controller;

//     static Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running").activityDate("2021").time("15:00").distance(15.00).build();
//     static Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling").activityDate("2021").time("5:00").distance(5.00).build();
//     static Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking").activityDate("2021").time("125:00").distance(125.00).build();
//     static List<Activity> activities = new ArrayList<>(Arrays.asList(activity1, activity2, activity3));


//     // @Before
//     // public void setUp() {
//     //     Mockito.when(repo.findAll()).thenReturn(activities);
//     // }


//     @Test
//     public void getAllActivities_success() throws Exception {
        
//         PageRequest pageable = PageRequest.of(1, 10, Sort.by("activityName"));
//         PageImpl<Activity> returnedActivities = new PageImpl<>(activities, pageable, 15);

        
//         // Mockito.when(activityService.getAllActivities(pageable)).thenReturn(returnedActivities);

//         Mockito.when(controller.getAllActivities(1, 10, "activityType")).thenReturn(returnedActivities);

//         this.mockMvc.perform(MockMvcRequestBuilders
//             .get("/activities")
//             .contentType(MediaType.APPLICATION_JSON))
//             .andExpect(status().isOk())
//             .andExpect(jsonPath("$", hasSize(3)))
//             .andExpect(jsonPath("$[2].activityName", is("Second name")));

//         // RequestBuilder rb = MockMvcRequestBuilders.get("/activities").accept(MediaType.APPLICATION_JSON);

//         // MvcResult result = mockMvc.perform(rb).andReturn();

//         // System.out.println(result.getResponse());

//         // String expected = "{id"
        
//     }

// }
