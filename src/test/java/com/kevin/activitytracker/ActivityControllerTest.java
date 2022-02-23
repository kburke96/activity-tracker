package com.kevin.activitytracker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kevin.activitytracker.controller.ActivityController;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    Activity activity1 = Activity.builder().id(1L).activityName("First name").activityType("Running")
            .activityDate(LocalDate.parse("01/01/2020", formatter)).time("15:00").distance(15.00).build();
    Activity activity2 = Activity.builder().id(2L).activityName("Second name").activityType("Cycling")
            .activityDate(LocalDate.parse("13/04/2020", formatter)).time("5:00").distance(5.00).build();
    Activity activity3 = Activity.builder().id(3L).activityName("Third name").activityType("Hiking")
            .activityDate(LocalDate.parse("21/10/2020", formatter)).time("125:00").distance(125.00).build();

    PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));

    List<Activity> listOfActivities = Stream.of(activity1, activity2, activity3).collect(Collectors.toList());
    List<Activity> listOfRunningActivities = Stream.of(activity1).collect(Collectors.toList());

    Page<Activity> allActivitiesPage = new PageImpl<>(listOfActivities);
    
    @WithMockUser(value = "spring")
    @Test
    void getAllActivitiesTest() throws Exception {
        when(activityService.getAllActivities(pageable)).thenReturn(allActivitiesPage);
        String expectedContent = "{\"content\":[{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-01-01\"},{\"id\":2,\"activityType\":\"Cycling\",\"activityName\":\"Second name\",\"time\":\"5:00\",\"distance\":5.0,\"activityDate\":\"2020-04-13\"},{\"id\":3,\"activityType\":\"Hiking\",\"activityName\":\"Third name\",\"time\":\"125:00\",\"distance\":125.0,\"activityDate\":\"2020-10-21\"}]";
        MvcResult result = this.mockMvc.perform(get("/activities"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains(expectedContent));
    }

    @WithMockUser(value = "spring")
    @Test
    void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mockMvc.perform(get("/activities").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
    
    @WithMockUser(value = "spring")
    @Test
    void getByActivityTypeTest() throws Exception {
        when(activityService.getByType("Running")).thenReturn(listOfRunningActivities);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/activities?activityType=Running")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        
        MvcResult result = this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        String expectedContent = "{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-01-01\"}";
        assertTrue(result.getResponse().getContentAsString().contains(expectedContent));
    }

    @WithMockUser(value = "spring")
    @Test
    void getByActivityIdTest() throws Exception {
        when(activityService.getById(1L)).thenReturn(activity1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/activities?id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        
        MvcResult result = this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        String expectedContent = "{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-01-01\"}";
        assertTrue(result.getResponse().getContentAsString().contains(expectedContent));
    }


    @WithMockUser(value = "spring")
    @Test
    void putNewActivityTest() throws Exception {
        when(activityService.insertActivity(any())).thenReturn(activity1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(activity1.toJsonString());
        
        String expectedContent = "{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-01-01\"}";

        MvcResult result = this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        assertTrue(result.getResponse().getContentAsString().contains(expectedContent));
    }

    @WithMockUser(value = "spring")
    @Test
    void deleteActivityTest() throws Exception {
        when(activityService.deleteActivity(any())).thenReturn(activity1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/activities/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        
        String expectedContent = "{\"id\":1,\"activityType\":\"Running\",\"activityName\":\"First name\",\"time\":\"15:00\",\"distance\":15.0,\"activityDate\":\"2020-01-01\"}";

        MvcResult result = this.mockMvc.perform(builder)
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        assertTrue(result.getResponse().getContentAsString().contains(expectedContent));
    }
}
