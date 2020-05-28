package com.github.codedoctorde.linwood.listener;

import com.github.codedoctorde.linwood.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author CodeDoctorDE
 */
public class CommandListener {
    @SubscribeEvent
    public void onCommand(@Nonnull MessageReceivedEvent event) {
        var session = Main.getInstance().getDatabase().getSessionFactory().openSession();
        if(event.getChannelType() != ChannelType.TEXT)
            return;
        var entity = Main.getInstance().getDatabase().getServerById(session, event.getGuild().getIdLong());
        if(event.getAuthor().isBot())
            return;
        var server = Main.getInstance().getDatabase().getServerById(session, event.getGuild().getIdLong());
        var content = event.getMessage().getContentRaw();
        var prefix = server.getPrefix();
        var id = event.getJDA().getSelfUser().getId();
        var nicknameMention = "<@!" + id + ">";
        var normalMention = "<@" + id + ">";
        if (content.startsWith(prefix) || content.startsWith(nicknameMention) || content.startsWith(normalMention)) {
            String split;
            if (content.startsWith(prefix))
                split = prefix;
            else if (content.startsWith(nicknameMention))
                split = nicknameMention;
            else
                split = normalMention;
            var command = content.substring(split.length()).trim().split(" ");
            var bundle = Main.getInstance().getBaseCommand().getBundle(entity);
            assert bundle != null;
            try {
                if (!Main.getInstance().getBaseCommand().onCommand(session, event.getMessage(), entity, server.getPrefix(), command))
                    event.getChannel().sendMessage(bundle.getString("Syntax")).queue();
            }catch(Exception e){
                event.getChannel().sendMessage(bundle.getString("Error")).queue();
                e.printStackTrace();
            }
        }
        session.close();
    }
}
