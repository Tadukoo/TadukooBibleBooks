package com.gmail.realtadukoo.TB.Minecraft.Command;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.realtadukoo.TB.Bible.BibleReference;
import com.gmail.realtadukoo.TB.Command.GetVerse;
import com.gmail.realtadukoo.TB.Minecraft.MinecraftMainInterface;

public class SendVerse extends MinecraftCommand{
	private String sendingPlayer;
	
	public SendVerse(MinecraftMainInterface MCInterface, String sendingPlayer){
		super("<Ref:bible reference> <Player:player>", MCInterface);
		this.sendingPlayer = sendingPlayer;
	}
	
	@Override
	public String[] runCommand(ArrayList<String> args){
		HashMap<String, Object> objs = getArgsAsObjects(args);
		BibleReference ref = (BibleReference) objs.get("Ref");
		String playerName = (String) objs.get("Player");
		String verse = GetVerse.getVerse(ref);
		this.getMCInterface().sendMessageToPlayer(playerName, "green", sendingPlayer + " sent you this Bible verse: " + verse);
		return new String[]{};
	}
}
