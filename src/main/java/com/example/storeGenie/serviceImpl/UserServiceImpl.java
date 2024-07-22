package com.example.storeGenie.serviceImpl;

import com.example.storeGenie.POJO.User;
import com.example.storeGenie.constants.CafeConstants;
import com.example.storeGenie.dao.UserDao;
import com.example.storeGenie.service.UserService;
import com.example.storeGenie.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
       try {
           log.info("inside singUp {}", requestMap);
           if (validateSignUp(requestMap)) {
               User user = userDao.findByEmailId(requestMap.get("email"));
               if (Objects.isNull(user)) {
                   userDao.save(getUserFromMap(requestMap));
                   return CafeUtils.getResponeEntity("Sucessfully registered", HttpStatus.OK);

               } else {
                   return CafeUtils.getResponeEntity("Email already exists", HttpStatus.BAD_REQUEST);
               }
           } else {
               return CafeUtils.getResponeEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return CafeUtils.getResponeEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUp(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
