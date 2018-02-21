package com.learn.todo.utils;

import java.util.HashMap;
import java.util.Map;

public enum CommandLineInput {

    FIND_ALL('a'), FIND_BY_ID('f'), INSERT('i'), UPDATE('u'), DELETE('d'), EXIT('e');

    private static final Map<Character, CommandLineInput> INPUTS;

    static {
        INPUTS =  new HashMap<>();
        for(CommandLineInput commandLineInput : values()){
            INPUTS.put(commandLineInput.getShortCmd(), commandLineInput);
        }
    }

    private final char shortCmd;

    private CommandLineInput(char shortCmd) {
        this.shortCmd = shortCmd;
    }

    public char getShortCmd() {
        return shortCmd;
    }

    public static CommandLineInput getCommandLineInputByCharInput(char input){
        return INPUTS.get(input);
    }
}
