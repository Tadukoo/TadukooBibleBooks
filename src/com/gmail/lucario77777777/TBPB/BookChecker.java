package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

import com.gmail.lucario77777777.TBPB.BookDefine;
import com.gmail.lucario77777777.TBPB.TBB;

import com.gmail.lucario77777777.TBP.commands.EnumBooks;

public class BookChecker {
	static TBB plugin = TBB.instance;
	
	public static void booksCheck(TBB plugin, String tran, String type) {
		Boolean cont = true;
		int i = 1;
		String book = null;
		EnumBooks ebook = EnumBooks.GENESIS;
		if(type.equalsIgnoreCase("check")){
			plugin.getLogger().log(Level.INFO, "Checking " + tran + " books.");
			while(cont == true){
				if(i == 67){
					cont = false;
					i = 1;
				}else{
					book = ebook.numtoBook(i, "int", null, null);
					i++;
					booksConfigCheck(plugin, tran, book);
				}
			}
		}else if(type.equalsIgnoreCase("correct")){
			plugin.getLogger().log(Level.INFO, "Correcting " + tran + " books.");
			while(cont == true){
				book = ebook.numtoBook(i, "int", null, null);
				i++;
				bookCreate(plugin, tran, book, "fix");
				if(i == 66){
					cont = false;
					i = 1;
				}
			}
		}else if(type.equalsIgnoreCase("create")){
			plugin.getLogger().log(Level.INFO, "Creating " + tran + " books.");
			while(cont == true){
				book = ebook.numtoBook(i, "int", null, null);
				i++;
				bookCreate(plugin, tran, book, "write");
				if(i == 66){
					cont = false;
					i = 1;
				}
			}
		}else{
			plugin.getLogger().log(Level.WARNING, "You do not have " + tran + "BookCheck set correctly in the " +
					"config.yml. It should be ignore, check, correct, or create.");
		}
	}
	
	public static void booksConfigCheck(TBB plugin, String tran, String bookName){
		plugin.getLogger().log(Level.INFO, "Checking for " + bookName + "...");
		if(TBB.TBP.getBook(tran, bookName).getString("ch1v1") != null){
			plugin.getLogger().log(Level.INFO, bookName + ".yml found. Checking for " + bookName + 
					" book config...");
			if(TBB.TBP.getigBook(tran).getBoolean(bookName + "Done") != true){
				plugin.getLogger().log(Level.INFO, bookName + " book config not found. Creating...");
				BookDefine.run(plugin, tran, bookName, "write");
			}else{
				plugin.getLogger().log(Level.INFO, bookName + " book config found.");
				return;
			}
		}else{
			plugin.getLogger().log(Level.INFO, bookName + ".yml not found.");
			return;
		}
	}
	
	public static void bookCreate(TBB plugin, String tran, String bookName, String cmd){
		plugin.getLogger().log(Level.INFO, "Checking for " + bookName + "...");
		if(TBB.TBP.getBook(tran, bookName).getString("ch1v1") != null){
			plugin.getLogger().log(Level.INFO, bookName + ".yml found. Creating/Overwriting book config...");
			BookDefine.run(plugin, tran, bookName, cmd);
		}else{
			plugin.getLogger().log(Level.INFO, bookName + ".yml not found.");
			return;
		}
	}
}
