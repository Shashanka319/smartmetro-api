package com.smartmetro.service;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.model.UserUpdateRequest;

import java.util.List;

public interface UserService {
    public List<UserTO> findAllUsers() throws UserNotFoundException;
    public UserTO findUserByID(int id) throws UserNotFoundException;
    public List<UserTO> findUserByName(String name) throws UserNotFoundException;
    public String deleteUserById(int id) throws UserNotFoundException;
    public String deleteUserByName(String name) throws UserNotFoundException;
    public int save(UserRequest userRequest) throws UserNotFoundException;
    public int update(int userId, UserUpdateRequest userUpdateRequest) throws UserNotFoundException;
}
