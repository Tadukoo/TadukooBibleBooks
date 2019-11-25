package com.gmail.realtadukoo.TB.Minecraft.API.Command;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.realtadukoo.TB.API.Constants.EnumTranslation;
import com.gmail.realtadukoo.TB.Minecraft.API.MinecraftInterface;
import com.gmail.realtadukoo.TB.Minecraft.API.Books.GenerateBook;

public class GenerateBooksCmd extends MinecraftCommand{
	
	public GenerateBooksCmd(MinecraftInterface MCInterface){
		super("<Tran:translation>", MCInterface);
	}
	
	@Override
	public String[] runCommand(ArrayList<String> args){
		HashMap<String, Object> objs = getArgsAsObjects(args);
		EnumTranslation tran = (EnumTranslation) objs.get("Tran");
		// TODO: Make numThreads configurable
		GenerateBook.generateWholeBible(4, tran);
		return new String[]{};
	}
}
