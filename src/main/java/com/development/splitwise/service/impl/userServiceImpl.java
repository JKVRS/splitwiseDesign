package com.development.splitwise.service.impl;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.dao.UserDao;
import com.development.splitwise.model.User;
import com.development.splitwise.model.UserStatus;
import com.development.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class userServiceImpl implements UserService {

   private final UserDao userDao;

   @Autowired
   public userServiceImpl(UserDao userDao){

       this.userDao=userDao;
   }

    @Override
    public User userRegister(String userName, String phone, String password) throws InvalidArgumentException {
          Long phoneChangeToLong=Long.parseLong(phone);
          BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
          Optional<User> optionalUser=userDao.findByPhone(phoneChangeToLong);
        if(optionalUser.isPresent()){
            if(optionalUser.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                throw new InvalidArgumentException("User is already exist with the phone!");
             }else{
                User user=optionalUser.get();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(userName);
                user.setPhone(phoneChangeToLong);
                user.setPassword(encoder.encode(password));
                return  userDao.save(user);
            }
        }
        User user=new User();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setName(userName);
        user.setPhone(phoneChangeToLong);
        user.setPassword(encoder.encode(password));
        return  userDao.save(user);

    }

    @Override
    public User userUpdateProfile(String newPassword,Long userId) throws InvalidArgumentException {
        Optional<User> optionalUser=userDao.findById(userId);

        if(optionalUser.isPresent()) {
            throw new InvalidArgumentException(optionalUser.get().getName() + " User is not preset !");
        }
            User user=optionalUser.get();
            user.setPassword(newPassword);
            userDao.save(user);
            return user;

    }
}
