package com.example.storeGenie.dao;

import com.example.storeGenie.POJO.User;
import com.example.storeGenie.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//we are going to write the implementation of this file in the pojo file
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUser();
}
