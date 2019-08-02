package com.oracat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oracat.dao.UserDao;
import com.oracat.model.User;
import com.oracat.service.UserService;

@Service  
public class UserServiceImpl implements UserService {

    @Autowired  
    private UserDao userDao;  

    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  
    }  
}