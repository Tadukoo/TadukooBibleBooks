package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

import com.gmail.lucario77777777.TBPB.TBB;

public class BookDefine {
	public static void run(TBB plugin, String tran, String bookName, String cmd){
		/*
		 * page = current page being worked on
		 * realPage = page to be added to config
		 * nextPage = next page to be worked on
		 */
		String page = "", realPage = null, nextPage = null;
		/*
		 * v = verse
		 * lv = 
		 * c = chapter
		 * lc = 
		 */
		int v = 1, lv = 0, c = 0, lc = 0;
		/*
		 * pageNum = current page #
		 * bookNum = current book #
		 */
		int pageNum = 1, bookNum = 1;
		/*
		 * j = amount of chapters gone through since last message
		 * jL = amount of chapters to go through before printing a message
		 */
		int j = 0, jL = plugin.getConfig().getInt("BookConfigChapterNotifications");
		/*
		 * cont = continue
		 * chpD = chapter done
		 */
		boolean cont = true, chpD = false;
		TBB.TBP.getigBook(tran).set(bookName + "1Start", "1:1");
		TBB.TBP.saveigBook(tran);
		while(cont == true){
			if(page == "" && nextPage != null){
				page = nextPage;
				nextPage = null;
				chpD = false;
			}
			if(page.length() < 256){
				if(chpD == false){
					if(TBB.TBP.getBook(tran, bookName).getString("ch" + c + "v" + v) == null){
						lc = c;
						c++;
						if(c != 1){
							nextPage = "\n";
							nextPage = nextPage + "Chapter " + c + "\n";
						}else{
							nextPage = "Chapter " + c + "\n";
						}
						chpD = true;
						j++;
						if(j == jL){
							j = 0;
							int chp = c;
							plugin.getLogger().log(Level.INFO, "Starting " + bookName + " Chapter " + 
									chp + "...");
						}
						lv = v;
						v = 1;
						if(TBB.TBP.getBook(tran, bookName).getString("ch" + c + "v" + v) == null){
							cont = false;
							realPage = page;
							int sv = lv - 1;
							TBB.TBP.getigBook(tran).set(bookName + bookNum + "End", lc + ":" + sv);
							TBB.TBP.saveigBook(tran);
						}
					}else{
						if(pageNum == 50){
							String temp = " " + v + " ";
							if (page.length() + temp.length() + TBB.TBP.getBook(tran, bookName).getString("ch"
									+ c + "v" + v).length() > 256){
								realPage = page;
								int sc = 0;
								if(v == 1){
									sc = lc;
								}else{
									sc = c;
								}
								int sbookNum = bookNum + 1;
								TBB.TBP.getigBook(tran).set(bookName + bookNum + "End", sc + ":" + lv);
								TBB.TBP.getigBook(tran).set(bookName + sbookNum + "Start", c + ":" + v);
								TBB.TBP.saveigBook(tran);
							}else{
								page = page + " ";
								if(v != 1){
									page = page + v + " ";
								}
								page = page + TBB.TBP.getBook(tran, bookName).getString("ch" + c + "v" + v);
								lv = v;
								v++;
							}
							temp = null;
						}else{
							page = page + " ";
							if(v != 1){
								page = page + v + " ";
							}
							page = page + TBB.TBP.getBook(tran, bookName).getString("ch" + c + "v" + v);
							lv = v;
							v++;
						}
					}
				}else{
					realPage = page;
				}
			}
			if(page.length() == 256){
				realPage = page;
			}else if(page.length() > 256){
				String[] sPage = page.split(" ");
				int sL = sPage.length;
				String tPage = "";
				String trPage = "";
				String tnPage = "";
				int k = 0;
				boolean sCont = true;
				boolean nCont = true;
				while(sCont == true){
					if(k < sL){
						tPage = tPage + sPage[k] + " ";
						k++;
					}else{
						sCont = false;
					}
					if(tPage.length() <= 256){
						trPage = tPage;
					}else{
						int tpL = tPage.length();
						int trpL = trPage.length();
						tnPage = tPage.substring(trpL, tpL);
						sCont = false;
					}
				}
				while(nCont == true){
					if(k < sL){
						tnPage = tnPage + sPage[k] + " ";
						k++;
					}else{
						nCont = false;
					}
				}
				realPage = trPage;
				nextPage = tnPage;
				trPage = "";
				tnPage = null;
			}
			if(pageNum > 50){
				bookNum++;
				pageNum = 1;
			}
			if(realPage != null){
				if(cmd.equalsIgnoreCase("write")){
					TBB.TBP.getigBook(tran).set(bookName + "Book" + bookNum + "." + pageNum, realPage);
					TBB.TBP.saveigBook(tran);
				}else if(cmd.equalsIgnoreCase("fix")){
					if(realPage != TBB.TBP.getigBook(tran).getString(bookName + "Book" + bookNum + "." 
							+ pageNum)){
						TBB.TBP.getigBook(tran).set(bookName + "Book" + bookNum + "." + pageNum, realPage);
						TBB.TBP.saveigBook(tran);
					}
				}else{
					plugin.getLogger().log(Level.SEVERE, "An error occured in creating the book " +
							"config for " +	tran + ".");
				}
				if(TBB.TBP.getigBook(tran).getString(bookName + bookNum + "End") != null){
					TBB.TBP.getigBook(tran).set(bookName + "Part" + bookNum + "Done", true);
					TBB.TBP.saveigBook(tran);
				}
				pageNum++;
				realPage = null;
				page = "";
			}
			if(cont == false){
				plugin.getLogger().log(Level.INFO, bookName + " finished.");
				TBB.TBP.getigBook(tran).set(bookName + "Done", true);
				plugin.getLogger().log(Level.INFO, "Saving " + tran + "bookconfig.yml...");
				TBB.TBP.saveigBook(tran);
				return;
			}
		}
	}
}
