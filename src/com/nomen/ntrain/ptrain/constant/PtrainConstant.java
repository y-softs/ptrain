package com.nomen.ntrain.ptrain.constant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.res.bean.ResCodeBean;

public final class PtrainConstant {
	/****************************题型*******************************/
	/**单选*/
	public final static String singleSel   = "10";
	public final static String TYPE_SINGLE = "单选";
	/**多选*/
	public final static String multSel   = "11";
	public final static String TYPE_MULT = "多选";
	/**判断*/
	public final static String judgeSel   = "12";
	public final static String TYPE_JUDGE = "判断";
	/****************************抽题策略设置*******************************/
	/**每日出题*/
	public final static String PARAM_MRCT = "DAY_COUNT";
	/**每月达标*/
	public final static String PARAM_MYDB = "MONTH_COUNT";
	/****************************附件模块标志*******************************/
	public final static String _TRAINREQ_MODSIGN = "1";// 培训点菜 子表模块ID
	/** 知识学习 附件模块标志 */
	public final static String FILE_MODSIGN_BBS = "0";
	/**课件资源表 附件模块标志(子目录) */
	public final static String FILE_MODSIGN_COURS = "2";
	/**影视/书籍资源/规程文件 附件模块标志 */
	public final static String FILE_MODSIGN_CONT = "3";
	/**课件资源表 附件模块标志(课件封面) */
	public final static String FILE_MODSIGN_COURS_FM = "4";

	/**每月达标分数*/
	public final static String REACH_SCORE = "24";
	
	

	/****************************投票相关*******************************/
	public final static int VOTE_MAXTIME    = 10;  //最大的投票次数
	public final static int VOTE_OUTTIMES   = -1;  //超过投票固定次数
	public final static int VOTE_OUTIPSCOPE = -2;  //IP不在规定范围
	public final static int VOTE_SUCCESS    = 1;   //投票成功
	public final static int VOTE_ERROR      = 0;   //投票失败
	
	/**
	 * 用到的题型列表及操作
	 */
	public static String quesTypeDefault(){
		List<ResCodeBean> typeList = typeListOper();
		return typeList.get(0).getId();
	}
	

	/**
	 * 用到的题型列表
	 */
	public static List typeListOper(){
		List<ResCodeBean> typeList = new ArrayList();
		ResCodeBean cB = new ResCodeBean();
		cB.setId("10");
		cB.setCodename("单选题");
		typeList.add(cB);
		cB = new ResCodeBean();
		cB.setId("11");
		cB.setCodename("多选题");
		typeList.add(cB);
		cB = new ResCodeBean();
		cB.setId("12");
		cB.setCodename("判断题选题");
		typeList.add(cB);
		return typeList;
	}
	/**
	 * 题型类别
	 * @return
	 */
	public static Map _typeListOperMap(){
		Map<String,String> signMap = new LinkedHashMap<String, String>();
		signMap.put(PtrainConstant.singleSel, PtrainConstant.TYPE_SINGLE);
		signMap.put(PtrainConstant.multSel, PtrainConstant.TYPE_MULT);
		signMap.put(PtrainConstant.judgeSel, PtrainConstant.TYPE_JUDGE);
		return signMap;
	}
	/**
	 * 每次答题正确获得的分值
	 * @param asknum 答题次数,score 本题分值
	 */
	public static Double getScore(int asknum,Double score){
		Double res=0D;
		if(0==asknum){
			res=score;//第1次答对，获得该题分值
		}else if(1==asknum){
			res=score/4;//第2次答对，获得该题1/4分值
		}
		return (double)Math.round(res*100)/100;//保留两位
	}
}
