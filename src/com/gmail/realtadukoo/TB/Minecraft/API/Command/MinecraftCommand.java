package com.gmail.realtadukoo.TB.Minecraft.API.Command;

import com.gmail.realtadukoo.TB.API.Command.Command;
import com.gmail.realtadukoo.TB.Minecraft.API.MinecraftInterface;

public abstract class MinecraftCommand extends Command{
	private MinecraftInterface MCInterface;
	
	public MinecraftCommand(String formatString, MinecraftInterface MCInterface){
		super(formatString);
		this.MCInterface = MCInterface;
	}
	
	protected MinecraftInterface getMCInterface(){
		return MCInterface;
	}
}
