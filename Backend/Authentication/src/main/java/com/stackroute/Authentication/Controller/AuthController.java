package com.stackroute.Authentication.Controller;

import com.stackroute.Authentication.Config.JwtTokenGenerator;
import com.stackroute.Authentication.Entity.UserInfo;
import com.stackroute.Authentication.Exception.UserNotFoundException;
import com.stackroute.Authentication.Services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/authorize")
@CrossOrigin("*")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity fetchUserDetails(@RequestBody UserInfo user) {
//        UserInfo userdata=authService.fetchUserDetails(user);
//        if (userdata!=null) {
//            return new ResponseEntity<UserInfo>(user, HttpStatus.CREATED);
//        }
//        else {
//            return new ResponseEntity<String>("duplicate id", HttpStatus.CONFLICT);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInfo user) throws UserNotFoundException {

        if (user.getUsername() == null||user.getPassword() == null) {
            logger.debug("The UserName & Password are NULL!");
            throw new UserNotFoundException("UserId and Password are null");
        }

        boolean result=authService.login(user.getUsername(), user.getPassword());

        if (result == false) {
            logger.error("The entered username & Password are not available with us.");
            throw new UserNotFoundException("UserName and password mismatch");
        }

        if(result) {
            Map<String, String> token= new JwtTokenGenerator().generateToken(user);
            logger.info("JWT Token Generated and Login Successful");
            return new ResponseEntity<Map>(token,HttpStatus.OK);
        }
        else {
            logger.error("The Username or Password is invalid");
            return new ResponseEntity("Invalid user", HttpStatus.UNAUTHORIZED);
        }
    }
}
