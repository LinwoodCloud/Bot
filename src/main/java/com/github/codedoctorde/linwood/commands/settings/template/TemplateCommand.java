package com.github.codedoctorde.linwood.commands.settings.template;

import com.github.codedoctorde.linwood.commands.Command;
import com.github.codedoctorde.linwood.commands.CommandManager;
import com.github.codedoctorde.linwood.entity.GuildEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author CodeDoctorDE
 */
public class TemplateCommand extends CommandManager {
    @Override
    public @NotNull Command[] commands() {
        return new Command[]{
                new CreateTemplateCommand(),
                new ListTemplateCommand(),
                new RemoveTemplateCommand()
        };
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        return null;
    }

    @Override
    public @NotNull ResourceBundle getBundle(GuildEntity entity) {
        return null;
    }
}
