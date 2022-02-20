package com.kevin.activitytracker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.kevin.activitytracker.controller.AuthController;
import com.kevin.activitytracker.model.ERole;
import com.kevin.activitytracker.model.Role;
import com.kevin.activitytracker.model.User;
import com.kevin.activitytracker.repository.RoleRepository;
import com.kevin.activitytracker.repository.UserRepository;
import com.kevin.activitytracker.security.jwt.AuthEntryPointJwt;
import com.kevin.activitytracker.security.jwt.JwtUtils;
import com.kevin.activitytracker.security.services.UserDetailsServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Optional;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
	AuthenticationManager authenticationManager;

	@MockBean
    UserRepository userRepository;

	@MockBean
	RoleRepository roleRepository;

	@MockBean
	PasswordEncoder encoder;

	@MockBean
    JwtUtils jwtUtils;

    @MockBean
    UserDetailsServiceImpl udsImpl;

    @MockBean
    private AuthEntryPointJwt aepJwt;

    User user1 = new User();

    @Test
    void signUpWithValidUserTest() throws Exception {
        
        user1.setEmail("somenewemail1@gmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithoutRoles());

        String expectedResult = "{\"message\":\"User registered successfully!\"}";

        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(expectedResult));
    }


    @Test
    void signUpWithInvalidEmailTest() throws Exception {
        
        user1.setEmail("somenewemagmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithInvalidEmailWithoutRoles());

        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().is4xxClientError());
    }

    @Test
    void signUpWithAlreadyUsedEmailTest() throws Exception {
        
        user1.setEmail("somenewemail@gmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(true);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithoutRoles());

        String expectedContent = "{\"message\":\"Error: Email address is already in use\"}";
        
        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(content().string(expectedContent));
    }

    @Test
    void signUpWithAlreadyUsedUsernameTest() throws Exception {
        
        user1.setEmail("somenewemail@gmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(true);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_USER)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithoutRoles());

        String expectedContent = "{\"message\":\"Error: Username is already taken\"}";
        
        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().is4xxClientError())
            .andExpect(content().string(expectedContent));
    }

    @Test
    void signUpWithAdminRoleTest() throws Exception {
        
        user1.setEmail("somenewemail@gmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_ADMIN)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithAdminRole());

        String expectedContent = "{\"message\":\"User registered successfully!\"}";
        
        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(expectedContent));
    }

    @Test
    void signUpWithModRoleTest() throws Exception {
        
        user1.setEmail("somenewemail@gmail.com");
        user1.setUsername("somesernameawgr");
        user1.setPassword("ergsehs33243");

        when(userRepository.existsByEmail(any())).thenReturn(false);
        when(userRepository.existsByUsername(any())).thenReturn(false);
        when(roleRepository.findByName(any())).thenReturn(Optional.of(new Role(ERole.ROLE_MODERATOR)));

        when(userRepository.save(any())).thenReturn(user1);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getUserJsonWithModAndUserRoles());

        String expectedContent = "{\"message\":\"User registered successfully!\"}";
        
        this.mockMvc.perform(builder)
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(expectedContent));
    }


    private String getUserJsonWithoutRoles() {
        return "{\"username\": \"" + "someuser" + "\",\"email\": \"" + "test@gmail.com" + "\",\"password\": \"" + "securepassword" + "\"}";
    }

    private String getUserJsonWithInvalidEmailWithoutRoles() {
        return "{\"username\": \"" + "someuser" + "\",\"email\": \"" + "tesgmail.com" + "\",\"password\": \"" + "securepassword" + "\"}";
    }

    private String getUserJsonWithModAndUserRoles() {
        return "{\"username\": \"" + "anotheruser" + "\",\"email\": \"" + "email@gmail.com" + "\",\"password\": \"" + "password" + "\",\"role\": [\"mod\", \"user\"]}";
    }

    private String getUserJsonWithAdminRole() {
        return "{\"username\": \"" + "anotheruser" + "\",\"email\": \"" + "email@gmail.com" + "\",\"password\": \"" + "password" + "\",\"role\": [\"admin\"]}";
    }
}
