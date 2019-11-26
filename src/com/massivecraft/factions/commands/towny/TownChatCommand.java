package com.massivecraft.factions.commands.towny;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class TownChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player))
            return false;
        if(args == null || args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Required a message to say, ex: /tc Hello!");
            return true;
        }
        Player player = (Player) sender;
        FPlayer me = FPlayer.get(player);
        if(me.hasFaction()) {
            player.sendMessage("You are not member of any faction.");
            return true;
        }
        StringBuilder msg = new StringBuilder();
        for(int i=0;i < args.length;i++)
            msg.append(args[i]).append(" ");
        String message = String.format(Conf.factionChatFormat, me.getNameAndRelevant(me), msg.toString());
        me.getFaction().sendMessage(message);
        Logger.getLogger("Minecraft").info(ChatColor.stripColor("FactionChat "+me.getFaction().getTag()+": "+message));
        return true;
    }
}
