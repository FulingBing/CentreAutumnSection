package com.github.FulingBing.Listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.github.FulingBing.CentreAutumnSectionConfig;
import com.github.FulingBing.core.CentreAutumnSectionMoonCakeCore;
import com.github.FulingBing.core.CentreAutumnSectionRiddleCore;
import com.github.FulingBing.pojo.CentreAutumnSectionMoonCake;

public class CentreAutumnSectionMoonCakeListener implements Listener{
	//食月饼
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void PlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
		if(e.isCancelled()) {
			return;
		}
		ItemStack is=e.getItem();
		CentreAutumnSectionMoonCake c=CentreAutumnSectionMoonCakeCore.isMoonCake(is);
		if(c!=null) {
			for(String cmd:c.cmd) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", e.getPlayer().getName()));
			}
			if((new Random()).nextInt(100)<c.giveRiddle && (!CentreAutumnSectionConfig.getRiddle().isEmpty())) {
				PlayerInventory pi=e.getPlayer().getInventory();
				if(CentreAutumnSectionMoonCakeCore.isNoItem(pi)) {
					return;
				}
				ItemStack riddle=CentreAutumnSectionRiddleCore.getRiddle();
				pi.addItem(new ItemStack[]{riddle});
				e.getPlayer().sendMessage(CentreAutumnSectionConfig.getMoonCakeGive());
			}
		}
	}
}
