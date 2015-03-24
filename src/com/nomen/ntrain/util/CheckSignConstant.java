package com.nomen.ntrain.util;

import java.util.LinkedHashMap;
import java.util.Map;

/*** 
 * 操作描述：
	常用类	0 报审	1通过	2不通过	3取消	4回退		 6收费	7发布	8公示	9归档
	报送类	10申报	11 确认	12 提交	15报名						
	审核类	20 确认	21同意	22不同意	23接收	24转委					
					                33撤回	34退回	35开票				        39作废
	维护类						    55调配				                        59撤销
 * @author lianjinliang
 * @date   2012-6-17
 */
public class CheckSignConstant {
	/**报审0**/
	public final static String BAO_SHEN="0";
	/**通过1**/
	public final static String TONG_GUO  ="1";
	/**不通过2**/
	public final static String BU_TONG_GUO  ="2";
	/**取消3**/
	public final static String QU_XIAO  ="3";
	/**回退4**/
	public final static String HUI_TUI  ="4";
	/**收费6**/
	public final static String SHOU_FEI  ="6";
	/**发布7**/
	public final static String FA_BU  ="7";
	/**公示8**/
	public final static String GONG_SHI  ="8";
	/**归档9**/
	public final static String GUI_DANG  ="9";
	/**申报**/
	public final static String SHEN_BAO  ="10";
	/**确认11[该标记自2013-05-10起不用] **/
	@Deprecated
	public final static String QUE_RENG  ="11";
	/**提交12**/
	public final static String TI_JIAO  ="12";
	/**报名15**/
	public final static String BAO_MING  ="15";
	/**确认20**/
	public final static String QUE_RENG2  ="20";
	/**同意21**/
	public final static String TONG_YI  ="21";
	/**不同意22**/
	public final static String BU_TONG_YI  ="22";
	/**接收23**/
	public final static String JIE_SHOU  ="23";
	/**转委24**/
	public final static String ZHUAN_WEI  ="24";
	/**撤回33**/
	public final static String CHE_HUI  ="33";
	/**退回34**/
	public final static String TUI_HUI  ="34";
	/**开票35**/
	public final static String KAI_PIAO  ="35";
	/**作废39**/
	public final static String ZUO_FEI  ="39";
	/**调配55**/
	public final static String TIAO_PEI  ="55";
	/**撤销59**/
	public final static String CHE_XIAO  ="59";
	

	/**
	 * 流程表 操作描述
	 */
	public static Map _Constant_ChkMap(){
		Map<String, String> linkMap = new LinkedHashMap<String, String>();
		linkMap.put(CheckSignConstant.BAO_SHEN, "报审");
		linkMap.put(CheckSignConstant.TONG_GUO, "通过");
		linkMap.put(CheckSignConstant.BU_TONG_GUO, "<span class='fontcolor_red_b'>！</span>不通过</span>");
		linkMap.put(CheckSignConstant.QU_XIAO, "取消");
		linkMap.put(CheckSignConstant.HUI_TUI, "回退");
		linkMap.put(CheckSignConstant.SHOU_FEI, "收费");
		linkMap.put(CheckSignConstant.FA_BU, "发布");
		linkMap.put(CheckSignConstant.GONG_SHI, "公示");
		linkMap.put(CheckSignConstant.GUI_DANG, "归档");
		linkMap.put(CheckSignConstant.SHEN_BAO, "申报");
		linkMap.put(CheckSignConstant.QUE_RENG, "确认");
		linkMap.put(CheckSignConstant.TI_JIAO, "提交");
		linkMap.put(CheckSignConstant.BAO_MING, "报名");
		linkMap.put(CheckSignConstant.QUE_RENG2, "同意");
		linkMap.put(CheckSignConstant.TONG_YI, "同意");
		linkMap.put(CheckSignConstant.BU_TONG_YI, "<span class='fontcolor_red_b'>！</span>不同意</span>");
		linkMap.put(CheckSignConstant.JIE_SHOU, "接收");
		linkMap.put(CheckSignConstant.ZHUAN_WEI, "转委");
		linkMap.put(CheckSignConstant.CHE_HUI, "撤回");
		linkMap.put(CheckSignConstant.TUI_HUI, "退回");
		linkMap.put(CheckSignConstant.KAI_PIAO, "开票");
		linkMap.put(CheckSignConstant.ZUO_FEI, "作废");
		linkMap.put(CheckSignConstant.TIAO_PEI, "调配");
		linkMap.put(CheckSignConstant.CHE_XIAO, "撤销");
		return linkMap;
	}
}
