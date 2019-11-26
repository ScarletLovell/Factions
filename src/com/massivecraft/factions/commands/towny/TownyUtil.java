package com.massivecraft.factions.commands.towny;

public class TownyUtil {
    static String formatTitle(String title) {
        String line = ".oOo.__________________________________________________.oOo.";
        int pivot = line.length() / 2;
        String center = ".[ §e" + title + "§6" + " ].";
        String out = "§6" + line.substring(0, pivot - center.length() / 2);
        out = out + center + line.substring(pivot + center.length() / 2);
        return out;
    }

    static String formatCommand(String requirement, String command, String subCommand, String help) {
        String out = "  ";
        if (requirement.length() > 0) {
            out = out + "§c" + requirement + ": ";
        }

        out = out + "§3" + command;
        if (subCommand.length() > 0) {
            out = out + " §b" + subCommand;
        }

        if (help.length() > 0) {
            out = out + " §7 : " + help;
        }

        return out;
    }
}
