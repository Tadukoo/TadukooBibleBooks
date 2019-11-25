package com.gmail.realtadukoo.TB.Minecraft.API.Command;

import java.io.IOException;
import java.util.ArrayList;

import com.gmail.realtadukoo.MinecraftAPI.MinecraftManager;
import com.gmail.realtadukoo.TB.API.Command.CommandHandler;

public class MinecraftCommandHandler extends CommandHandler{
	private MinecraftManager MCMan;
	
	public MinecraftCommandHandler(MinecraftManager MCMan){
		this.MCMan = MCMan;
	}
	
	@Override
	public String[] handleCommand(String command) throws IOException{
		if(command.startsWith("/bible")){
			String[] parts = command.split(" ");
			ArrayList<String> args = new ArrayList<String>();
			for(int i = 1; i < parts.length; i++){
				args.add(parts[i]);
			}
			if(parts[1].equalsIgnoreCase("Minecraft")){
				return new GenerateBooksCmd(MCMan).runCommand(args);
			}else if(parts[1].equalsIgnoreCase("send")){
				// TODO: Figure out a way to get sender here
				return new SendVerse(MCMan, null).runCommand(args);
			}
		}
		return super.handleCommand(command);
	}
}
