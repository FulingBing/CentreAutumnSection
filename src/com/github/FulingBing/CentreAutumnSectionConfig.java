package com.github.FulingBing;

import java.util.ArrayList;
import java.util.List;

import com.github.FulingBing.pojo.CentreAutumnSectionMoonCake;
import com.github.FulingBing.pojo.CentreAutumnSectionRiddle;

public class CentreAutumnSectionConfig {
	private static List<CentreAutumnSectionRiddle> riddle=new ArrayList<CentreAutumnSectionRiddle>();
	private static String RiddleName;
	private static String RiddleMsg;
	private static String RiddleNo;
	private static String RiddleYes;
	
	private static List<CentreAutumnSectionMoonCake> mooncake=new ArrayList<CentreAutumnSectionMoonCake>();
	private static String MoonCakeGive;
	
	private static String MoonCakeError;
	private static String MoonCakeMiss;
	
	private static String[] MoonTp;
	private static String MoonTpError;
	
	private static String NoPermissions;
	private static String GiveError;
	private static String GiveRiddle;
	private static String GiveMiss;
	private static String ReOk;
	private static String NeedPlayer;
	private static String RiddleBlockAdd;
	private static String RiddleBlockDel;

	private static String ChangeErr;
	private static String ChangeTrue;
	private static String RiddleItem;
	private static String RiddleItemGive;
	private static String HelpMsgMenu;
	private static String HelpMsg1;
	private static String HelpMsg2;
	private static String HelpMsg3;
	private static String HelpMsg4;
	private static String HelpMsg5;

	private static List<String> RiddleLore=new ArrayList<String>();
	private static List<String> RiddleBlock=new ArrayList<String>();

	public static String getMoonTpError() {
		return MoonTpError;
	}
	public static void setMoonTpError(String moonTpError) {
		MoonTpError = moonTpError;
	}
	public static String[] getMoonTp() {
		return MoonTp;
	}
	public static void setMoonTp(String[] moonTp) {
		MoonTp = moonTp;
	}
	public static String getRiddleItemGive() {
		return RiddleItemGive;
	}
	public static void setRiddleItemGive(String riddleItemGive) {
		RiddleItemGive = riddleItemGive;
	}
	public static String getRiddleItem() {
		return RiddleItem;
	}
	public static void setRiddleItem(String riddleItem) {
		RiddleItem = riddleItem;
	}
	public static List<String> getRiddleLore() {
		return RiddleLore;
	}
	public static void setRiddleLore(List<String> riddleLore) {
		RiddleLore = riddleLore;
	}
	public static String getRiddleBlockAdd() {
		return RiddleBlockAdd;
	}
	public static void setRiddleBlockAdd(String riddleBlockAdd) {
		RiddleBlockAdd = riddleBlockAdd;
	}
	public static String getRiddleBlockDel() {
		return RiddleBlockDel;
	}
	public static void setRiddleBlockDel(String riddleBlockDel) {
		RiddleBlockDel = riddleBlockDel;
	}
	public static String getChangeErr() {
		return ChangeErr;
	}
	public static void setChangeErr(String changeErr) {
		ChangeErr = changeErr;
	}
	public static String getChangeTrue() {
		return ChangeTrue;
	}
	public static void setChangeTrue(String changeTrue) {
		ChangeTrue = changeTrue;
	}
	public static List<String> getRiddleBlock() {
		return RiddleBlock;
	}
	public static void setRiddleBlock(List<String> riddleBlock) {
		RiddleBlock = riddleBlock;
	}
	public static String getNeedPlayer() {
		return NeedPlayer;
	}
	public static void setNeedPlayer(String needPlayer) {
		NeedPlayer = needPlayer;
	}
	public static String getHelpMsgMenu() {
		return HelpMsgMenu;
	}
	public static void setHelpMsgMenu(String helpMsgMenu) {
		HelpMsgMenu = helpMsgMenu;
	}
	public static String getHelpMsg1() {
		return HelpMsg1;
	}
	public static void setHelpMsg1(String helpMsg1) {
		HelpMsg1 = helpMsg1;
	}
	public static String getHelpMsg2() {
		return HelpMsg2;
	}
	public static void setHelpMsg2(String helpMsg2) {
		HelpMsg2 = helpMsg2;
	}
	public static String getHelpMsg3() {
		return HelpMsg3;
	}
	public static void setHelpMsg3(String helpMsg3) {
		HelpMsg3 = helpMsg3;
	}
	public static String getHelpMsg4() {
		return HelpMsg4;
	}
	public static void setHelpMsg4(String helpMsg4) {
		HelpMsg4 = helpMsg4;
	}
	public static String getHelpMsg5() {
		return HelpMsg5;
	}
	public static void setHelpMsg5(String helpMsg5) {
		HelpMsg5 = helpMsg5;
	}
	public static String getReOk() {
		return ReOk;
	}
	public static void setReOk(String reOk) {
		ReOk = reOk;
	}
	public static String getGiveMiss() {
		return GiveMiss;
	}
	public static void setGiveMiss(String giveMiss) {
		GiveMiss = giveMiss;
	}
	public static String getNoPermissions() {
		return NoPermissions;
	}
	public static void setNoPermissions(String noPermissions) {
		NoPermissions = noPermissions;
	}
	public static String getGiveError() {
		return GiveError;
	}
	public static void setGiveError(String giveError) {
		GiveError = giveError;
	}
	public static String getGiveRiddle() {
		return GiveRiddle;
	}
	public static void setGiveRiddle(String giveRiddle) {
		GiveRiddle = giveRiddle;
	}
	public static List<CentreAutumnSectionRiddle> getRiddle() {
		return riddle;
	}
	public static void setRiddle(List<CentreAutumnSectionRiddle> riddle) {
		CentreAutumnSectionConfig.riddle = riddle;
	}
	public static String getRiddleName() {
		return RiddleName;
	}
	public static void setRiddleName(String riddleName) {
		RiddleName = riddleName;
	}
	public static String getRiddleMsg() {
		return RiddleMsg;
	}
	public static void setRiddleMsg(String riddleMsg) {
		RiddleMsg = riddleMsg;
	}
	public static String getRiddleNo() {
		return RiddleNo;
	}
	public static void setRiddleNo(String riddleNo) {
		RiddleNo = riddleNo;
	}
	public static String getRiddleYes() {
		return RiddleYes;
	}
	public static void setRiddleYes(String riddleYes) {
		RiddleYes = riddleYes;
	}
	public static List<CentreAutumnSectionMoonCake> getMooncake() {
		return mooncake;
	}
	public static void setMooncake(List<CentreAutumnSectionMoonCake> mooncake) {
		CentreAutumnSectionConfig.mooncake = mooncake;
	}
	public static String getMoonCakeGive() {
		return MoonCakeGive;
	}
	public static void setMoonCakeGive(String moonCakeGive) {
		MoonCakeGive = moonCakeGive;
	}
	public static String getMoonCakeError() {
		return MoonCakeError;
	}
	public static void setMoonCakeError(String moonCakeError) {
		MoonCakeError = moonCakeError;
	}
	public static String getMoonCakeMiss() {
		return MoonCakeMiss;
	}
	public static void setMoonCakeMiss(String moonCakeMiss) {
		MoonCakeMiss = moonCakeMiss;
	}
}
