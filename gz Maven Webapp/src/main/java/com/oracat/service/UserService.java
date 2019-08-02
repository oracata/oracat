package com.oracat.service;

import com.oracat.model.User;

public interface UserService {
    User selectUserById(Integer userId);  
}