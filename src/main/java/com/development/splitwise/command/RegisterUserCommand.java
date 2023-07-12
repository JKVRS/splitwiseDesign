package com.development.splitwise.command;

import com.development.splitwise.Exception.InvalidArgumentException;
import com.development.splitwise.controller.UserController;
import com.development.splitwise.dto.RegisterRequestDto;
import com.development.splitwise.dto.RegisterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RegisterUserCommand implements InputInterface{

    UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController){
        this.userController=userController;
    }
    @Override
    public boolean matches(String input)
    {
        List<String> registerInput= List.of(input.split(" "));

        if(registerInput.size()==4 && registerInput.get(0).equalsIgnoreCase(CommandConstants.USER_REGISTER)){
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {

        List<String> registerInput= Arrays.stream(input.split(" ")).toList();
         String userName=registerInput.get(1);
         String phone=registerInput.get(2);
         String password=registerInput.get(3);

         // call the controller file from here for doing the registration activity
        RegisterRequestDto requestDto=new RegisterRequestDto();
        requestDto.setUserName(userName);
        requestDto.setPhone(phone);
        requestDto.setPassword(password);

        try {
            RegisterResponseDto responseDto= userController.register(requestDto);
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }


    }
}
