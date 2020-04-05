package bungeeteleport.command;

import java.util.Optional;

public interface CMDManager {
    Optional<Command> getCommand(String p0);
}