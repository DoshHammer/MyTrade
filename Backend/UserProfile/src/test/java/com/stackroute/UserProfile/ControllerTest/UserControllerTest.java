package com.stackroute.UserProfile.ControllerTest;

import com.stackroute.UserProfile.Controller.UserController;
import com.stackroute.UserProfile.Entity.UserEntity;
import com.stackroute.UserProfile.Exception.UserAlreadyExistsById;
import com.stackroute.UserProfile.Exception.UserNotFoundException;
import com.stackroute.UserProfile.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    UserEntity testEntity;

    @BeforeEach
    public void setUp() {
        testEntity = new UserEntity();
        testEntity.setUserId(1);
        testEntity.setUsername("qwerty");
        testEntity.setPassword("paass");
        testEntity.setEmail("test@tester.in");
    }

    @Test
    public void testCreateUser() throws UserAlreadyExistsById {

        when(userService.createUser(any(UserEntity.class))).thenReturn(testEntity);

        ResponseEntity<UserEntity> responseEntity = userController.createUser(testEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(testEntity, responseEntity.getBody());
        verify(userService, times(1)).createUser(testEntity);

    }

    @Test
    public void testGetAllUser() {

        when(userService.getAllUsers()).thenReturn(Arrays.asList(testEntity));

        ResponseEntity<List<UserEntity>> responseEntity = userController.getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Arrays.asList(testEntity), responseEntity.getBody());
        verify(userService, times(1)).getAllUsers();

    }

    @Test
    public void testGetUserById() throws UserNotFoundException{

        when(userService.getUserById(anyInt())).thenReturn(testEntity);

        ResponseEntity<UserEntity> responseEntity = userController.getUserById(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testEntity, responseEntity.getBody());
        verify(userService, times(1)).getUserById(1);

    }

    @Test
    public void tesUpdateUser() throws UserNotFoundException {

        when(userService.updateUser(any(UserEntity.class))).thenReturn(testEntity);

        ResponseEntity<UserEntity> responseEntity = userController.updateUser(testEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testEntity, responseEntity.getBody());
        verify(userService, times(1)).updateUser(testEntity);

    }
}
