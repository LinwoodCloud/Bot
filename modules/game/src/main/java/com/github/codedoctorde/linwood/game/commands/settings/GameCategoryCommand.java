package com.github.codedoctorde.linwood.game.commands.settings;

import com.github.codedoctorde.linwood.core.entity.GuildEntity;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
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
public class GameCategoryCommand extends Command {
    @Override
    public boolean onCommand(final CommandEvent event) {
        ResourceBundle bundle = getBundle(entity);
        if(args.length > 1)
            return false;
        if(args.length == 0)
            if(entity.getGameEntity().getGameCategoryId() != null)
                message.getChannel().sendMessageFormat(bundle.getString("Get"), entity.getGameEntity().getGameCategory().getName(), entity.getGameEntity().getGameCategoryId()).queue();
            else
                message.getChannel().sendMessage(bundle.getString("GetNull")).queue();
        else {
            try {
                Category category = null;
                try{
                    category = message.getGuild().getCategoryById(args[0]);
                }catch(Exception ignored){

                }
                if(category == null){
                    var categories = message.getGuild().getCategoriesByName(args[0], true);
                    if(categories.size() < 1)
                        message.getChannel().sendMessage(bundle.getString("SetNothing")).queue();
                    else if(categories.size() > 1)
                        message.getChannel().sendMessage(bundle.getString("SetMultiple")).queue();
                    else
                        category = categories.get(0);
                    if(category == null)
                        return true;
                }
                entity.getGameEntity().setGameCategory(category);
                entity.save(session);
                message.getChannel().sendMessageFormat(bundle.getString("Set"), entity.getGameEntity().getGameCategory().getName(), entity.getGameEntity().getGameCategoryId()).queue();
            }catch(NullPointerException e){
                message.getChannel().sendMessage(bundle.getString("NotValid")).queue();
            }
        }
        return true;
    }
    @Override
    public boolean hasPermission(final CommandEvent event) {
       var member = event.getMember();
       var entity = event.getEntity();
       return member.hasPermission(Permission.MANAGE_SERVER) || entity.getMaintainerId() != null && member.getRoles().contains(member.getGuild().getRoleById(entity.getMaintainerId()));
    }

    @Override
    public @NotNull Set<String> aliases(GuildEntity entity) {
        super(
                "gamecategory",
                "game-category",
                "category"
        ));
    }
}
