package com.nomen.ntrain.ptrain.enums;

/**
 *  ptrain_req:流程状态（0待需求报审，51待需求审核，99归档）
 * @author lms
 * @date   2014-3-18
 */
public enum PtrainReqFlowStaEnum {
	/***待需求报审*/
	XQSB(0,"需求报审"),
	/***待需求审核*/
	XQSH(51,"需求审核"),
	/***归档*/
	GD(99,"归档");
	private int    key;
	private String desc;
	
	/**
	 * 构造方法
	 * @param eV
	 * @param eD
	 */
	private PtrainReqFlowStaEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 类似Integer.valueOf设置方法
	 * @param key
	 * @return
	 */
	public static PtrainReqFlowStaEnum valueOf(int key){
		PtrainReqFlowStaEnum[] qT=PtrainReqFlowStaEnum.values();
		for (PtrainReqFlowStaEnum cs: qT){
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