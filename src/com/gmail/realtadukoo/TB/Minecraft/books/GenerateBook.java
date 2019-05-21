package com.gmail.realtadukoo.TB.Minecraft.books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.gmail.realtadukoo.TB.VerseReading;
import com.gmail.realtadukoo.TB.Bible.BibleReference;
import com.gmail.realtadukoo.TB.Bible.EnumBible;
import com.gmail.realtadukoo.TB.Bible.EnumBibleChapters;
import com.gmail.realtadukoo.TB.Bible.EnumTranslations;

public class GenerateBook{
	
	public static void generateWholeBible(int numThreads, EnumTranslations tran){
		Queue<ChpWorkInfo> todo, done;
		todo = new Queue<ChpWorkInfo>(150);
		done = new Queue<ChpWorkInfo>(150);
		
		// Start Worker threads
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < numThreads; i++){
			ChpWorker worker = new ChpWorker(todo, done);
			threads.add(new Thread(worker));
			threads.get(i).start();
		}
		
		// Generate All Books
		for(int i = 1; i <= 66; i++){
			EnumBible book = EnumBible.fromInt(i);
			generateWholeBook(book, tran, todo, done, numThreads);
		}
		
		// Send terminate infos
		for(int i = 0; i < numThreads; i++){
			try{
				todo.enqueue(new ChpWorkInfo(null, -1, null));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		// End Worker threads
		for(int i = 0; i < numThreads; i++){
			try{
				threads.get(i).join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void generateWholeBook(EnumBible book, EnumTranslations tran, Queue<ChpWorkInfo> todo, Queue<ChpWorkInfo> done,
			int numThreads){
		// Generate an entire book of the Bible

		// Send out work
		int chps = EnumBibleChapters.fromBook(book.getBook()).getChps().length;
		for(int j = 1; j <= chps; j++){
			//System.out.println("Making Work for " + book.book() + " Chp " + j);
			ChpWorkInfo work = new ChpWorkInfo(book, j, tran);
			try{
				todo.enqueue(work);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		// Receive work
		Properties bookFile = new Properties();
		int bookNum = 1, pageNum = 1;
		int chpDone = 0;
		ArrayList<ChpWorkInfo> works = new ArrayList<ChpWorkInfo>();
		for(int j = 1; j <= chps; j++){
			try{
				//System.out.println("Trying to Receive Stuff...");
				ChpWorkInfo info = done.dequeue();
				//System.out.println("Received " + info.getBook().book() + " Chp " + info.getChp());
				if(info.getChp() == chpDone + 1){
					for(String page: info.getPages()){
						bookFile.put("Book" + bookNum + "Page" + pageNum, page);
						pageNum++;
						if(pageNum > 50){
							bookNum++;
							pageNum = 1;
						}
					}
					//System.out.println("Added " + info.getBook().book() + " Chp " + info.getChp());
					chpDone++;
					while(works.size() > 0 && works.get(0).getChp() == chpDone + 1){
						info = works.remove(0);
						for(String page: info.getPages()){
							bookFile.put("Book" + bookNum + "Page" + pageNum, page);
							pageNum++;
							if(pageNum > 50){
								bookNum++;
								pageNum = 1;
							}
						}
						//System.out.println("Added " + info.getBook().book() + " Chp " + info.getChp());
						chpDone++;
					}
				}else{
					boolean infoInArray = false;
					for(int k = 0; k < works.size(); k++){
						if(works.get(k).getChp() > info.getChp()){
							works.add(k, info);
							infoInArray = true;
							break;
						}
					}
					if(!infoInArray){
						works.add(info);
					}
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		try{
			MinecraftBookFiles.saveMinecraftBook(bookFile, book, tran);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> generateWholeChapter(EnumBible book, int chp, EnumTranslations tran){
		ArrayList<String> pages = new ArrayList<String>();
		String page = "Chapter " + chp + "\n";
		
		ArrayList<String> verses = new ArrayList<String>();
		BibleReference ref = new BibleReference(book, chp, 1, tran);
		for(int i = 1; i <= EnumBibleChapters.fromBook(book.getBook()).getNum(chp); i++){
			ref.setVerse(i);
			verses.add(VerseReading.getVerse(ref));
		}
		for(int i = 0; i < verses.size(); i++){
			if(page.length() + verses.get(i).length() + 9 <= 256){
				if(i == 0){
					page += verses.get(i);
				}else{
					page += " &l" + (i + 1) + "&r" + verses.get(i);
				}
			}else{
				String[] verseSplit = verses.get(i).split(" ");
				if(page.length() + verseSplit[0].length() + 9 > 256){
					pages.add(page);
					page = "&l" + (i + 1) + "&r" + verses.get(i);
				}else{
					page += " &l" + (i + 1) + "&r" + verseSplit[0];
					for(int j = 1; j < verseSplit.length; j++){
						if(page.length() + verseSplit[j].length() + 1 > 256){
							pages.add(page);
							page = verseSplit[j];
						}else{
							page += " " + verseSplit[j];
						}
					}
				}
			}
		}
		pages.add(page);
		return pages;
	}
}
