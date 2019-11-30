package com.github.FulingBing.core;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.FulingBing.CentreAutumnSectionConfig;
import com.github.FulingBing.pojo.CentreAutumnSectionMoonCake;

public class CentreAutumnSectionMoonCakeCore {
	//加载月饼的合成表
	public static void loadRecipe() {
		ItemStack is=new ItemStack(Material.COOKIE);
		for(CentreAutumnSectionMoonCake c:CentreAutumnSectionConfig.getMooncake()) {
			ItemMeta im=is.getItemMeta();
			im.setDisplayName(c.name);
			if(!c.lore.isEmpty()) {
				im.setLore(c.lore);
			}
			is.setItemMeta(im);
			if(c.shape.size()==3) {
				boolean yes=true;
				String[][] line=new String[3][3];
				for(int i=0;i<3;i++) {
					line[i]=c.shape.get(i).split(",");
					if(line[i].length!=3) {
						yes=false;
						System.out.println(CentreAutumnSectionConfig.getMoonCakeError().replaceAll("%name%", c.name));
						break;
					}
					for(int j=0;j<3;j++) {
						if(Material.getMaterial(line[i][j])==null) {
							yes=false;
							System.out.println(CentreAutumnSectionConfig.getMoonCakeMiss().replaceAll("%name%", c.name).replaceAll("%item%", line[i][j]));
						}
					}
				}
				Bukkit.addRecipe(new ShapedRecipe(is).shape(new String[] { "ABC", "DEF", "GHI"})
					.setIngredient('A', Material.getMaterial(line[0][0])).setIngredient('B', Material.getMaterial(line[0][1])).setIngredient('C', Material.getMaterial(line[0][2]))
					.setIngredient('D', Material.getMaterial(line[1][0])).setIngredient('E', Material.getMaterial(line[1][1])).setIngredient('F', Material.getMaterial(line[1][2]))
					.setIngredient('G', Material.getMaterial(line[2][0])).setIngredient('H', Material.getMaterial(line[2][1])).setIngredient('I', Material.getMaterial(line[2][2]))
				);
			}else {
				System.out.println(CentreAutumnSectionConfig.getMoonCakeError().replaceAll("%name%", c.name));
			}
		}
	}
	
	//判断是否是一个月饼
	public static CentreAutumnSectionMoonCake isMoonCake(ItemStack is) {
		if(is==null) {
			return null;
		}
		if(is.getType()==Material.COOKIE) {
			ItemMeta im=is.getItemMeta();
			if(im.hasDisplayName()) {
				for(CentreAutumnSectionMoonCake c:CentreAutumnSectionConfig.getMooncake()) {
					//if(im.getDisplayName().equals(c.name)) {
					if(im.getDisplayName().endsWith(c.name)) {//针对部分谜之核心
						if(c.lore.isEmpty()) {
							if(im.hasLore()) {
								return null;
							}else {
								return c;
							}
						}else {
							if(!im.hasLore()) {
								return null;
							}
							//if(im.getLore().equals(c.lore)) {
							if(equalsLore(im.getLore(),c.lore)) {//针对部分谜之核心
								return c;
							}else {
								return null;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	//判断背包是否还有空位
	@SuppressWarnings("deprecation")
	public static boolean isNoItem(PlayerInventory piy){
		ItemStack[] allis=piy.getContents();
		if(allis.length<36){
			return false;
		}
		for(ItemStack sis:allis){
			if(sis==null){
				return false;
			}
			if(sis.getType()==Material.AIR){
				return false;
			}
		}
		return true;
	}
	
	//针对部分谜之核心
	//比较两个List是否相同
	public static boolean equalsLore(List<String> lore,List<String> str) {
		if(lore.size()!=str.size()) {
			return false;
		}
		for(int i=0;i<lore.size();i++) {
			if(!lore.get(i).endsWith(str.get(i))) {
				return false;
			}
		}
		return true;
	}
}
