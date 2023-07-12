package com.development.splitwise.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistery {

    // this class is use to store command like register command , update profile command
    // it is part of command design pattern
    // try to use framework annotation
    List<InputInterface> command;

    @Autowired
    public CommandRegistery(RegisterUserCommand registerUserCommand,UpdateProfileCommand updateProfileCommand){
        command=new ArrayList<>();
        command.add(registerUserCommand);
        command.add(updateProfileCommand);
    }

    public void Execute(String input){

        for(InputInterface comm:command){
            if(comm.matches(input)){
                comm.execute(input);
                break;
            }
        }
    }


}
