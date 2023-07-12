package com.development.splitwise.service;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User userRegister(String userName, String phone, String password) throws InvalidArgumentException;
    public User userUpdateProfile(String newPassword,Long userId) throws InvalidArgumentException;
}
