package com.gmail.realtadukoo.TB.Minecraft.API.Books;

import java.util.ArrayList;

import com.gmail.realtadukoo.TB.API.Constants.EnumBible;
import com.gmail.realtadukoo.TB.API.Constants.EnumTranslation;

public class ChpWorkInfo{
	private EnumBible book;
	private int chp;
	private EnumTranslation tran;
	private ArrayList<String> pages;
	
	public ChpWorkInfo(EnumBible book, int chp, EnumTranslation tran){
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
	
	public EnumTranslation getTran(){
		return tran;
	}
	
	public ArrayList<String> getPages(){
		return pages;
	}
	
	public void setPages(ArrayList<String> pages){
		this.pages = pages;
	}
}
