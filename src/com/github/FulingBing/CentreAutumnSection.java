package com.github.FulingBing;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.FulingBing.Listener.CentreAutumnSectionLookMoonListener;
import com.github.FulingBing.Listener.CentreAutumnSectionMoonCakeListener;
import com.github.FulingBing.Listener.CentreAutumnSectionRiddleListener;
import com.github.FulingBing.core.CentreAutumnSectionMoonCakeCore;
import com.github.FulingBing.core.CentreAutumnSectionRiddleCore;
import com.github.FulingBing.pojo.CentreAutumnSectionMoonCake;
import com.github.FulingBing.pojo.CentreAutumnSectionRiddle;

public class CentreAutumnSection extends JavaPlugin{
	//这是一款神奇的插件
	//它最牛逼的地方就在于我想吹它一翻都不知道从何处开始吹
	public void onEnable() {
		if (!getDataFolder().exists()){
			saveDefaultConfig();
		}
		//加载配置文件
		readsize();
		//加载月饼的合成表
		CentreAutumnSectionMoonCakeCore.loadRecipe();
		Bukkit.getPluginManager().registerEvents(new CentreAutumnSectionRiddleListener(),this);
		Bukkit.getPluginManager().registerEvents(new CentreAutumnSectionLookMoonListener(),this);
		Bukkit.getPluginManager().registerEvents(new CentreAutumnSectionMoonCakeListener(),this);
		getLogger().info("插件启用");
	}

	public void onDisable() {
		getConfig().set("RiddleBlock", CentreAutumnSectionConfig.getRiddleBlock());
		saveConfig();
		getLogger().info("插件停用");
	}
	
