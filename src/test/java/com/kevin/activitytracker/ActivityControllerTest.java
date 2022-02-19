package com.kevin.activitytracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kevin.activitytracker.controllers.HomeController;
import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.security.jwt.AuthEntryPointJwt;
import com.kevin.activitytracker.security.jwt.JwtUtils;
import com.kevin.activitytracker.security.services.UserDetailsServiceImpl;
import com.kevin.activitytracker.service.ActivityService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeController.class)
public class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @MockBean
    private UserDetailsServiceImpl udsImpl;

    @MockBean
    private AuthEntryPointJwt aepJwt;

    @MockBean
    private JwtUtils jwtUtils;

    Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running")
            .activityDate("2021").time("15:00").distance(15.00).build();
    Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling")
            .activityDate("2021").time("5:00").distance(5.00).build();
    Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking")
            .activityDate("2021").time("125:00").distance(125.00).build();

    PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));

    List<Activity> listOfActivities = Stream.of(activity1, activity2, activity3).collect(Collectors.toList());

    Page<Activity> allActivitiesPage = new PageImpl<>(listOfActivities);
    
    @WithMockUser(value = "spring")
    @Test
    void getAllActivitiesTest() throws Exception {
        when(activityService.getAllActivities(pageable)).thenReturn(allActivitiesPage);
        String expectedContent = "{\"content\":[{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2021\"},{\"id\":2,\"activityType\":\"Cycling\",\"activityName\":\"Second name\",\"time\":\"5:00\",\"distance\":5.0,\"activityDate\":\"2021\"},{\"id\":3,\"activityType\":\"Hiking\",\"activityName\":\"Third name\",\"time\":\"125:00\",\"distance\":125.0,\"activityDate\":\"2021\"}]";
        MvcResult result = this.mockMvc.perform(get("/activities"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains(expectedContent));
    }

    @WithMockUser(value = "spring")
    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/activities").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
    
}
