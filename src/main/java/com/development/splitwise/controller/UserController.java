package com.development.splitwise.controller;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.dto.RegisterRequestDto;
import com.development.splitwise.dto.RegisterResponseDto;
import com.development.splitwise.dto.UserUpdateProfileRequestDto;
import com.development.splitwise.dto.UserUpdateProfileResponseDto;
import com.development.splitwise.model.User;
import com.development.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){

        this.userService=userService;
    }
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) throws InvalidArgumentException {

        RegisterResponseDto responseDto=new RegisterResponseDto();
        User user= new User();
        try {
            String userName=registerRequestDto.getUserName();
            String phone=registerRequestDto.getPhone();
            String password=registerRequestDto.getPassword();
             user= userService.userRegister(userName,phone,password);
             responseDto.setResponseStatus("200");
             responseDto.setResponseMessage(user.getName()+" sign up successfully!");

        } catch (InvalidArgumentException e) {
             responseDto.setResponseStatus("-200");
             responseDto.setResponseMessage(("Failed!"));
        }
        return responseDto;
    }

    public UserUpdateProfileResponseDto upateProfile(UserUpdateProfileRequestDto userUpdateProfileRequestDto){
        UserUpdateProfileResponseDto updateResponseDto=new UserUpdateProfileResponseDto();
        User user=new User();
        try {
            user =userService.userUpdateProfile(userUpdateProfileRequestDto.getNewPassword(),user.getId());
            updateResponseDto.setUserName(user.getName());
            updateResponseDto.setResponseMessage("Password has change successfully !");
            updateResponseDto.setResponseStatus("200");
        } catch (InvalidArgumentException e) {
            updateResponseDto.setUserName(user.getName());
            updateResponseDto.setResponseMessage("Something went wrong!");
            updateResponseDto.setResponseStatus("-200");
        }
        return  updateResponseDto;
    }
}
