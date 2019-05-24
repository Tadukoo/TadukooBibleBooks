package com.gmail.realtadukoo.TB.Minecraft.Command;

import com.gmail.realtadukoo.TB.Command.Command;
import com.gmail.realtadukoo.TB.Minecraft.MinecraftInterface;

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
