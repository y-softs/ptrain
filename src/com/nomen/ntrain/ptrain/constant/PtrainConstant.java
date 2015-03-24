package com.nomen.ntrain.ptrain.constant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.res.bean.ResCodeBean;

public final class PtrainConstant {
	/****************************����*******************************/
	/**��ѡ*/
	public final static String singleSel   = "10";
	public final static String TYPE_SINGLE = "��ѡ";
	/**��ѡ*/
	public final static String multSel   = "11";
	public final static String TYPE_MULT = "��ѡ";
	/**�ж�*/
	public final static String judgeSel   = "12";
	public final static String TYPE_JUDGE = "�ж�";
	/****************************�����������*******************************/
	/**ÿ�ճ���*/
	public final static String PARAM_MRCT = "DAY_COUNT";
	/**ÿ�´��*/
	public final static String PARAM_MYDB = "MONTH_COUNT";
	/****************************����ģ���־*******************************/
	public final static String _TRAINREQ_MODSIGN = "1";// ��ѵ��� �ӱ�ģ��ID
	/** ֪ʶѧϰ ����ģ���־ */
	public final static String FILE_MODSIGN_BBS = "0";
	/**�μ���Դ�� ����ģ���־(��Ŀ¼) */
	public final static String FILE_MODSIGN_COURS = "2";
	/**Ӱ��/�鼮��Դ/����ļ� ����ģ���־ */
	public final static String FILE_MODSIGN_CONT = "3";
	/**�μ���Դ�� ����ģ���־(�μ�����) */
	public final static String FILE_MODSIGN_COURS_FM = "4";

	/**ÿ�´�����*/
	public final static String REACH_SCORE = "24";
	
	

	/****************************ͶƱ���*******************************/
	public final static int VOTE_MAXTIME    = 10;  //����ͶƱ����
	public final static int VOTE_OUTTIMES   = -1;  //����ͶƱ�̶�����
	public final static int VOTE_OUTIPSCOPE = -2;  //IP���ڹ涨��Χ
	public final static int VOTE_SUCCESS    = 1;   //ͶƱ�ɹ�
	public final static int VOTE_ERROR      = 0;   //ͶƱʧ��
	
	/**
	 * �õ��������б�����
	 */
	public static String quesTypeDefault(){
		List<ResCodeBean> typeList = typeListOper();
		return typeList.get(0).getId();
	}
	

	/**
	 * �õ��������б�
	 */
	public static List typeListOper(){
		List<ResCodeBean> typeList = new ArrayList();
		ResCodeBean cB = new ResCodeBean();
		cB.setId("10");
		cB.setCodename("��ѡ��");
		typeList.add(cB);
		cB = new ResCodeBean();
		cB.setId("11");
		cB.setCodename("��ѡ��");
		typeList.add(cB);
		cB = new ResCodeBean();
		cB.setId("12");
		cB.setCodename("�ж���ѡ��");
		typeList.add(cB);
		return typeList;
	}
	/**
	 * �������
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
	 * ÿ�δ�����ȷ��õķ�ֵ
	 * @param asknum �������,score �����ֵ
	 */
	public static Double getScore(int asknum,Double score){
		Double res=0D;
		if(0==asknum){
			res=score;//��1�δ�ԣ���ø����ֵ
		}else if(1==asknum){
			res=score/4;//��2�δ�ԣ���ø���1/4��ֵ
		}
		return (double)Math.round(res*100)/100;//������λ
	}
}
