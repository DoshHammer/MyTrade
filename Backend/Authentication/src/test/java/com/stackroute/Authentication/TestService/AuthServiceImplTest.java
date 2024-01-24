package com.stackroute.Authentication.TestService;

import com.stackroute.Authentication.Entity.UserInfo;
import com.stackroute.Authentication.Repo.UserRepo;
import com.stackroute.Authentication.Services.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    UserRepo userRepo;

    @InjectMocks
    AuthServiceImpl authService;


    @Test
    public void login_validCredentials_returnsTrue() {
        String username = "newUser";
        String password = "userPass";
        UserInfo user = new UserInfo(username, password);
        when(userRepo.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(user));

        boolean result = authService.login(username, password);

        assertTrue(result);
    }

    @Test
    public void login_invalidCredentials_returnsFalse() {
        String username = "invalidUser";
        String password = "invalidPassword";
        when(userRepo.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        boolean result = authService.login(username, password);

        assertFalse(result);
    }
}
