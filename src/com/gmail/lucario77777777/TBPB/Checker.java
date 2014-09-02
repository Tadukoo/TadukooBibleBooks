package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

import com.gmail.lucario77777777.TBP.TB;
import com.gmail.lucario77777777.TBP.Enums.EnumBooks;
import com.gmail.lucario77777777.TBP.Enums.EnumTrans;

public class Checker {
static TBB plugin = TBB.instance;

	public static boolean tranCheck(TB TBP, EnumBooks ebook, EnumTrans etran){
		plugin.getLogger().log(Level.INFO, "Checking translations for Tadukoo Bible...");
		if(tranDoCheck("KJV", TBP, ebook, etran)){
			plugin.getLogger().log(Level.INFO, "Finished checking translations.");
			return true;
		}else{
			plugin.getLogger().log(Level.WARNING, "Failed to check all translations for Tadukoo Bible!");
			return false;
		}
	}
	
	public static boolean tranSave(EnumBooks ebook){
		plugin.getLogger().log(Level.INFO, "Saving translations...");
		if(tranDoSave("KJV", ebook, TBB.TBP)){
			plugin.getLogger().log(Level.INFO, "Finished saving translations.");
			return true;
		}else{
			plugin.getLogger().log(Level.WARNING, "Failed to save all translations.");
			return false;
		}
	}
	
	public static boolean tranDoCheck(String tran, TB TBP, EnumBooks ebook, EnumTrans etran){
		plugin.getLogger().log(Level.INFO, "Checking KJV...");
		if(TBP.getConfig().getBoolean(tran)){
			plugin.getLogger().log(Level.INFO, tran + " = true in Tadukoo Bible config.");
			plugin.getLogger().log(Level.INFO, "Checking if " + tran + " is complete...");
			etran = etran.fromString(tran);
			boolean complete = etran.isComplete();
			boolean[] books = new boolean[66];
			if(complete){
				plugin.getLogger().log(Level.INFO, tran + " is complete.");
				int i = 0;
				while(i < 66){
					books[i] = true;
					i++;
				}
			}else{
				plugin.getLogger().log(Level.INFO, tran + " is not complete.");
				int i = 0;
				while(i < 66){
					books[i] = ebook.fromString(ebook.numtoBook(i + 1, "int", null, null)).isAvailable(tran);
					i++;
				}
			}
			boolean all = plugin.getConfig().getBoolean(tran + ".all.check");
			if(!all){
				int i = 0;
				String book;
				while(i < 66){
					book = ebook.numtoBook(i + 1, "int", null, null);
					if(!plugin.getConfig().getBoolean(tran + "." + book + ".check")){
						books[i] = false;
					}
					i++;
				}
			}
			int i = 0;
			boolean checking;
			String book;
			String type;
			while(i < 66){
				checking = books[i];
				book = ebook.numtoBook(i + 1, "int", null, null);
				plugin.getLogger().log(Level.INFO, "Checking " + book + ": " + checking);
				if(checking){
					ebook = ebook.fromString(book);
					type = plugin.getConfig().getString(tran + "." + book + ".type");
					if(TBP.getBook(book, tran).getString("ch1v1") != null){
						if(!(type.equalsIgnoreCase("check") && TBP.getigBook(book, tran).getBoolean("Done"))){
							Define.run(TBP, plugin, book, ebook, tran);
						}else{
							plugin.getLogger().log(Level.INFO, book + " is already finished.");
						}
					}else{
						plugin.getLogger().log(Level.INFO, "Couldn't find " + book + ".yml.");
						plugin.getLogger().log(Level.INFO, "Check cancelled.");
					}
				}
				i++;
			}
			if(plugin.getConfig().getBoolean(tran + ".list.check")){
				plugin.getLogger().log(Level.INFO, "Checking for list book in " + tran + "...");
				type = plugin.getConfig().getString(tran + ".list.type");
				if(!(type == "check" && TBP.getigBook("List", tran).getBoolean("Done"))){
					makeList(plugin, TBP, ebook, tran, books);
				}
			}
			return true;
		}else{
			plugin.getLogger().log(Level.INFO, tran + " = false or is not found in Tadukoo Bible config.");
		}
		return true;
	}
	
	public static void makeList(TBB plugin, TB TBP, EnumBooks ebook, String tran, boolean[] books){
		int i = 0;
		boolean exists;
		String book;
		String bookName;
		String page = "";
		int pageNum = 1;
		int bookNum = 1;
		while(i < 66){
			exists = books[i];
			if(exists){
				book = ebook.numtoBook(i + 1, "int", null, null);
				ebook = ebook.fromString(book);
				if(ebook.getAlias3() != null){
					bookName = ebook.getAlias3();
				}else if(ebook.getAlias2() != null){
					bookName = ebook.getAlias2();
				}else if(ebook.getAlias() != null){
					bookName = ebook.getAlias();
				}else{
					bookName = ebook.getBook();
				}
				boolean cont = true;
				int j = 1;
				int k = 0;
				page = book + "\n";
				k++;
				while(cont){
					if(TBP.getigBook(book, tran).getString("Book" + j + ".start.c") != null){
						page = page + bookName + " " + j + ": " + 
								TBP.getigBook(book, tran).getString("Book" + j + ".start.c") + ":" 
								+ TBP.getigBook(book, tran).getString("Book" + j + ".start.v") + " - "
								+ TBP.getigBook(book, tran).getString("Book" + j + ".end.c") + ":" 
								+ TBP.getigBook(book, tran).getString("Book" + j + ".end.v") + "\n";
						j++;
						k++;
					}else{
						cont = false;
					}
					if(k == 13 || cont == false){
						if(pageNum == 50){
							bookNum++;
							pageNum = 1;
						}
						TBP.getigBook("List", tran).set("Book" + bookNum + "." + pageNum, page);
						TBP.saveigBook("List", tran);
						page = "";
						pageNum++;
						k = 0;
					}
				}
			}
			i++;
		}
		TBP.getigBook("List", tran).set("Done", true);
		TBP.saveigBook("List", tran);
		plugin.getLogger().log(Level.INFO, "Finished making List book for " + tran + ".");
	}
	
	public static boolean tranDoSave(String tran, EnumBooks ebook, TB TBP){
		if(TBP.getConfig().getBoolean(tran)){
			int i = 0;
			String book;
			while(i < 66){
				book = ebook.numtoBook(i + 1, "int", null, null);
				TBP.saveigBook(book, tran);
				plugin.getLogger().log(Level.INFO, tran + " " + book + " saved.");
				i++;
			}
			return true;
		}else{
			plugin.getLogger().log(Level.INFO, tran + " not saved because " + tran + " = false in Tadukoo " +
					"Bible config.");
			return true;
		}
	}
}
