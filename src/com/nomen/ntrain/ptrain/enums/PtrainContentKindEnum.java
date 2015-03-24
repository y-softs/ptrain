package com.nomen.ntrain.ptrain.enums;

/**
 *  ptrain_content:资源类型（1.规程文件 2.影音影视库 3.经典书籍库 4.典型经验库）
 * @author lms
 * @date   2014-11-17
 */
public enum PtrainContentKindEnum {
	/***规程文件*/
	CONTENT_RULES(1,"规程文件"),
	/***影音影视库*/
	CONTENT_FILM(2,"影音影视库"),
	/***经典书籍库*/
	CONTENT_BOOKS(3,"经典书籍库"),
	/***典型经验库*/
	CONTENT_EXPER(4,"典型经验库");
	private int    key;
	private String desc;
	
	/**
	 * 构造方法
	 * @param eV
	 * @param eD
	 */
	private PtrainContentKindEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 类似Integer.valueOf设置方法
	 * @param key
	 * @return
	 */
	public static PtrainContentKindEnum valueOf(int key){
		PtrainContentKindEnum[] qT=PtrainContentKindEnum.values();
		for (PtrainContentKindEnum cs: qT){
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
