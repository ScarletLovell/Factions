package com.massivecraft.factions.commands;

import com.massivecraft.factions.Conf;
import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class FCommandChat extends FBaseCommand {
	
	public FCommandChat() {
		aliases.add("chat");
		aliases.add("c");
		
		helpDescription = "Use faction only chat";
	}
	
	@Override
	public void perform() {
		if ( ! Conf.factionOnlyChat ) {
			sendMessage("Faction-only chat is disabled on this server.");
			return;
		}
		if ( ! assertHasFaction()) {
			return;
		}

		if(parameters.size() > 0) {
			if(parameters.get(0).equalsIgnoreCase("toggle")) {
				me.setFactionChatting(!me.isFactionChatting());
				sendMessage("Faction-only chat " + (!me.isFactionChatting() ? "DISABLED." : "ENABLED."));
			} else {
				StringBuilder msg = new StringBuilder();
				for(int i=0;i < parameters.size();i++)
					msg.append(parameters.get(i)).append(" ");
				String message = String.format(Conf.factionChatFormat, me.getNameAndRelevant(me), msg.toString());
				me.getFaction().sendMessage(message);
				Logger.getLogger("Minecraft").info(ChatColor.stripColor("FactionChat "+me.getFaction().getTag()+": "+message));
			}
		} else {

		}
	}
	
}
