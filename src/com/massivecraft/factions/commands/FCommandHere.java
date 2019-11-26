package com.massivecraft.factions.commands;

import com.massivecraft.factions.*;
import com.massivecraft.factions.struct.Role;
import com.massivecraft.factions.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Collection;

public class FCommandHere extends FBaseCommand {
    public FCommandHere() {
        aliases.add("here");

        helpDescription = "Display the faction you're standing in";
    }

    @Override
    public boolean hasPermission(CommandSender sender) {
        return true;
    }

    @Override
    public void perform() {
        Faction faction = me.getFaction();
        FLocation flocation = new FLocation(me);

        if (Board.getIdAt(flocation) != faction.getId()) {
            faction = Board.getFactionAt(flocation);
            if (!faction.isNormal()) {
                me.sendMessage("This land is not claimed by any faction, thus no owners.");
                return;
            }
        }
        Collection<FPlayer> admins = faction.getFPlayersWhereRole(Role.ADMIN);
        Collection<FPlayer> mods = faction.getFPlayersWhereRole(Role.MODERATOR);
        Collection<FPlayer> normals = faction.getFPlayersWhereRole(Role.NORMAL);
        sendMessage(TextUtil.titleize(faction.getTag(me)));
        sendMessage(Conf.colorChrome+"Description: "+Conf.colorSystem+faction.getDescription());
        String peaceStatus = "";
        if (faction.isPeaceful()) {
            peaceStatus = "     "+Conf.colorNeutral+"This faction is Peaceful";
        }
        sendMessage(Conf.colorChrome+"Joining: "+Conf.colorSystem+(faction.getOpen() ? "no invitation is needed" : "invitation is required")+peaceStatus);
        sendMessage(Conf.colorChrome+"Land / Power / Maxpower: "+Conf.colorSystem+ faction.getLandRounded()+" / "+faction.getPowerRounded()+" / "+faction.getPowerMaxRounded());
        if (faction.isPermanent()) {
            sendMessage(Conf.colorChrome+"This faction is permanent, remaining even with no members.");
        }
        // show the land value
        if (Econ.enabled()) {
            double value = Econ.calculateTotalLandValue(faction.getLandRounded());
            double refund = value * Conf.econClaimRefundMultiplier;
            if (value > 0) {
                String stringValue = Econ.moneyString(value);
                String stringRefund = (refund > 0.0) ? (" ("+Econ.moneyString(refund)+" depreciated)") : "";
                sendMessage(Conf.colorChrome+"Total land value: " + Conf.colorSystem + stringValue + stringRefund);
            }
        }

        String listpart;

        // List relation
        String allyList = Conf.colorChrome+"Allies: ";
        String enemyList = Conf.colorChrome+"Enemies: ";
        for (Faction otherFaction : Faction.getAll()) {
            if (otherFaction == faction) {
                continue;
            }
            listpart = otherFaction.getTag(me)+Conf.colorSystem+", ";
            if (otherFaction.getRelation(faction).isAlly()) {
                allyList += listpart;
            } else if (otherFaction.getRelation(faction).isEnemy()) {
                enemyList += listpart;
            }
        }
        if (allyList.endsWith(", ")) {
            allyList = allyList.substring(0, allyList.length()-2);
        }
        if (enemyList.endsWith(", ")) {
            enemyList = enemyList.substring(0, enemyList.length()-2);
        }

        sendMessage(allyList);
        sendMessage(enemyList);

        // List the members...
        String onlineList = Conf.colorChrome+"Members online: ";
        String offlineList = Conf.colorChrome+"Members offline: ";
        for (FPlayer follower : admins) {
            listpart = follower.getNameAndTitle(me)+Conf.colorSystem+", ";
            if (follower.isOnline()) {
                onlineList += listpart;
            } else {
                offlineList += listpart;
            }
        }
        for (FPlayer follower : mods) {
            listpart = follower.getNameAndTitle(me)+Conf.colorSystem+", ";
            if (follower.isOnline()) {
                onlineList += listpart;
            } else {
                offlineList += listpart;
            }
        }
        for (FPlayer follower : normals) {
            listpart = follower.getNameAndTitle(me)+Conf.colorSystem+", ";
            if (follower.isOnline()) {
                onlineList += listpart;
            } else {
                offlineList += listpart;
            }
        }

        if (onlineList.endsWith(", ")) {
            onlineList = onlineList.substring(0, onlineList.length()-2);
        }
        if (offlineList.endsWith(", ")) {
            offlineList = offlineList.substring(0, offlineList.length()-2);
        }

        sendMessage(onlineList);
        sendMessage(offlineList);
    }
}
