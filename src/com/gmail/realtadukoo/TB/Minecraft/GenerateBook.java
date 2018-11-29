package com.gmail.realtadukoo.TB.Minecraft;

import java.io.IOException;
import java.util.ArrayList;

import com.gmail.realtadukoo.TB.VerseReading;
import com.gmail.realtadukoo.TB.Enums.EnumTranslations;
import com.gmail.realtadukoo.TB.Enums.Bible.EnumBible;
import com.gmail.realtadukoo.TB.Enums.Bible.EnumBibleChapters;

public class GenerateBook{
	
	public static void generateWholeTranslation(EnumTranslations tran){
		for(int i = 1; i <= 66; i++){
			generateWholeBook(tran, EnumBible.fromInt(i));
		}
	}
	
	public static void generateWholeBook(EnumTranslations tran, EnumBible book){
		for(int i = 1; i <= EnumBibleChapters.fromBook(book.getBook()).getChps().length; i++){
			generateWholeChapter(tran, book, i);
		}
		System.out.println(book.getBook() + " Done");
	}
	
	public static void generateWholeChapter(EnumTranslations tran, EnumBible book, int chp){
		int bookNum = 1, pageNum = 1;
		if(chp != 1){
			String bookAndPageEnd = "";
			try{
				bookAndPageEnd = 
						MinecraftBookFiles.loadMinecraftBook(book, tran).getProperty("Ch" + (chp-1));
			}catch(IOException e){
				e.printStackTrace();
			}
			bookAndPageEnd = bookAndPageEnd.replace("B", "");
			String[] bookPageSplit = bookAndPageEnd.split("P");
			bookNum = Integer.parseInt(bookPageSplit[0]);
			pageNum = Integer.parseInt(bookPageSplit[1]);
			if(pageNum == 50){
				bookNum++;
				pageNum = 1;
			}else{
				pageNum++;
			}
		}
		String page = "Chapter " + chp + "\n";
		
		ArrayList<String> verses = new ArrayList<String>();
		for(int i = 1; i <= EnumBibleChapters.fromBook(book.getBook()).getNum(chp); i++){
			verses.add(VerseReading.getVerse(book, chp, i, tran));
		}
		for(int i = 0; i < verses.size(); i++){
			if(page.length() + verses.get(i).length() + 9 <= 256){
				if(i == 0){
					page += verses.get(i);
				}else{
					page += " &l" + (i + 1) + "&r" + verses.get(i);
				}
			}else{
				if(pageNum == 50){
					MinecraftBookFiles.addToMinecraftBook(book, tran, bookNum, pageNum, page);
					bookNum++;
					pageNum = 1;
					page = "Chapter " + chp + " Cont.\n" + verses.get(i);
				}else{
					String[] verseSplit = verses.get(i).split(" ");
					if(page.length() + verseSplit[0].length() + 9 > 256){
						MinecraftBookFiles.addToMinecraftBook(book, tran, bookNum, pageNum, page);
						page = "&l" + (i + 1) + "&r" + verses.get(i);
						if(pageNum == 50){
							bookNum++;
							pageNum = 1;
							page = "Chapter " + chp + " Cont.\n" + page;
						}else{
							pageNum++;
						}
					}else{
						page += " &l" + (i + 1) + "&r" + verseSplit[0];
						for(int j = 1; j < verseSplit.length; j++){
							if(page.length() + verseSplit[j].length() + 1 > 256){
								MinecraftBookFiles.addToMinecraftBook(book, tran, bookNum, pageNum, 
										page);
								page = verseSplit[j];
								if(pageNum == 50){
									bookNum++;
									pageNum = 1;
									page = "Chapter " + chp + " Cont.\n" + page;
								}else{
									pageNum++;
								}
							}else{
								page += " " + verseSplit[j];
							}
						}
					}
				}
			}
		}
		MinecraftBookFiles.addToMinecraftBook(book, tran, bookNum, pageNum, page);
		MinecraftBookFiles.addToMinecraftBook(book, tran, chp, bookNum, pageNum);
		System.out.println(book.getBook() + " Chapter " + chp + " Done");
	}
}
