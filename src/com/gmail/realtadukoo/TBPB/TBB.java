package com.gmail.realtadukoo.TBPB;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.realtadukoo.TBP.Enums.EnumBooks;
import com.gmail.realtadukoo.TBP.Enums.EnumTrans;

import com.gmail.realtadukoo.TBP.TB;
import com.gmail.realtadukoo.TBPB.Checker;
import com.gmail.realtadukoo.TBPB.TBB;

public class TBB extends JavaPlugin {
	public static TBB plugin;
	PluginDescriptionFile pdfFile = this.getDescription();
	public static TBB instance;
	public static TB TBP;
	public static File TBPF;
	public static EnumBooks ebook;
	public static EnumTrans etran;
	
	@Override
	public void onDisable () {
		saveConfig();
		Checker.tranSave(ebook);
		plugin = null;
		pdfFile = null;
		instance = null;
		TBP = null;
		TBPF = null;
		ebook = null;
		etran = null;
	}
	@Override
	public void onEnable () {
		getLogger().log(Level.INFO, "Checking for Tadukoo Bible...");
		if(setupTBP()){
			getLogger().log(Level.INFO, "Hooked into Tadukoo Bible.");
		}else{
			getLogger().log(Level.WARNING, "Failed to hook into Tadukoo Bible.");
		}
		instance = this;
		Checker.tranCheck(TBP, ebook, etran);
	}
	
	private Boolean setupTBP(){
		TBP = (TB) getServer().getPluginManager().getPlugin("Tadukoo_Bible");
		ebook = EnumBooks.GENESIS;
		ebook = ebook.getDefault();
		etran = EnumTrans.KJV;
		etran = etran.getDefault();
		return TBP != null;
	}
}
