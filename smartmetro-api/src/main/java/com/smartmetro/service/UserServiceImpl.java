package com.smartmetro.service;

import com.smartmetro.entity.User;
import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.MetroCardTO;
import com.smartmetro.model.UserRequest;
import com.smartmetro.model.UserTO;
import com.smartmetro.model.UserUpdateRequest;
import com.smartmetro.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (users == null || users.isEmpty()) {
            log.error("Users are not found");
            throw new UserNotFoundException("Users are empty");
        }

        List<UserTO> userTOS = users.stream().map(user -> {
            UserTO userTO = new UserTO();
            userTO.setUserId(user.getUserId());
            userTO.setUserName(user.getUserName());
            userTO.setPassword(user.getPassword());
            userTO.setPhone(user.getPhone());
            userTO.setEmail(user.getEmail());
            userTO.setRole(user.getRole());
            userTO.setDate(user.getDate());

            // Check user (the database entity), not userTO
            if (user.getMetroCards() != null) {
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
            }

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
    public String deleteUserById(int id) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.findUserByID");
        Optional<User> users = userRepository.findById(id);
        if(users.isEmpty()) {
            log.error("Users are not Found");
            throw new UserNotFoundException("Users are Empty");
        }
            userRepository.deleteById(id);
            return "User Detailes are Deleted";

    }

    @Override
    public String deleteUserByName(String name) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.deleteUserByName");
        List<User> user = userRepository.findByName(name);
        if(user.isEmpty()||user ==null) {
            log.error("Users are not Found");
            throw new UserNotFoundException("Users are Empty");
        }
        userRepository.deleteByName(name);
        return "User Data are Deleted  ";
    }

    @Override
    public int save(UserRequest userRequest) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.save,user={}", userRequest);
        User user1 =new User();
        if(userRequest.getUserName()!=null) {
            user1.setUserName(userRequest.getUserName());
        }
        if(userRequest.getPassword()!=null) {
            user1.setPassword(userRequest.getPassword());
        }
        if(userRequest.getEmail()!=null) {
            user1.setEmail(userRequest.getEmail());
        }
        if(userRequest.getRole()!=null) {
            user1.setRole(userRequest.getRole());
        }
        if(userRequest.getDate()!=null) {
            user1.setDate(userRequest.getDate());
        }
        if(userRequest.getPhone()!=null) {
            user1.setPhone(userRequest.getPhone());
        }
        User savedUser = userRepository.save(user1);
        if(savedUser==null) {
            log.error("Users are not Saved");
            throw new UserNotFoundException("Users are Empty");
        }
        return savedUser.getUserId();
    }

    @Override
    //@Transactional
    public int update(int userId, UserUpdateRequest userTO) throws UserNotFoundException {
        log.info("Inside the UserServiceImpl.update, userId={}, userTO={}", userId, userTO);
        Optional<User> savedUser = userRepository.findById(userId);
        if(savedUser.isEmpty()) {
            log.error("Users are not Saved");
            throw new UserNotFoundException("Users are Empty");
        }
        User user=savedUser.get();
        if(user.getUserId()!=null){
            user.setUserId(user.getUserId());
        }
        if(user.getUserName()!=null) {
            user.setUserName(user.getUserName());
        }
        if(userTO.getPassword()!=null) {
            userTO.setPassword(userTO.getPassword());
        }
        if(userTO.getEmail()!=null) {
            userTO.setEmail(userTO.getEmail());
        }
        if(userTO.getPhone()!=null) {
            userTO.setPhone(userTO.getPhone());
        }
        User update = userRepository.save(user);
        log.info("User updated successfully with id: {}", update.getUserId());

        return update.getUserId();
    }

}
