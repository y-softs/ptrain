package com.nomen.ntrain.ptrain.enums;

/**
 *  ptrain_req:�������1��ѵ��������2ר���ڿ�����9Ա������
 * @author lms
 * @date   2014-3-18
 */
public enum PtrainReqReqtypeEnum {
	/***��ѵ��������*/
	REQ_COM(1,"��ѵ����"),
	/***ר���ڿ�����*/
	REQ_EXP(2,"ר���ڿ�"),
	/***�μ����*/
	REQ_COUR(3,"�μ���ѵ"),
	/***Ա������*/
	REQ_USER(9,"Ա������");
	private int    key;
	private String desc;
	
	/**
	 * ���췽��
	 * @param eV
	 * @param eD
	 */
	private PtrainReqReqtypeEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * ����Integer.valueOf���÷���
	 * @param key
	 * @return
	 */
	public static PtrainReqReqtypeEnum valueOf(int key){
		PtrainReqReqtypeEnum[] qT=PtrainReqReqtypeEnum.values();
		for (PtrainReqReqtypeEnum cs: qT){
			if (cs.getKey()==key){
				return cs;
			}
		}
		return null;
	}
	
	
	//set get����
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
