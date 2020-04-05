package bungeeteleport.data;

import bungeeteleport.command.Command;
import bungeeteleport.utils.JSONDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Config extends JSONDatabase {

    private Set<Command> commands = new HashSet<>(Arrays.asList(new Command("mg", "destination", new ArrayList<>(), false, new ArrayList<>())));

    public Set<Command> getCommands() {
        return commands;
    }
}