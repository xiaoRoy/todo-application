package com.learn.todo;

import com.learn.todo.utils.CommandLineInput;
import com.learn.todo.utils.CommandLineInputHandler;

public class TodoApplication {

    public static final char DEFAULT_INPUT = '\u0000';

    public static void main(String[] args) {
        CommandLineInputHandler commandLineInputHandler = new CommandLineInputHandler();
        char command = DEFAULT_INPUT;
        while (command != CommandLineInput.EXIT.getShortCmd()){
            commandLineInputHandler.printOptions();
            String input = commandLineInputHandler.readInput();
            char[] inputChars = input.length() == 1 ? input.toCharArray() : new char[]{DEFAULT_INPUT};
            command =inputChars[0];
            CommandLineInput commandLineInput = CommandLineInput.getCommandLineInputByCharInput(command);
            commandLineInputHandler.processInput(commandLineInput);
        }
    }
}
