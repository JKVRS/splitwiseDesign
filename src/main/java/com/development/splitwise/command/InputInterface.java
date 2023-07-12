package com.development.splitwise.command;

public interface InputInterface {

    boolean matches(String input);
    void execute(String input);
}
