package com.gmail.lucario77777777.TBPB;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.lucario77777777.TBPB.TBB;
import com.gmail.lucario77777777.TBPB.TranslationChecker;

import com.gmail.lucario77777777.TBP.TB;

public class TBB extends JavaPlugin {
	public static TBB plugin;
	PluginDescriptionFile pdfFile = this.getDescription();
	public static TBB instance;
	static TB TBP;
	public static File TBPF;
	
	@Override
	public void onDisable () {
		saveConfig();
		TranslationChecker.tranSave();
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
		TranslationChecker.tranCheck();
	}
	
	private Boolean setupTBP(){
		TBP = (TB) getServer().getPluginManager().getPlugin("Tadukoo_Bible");
		return TBP != null;
	}
}
