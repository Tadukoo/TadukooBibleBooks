package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

public class TranslationChecker {
static TBB plugin = TBB.instance;

	public static boolean tranCheck(){
		plugin.getLogger().log(Level.INFO, "Checking translations...");
		if(tranDoCheck("KJV")){
			plugin.getLogger().log(Level.INFO, "Finished checking translations.");
			return true;
		}else{
			plugin.getLogger().log(Level.WARNING, "Failed to check all translations.");
			return false;
		}
	}
	
	public static boolean tranSave(){
		plugin.getLogger().log(Level.INFO, "Saving translations...");
		if(tranDoSave("KJV")){
			plugin.getLogger().log(Level.INFO, "Finished saving translations.");
			return true;
		}else{
			plugin.getLogger().log(Level.WARNING, "Failed to save all translations.");
			return false;
		}
	}
	
	public static boolean tranDoCheck(String tran){
		plugin.getLogger().log(Level.INFO, "Checking KJV...");
		if(TBB.TBP.getConfig().getBoolean(tran)){
			plugin.getLogger().log(Level.INFO, tran + " = true in Tadukoo Bible config, checking TBB " +
					"config...");
			String type;
			if(plugin.getConfig().getString(tran + "BookCheck") != null){
				type = plugin.getConfig().getString(tran + "BookCheck");
				plugin.getLogger().log(Level.INFO, tran + "BookCheck = " + type);
			}else{
				type = "ignore";
				plugin.getConfig().set(tran + "BookCheck", "ignore");
				plugin.getLogger().log(Level.INFO, tran + "BookCheck not found, setting to ignore...");
			}
			if(type.equalsIgnoreCase("ignore")){
				plugin.getLogger().log(Level.INFO, tran + " ignored.");
			}else{
				plugin.getLogger().log(Level.INFO, "Checking " + tran);
				BookChecker.booksCheck(plugin, tran, type);
			}
		}else{
			plugin.getLogger().log(Level.INFO, tran + " = false or is not found in Tadukoo Bible config.");
		}
		return true;
	}
	
	public static boolean tranDoSave(String tran){
		if(TBB.TBP.getConfig().getBoolean(tran)){
			TBB.TBP.saveigBook(tran);
			plugin.getLogger().log(Level.INFO, tran + " saved.");
			return true;
		}else{
			plugin.getLogger().log(Level.INFO, tran + " not saved because " + tran + " = false in Tadukoo " +
					"Bible config.");
			return true;
		}
	}
}
