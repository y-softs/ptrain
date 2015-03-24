package com.nomen.ntrain.ptrain.enums;
/**
 * 莆田岗位学习_审核流程 模块流程标记
 * 模块标志（1培训点菜 2课件库  3影视/书籍资源/规程文件/典型经验）
 * @author lms
 * @date   2014-3-18
 */
public enum PtrainFlowModeSignEnum {
	/***培训点菜*/
	TRAINREQ(1,"培训点菜"),
	/***课件库*/
	TRAINCOURS(2,"课件库"),
	/***影视/书籍资源/规程文件/典型经验*/
	TRAINCONT(3,"影视/书籍资源/规程文件/典型经验");
	
	
	
	private int    key;
	private String desc;
	
	/**
	 * 构造方法
	 * @param eV
	 * @param eD
	 */
	private PtrainFlowModeSignEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 类似Integer.valueOf设置方法
	 * @param key
	 * @return
	 */
	public static PtrainFlowModeSignEnum valueOf(int key){
		PtrainFlowModeSignEnum[] qT=PtrainFlowModeSignEnum.values();
		for (PtrainFlowModeSignEnum cs: qT){
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