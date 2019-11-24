package com.gmail.realtadukoo.TB.Minecraft;

import com.gmail.realtadukoo.TB.API;

/**
 * Class used for storing Minecraft API related information.
 * 
 * @author Logan Ferree (Tadukoo)
 */
public final class MinecraftAPI{
	/** The current version of the Minecraft API */
	public static final String version = "2.0-Alpha1-SNAPSHOT";
	/** The version of the main TadukooBible API this supports */
	public static final String supportedTBVersion = "2.0-Alpha1-SNAPSHOT";
	/** The release date of this Minecraft API version */
	public static final String releaseDate = "November 23, 2019";
	
	/** @return Whether or not the currently used TadukooBible API is officially supported */
	public static boolean APIVersionSupported(){
		return supportedTBVersion.equalsIgnoreCase(API.version);
	}
}
