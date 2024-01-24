package com.stackroute.Authentication.Config;

import com.stackroute.Authentication.Entity.UserInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenGenerator {
    public Map<String, String> generateToken(UserInfo user) {

        Map<String, Object> userdata = new HashMap<>();

        userdata.put("username", user.getUsername());
        userdata.put("password", user.getPassword());

        String jwtToken = "";


        jwtToken = Jwts.builder().setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret").compact();

        Map<String, String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put("token", jwtToken);
        jwtTokenMap.put("message", "Login Successful");
        return jwtTokenMap;

    }
}