	public void readsize(){
		CentreAutumnSectionConfig.setRiddleMsg(getzhcn(getConfig().getString("RiddleMsg")));
		CentreAutumnSectionConfig.setRiddleName(getzhcn(getConfig().getString("RiddleName")));
		CentreAutumnSectionConfig.setRiddleNo(getzhcn(getConfig().getString("RiddleNo")));
		CentreAutumnSectionConfig.setRiddleYes(getzhcn(getConfig().getString("RiddleYes")));
		int i=1;
		List<CentreAutumnSectionRiddle> lr=new ArrayList<CentreAutumnSectionRiddle>();
		while(getConfig().contains("RiddleList."+i+".lore")) {
			lr.add(new CentreAutumnSectionRiddle(
				getzhcn(getConfig().getStringList("RiddleList."+i+".lore")),
				getConfig().getStringList("RiddleList."+i+".answer"),
				getzhcn(getConfig().getStringList("RiddleList."+i+".cmd")))
			);
			i++;
		}
		CentreAutumnSectionConfig.setRiddle(lr);
		CentreAutumnSectionConfig.setMoonCakeGive(getzhcn(getConfig().getString("MoonCakeGive")));
		CentreAutumnSectionConfig.setMoonCakeError(getzhcn(getConfig().getString("MoonCakeError")));
		CentreAutumnSectionConfig.setMoonCakeMiss(getzhcn(getConfig().getString("MoonCakeMiss")));
		i=1;
		List<CentreAutumnSectionMoonCake> lc=new ArrayList<CentreAutumnSectionMoonCake>();
		while(getConfig().contains("MoonCakeList."+i+".lore")) {
			lc.add(new CentreAutumnSectionMoonCake(
				getzhcn(getConfig().getString("MoonCakeList."+i+".name")),
				getzhcn(getConfig().getStringList("MoonCakeList."+i+".lore")),
				getConfig().getInt("MoonCakeList."+i+".giveRiddle"),
				getConfig().getStringList("MoonCakeList."+i+".shape"),
				getzhcn(getConfig().getStringList("MoonCakeList."+i+".cmd")))
			);
			i++;
		}
		CentreAutumnSectionConfig.setMooncake(lc);
		CentreAutumnSectionConfig.setJump(getConfig().getInt("Jump"));
		CentreAutumnSectionConfig.setJumpByTp(getConfig().getBoolean("JumpByTp"));
		CentreAutumnSectionConfig.setGiveError(getzhcn(getConfig().getString("GiveError")));
		CentreAutumnSectionConfig.setNoPermissions(getzhcn(getConfig().getString("NoPermissions")));
		CentreAutumnSectionConfig.setGiveRiddle(getzhcn(getConfig().getString("GiveRiddle")));
		CentreAutumnSectionConfig.setGiveMiss(getzhcn(getConfig().getString("GiveMiss")));
		CentreAutumnSectionConfig.setReOk(getzhcn(getConfig().getString("ReOk")));
		CentreAutumnSectionConfig.setHelpMsgMenu(getzhcn(getConfig().getString("HelpMsgMenu")));
		CentreAutumnSectionConfig.setHelpMsg1(getzhcn(getConfig().getString("HelpMsg1")));
		CentreAutumnSectionConfig.setHelpMsg2(getzhcn(getConfig().getString("HelpMsg2")));
		CentreAutumnSectionConfig.setHelpMsg3(getzhcn(getConfig().getString("HelpMsg3")));
		CentreAutumnSectionConfig.setHelpMsg4(getzhcn(getConfig().getString("HelpMsg4")));
		CentreAutumnSectionConfig.setHelpMsg5(getzhcn(getConfig().getString("HelpMsg5")));
		CentreAutumnSectionConfig.setNeedPlayer(getzhcn(getConfig().getString("NeedPlayer")));
		CentreAutumnSectionConfig.setRiddleBlock(getzhcn(getConfig().getStringList("RiddleBlock")));
		CentreAutumnSectionConfig.setChangeTrue(getzhcn(getConfig().getString("ChangeTrue")));
		CentreAutumnSectionConfig.setChangeErr(getzhcn(getConfig().getString("ChangeErr")));
		CentreAutumnSectionConfig.setRiddleBlockAdd(getzhcn(getConfig().getString("RiddleBlockAdd")));
		CentreAutumnSectionConfig.setRiddleBlockDel(getzhcn(getConfig().getString("RiddleBlockDel")));
		CentreAutumnSectionConfig.setRiddleLore(getzhcn(getConfig().getStringList("RiddleLore")));
		CentreAutumnSectionConfig.setRiddleItem(getzhcn(getConfig().getString("RiddleItem")));
		CentreAutumnSectionConfig.setRiddleItemGive(getzhcn(getConfig().getString("RiddleItemGive")));
	}
	
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args){
		if(args.length>0) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(sender instanceof Player) {
					if(!((Player)sender).hasPermission("CentreAutumnSection.reload")) {
						sender.sendMessage(CentreAutumnSectionConfig.getNoPermissions());
						return false;
					}
				}
				reloadConfig();
				readsize();
				sender.sendMessage(CentreAutumnSectionConfig.getReOk());
				return true;
			}
			if(args[0].equalsIgnoreCase("give")) {
				Player pl=null;
				if(args.length==1) {
					if(sender instanceof Player) {
						pl=(Player)sender;
						if(!(pl.hasPermission("CentreAutumnSection.give"))) {
							sender.sendMessage(CentreAutumnSectionConfig.getNoPermissions());
							return false;
						}
					}else {
						sender.sendMessage(CentreAutumnSectionConfig.getNeedPlayer());
						return false;
					}
				}else {
					if(sender instanceof Player) {
						if(!((Player)sender).hasPermission("CentreAutumnSection.giveplayer")) {
							sender.sendMessage(CentreAutumnSectionConfig.getNoPermissions());
							return false;
						}
					}
					pl=Bukkit.getPlayerExact(args[1]);
				}
				if(pl==null) {
					sender.sendMessage(CentreAutumnSectionConfig.getGiveMiss());
					return false;
				}
				PlayerInventory pi=pl.getInventory();
				if(CentreAutumnSectionMoonCakeCore.isNoItem(pi)) {
					sender.sendMessage(CentreAutumnSectionConfig.getGiveError());
					return false;
				}
				ItemStack riddle=CentreAutumnSectionRiddleCore.getRiddle();
				pi.addItem(new ItemStack[]{riddle});
				sender.sendMessage(CentreAutumnSectionConfig.getGiveRiddle().replaceAll("%player%", pl.getName()));
				return true;
			}
			if(args[0].equalsIgnoreCase("type")) {
				if(sender instanceof Player) {
					Player pl=(Player)sender;
					if(!(pl.hasPermission("CentreAutumnSection.type"))) {
						sender.sendMessage(CentreAutumnSectionConfig.getNoPermissions());
						return false;
					}
					ItemStack is=pl.getItemInHand();
					if(is==null) {
						pl.sendMessage(Material.AIR.name());
					}else {
						pl.sendMessage(is.getType().name());
					}
				}else {
					sender.sendMessage(CentreAutumnSectionConfig.getNeedPlayer());
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("item")) {
				if(sender instanceof Player) {
					Player pl=(Player)sender;
					if(!(pl.hasPermission("CentreAutumnSection.item"))) {
						sender.sendMessage(CentreAutumnSectionConfig.getNoPermissions());
						return false;
					}
					PlayerInventory pi=pl.getInventory();
					if(CentreAutumnSectionMoonCakeCore.isNoItem(pi)) {
						sender.sendMessage(CentreAutumnSectionConfig.getGiveError());
						return false;
					}
					ItemStack is=new ItemStack(Material.FEATHER);
					ItemMeta im=is.getItemMeta();
					im.setDisplayName(CentreAutumnSectionConfig.getRiddleItem());
					im.setLore(CentreAutumnSectionConfig.getRiddleLore());
					is.setItemMeta(im);
					pi.addItem(new ItemStack[]{is});
					sender.sendMessage(CentreAutumnSectionConfig.getRiddleItemGive().replaceAll("%player%", pl.getName()));
				}else {
					sender.sendMessage(CentreAutumnSectionConfig.getNeedPlayer());
				}
				return true;
			}
		}
		//插件帮助信息
		sender.sendMessage(CentreAutumnSectionConfig.getHelpMsgMenu());
		if(sender instanceof Player) {
			Player pl=(Player)sender;
			if(pl.hasPermission("CentreAutumnSection.give")) {
				sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg1());
			}
			if(pl.hasPermission("CentreAutumnSection.giveplayer")) {
				sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg2());
			}
			if(pl.hasPermission("CentreAutumnSection.type")) {
				sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg3());
			}
			if(pl.hasPermission("CentreAutumnSection.item")) {
				sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg4());
			}
			if(pl.hasPermission("CentreAutumnSection.reload")) {
				sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg5());
			}
		}else {
			sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg1());
			sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg2());
			sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg3());
			sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg4());
			sender.sendMessage(CentreAutumnSectionConfig.getHelpMsg5());
		}
		return false;
	}
	
	//编码转换
	public String getzhcn(String intext){
		String t="\\u";
		if(intext.indexOf(t)==-1){
			return intext.replaceAll("&", "§");
		}
		while(intext.indexOf(t)>=0){
			StringBuilder sb = new StringBuilder();
			int i=intext.indexOf(t);
			if(i+6>intext.length()){
				break;
			}
			String txt=intext.substring(i+2,i+6);
			if(txt.matches("^[A-Za-z0-9]+$")){
				sb.append((char)Integer.parseInt(txt, 16));
				intext=intext.substring(0, i)+sb.toString()+intext.substring(i+6,intext.length());
			}else{
				txt="strJAVAreplaceSetTeXt6699"+txt;
				intext=intext.substring(0, i)+txt+intext.substring(i+6,intext.length());
			}
		}
		intext=intext.replace("strJAVAreplaceSetTeXt6699", "\\u");
		return intext.replaceAll("&", "§");
	}
	
	public List<String> getzhcn(List<String> intext){
		for(int i=0;i<intext.size();i++) {
			intext.set(i, getzhcn(intext.get(i)));
		}
		return intext;
	}
}
