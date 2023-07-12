package com.development.splitwise.command;

import com.development.splitwise.controller.UserController;
import com.development.splitwise.dto.UserUpdateProfileRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.development.splitwise.command.CommandConstants.USER_UPDATE_PROFILE;

@Component
public class UpdateProfileCommand implements InputInterface{

    private final UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController){
        this.userController=userController;
    }

    @Override
    public boolean matches(String input) {
        List<String> inputList= Arrays.stream(input.split(" ")).toList();
        if(inputList.size()==2 && inputList.get(0).equals(USER_UPDATE_PROFILE)) return true;
        return false;
    }

    @Override
    public void execute(String input) {
        List<String> inputList= Arrays.stream(input.split(" ")).toList();
        String newPassword=inputList.get(1);

        UserUpdateProfileRequestDto userUpdateProfileRequestDto=new UserUpdateProfileRequestDto();
        userUpdateProfileRequestDto.setNewPassword(newPassword);

        userController.upateProfile(userUpdateProfileRequestDto);

    }
}
