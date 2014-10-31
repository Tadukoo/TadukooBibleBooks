package com.gmail.realtadukoo.TBPB;

import java.util.logging.Level;

import com.gmail.realtadukoo.TBP.TB;
import com.gmail.realtadukoo.TBP.Enums.EnumBooks;
import com.gmail.realtadukoo.TBP.Enums.EnumChps;

public class Define {
	public static void run(TB TBP, TBB plugin, String book, EnumBooks ebook, String tran){
		TBP.getigBook(book, tran).set("Book1.start.c", 1);
		TBP.getigBook(book, tran).set("Book1.start.v", 1);
		plugin.getLogger().log(Level.INFO, "Starting " + book + "...");
		
		// nc = notify chapter, it keeps track of chapters for notifying on chapter progress.
		// ncL = notify chapter limit, when nc = ncL, it will notify the console on chapter progress.
		int nc = 1;
		int ncL = plugin.getConfig().getInt("chapter-notify");
		
		//cL = chapter limit, this is used for when the book is completed.
		int cL = ebook.getChp();
		
		EnumChps echp = EnumChps.GENESIS;
		echp = echp.fromString(book, 1);
		
		//vL = verse limit, this is used for when a chapter is completed.
		int vL = echp.getNum(1);
		
		//tempPage = temporary page, used in preparation for the real page.
		//page = real page
		String tempPage = "Chapter 1\n";
		String page = "";
		String nextPage = "";
		
		//verse = the current verse to be added to temporary page.
		//c = chapter number, used for getting verses.
		//v = verse number, used for getting verses.
		String verse = TBP.getBook(book, tran).getString("ch1v1");
		int c = 1;
		int v = 1;
		
		tempPage = tempPage + verse;
		
		//pageNum = page number, used for the current page in the current Minecraft book.
		//bookNum = book number, used for the current Minecraft book.
		//bookDone = book finished, used to do certain things when a Minecraft book is completed.
		//bibleDone = book of the Bible finished, used to end the loop.
		int pageNum = 1;
		int bookNum = 1;
		boolean bookDone = false;
		boolean bibleDone = false;
		
		//eV = end verse, used for ending a Minecraft book.
		//eC = end chapter, used for ending a Minecraft book.
		int eV;
		int eC;
		
		//sV = start verse, used for starting a Minecraft book.
		//sC = start chapter, used for starting a Minecraft book.
		int sV = 1;
		int sC = 1;
		
		if(ncL != 0){
			plugin.getLogger().log(Level.INFO, "Starting " + book + " Chapter 1...");
		}
		boolean cont = true;
		
		while(cont){
			if(page != ""){
				TBP.getigBook(book, tran).set("Book" + bookNum + "." + pageNum, page);
				TBP.saveigBook(book, tran);
				page = "";
				if(pageNum == 50 || bibleDone){
					if(!bibleDone){
						if(v == 1){
							eC = c - 1;
							eV = echp.getNum(eC);
						}else{
							eC = c;
							eV = v - 1;
						}
					}else{
						eC = c;
						eV = v;
					}
					sC = c;
					sV = v;
					TBP.getigBook(book, tran).set("Book" + bookNum + ".end.c", eC);
					TBP.getigBook(book, tran).set("Book" + bookNum + ".end.v", eV);
					TBP.saveigBook(book, tran);
					if(pageNum == 50 && !bibleDone){
						pageNum = 0;
						bookNum++;
						nextPage = "Chapter " + c + " Cont.\n" + nextPage;
						bookDone = true;
					}else if(bibleDone){
						cont = false;
						break;
					}
				}
				pageNum++;
			}
			if(nextPage != ""){
				tempPage = nextPage;
				nextPage = "";
			}
			if(v == vL){
				if(c == cL){
					bibleDone = true;
					TBP.getigBook(book, tran).set("Done", true);
					TBP.saveigBook(book, tran);
					plugin.getLogger().log(Level.INFO, "Finished " + book + "...");
				}else{
					c++;
					v = 1;
					vL = echp.getNum(c);
					if(nc == ncL){
						plugin.getLogger().log(Level.INFO, "Starting " + book + " Chapter " + c + "...");
						nc = 0;
					}
					nc++;
					verse = TBP.getBook(book, tran).getString("ch" + c + "v" + v);
					nextPage = "Chapter " + c + "\n" + verse;
				}
				page = tempPage;
				tempPage = "";
			}else{
				v++;	
				if(bookDone){
					TBP.getigBook(book, tran).set("Book" + bookNum + ".start.c", sC);
					TBP.getigBook(book, tran).set("Book" + bookNum + ".start.v", sV);
					TBP.saveigBook(book, tran);
					bookDone = false;
				}
				verse = TBP.getBook(book, tran).getString("ch" + c + "v" + v);
				if(pageNum == 50 && (tempPage + " &l" + v + "&r" + verse).length() > 256){
					page = tempPage;
					nextPage = "&l" + v + "&r" + verse;
				}else{
					tempPage = tempPage + " &l" + v + "&r" + verse;
				}
				if(!(tempPage.length() < 256)){
					if(tempPage.length() == 256){
						page = tempPage;
					}else{
						String[] words = tempPage.split(" ");
						boolean contA = true;
						boolean contB = true;
						int iL = words.length;
						page = words[0];
						int i = 1;
						while(contA){
							if((page + " " + words[i]).length() <= 256){
								page = page + " " + words[i];
								i++;
							}else{
								contA = false;
							}
						}
						nextPage = words[i];
						i++;
						while(contB){
							if(i < iL){
								nextPage = nextPage + " " + words[i];
								i++;
							}else{
								contB = false;
							}
						}
					}
					tempPage = "";
				}
			}
		}
	}
}
