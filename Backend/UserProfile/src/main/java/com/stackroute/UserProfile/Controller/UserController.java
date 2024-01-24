package com.stackroute.UserProfile.Controller;

import com.stackroute.UserProfile.Entity.UserEntity;
import com.stackroute.UserProfile.Exception.UserAlreadyExistsById;
import com.stackroute.UserProfile.Exception.UserNotFoundException;
import com.stackroute.UserProfile.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws UserAlreadyExistsById {
        logger.info("New User details received successfully");
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/viewAllUser")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        logger.info("User Info displayed successfully");
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/viewUser/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        logger.info("Details fetched for UserId: ", id);
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) throws UserNotFoundException {
        logger.info("Details of user updated successfully");
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }
}
