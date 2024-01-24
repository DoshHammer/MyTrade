package com.stackroute.Authentication.AuthTestController;

import com.stackroute.Authentication.Controller.AuthController;
import com.stackroute.Authentication.Entity.UserInfo;
import com.stackroute.Authentication.Exception.UserNotFoundException;
import com.stackroute.Authentication.Services.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
public class TestController {

    @Autowired
    private AuthController userController;

    @MockBean
    private AuthService userService;


    @Test
    void testLoginUser() throws UserNotFoundException {

        AuthController userController = new AuthController();

        UserInfo user = new UserInfo();
        user.setPassword(null);
        user.setUsername(null);
        assertThrows(UserNotFoundException.class, () -> userController.login(user));
    }


    @Test
    void testLoginUser2() throws UserNotFoundException {


        AuthController userController = new AuthController();

        UserInfo user = new UserInfo();
        user.setPassword(null);
        user.setUsername("foo");
        assertThrows(UserNotFoundException.class, () -> userController.login(user));
    }


//    @Test
//    void testLoginUser3() throws UserNotFoundException {
//
//
//        AuthController userController = new AuthController();
//        UserInfo user = mock(UserInfo.class);
//        when(user.getPassword()).thenThrow(new UserNotFoundException("An error occurred"));
//        when(user.getUsername()).thenReturn("any");
//        doNothing().when(user).setPassword(Mockito.<String>any());
//        doNothing().when(user).setUsername(Mockito.<String>any());
//        user.setPassword("abc");
//        user.setUsername("qwert");
//        assertThrows(UserNotFoundException.class, () -> userController.login(user));
//        verify(user).getPassword();
//        verify(user).getUsername();
//        verify(user).setPassword(Mockito.<String>any());
//        verify(user).setUsername(Mockito.<String>any());
//    }
    @Test
    void testLoginUser_() throws UserNotFoundException {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("any");
        userInfo.setUsername("any");
        Mockito.when(userService.login(userInfo.getUsername(), userInfo.getPassword())).thenReturn(true);

        userController.login(userInfo);
    }
    @Test
    void testLoginUser_for_false() throws UserNotFoundException {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("any");
        userInfo.setUsername("any");
        Mockito.when(userService.login(userInfo.getUsername(), userInfo.getPassword())).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class,()->userController.login(userInfo));
    }

}
