package com.smartmetro.service;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;

import java.util.List;

public interface UserService {
    public List<UserTO> findAllUsers() throws UserNotFoundException;
}
