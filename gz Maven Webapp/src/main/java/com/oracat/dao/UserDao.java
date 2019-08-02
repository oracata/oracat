package com.oracat.dao;

import com.oracat.model.User;

public interface UserDao {
	/**
     * @param userId
     * @return User
     */
    public User selectUserById(Integer userId);
    
}
