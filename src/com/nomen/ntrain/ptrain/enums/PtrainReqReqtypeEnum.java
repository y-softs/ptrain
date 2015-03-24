package com.nomen.ntrain.ptrain.enums;

/**
 *  ptrain_req:需求类别（1培训中心需求，2专家授课需求，9员工需求）
 * @author lms
 * @date   2014-3-18
 */
public enum PtrainReqReqtypeEnum {
	/***培训中心需求*/
	REQ_COM(1,"培训中心"),
	/***专家授课需求*/
	REQ_EXP(2,"专家授课"),
	/***课件点菜*/
	REQ_COUR(3,"课件培训"),
	/***员工需求*/
	REQ_USER(9,"员工需求");
	private int    key;
	private String desc;
	
	/**
	 * 构造方法
	 * @param eV
	 * @param eD
	 */
	private PtrainReqReqtypeEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 类似Integer.valueOf设置方法
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
	
	
	//set get方法
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
