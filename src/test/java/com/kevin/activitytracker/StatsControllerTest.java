package com.kevin.activitytracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.kevin.activitytracker.controller.StatsController;
import com.kevin.activitytracker.model.Activity;
import com.kevin.activitytracker.security.jwt.AuthEntryPointJwt;
import com.kevin.activitytracker.security.jwt.JwtUtils;
import com.kevin.activitytracker.security.services.UserDetailsServiceImpl;
import com.kevin.activitytracker.service.StatsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebMvcTest(StatsController.class)
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @MockBean
    private UserDetailsServiceImpl udsImpl;

    @MockBean
    private AuthEntryPointJwt aepJwt;

    @MockBean
    private JwtUtils jwtUtils;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running")
            .activityDate(LocalDate.parse("21/10/2020", formatter)).time("15:00").distance(15.00).build();
    Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling")
            .activityDate(LocalDate.parse("12/11/2020", formatter)).time("5:00").distance(5.00).build();
    Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking")
            .activityDate(LocalDate.parse("03/05/2020", formatter)).time("125:00").distance(125.00).build();
    Activity activity4 = Activity.builder().id(4L).activityName("Another activity").activityType("Running")
            .activityDate(LocalDate.parse("06/06/2020", formatter)).time("00:15:00").distance(15.00).build();

    List<Activity> activityList = Stream.of(activity1, activity2, activity3, activity4).collect(Collectors.toList());
    List<Activity> runningActivities = Stream.of(activity1, activity4).collect(Collectors.toList());

    @Test
    @WithMockUser(value = "spring")
    void getTotalActivityTimeByMonthTest() throws Exception {
        when(statsService.getActivityHoursInMonth(anyInt(), anyString())).thenReturn(10L);

        MvcResult result = this.mockMvc.perform(get("/stats/time/2020/aug"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        
        assertEquals(String.valueOf(10L), result.getResponse().getContentAsString());
    }

    @Test
    @WithMockUser(value = "spring")
    void getActivitiesByTypeInMonthTest() throws Exception {
        when(statsService.getActivitiesByTypeInMonth(anyString(), anyInt(), anyString())).thenReturn(runningActivities);

        MvcResult result = this.mockMvc.perform(get("/stats/2020/aug?activityType=Running"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        
        String expected =  "[{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-10-21\"},{\"id\":4,\"activityType\":\"Running\",\"activityName\":\"Another activity\",\"time\":\"00:15:00\",\"distance\":15.0,\"activityDate\":\"2020-06-06\"}]";
        assertTrue(result.getResponse().getContentAsString().contains(expected));
    }
}
