package com.smartmetro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Instantiate directly to avoid NoSuchBeanDefinitionException
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private UserService userService;

    //getalluser positive

    @Test
    public void getAllUsersTest() throws Exception {
        List<UserTO> userTOList = new ArrayList<>();
        UserTO userTO = new UserTO(1, "name", "email", 8686868685L, "name123", "role", null, null);
        userTOList.add(userTO);

        when(userService.findAllUsers()).thenReturn(userTOList);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }
    //getalluser namgetive
    @Test
    public void getAllUsersTest2() throws Exception {
        when(userService.findAllUsers()).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isInternalServerError());
    }

    //GetUserById positive

    @Test
    public void getUserByIdTest() throws Exception {
        UserTO userTO = new UserTO(1, "name", "email", 8686868685L, "name123", "role", null, null);
        when(userService.findUserByID(1)).thenReturn(userTO);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk());
    }

    //GetUserById Nagative

    @Test
    public void getUserByIdTest2() throws Exception {
        when(userService.findUserByID(1)).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isInternalServerError());
    }

    //GetUserByName positive

    @Test
    public void getUserByNameTest() throws Exception {
        UserTO userTO = new UserTO(1, "name", "email", 8686868685L, "name123", "role", null, null);
        when(userService.findUserByName("name")).thenReturn(Collections.singletonList(userTO));

        mockMvc.perform(get("/api/v1/users").param("userName", "name"))
                .andExpect(status().isOk());
    }
    //GetUserByName Nagative
    @Test
    public void getUserByNameTest2() throws Exception {
        when(userService.findUserByName("name")).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/api/v1/users/userName").param("userName", "name"))
                .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError());
    }

    //Save User positive
    @Test
    public void saveUserTest() throws Exception {
      UserRequest userRequest = new UserRequest();
      userRequest.setUserId(1);
      userRequest.setUserName("name");
      userRequest.setPassword("password");
      userRequest.setEmail("email");
      userRequest.setRole("role");
      UserTO userTO = new UserTO(1, "name", "email", 8686868685L, "name123", "role", null, null);
      mockMvc.perform(post("/api/v1/users")
      .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(userRequest))).andExpect(status().isCreated());

    }

    //Save User negative
    @Test
    public void saveUserTest2() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserId(1);
        userRequest.setUserName("name");
        userRequest.setPassword("password");
        userRequest.setEmail("email");
        userRequest.setRole("role");

        when(userService.save(any(UserRequest.class))).thenThrow(new RuntimeException("Database error or Duplicate user Data Information"));

        mockMvc.perform(post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userRequest))).andExpect(status().isInternalServerError());

    }
}