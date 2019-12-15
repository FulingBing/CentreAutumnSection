package com.github.FulingBing.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;

import com.github.FulingBing.CentreAutumnSectionConfig;

public class CentreAutumnSectionLookMoonListener implements Listener{
	//发射
	@EventHandler(priority = EventPriority.NORMAL,ignoreCancelled=true)
	public void BlockDispenseEvent(BlockDispenseEvent e) {
		if(e.isCancelled()) {
			return;
		}
		String[] tpPoint=CentreAutumnSectionConfig.getMoonTp();
		if(tpPoint[0].equals("~") && tpPoint[1].equals("~") && tpPoint[2].equals("~")) {
			return;
		}
		Location loc=e.getBlock().getLocation();
		if(loc.getBlockY()<1) {
			return;
		}
		loc.setY(loc.getY()-1);
		Block b=loc.getBlock();
		if(b.getType()!=Material.GLOWSTONE) {
			return;
		}
		loc.setY(loc.getY()+2);
		List<Player> pl=new ArrayList<Player>();
		for(Player p:loc.getWorld().getPlayers()) {
			Location l=p.getLocation();
			if(l.getBlockX()==loc.getBlockX() && l.getBlockY()==loc.getBlockY() && l.getBlockZ()==loc.getBlockZ()) {
				pl.add(p);
			}
		}
		if(pl.isEmpty()) {
			return;
		}
		loc.getWorld().createExplosion(loc, 0.0F, false);
		if(!tpPoint[0].equals("~")) {
			loc.setX(Double.parseDouble(tpPoint[0]));
		}
		if(!tpPoint[1].equals("~")) {
			loc.setY(Double.parseDouble(tpPoint[1]));
		}
		if(!tpPoint[2].equals("~")) {
			loc.setZ(Double.parseDouble(tpPoint[2]));
		}
		for(Player p:pl) {
			p.teleport(loc);
		}
	}
}
