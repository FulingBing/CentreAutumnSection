package com.github.FulingBing.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.FulingBing.CentreAutumnSectionConfig;
import com.github.FulingBing.core.CentreAutumnSectionMoonCakeCore;
import com.github.FulingBing.core.CentreAutumnSectionRiddleCore;
import com.github.FulingBing.pojo.CentreAutumnSectionRiddle;

public class CentreAutumnSectionRiddleListener implements Listener{
	//猜谜（方块）
	@EventHandler
	public void PlayerInteractEvent(PlayerInteractEvent e) {
		if(e.hasBlock() && e.hasItem()) {
			ItemStack is=e.getItem();
			if(CentreAutumnSectionRiddleCore.isRiddle(is)) {
				CentreAutumnSectionRiddle c=CentreAutumnSectionRiddleCore.getRiddleByLore(is.getItemMeta().getLore());
				if(c!=null) {
					String type=e.getClickedBlock().getType().name();
					if(CentreAutumnSectionRiddleCore.isRiddleOk(c, type)) {
						e.getPlayer().sendMessage(CentreAutumnSectionConfig.getRiddleYes());
						if(is.getAmount()>1) {
							is.setAmount(is.getAmount()-1);
						}else {
							is=null;
						}
						try {
							//有双持的mc
							if(e.getHand().equals(EquipmentSlot.HAND)) {
								e.getPlayer().getInventory().setItemInMainHand(is);
							}else {
								e.getPlayer().getInventory().setItemInOffHand(is);
							}
						} catch (NoSuchMethodError e2) {
							//没有双持的mc
							e.getPlayer().getInventory().setItemInHand(is);
						}
						for(String cmd:c.cmd) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", e.getPlayer().getName()));
						}
					}else {
						e.getPlayer().sendMessage(CentreAutumnSectionConfig.getRiddleNo());
					}
					return;
				}
			}else {
				if(is==null) {
					return;
				}
				//通过谜题获取方块获取谜题
				if(is.getType()==Material.PAPER && CentreAutumnSectionRiddleCore.isRiddleBlock(e.getClickedBlock())) {
					ItemMeta im=is.getItemMeta();
					if((!im.hasDisplayName()) && (!im.hasLore())) {
						ItemStack riddle=CentreAutumnSectionRiddleCore.getRiddle();
						if(is.getAmount()!=1) {
							if(CentreAutumnSectionMoonCakeCore.isNoItem(e.getPlayer().getInventory())) {
								e.getPlayer().sendMessage(CentreAutumnSectionConfig.getChangeErr());
								return;
							}
						}
						if(is.getAmount()>1) {
							is.setAmount(is.getAmount()-1);
						}else {
							is=null;
						}
						try {
							//有双持的mc
							if(e.getHand().equals(EquipmentSlot.HAND)) {
								e.getPlayer().getInventory().setItemInMainHand(is);
							}else {
								e.getPlayer().getInventory().setItemInOffHand(is);
							}
						} catch (NoSuchMethodError e2) {
							//没有双持的mc
							e.getPlayer().getInventory().setItemInHand(is);
						}
						e.getPlayer().getInventory().addItem(new ItemStack[]{riddle});
						e.getPlayer().updateInventory();
						e.getPlayer().sendMessage(CentreAutumnSectionConfig.getChangeTrue());
					}
					return;
				}
				//使用谜题获取方块调节器
				if(CentreAutumnSectionRiddleCore.isRiddleBlockItem(is)) {
					if(e.getPlayer().hasPermission("CentreAutumnSection.item")) {
						e.getPlayer().sendMessage(CentreAutumnSectionRiddleCore.changeRiddleBlock(e.getClickedBlock()));
					}
					//return;
				}
			}
		}
	}
	
	//猜谜（实体）
	@EventHandler
	public void PlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
		ItemStack is=null;
		try {
			//有双持的mc
			if(e.getHand().equals(EquipmentSlot.HAND)) {
				is=e.getPlayer().getInventory().getItemInMainHand();
			}else {
				is=e.getPlayer().getInventory().getItemInOffHand();
			}
		} catch (NoSuchMethodError e2) {
			//没有双持的mc
			is=e.getPlayer().getItemInHand();
		}
		if(CentreAutumnSectionRiddleCore.isRiddle(is)) {
			CentreAutumnSectionRiddle c=CentreAutumnSectionRiddleCore.getRiddleByLore(is.getItemMeta().getLore());
			if(c!=null) {
				String type=e.getRightClicked().getType().name();
				if(CentreAutumnSectionRiddleCore.isRiddleOk(c, type)) {
					e.getPlayer().sendMessage(CentreAutumnSectionConfig.getRiddleYes());
					if(is.getAmount()>1) {
						is.setAmount(is.getAmount()-1);
					}else {
						is=null;
					}
					try {
						//有双持的mc
						if(e.getHand().equals(EquipmentSlot.HAND)) {
							e.getPlayer().getInventory().setItemInMainHand(is);
						}else {
							e.getPlayer().getInventory().setItemInOffHand(is);
						}
					} catch (NoSuchMethodError e2) {
						//没有双持的mc
						e.getPlayer().getInventory().setItemInHand(is);
					}
					for(String cmd:c.cmd) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", e.getPlayer().getName()));
					}
				}else {
					e.getPlayer().sendMessage(CentreAutumnSectionConfig.getRiddleNo());
				}
				return;
			}
		}
		//使用谜题获取方块调节器
		if(CentreAutumnSectionRiddleCore.isRiddleBlockItem(is)) {
			if(e.getPlayer().hasPermission("CentreAutumnSection.item")) {
				e.getPlayer().sendMessage(e.getRightClicked().getType().name());
			}
			//return;
		}
	}
	
	//猜谜（物品）
	@SuppressWarnings("deprecation")
	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e) {
		ItemStack is=e.getCursor();
		ItemStack ic=e.getCurrentItem();
		if(ic==null) {
			return;
		}
		if(ic.getType()==Material.AIR) {
			return;
		}
		if(CentreAutumnSectionRiddleCore.isRiddle(is)) {
			CentreAutumnSectionRiddle c=CentreAutumnSectionRiddleCore.getRiddleByLore(is.getItemMeta().getLore());
			if(c!=null) {
				Player pl=(Player)e.getWhoClicked();
				String type=ic.getType().name();
				if(CentreAutumnSectionRiddleCore.isRiddleOk(c, type)) {
					pl.sendMessage(CentreAutumnSectionConfig.getRiddleYes());
					is.setAmount(is.getAmount()-1);
					for(String cmd:c.cmd) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replaceAll("%player%", pl.getName()));
					}
				}else {
					pl.sendMessage(CentreAutumnSectionConfig.getRiddleNo());
				}
				return;
			}
		}
	}
	
	//阅读理解0分的玩家
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if(e.isCancelled()) {
			return;
		}
		if(e.getDamager() instanceof Player) {
			Player pl=(Player)(e.getDamager());
			ItemStack is=pl.getItemInHand();
			if(CentreAutumnSectionRiddleCore.isRiddle(is)) {
				CentreAutumnSectionRiddle c=CentreAutumnSectionRiddleCore.getRiddleByLore(is.getItemMeta().getLore());
				if(c!=null) {
					pl.sendMessage(CentreAutumnSectionConfig.getRiddleMsg());
					return;
				}
			}
		}
	}
}
