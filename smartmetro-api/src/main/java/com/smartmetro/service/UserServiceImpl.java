package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.UserTO;
import com.smartmetro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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
            log.error("User are not Found");
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
            Set<MetroCardTO> metroCardTOSet = user.getMetroCards().stream().map(metroCard -> {
                MetroCardTO metroCardTO = new MetroCardTO();
                metroCardTO.setCardId(metroCard.getCardId());
                metroCardTO.setCardNumber(metroCard.getCardNumber());
                metroCardTO.setBalance(metroCard.getBalance());
                metroCardTO.setIssuedDate(metroCard.getIssuedDate());
                metroCardTO.setStatus(metroCard.getStatus());
                return metroCardTO;
            }).collect(Collectors.toSet());
            userTO.setMetroCards(metroCardTOSet);

            return userTO;
        }).toList();
        log.info("Total Users Found: {}", userTOS.size());
        return userTOS;
    }

    @Override
    public UserTO findUserByID(int id) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findUserByID");
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()) {
            log.error("Users are not Found");
            throw new UserNotFoundException("Users are Empty");
        }
        User user=users.get();

        UserTO userTO = new UserTO();
        userTO.setUserId(user.getUserId());
        userTO.setUserName(user.getUserName());
        userTO.setPassword(user.getPassword());
        userTO.setPhone(user.getPhone());
        userTO.setEmail(user.getEmail());
        userTO.setRole(user.getRole());
        userTO.setDate(user.getDate());

        if(user.getMetroCards() != null) {
            Set<MetroCardTO> metroCardTOSet = user.getMetroCards().stream().map(metroCard -> {
                        MetroCardTO metroCardTO = new MetroCardTO();
                        metroCardTO.setCardId(metroCard.getCardId());
                        metroCardTO.setCardNumber(metroCard.getCardNumber());
                        metroCardTO.setBalance(metroCard.getBalance());
                        metroCardTO.setIssuedDate(metroCard.getIssuedDate());
                        metroCardTO.setStatus(metroCard.getStatus());
                        return metroCardTO;
                    })
                    .collect(Collectors.toSet());
            userTO.setMetroCards(metroCardTOSet);
        }
        return userTO;
    }

    @Override
    public List<UserTO> findUserByName(String name) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findAllUsers");
        List<User> users = userRepository.findByName(name);
        if(users.isEmpty()) {
            log.error("User are not Found");
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
            Set<MetroCardTO> metroCardTOSet = user.getMetroCards().stream().map(metroCard -> {
                MetroCardTO metroCardTO = new MetroCardTO();
                metroCardTO.setCardId(metroCard.getCardId());
                metroCardTO.setCardNumber(metroCard.getCardNumber());
                metroCardTO.setBalance(metroCard.getBalance());
                metroCardTO.setIssuedDate(metroCard.getIssuedDate());
                metroCardTO.setStatus(metroCard.getStatus());
                return metroCardTO;
            }).collect(Collectors.toSet());
            userTO.setMetroCards(metroCardTOSet);

            return userTO;
        }).toList();
        log.info("Total Users Found: {}", userTOS.size());
        return userTOS;
    }

    @Override
    public String deleteUserByID(int id) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findUserByID");
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()) {
            log.error("Users are not Found");
            throw new UserNotFoundException("Users are Empty");
        }
            userRepository.deleteById(id);
            return "User Detailes are Deleted";

    }

}
