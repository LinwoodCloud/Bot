package com.github.codedoctorde.linwood.template.commands.settings;

import com.github.codedoctorde.linwood.core.commands.CommandManager;
import com.github.codedoctorde.linwood.core.entity.GuildEntity;
import org.jetbrains.annotations.NotNull;

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
}
