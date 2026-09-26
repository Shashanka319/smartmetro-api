package com.smartmetro.controller;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping                                 //Read the Data Source
    public ResponseEntity<List<UserTO>> getAllUsers() {
        log.info("Inside the getAllUsers method");
        List<UserTO> userTo=null;
        try{
            userTo=userService.findAllUsers();
        }
        catch (UserNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//400 error
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 error
        }
        return new ResponseEntity<>( userTo,HttpStatus.OK);

    }
}
