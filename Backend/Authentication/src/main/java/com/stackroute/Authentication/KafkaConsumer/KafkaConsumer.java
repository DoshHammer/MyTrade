package com.stackroute.Authentication.KafkaConsumer;

import com.google.gson.Gson;
import com.stackroute.Authentication.Entity.UserInfo;
import com.stackroute.Authentication.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {
    @Autowired
    Gson gson;

    @Autowired
    AuthService authService;

    @KafkaListener(topics = "topic",groupId = "group_id")
    public void consume(String user) {

        System.out.println("received message = " + user);
        UserInfo userdata=gson.fromJson(user,UserInfo.class);
        UserInfo result = authService.fetchUserDetails(userdata);
        System.out.println("stored data in user copy" + userdata.toString());
    }
}
