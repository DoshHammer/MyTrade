package com.stackroute.Authentication.Services;

import com.stackroute.Authentication.Entity.UserInfo;
import com.stackroute.Authentication.Repo.UserRepo;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    /**
     * @param user 
     * @return
     */
    @Override
    public UserInfo fetchUserDetails(UserInfo user) {
        Optional<UserInfo> userinfo=userRepo.findById(user.getUsername());
        if(userinfo.isEmpty()) {
            logger.info("Details available for User: ", userinfo);
            return	userRepo.save(user);
        } else {
            logger.debug("The Fields are Empty");
            return null;
        }
    }

    /**
     * @param username 
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        Optional<UserInfo> user= userRepo.findByUsernameAndPassword(username, password);
        if(user.isPresent()) {
            logger.info("Successfully logged in User: ", username);
            return true;
        } else {
            logger.error("UserName or the password is invalid or not matching");
            return false;
        }
    }
}
