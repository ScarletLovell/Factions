package com.massivecraft.factions.commands.towny;

import com.massivecraft.factions.Factions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TownCommand implements CommandExecutor {
    private static final List<String> output = new ArrayList<String>();
    private Factions factions;
    public TownCommand(Factions fact) {
        this.factions = fact;
        initTownyHelp();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        if(args == null || args.length < 1) {
            factions.getCommand("f").execute(sender, "f", new String[]{"show"});
        } else {
            String f = args[0].toLowerCase();
            switch(f) {
                case "list":
                case "leave":
                case "here":
                    factions.getCommand("f").execute(sender, "f", args);
                    break;
                case "spawn":
                case "home":
                    args[0] = "home";
                    factions.getCommand("f").execute(sender, "f", args);
                    break;
                case "new":
                case "create":
                    args[0] = "create";
                    factions.getCommand("f").execute(sender, "f", args);
                    break;
                case "help":
                case "h":
                case "?":
                    getTownyHelp(player);
                    break;
                default:
                    getTownyHelp(player);
                    break;
            }
        }
        return true;
    }

    public void initTownyHelp() {
        output.add(TownyUtil.formatTitle("/town"));
        output.add(TownyUtil.formatCommand("", "/town", "", "Your town\'s status"));
        output.add(TownyUtil.formatCommand("", "/town", "[town]", "Selected town\'s status"));
        output.add(TownyUtil.formatCommand("", "/town", "here", "Shortcut to the town''s status of your location."));
        output.add(TownyUtil.formatCommand("", "/town", "list", ""));
        output.add(TownyUtil.formatCommand("", "/town", "leave", ""));
        output.add(TownyUtil.formatCommand("", "/town", "spawn", "Teleport to town\'s spawn."));

        output.add(TownyUtil.formatCommand(ChatColor.RED+"Admin", "/town", "new [town] " + "[mayor]", "New town with target mayor."));
        output.add(TownyUtil.formatCommand(ChatColor.RED+"Resident", "/town", "deposit [$]", ""));
        output.add(TownyUtil.formatCommand(ChatColor.RED+"Mayor", "/town", "mayor ?", "List commands for mayors."));
        output.add(TownyUtil.formatCommand(ChatColor.RED+"Admin", "/town", "delete [town]", ""));
    }

    private void getTownyHelp(Player p) {
        for(int i=0;i < output.size();i++) {
            p.sendMessage((String)output.get(i));
        }
    }
}
