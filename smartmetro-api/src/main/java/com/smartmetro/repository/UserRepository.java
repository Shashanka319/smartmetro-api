package com.smartmetro.repository;

import com.smartmetro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
   // List<User> findByName(String name);
   @Query(value="SELECT u FROM User u WHERE u.userName = ?1" )
    List<User> findByName(String name);
}
