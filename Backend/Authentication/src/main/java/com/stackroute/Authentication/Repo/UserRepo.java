package com.stackroute.Authentication.Repo;

import com.stackroute.Authentication.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserInfo,String> {
        Optional<UserInfo> findByUsernameAndPassword(String username, String password);
}
