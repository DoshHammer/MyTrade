package com.stackroute.Authentication.Services;

import com.stackroute.Authentication.Entity.UserInfo;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserInfo fetchUserDetails(UserInfo user);

    boolean login(String username,String password);
}
