package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserTO> findAllUsers() throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findAllUsers");
        List<User> users = userRepository.findAll();
        if(users.isEmpty()) {
            log.error("Users are not Found");
            throw new UserNotFoundException("Users are Empty");
        }
        List<UserTO> userTOS= users.stream().map(user -> {
            UserTO userTO = new UserTO();
            userTO.setUserId(user.getUserId());
            userTO.setUserName(user.getUserName());
            userTO.setPassword(user.getPassword());
            userTO.setEmail(user.getEmail());
            userTO.setRole(user.getRole());
            userTO.setDate(user.getDate());
            return userTO;

        }).toList();
        log.info("Total Users Found: {}", userTOS.size());
        return userTOS;
    }
}
