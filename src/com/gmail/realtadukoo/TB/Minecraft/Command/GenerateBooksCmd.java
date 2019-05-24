package com.gmail.realtadukoo.TB.Minecraft.Command;

import java.util.ArrayList;
import java.util.HashMap;

import com.gmail.realtadukoo.TB.Bible.EnumTranslations;
import com.gmail.realtadukoo.TB.Minecraft.MinecraftInterface;
import com.gmail.realtadukoo.TB.Minecraft.Books.GenerateBook;

public class GenerateBooksCmd extends MinecraftCommand{
	
	public GenerateBooksCmd(MinecraftInterface MCInterface){
		super("<Tran:translation>", MCInterface);
	}
	
	@Override
	public String[] runCommand(ArrayList<String> args){
		HashMap<String, Object> objs = getArgsAsObjects(args);
		EnumTranslations tran = (EnumTranslations) objs.get("Tran");
		// TODO: Make numThreads configurable
		GenerateBook.generateWholeBible(4, tran);
		return new String[]{};
	}
}
