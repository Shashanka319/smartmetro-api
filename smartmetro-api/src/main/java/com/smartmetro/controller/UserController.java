package com.smartmetro.controller;

import com.smartmetro.exception.UserNotFoundException;
import com.smartmetro.model.UserTO;
import com.smartmetro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<UserTO> getUserById(@PathVariable Integer id) {
        log.info("Inside the getUserById method");
        UserTO userTO=null;
        try {
            userTO=userService.findUserByID(id);
        }catch (UserNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userTO,HttpStatus.OK);
    }

    @GetMapping("/userName")
    public ResponseEntity<List<UserTO>> getUserByName(@RequestParam String userName) {
        log.info("Inside the getUserByName method");
        List<UserTO> userTO=null;
        try {
            userTO=userService.findUserByName(userName);
        }catch (UserNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userTO,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <String>deleteUserById(@PathVariable Integer id) {
        log.info("Inside the deleteUserById method");
        String userName=null;
        try{
            userName=userService.deleteUserById(id);
        }catch (UserNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userName,HttpStatus.OK);
    }
    @DeleteMapping("/name")
    public ResponseEntity<String> deleteUserByName(@RequestParam String userName) {
        log.info("Inside the deleteUserByName method");
        String name=null;
        try{
            name=userService.deleteUserByName(userName);
        }catch (UserNotFoundException e){
            log.error("User not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Exception Occured Check Once");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

}
