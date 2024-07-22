package com.example.storeGenie.dao;

import com.example.storeGenie.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

//we are going to write the implementation of this file in the pojo file
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);
}
