package com.gmail.realtadukoo.TB.Minecraft;

import java.io.IOException;

import com.gmail.realtadukoo.TB.Enums.EnumTranslations;
import com.gmail.realtadukoo.TB.Minecraft.books.GenerateBook;
import com.gmail.realtadukoo.TB.command.CommandHandler;

public class MinecraftCommandHandler extends CommandHandler{
	
	@Override
	public String[] handleCommand(String command) throws IOException{
		if(command.startsWith("/")){
			String[] parts = command.split(" ");
			if(parts[1].equalsIgnoreCase("Minecraft")){
				GenerateBook.generateWholeTranslation(EnumTranslations.fromAbbreviation(parts[2]));
				return new String[]{};
			}
		}
		return super.handleCommand(command);
	}
}
