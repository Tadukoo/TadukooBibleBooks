package com.gmail.realtadukoo.TB.Minecraft.Books;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.gmail.realtadukoo.TB.Bible.EnumBible;
import com.gmail.realtadukoo.TB.Bible.EnumTranslations;

public class MinecraftBookFiles{
	
	public static Properties loadMinecraftBook(EnumBible book, EnumTranslations tran) 
			throws IOException{
		Properties prop = new Properties();
		InputStream is = new FileInputStream("resource/Bible/" + tran.getAbbreviation() + "/Minecraft/" + 
				book.getBook().replaceAll(" ", "") + ".properties");
		prop.load(is);
		return prop;
	}
	
	public static void saveMinecraftBook(Properties prop, EnumBible book, EnumTranslations tran)
			throws IOException{
		try{
			FileOutputStream fos = new FileOutputStream("resource/Bible/" + tran.getAbbreviation() + 
					"/Minecraft/" + book.getBook().replaceAll(" ", "") + ".properties");
			fos.close();
		}catch(FileNotFoundException e){
			File directory = new File("resource/Bible/" + tran.getAbbreviation() + "/Minecraft");
			directory.mkdirs();
		}
		OutputStream os = new FileOutputStream("resource/Bible/" + tran.getAbbreviation() + "/Minecraft/" +
				book.getBook().replaceAll(" ", "") + ".properties");
		prop.store(os, "No Comment");
	}
}
