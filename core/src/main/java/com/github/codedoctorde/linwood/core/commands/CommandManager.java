package com.github.codedoctorde.linwood.core.commands;

import com.github.codedoctorde.linwood.core.Linwood;
import com.github.codedoctorde.linwood.core.entity.GuildEntity;
import net.dv8tion.jda.api.entities.Message;
import org.hibernate.Session;

import java.util.*;

/**
 * @author CodeDoctorDE
 */
public abstract class CommandManager extends Command {
    protected Set<Command> commands = new HashSet<>();

    @Override
    public boolean onCommand(Session session, Message message, GuildEntity entity, String label, String[] args) {
        var bundle = getBundle(entity);
        var baseBundle = getBaseBundle(entity);
        for (Command command : commands)
            if (command.hasAlias(
                    (args.length > 0) ? args[0].toLowerCase() : "")) {
                if(command.hasPermission(message.getMember(), entity, session) || Linwood.getInstance().getConfig().getOwners().contains(message.getAuthor().getIdLong())) {
                    if (!command.onCommand(session, message, entity,
                            (args.length > 0) ? args[0] : "",
                            (args.length > 0) ? Arrays.copyOfRange(args, 1, args.length) : new String[0]))
                        message.getChannel().sendMessageFormat(ResourceBundle.getBundle("locale.Command").getString("Syntax"), Objects.requireNonNull(command.getBundle(entity)).getString("Syntax")).queue();
                }
                else
                    message.getChannel().sendMessage(baseBundle.getString("NoPermission")).queue();
                return true;
            }
        if(args.length <= 0) Linwood.getInstance().getBaseCommand().getHelpCommand().sendHelp(entity, this, message.getTextChannel());
        else
            return false;
        return true;
    }

    public Command getCommand(GuildEntity entity, String... args){
        Command command = this;
        for (String arg:
                args)
            if (command instanceof CommandManager)
                for (Command current :
                        ((CommandManager) command).commands)
                    if (current.hasAlias(arg.toLowerCase())) command = current;
        return command;
    }
    public Command getCommand(Class<? extends Command> commandClass){
        return commands.stream().filter(command -> command.getClass().equals(commandClass)).findFirst().orElse(null);
    }
    private ResourceBundle getBaseBundle(GuildEntity entity){
        return ResourceBundle.getBundle("locale.Command", entity.getLocalization());
    }

    public Set<Command> getCommands() {
        return commands;
    }
}
