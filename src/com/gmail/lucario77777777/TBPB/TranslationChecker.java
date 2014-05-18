package com.gmail.lucario77777777.TBPB;

import java.util.logging.Level;

import com.gmail.lucario77777777.TBPB.TBB;
import com.gmail.lucario77777777.TBPB.BookChecker;

public class TranslationChecker {
static TBB plugin = TBB.instance;
	
	public static void tranCheck() {
		plugin.getLogger().log(Level.INFO, "Checking Translations...");
		tranDoCheck("KJV");
	}
	
	public static void tranSave() {
		tranDoSave("KJV");
	}
	
	public static void tranDoCheck(String tran) {
		if(TBB.TBP.getConfig().getBoolean(tran) == true){
			String type = "";
			if(plugin.getConfig().getString(tran + "BookCheck") != null){
				type = plugin.getConfig().getString(tran + "BookCheck");
				plugin.getLogger().log(Level.INFO, tran + "BookCheck is set to " + type + ".");
			}else{
				plugin.getConfig().set(tran + "BookCheck", "ignore");
				type = "ignore";
				plugin.getLogger().log(Level.INFO, "Set " + tran + "BookCheck to ignore.");
			}
			if(type.equalsIgnoreCase("ignore")){
				plugin.getLogger().log(Level.INFO, tran + " ignored.");
				return;
			}else{
				plugin.getLogger().log(Level.INFO, "Checking KJV...");
				BookChecker.booksCheck(plugin, tran, type);
			}
		}
	}
	
	public static void tranDoSave(String tran) {
		if(plugin.getConfig().getBoolean(tran) == true){
			TBB.TBP.saveigBook(tran);
		}
	}
}
