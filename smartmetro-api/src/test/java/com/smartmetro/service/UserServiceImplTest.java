package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void testGetAllUsers_whenUserExist_thenReturnData() throws UserNotFoundException {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserId(1);
        user.setUserName("testname");
        user.setPassword("testpassword");
        user.setEmail("testemail");
        user.setRole("testrole");
        userList.add(user);

        User user1 = new User();
        user1.setUserId(2);
        user1.setUserName("name");
        user1.setPassword("password");
        user1.setEmail("email");
        user1.setRole("role");
        userList.add(user1);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserTO> userTOS = userServiceImpl.findAllUsers();
        assertEquals(2, userTOS.size());
    }

    @Test
    public void testGetAllUsers_whenUserNotExist_thenThrowException() {
        List<User> userList = null;
        when(userRepository.findAll()).thenReturn(userList);

        assertThrows(UserNotFoundException.class, ()->userServiceImpl.findAllUsers());
    }

//  Testing of userbyid when exist

    @Test
    public void testGetUserById_whenUserExist_thenReturnData() throws UserNotFoundException {
        User user = new User();
        user.setUserId(1);
        user.setUserName("testname");
        user.setPassword("testpassword");
        user.setEmail("testemail");
        user.setRole("testrole");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserTO userTO = userServiceImpl.findUserByID(1);
        assertEquals(1, userTO.getUserId());
        assertEquals("testname", userTO.getUserName());
        assertEquals("testpassword", userTO.getPassword());
        assertEquals("testemail", userTO.getEmail());
        assertEquals("testrole", userTO.getRole());

    }

    @Test
    public void testGetUserById_whenUserNotExist_thenThrowException() {
        User user = null;
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()->userServiceImpl.findUserByID(1));
    }

    // Testing of UserByName
   // @Test
    public void testUserByName_whenUserExist_thenReturnData() throws UserNotFoundException {
        User user = new User();
        user.setUserName("testname");
        user.setPassword("testpassword");
        user.setEmail("testemail");
        user.setRole("testrole");


    }
    @Test
    public void testSaveUser_whenUserExist_thenReturnData() throws UserNotFoundException {
        UserRequest ur = new UserRequest();
        ur.setUserId(1);
        ur.setUserName("testname");
        ur.setPassword("testpassword");
        ur.setEmail("testemail");
        ur.setRole("testrole");
        ur.setPhone(987968686l);

        User user = new User();
        user.setUserId(1);
        user.setUserName("testname");
        user.setPassword("testpassword");
        user.setEmail("testemail");
        user.setRole("testrole");
        ur.setPhone(987968686l);

        when(userRepository.save(any())).thenReturn(user);
        int userId= userServiceImpl.save(ur);
        assertEquals(1,userId);

    }
}
