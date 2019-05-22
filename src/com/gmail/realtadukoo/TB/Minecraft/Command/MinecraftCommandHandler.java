package com.gmail.realtadukoo.TB.Minecraft.Command;

import java.io.IOException;
import java.util.ArrayList;

import com.gmail.realtadukoo.TB.Command.CommandHandler;
import com.gmail.realtadukoo.TB.Minecraft.MinecraftMainInterface;

public class MinecraftCommandHandler extends CommandHandler{
	private MinecraftMainInterface MCInterface;
	
	public MinecraftCommandHandler(MinecraftMainInterface MCInterface){
		this.MCInterface = MCInterface;
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
				return new GenerateBooksCmd(MCInterface).runCommand(args);
			}else if(parts[1].equalsIgnoreCase("send")){
				// TODO: Figure out a way to get sender here
				return new SendVerse(MCInterface, null).runCommand(args);
			}
		}
		return super.handleCommand(command);
	}
}
