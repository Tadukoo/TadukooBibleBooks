package com.gmail.realtadukoo.TB.Minecraft.API.Command;

import com.gmail.realtadukoo.MinecraftAPI.MinecraftManager;
import com.gmail.realtadukoo.TB.API.Command.Command;

public abstract class MinecraftCommand extends Command{
	private MinecraftManager MCMan;
	
	public MinecraftCommand(String formatString, MinecraftManager MCMan){
		super(formatString);
		this.MCMan = MCMan;
	}
	
	protected MinecraftManager getMCMan(){
		return MCMan;
	}
}
