package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

import com.gmail.lucario77777777.TBP.commands.EnumBooks;

public class BookChecker {
	static TBB plugin = TBB.instance;
	
	public static boolean booksCheck(TBB plugin, String tran, String type){
		boolean cont = true;
		int i = 1;
		String book;
		EnumBooks ebook = EnumBooks.GENESIS;
		if(type.equalsIgnoreCase("correct")){
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
			return true;
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
			return true;
		}else{
			/*
			 * Includes check
			 */
			plugin.getConfig().set(tran + "BookCheck", "check");
			plugin.getLogger().log(Level.INFO, "Checking " + tran + " books...");
			while(cont){
				if(i == 67){
					i = 1;
					plugin.getLogger().log(Level.INFO, "Done checking " + tran + " books.");
					cont = false;
				}else{
					book = ebook.numtoBook(i, "int", null, null);
					i++;
					booksConfigCheck(plugin, tran, book);
				}
			}
			return true;
		}
	}
	
	public static boolean booksConfigCheck(TBB plugin, String tran, String bookName){
		plugin.getLogger().log(Level.INFO, "Checking for " + bookName + "...");
		if(TBB.TBP.getBook(tran, bookName).getString("ch1v1") != null){
			plugin.getLogger().log(Level.INFO, bookName + ".yml found. Checking for " + bookName + 
					" book config...");
			if(TBB.TBP.getigBook(tran).getBoolean(bookName + "Done") != true){
				plugin.getLogger().log(Level.INFO, bookName + " book config not found. Checking if " +
						"partially done...");
				Boolean cont = true;
				int i = 1, j = -1, k = -1;
				while(cont == true){
					if(TBB.TBP.getigBook(tran).getBoolean(bookName + "Part" + i + "Done") == true){
						plugin.getLogger().log(Level.INFO, bookName + "Part" + i + "Done = true!");
						i++;
					}else{
						if(TBB.TBP.getigBook(tran).getString(bookName + i + "Start") == null){
							int l = i -1;
							j = TBB.TBP.getigBook(tran).getInt(bookName + l + "End.c");
							k = TBB.TBP.getigBook(tran).getInt(bookName + l + "End.v") + 1;
						}else{
							j = TBB.TBP.getigBook(tran).getInt(bookName + i + "Start.c");
							k = TBB.TBP.getigBook(tran).getInt(bookName + i + "Start.v");
						}
						cont = false;
					}
				}
				BookDefine.run(plugin, tran, bookName, "write", j, k);
				return true;
			}else{
				plugin.getLogger().log(Level.INFO, bookName + " book config found.");
				return true;
			}
		}else{
			plugin.getLogger().log(Level.INFO, bookName + ".yml not found.");
			return true;
		}
	}
	
	public static boolean bookCreate(TBB plugin, String tran, String bookName, String cmd){
		plugin.getLogger().log(Level.INFO, "Checking for " + bookName + "...");
		if(TBB.TBP.getBook(tran, bookName).getString("ch1v1") != null){
			plugin.getLogger().log(Level.INFO, bookName + ".yml found. Creating/Overwriting book config...");
			BookDefine.run(plugin, tran, bookName, cmd, -1, -1);
			return true;
		}else{
			plugin.getLogger().log(Level.INFO, bookName + ".yml not found.");
			return true;
		}
	}
}
