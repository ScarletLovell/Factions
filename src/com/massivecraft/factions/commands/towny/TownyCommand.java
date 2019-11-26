package com.massivecraft.factions.commands.towny;

import com.massivecraft.factions.Factions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TownyCommand implements CommandExecutor {
    private Factions factions;
    //private List<String> towny_general_help = new ArrayList<>();
    private List<String> towny_help = new ArrayList<>();
    public TownyCommand(Factions fact) {
        this.factions = fact;
        initTownyHelp();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        if(args == null || args.length < 1) {
            getTownyHelp(player);
        } else {
            String f = args[0].toLowerCase();
            switch(f) {
                case "map":
                    factions.getCommand("f").execute(sender, "f", args);
                    break;
                /*case "top":
                case "list":
                    args[0] = "list";
                    factions.getCommand("f").execute(sender, "f", args);
                    break;*/
                case "v":
                case "version":
                    args[0] = "version";
                    factions.getCommand("f").execute(sender, "f", args);
                    break;
                default:
                    getTownyHelp(player);
                    break;
            }
        }
        return true;
    }

    private void getTownyHelp(Player p) {
        for(int i=0;i < towny_help.size();i++) {
            p.sendMessage(towny_help.get(i));
        }
    }

    private void initTownyHelp() {
        /*towny_general_help.add(TownyUtil.formatTitle(TownySettings.getLangString("help_0")));
        towny_general_help.add(TownySettings.getLangString("help_1"));
        towny_general_help.add(TownyUtil.formatCommand("", "/resident", "?", "") + ", " + TownyUtil.formatCommand("", "/town", "?", "") + ", " + TownyUtil.formatCommand("", "/nation", "?", "") + ", " + TownyUtil.formatCommand("", "/plot", "?", "") + ", " + TownyUtil.formatCommand("", "/towny", "?", ""));
        towny_general_help.add(TownyUtil.formatCommand("", "/townchat", " [msg]", TownySettings.getLangString("help_2")) + ", " + TownyUtil.formatCommand("", "/nationchat", " [msg]", TownySettings.getLangString("help_3")));
        towny_general_help.add(TownyUtil.formatCommand(TownySettings.getLangString("admin_sing"), "/townyadmin", "?", ""));*/
        towny_help.add(TownyUtil.formatTitle("/towny"));
        towny_help.add(TownyUtil.formatCommand("", "/towny", "", "General help for Towny"));
        towny_help.add(TownyUtil.formatCommand("", "/towny", "map", "Displays a map of the nearby townblocks"));
        //towny_help.add(TownyUtil.formatCommand("", "/towny", "prices", "Display the prices used with iConomy"));
        //towny_help.add(TownyUtil.formatCommand("", "/towny", "top", "Display highscores"));
        //towny_help.add(TownyUtil.formatCommand("", "/towny", "universe", "Displays stats"));
        towny_help.add(TownyUtil.formatCommand("", "/towny", "v", "Displays the version of Towny"));
        //towny_help.add(TownyUtil.formatCommand("", "/towny", "war", "'/towny war' for more info"));
    }
}
