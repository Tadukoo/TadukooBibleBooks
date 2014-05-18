package com.gmail.lucario77777777.TBPB;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.lucario77777777.TBPB.TBB;
import com.gmail.lucario77777777.TBPB.TranslationChecker;

import com.gmail.lucario77777777.TBP.Main;

public class TBB extends JavaPlugin {
	public static TBB plugin;
	PluginDescriptionFile pdfFile = this.getDescription();
	public FileConfiguration book = null;
	public File bookFile = null;
	public FileConfiguration igbook = null;
	public File igbookFile = null;
	public static TBB instance;
	public Boolean perms = null;
	static Main TBP;
	public static File TBPF;
	
	@Override
	public void onDisable () {
		saveConfig();
		TranslationChecker.tranSave();
	}
	@Override
	public void onEnable () {
		getLogger().log(Level.INFO, "Hooking into Tadukoo Bible...");
		boolean T = setupTBP();
		if(T == true){
			getLogger().log(Level.INFO, "Hooked into Tadukoo Bible.");
		}else{
			getLogger().log(Level.WARNING, "Failed to hook into Tadukoo Bible.");
		}
		instance = this;
		getConfig().options().copyDefaults(true);
		saveConfig();
		TranslationChecker.tranCheck();
		perms = getConfig().getBoolean("Permissions");
	}
	
	private Boolean setupTBP(){
		TBP = (Main) getServer().getPluginManager().getPlugin("Tadukoo_Bible");
		return TBP != null;
	}
}
