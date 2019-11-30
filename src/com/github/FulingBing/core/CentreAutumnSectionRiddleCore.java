package com.github.FulingBing.core;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.FulingBing.CentreAutumnSectionConfig;
import com.github.FulingBing.pojo.CentreAutumnSectionRiddle;

public class CentreAutumnSectionRiddleCore {
	//获取一个谜题
	public static ItemStack getRiddle() {
		int n=(new Random()).nextInt(CentreAutumnSectionConfig.getRiddle().size());
		CentreAutumnSectionRiddle casr=CentreAutumnSectionConfig.getRiddle().get(n);
		ItemStack is=new ItemStack(Material.PAPER);
		ItemMeta im=is.getItemMeta();
		im.setDisplayName(CentreAutumnSectionConfig.getRiddleName());
		im.setLore(casr.lore);
		is.setItemMeta(im);
		return is;
	}
	
	//获取谜题的答案
	public static CentreAutumnSectionRiddle getRiddleByLore(List<String> lore) {
		for(CentreAutumnSectionRiddle c:CentreAutumnSectionConfig.getRiddle()) {
			//if(lore.equals(c.lore)) {
			if(CentreAutumnSectionMoonCakeCore.equalsLore(lore,c.lore)) {//针对部分谜之核心
				return c;
			}
		}
		return null;
	}
	
	//判断物品是否是谜题
	public static boolean isRiddle(ItemStack is) {
		if(is==null) {
			return false;
		}
		if(is.getType()==Material.PAPER) {
			ItemMeta im=is.getItemMeta();
			if(im.hasDisplayName()) {
				//if(im.getDisplayName().equals(CentreAutumnSectionConfig.getRiddleName())) {
				if(im.getDisplayName().endsWith(CentreAutumnSectionConfig.getRiddleName())) {//针对部分谜之核心
					return im.hasLore();
				}
			}
		}
		return false;
	}
	
	//判断是否是谜题获取方块调节器
	public static boolean isRiddleBlockItem(ItemStack is) {
		if(is==null) {
			return false;
		}
		if(is.getType()==Material.FEATHER) {
			ItemMeta im=is.getItemMeta();
			if(im.hasDisplayName()) {
				//if(im.getDisplayName().equals(CentreAutumnSectionConfig.getRiddleItem())) {
				if(im.getDisplayName().endsWith(CentreAutumnSectionConfig.getRiddleItem())) {//针对部分谜之核心
					if(im.hasLore()) {
						return CentreAutumnSectionMoonCakeCore.equalsLore(im.getLore(),CentreAutumnSectionConfig.getRiddleLore());
					}
				}
			}
		}
		return false;
	}
	
	//判断谜题是否猜对
	public static boolean isRiddleOk(CentreAutumnSectionRiddle c,String str) {
		for(String tmp:c.answer) {
			if(tmp.toLowerCase().equals(str.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	//判断是否是获得谜题的方块
	public static boolean isRiddleBlock(Block b) {
		Location loc=b.getLocation();
		String tmp=loc.getWorld()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
		for(String str:CentreAutumnSectionConfig.getRiddleBlock()) {
			if(str.equals(tmp)) {
				return true;
			}
		}
		return false;
	}
	
	//添加或删除获得谜题的方块
	public static String changeRiddleBlock(Block b) {
		Location loc=b.getLocation();
		String tmp=loc.getWorld()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ();
		List<String> str=CentreAutumnSectionConfig.getRiddleBlock();
		for(int i=0;i<str.size();i++) {
			if(str.get(i).equals(tmp)) {
				str.remove(i);
				CentreAutumnSectionConfig.setRiddleBlock(str);
				return CentreAutumnSectionConfig.getRiddleBlockDel();
			}
		}
		str.add(tmp);
		CentreAutumnSectionConfig.setRiddleBlock(str);
		return CentreAutumnSectionConfig.getRiddleBlockAdd();
	}
}
