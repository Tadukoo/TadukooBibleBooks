package com.gmail.realtadukoo.TB.Minecraft.Command;

import com.gmail.realtadukoo.TB.Command.Command;
import com.gmail.realtadukoo.TB.Minecraft.MinecraftMainInterface;

public abstract class MinecraftCommand extends Command{
	private MinecraftMainInterface MCInterface;
	
	public MinecraftCommand(String formatString, MinecraftMainInterface MCInterface){
		super(formatString);
		this.MCInterface = MCInterface;
	}
	
	protected MinecraftMainInterface getMCInterface(){
		return MCInterface;
	}
}
