package com.stackroute.UserProfile.Services;

import com.google.gson.Gson;
import com.stackroute.UserProfile.Entity.UserEntity;
import com.stackroute.UserProfile.Exception.UserAlreadyExistsById;
import com.stackroute.UserProfile.Exception.UserNotFoundException;
import com.stackroute.UserProfile.Repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson gson;

    private static final String TOPIC = "topic";

    /**
     * @param user 
     * @return
     * @throws UserAlreadyExistsById
     */
    @Override
    public UserEntity createUser(UserEntity user) throws UserAlreadyExistsById {
        Optional<UserEntity> userdata = userRepository.findById(user.getUserId());
        if(userdata.isPresent()){
            logger.error("User Already exists by the userId: ", user.getUserId());
            throw new UserAlreadyExistsById("User already exits by given id");
        }
        userRepository.save(user);
        kafkaTemplate.send(TOPIC, gson.toJson(user));
        logger.info("New user created with userId: ", user.getUserId());
        return user;
    }

    /**
     * @return 
     */
    @Override
    public List<UserEntity> getAllUsers() {
        logger.info("Details of All Users Fetched!");
        return userRepository.findAll();
    }

    /**
     * @param id 
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public UserEntity getUserById(int id) throws UserNotFoundException {
        Optional <UserEntity> users = userRepository.findById(id);
        if (users.isPresent()) {
            logger.info("The Details fetched of userId: ", id);
            return users.get();
        } else {
            logger.error("No User Exists with userId: ", id);
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }

    /**
     * @param user 
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public UserEntity updateUser(UserEntity user) throws UserNotFoundException {
        Optional<UserEntity> users = userRepository.findById(user.getUserId());
        if (users.isPresent()) {
            UserEntity userUpdate = users.get();
            userUpdate.setUsername(user.getUsername());
            userUpdate.setPassword(user.getPassword());
            //userUpdate.setEmail(user.getEmail());
            logger.info("Following Details Updated:-");
            logger.info("username: ", user.getUsername());
            logger.info("password: ", user.getPassword());
            logger.info("Email: ", user.getEmail());
            kafkaTemplate.send(TOPIC, gson.toJson(userUpdate));
            return userRepository.save(userUpdate);
        } else{
            logger.error("User does not exists with userId: ", user.getUserId());
            throw new UserNotFoundException("User not found with id : " + user.getUserId());

        }
    }
}
