package com.gmail.realtadukoo.TB.Minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.gmail.realtadukoo.TB.Enums.EnumTranslations;
import com.gmail.realtadukoo.TB.Enums.Bible.EnumBible;

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
	
	public static void addToMinecraftBook(EnumBible book, EnumTranslations tran, int chpNum, 
			int bookEnd, int pageEnd){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book, tran);
		}catch(IOException e){
			
		}
		prop.setProperty("Ch" + chpNum, "B" + bookEnd + "P" + pageEnd);
		try{
			saveMinecraftBook(prop, book, tran);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void addToMinecraftBook(EnumBible book, EnumTranslations tran, int bookNum, 
			int pageNum, String page){
		Properties prop = new Properties();
		try{
			prop = loadMinecraftBook(book, tran);
		}catch(IOException e){
			
		}
		prop.setProperty("Book" + bookNum + "Page" + pageNum, page);
		try{
			saveMinecraftBook(prop, book, tran);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
