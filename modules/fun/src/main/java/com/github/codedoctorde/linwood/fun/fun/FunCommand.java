package com.github.codedoctorde.linwood.fun.fun;

import com.github.codedoctorde.linwood.core.commands.CommandManager;
import com.github.codedoctorde.linwood.core.entity.GuildEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CodeDoctorDE
 */
public class FunCommand extends CommandManager {
    public FunCommand() {
        super(
                "fun", "f", "funny"
        );
        registerCommands(
                new WindowsCommand(),
                new DiceCommand()
        );
    }
}