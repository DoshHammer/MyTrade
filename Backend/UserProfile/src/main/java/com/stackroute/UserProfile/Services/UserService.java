package com.stackroute.UserProfile.Services;

import com.stackroute.UserProfile.Entity.UserEntity;
import com.stackroute.UserProfile.Exception.UserAlreadyExistsById;
import com.stackroute.UserProfile.Exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserEntity createUser(UserEntity user) throws UserAlreadyExistsById;

    List<UserEntity> getAllUsers();

    UserEntity getUserById(int id) throws UserNotFoundException;

    UserEntity updateUser(UserEntity user) throws UserNotFoundException;
}
