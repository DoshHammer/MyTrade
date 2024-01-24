package com.stackroute.UserProfile.ServiceTest;

import com.stackroute.UserProfile.Entity.UserEntity;
import com.stackroute.UserProfile.Exception.UserAlreadyExistsById;
import com.stackroute.UserProfile.Exception.UserNotFoundException;
import com.stackroute.UserProfile.Repo.UserRepo;
import com.stackroute.UserProfile.Services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    UserRepo userRepository;

    @Mock
    KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        // ...
    }

    @Test
    public void createUser_validUser_returnsSavedUser() throws UserAlreadyExistsById {
        UserEntity user = new UserEntity(501, "Joker", "password", "joker@joke.com");
        when(userRepository.save(user)).thenReturn(user);

        UserEntity savedUser = userService.createUser(user);

        assertEquals(user, savedUser);
    }

    @Test
    public void createUser_existingUserId_throwsException() {
        UserEntity user = new UserEntity(101, "test", "root", "test@mail.com");
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsById.class, () -> userService.createUser(user));
    }

    @Test
    public void getAllUsers_usersExist_returnsListOfUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity(1, "John Doe", "password", "john.doe@example.com"));
        users.add(new UserEntity(2, "Jane Doe", "password", "jane.doe@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> retrievedUsers = userService.getAllUsers();

        assertEquals(users, retrievedUsers);
    }

    @Test
    public void getUserById_validId_returnsUser() throws UserNotFoundException {
        int userId = 1;
        UserEntity user = new UserEntity(userId, "John Doe", "password", "john.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserEntity retrievedUser = userService.getUserById(userId);

        assertEquals(user, retrievedUser);
    }

    @Test
    public void getUserById_invalidId_throwsException() {
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    public void updateUser_validUser_returnsUpdatedUser() throws UserNotFoundException {
        int userId = 1;
        UserEntity user = new UserEntity(userId, "John Doe", "password", "john.doe@example.com");
        user.setUsername("John Smith");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserEntity updatedUser = userService.updateUser(user);

        assertEquals("John Smith", updatedUser.getUsername());
    }

    @Test
    public void updateUser_invalidId_throwsException() {
        int userId = 1;
        UserEntity user = new UserEntity(userId, "John Doe", "password", "john.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));
    }

}
