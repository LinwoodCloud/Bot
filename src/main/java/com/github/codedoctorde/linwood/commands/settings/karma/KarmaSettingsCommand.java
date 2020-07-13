package com.github.codedoctorde.linwood.commands.settings.karma;

import com.github.codedoctorde.linwood.commands.Command;
import com.github.codedoctorde.linwood.commands.CommandManager;
import com.github.codedoctorde.linwood.entity.GuildEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ResourceBundle;

public class KarmaSettingsCommand extends CommandManager {
    @Override
    public Command[] commands() {
        return new Command[]{
                new LikeCommand(),
                new ClearLikeCommand(),
                new DislikeCommand(),
                new ClearDislikeCommand()
        };
    }

    @Override
    public String[] aliases(GuildEntity entity) {
        return new String[]{
                "karma"
        };
    }

    @Override
    public @Nullable ResourceBundle getBundle(GuildEntity entity) {
        return ResourceBundle.getBundle("locale.commands.settings.karma.Base");
    }
}
