package com.gmail.realtadukoo.TB.Minecraft.API.Command;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.realtadukoo.MinecraftAPI.MinecraftFormatting.Color;
import com.gmail.realtadukoo.MinecraftAPI.MinecraftManager;
import com.gmail.realtadukoo.MinecraftAPI.Entity.Player;
import com.gmail.realtadukoo.MinecraftAPI.Entity.PlayerManager;
import com.gmail.realtadukoo.TB.API.Bible.BibleReference;
import com.gmail.realtadukoo.TB.API.Command.GetVerse;

public class SendVerse extends MinecraftCommand{
	private String sendingPlayer;
	
	public SendVerse(MinecraftManager MCMan, String sendingPlayer){
		super("<Ref:bible reference> <Player:player>", MCMan);
		this.sendingPlayer = sendingPlayer;
	}
	
	@Override
	public String[] runCommand(ArrayList<String> args){
		PlayerManager playerMan = getMCMan().getPlayerMan();
		
		HashMap<String, Object> objs = getArgsAsObjects(args);
		BibleReference ref = (BibleReference) objs.get("Ref");
		String playerName = (String) objs.get("Player");
		String verse = GetVerse.getVerse(ref);
		Player player = playerMan.getPlayerFromName(playerName);
		playerMan.sendMessage(player, Color.GREEN, sendingPlayer + " sent you this Bible verse: " + verse);
		return new String[]{};
	}
}
