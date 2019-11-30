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
import org.bukkit.util.Vector;

import com.github.FulingBing.CentreAutumnSectionConfig;

public class CentreAutumnSectionLookMoonListener implements Listener{
	//发射
	@EventHandler(priority = EventPriority.NORMAL,ignoreCancelled=true)
	public void BlockDispenseEvent(BlockDispenseEvent e) {
		if(e.isCancelled() && CentreAutumnSectionConfig.getJump()!=0) {
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
		//Vector v=new Vector(0,CentreAutumnSectionConfig.getJump(),0);
		//for(Player p:pl) {
		//	p.setVelocity(v);
		//}
		//针对服务器的某种谜之操作
		if(CentreAutumnSectionConfig.isJumpByTp()) {
			loc.setY(loc.getY()+CentreAutumnSectionConfig.getJump());
			for(Player p:pl) {
				p.teleport(loc);
			}
		}else {
			Vector v=new Vector(0,CentreAutumnSectionConfig.getJump(),0);
			for(Player p:pl) {
				p.setVelocity(v);
			}
		}
	}
}
