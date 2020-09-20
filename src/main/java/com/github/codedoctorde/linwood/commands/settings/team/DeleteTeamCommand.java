package com.github.codedoctorde.linwood.commands.settings.team;

import com.github.codedoctorde.linwood.Linwood;
import com.github.codedoctorde.linwood.commands.Command;
import com.github.codedoctorde.linwood.entity.GuildEntity;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author CodeDoctorDE
 */
public class DeleteTeamCommand implements Command {
    @Override
    public boolean onCommand(Session session, Message message, GuildEntity entity, String label, String[] args) {
        if(args.length != 1)
        return false;
        var bundle = getBundle(entity);
        Linwood.getInstance().getDatabase().getTeam(session, args[0]).delete(session);
        message.getChannel().sendMessageFormat(bundle.getString("Success"), args[0]);
        return true;
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        return new HashSet<>(Arrays.asList("delete", "d"));
    }

    @Override
    public @NotNull ResourceBundle getBundle(GuildEntity entity) {
        return ResourceBundle.getBundle("locale.commands.settings.team.DeleteTeam", entity.getLocalization());
    }
}