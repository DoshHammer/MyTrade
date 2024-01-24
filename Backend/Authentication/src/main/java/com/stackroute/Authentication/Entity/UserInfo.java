package com.stackroute.Authentication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
public class UserInfo {
    @Id
    String username;

    String password;

}
