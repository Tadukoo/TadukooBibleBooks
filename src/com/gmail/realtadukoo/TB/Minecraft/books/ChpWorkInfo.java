package com.gmail.realtadukoo.TB.Minecraft.books;

import java.util.ArrayList;

import com.gmail.realtadukoo.TB.Bible.EnumBible;
import com.gmail.realtadukoo.TB.Bible.EnumTranslations;

public class ChpWorkInfo{
	private EnumBible book;
	private int chp;
	private EnumTranslations tran;
	private ArrayList<String> pages;
	
	public ChpWorkInfo(EnumBible book, int chp, EnumTranslations tran){
		this.book = book;
		this.chp = chp;
		this.tran = tran;
		pages = new ArrayList<String>();
	}
	
	public EnumBible getBook(){
		return book;
	}
	
	public int getChp(){
		return chp;
	}
	
	public EnumTranslations getTran(){
		return tran;
	}
	
	public ArrayList<String> getPages(){
		return pages;
	}
	
	public void setPages(ArrayList<String> pages){
		this.pages = pages;
	}
}
