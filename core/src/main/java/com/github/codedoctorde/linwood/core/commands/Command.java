package com.github.codedoctorde.linwood.core.commands;

import com.github.codedoctorde.linwood.core.entity.GuildEntity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class Command {
    private final Set<String> aliases = new HashSet<>();

    protected Command(String... aliases){
        Collections.addAll(this.aliases, aliases);
    }

    abstract public boolean onCommand(final CommandEvent event);
    @NotNull
    public String getTranslationString(final GuildEntity entity, final String key){
        return ResourceBundle.getBundle("locale." + getClass().getCanonicalName(), entity.getLocalization()).getString(key);
    }
    protected ResourceBundle getBaseBundle(GuildEntity entity){
        return ResourceBundle.getBundle("locale.Command", entity.getLocalization());
    }
    public boolean hasPermission(final CommandEvent event){
        return true;
    }

    public Set<String> getAliases() {
        return aliases;
    }
    public boolean hasAlias(String alias){
        return aliases.stream().anyMatch(current -> current.equalsIgnoreCase(alias));
    }
}