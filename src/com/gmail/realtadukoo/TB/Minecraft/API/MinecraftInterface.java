package com.gmail.realtadukoo.TB.Minecraft.API;

public interface MinecraftInterface{
	
	public void sendMessageToPlayer(String playerName, String color, String message);
	
	public void printMessageToConsole(String priority, String color, String message);
	
	public void hasPermission(String playerName, String permission);
}
