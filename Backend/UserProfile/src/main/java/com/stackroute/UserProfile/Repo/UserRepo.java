package com.stackroute.UserProfile.Repo;

import com.stackroute.UserProfile.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
}
