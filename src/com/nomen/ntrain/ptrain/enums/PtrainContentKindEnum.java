package com.nomen.ntrain.ptrain.enums;

/**
 *  ptrain_content:��Դ���ͣ�1.����ļ� 2.Ӱ��Ӱ�ӿ� 3.�����鼮�� 4.���;���⣩
 * @author lms
 * @date   2014-11-17
 */
public enum PtrainContentKindEnum {
	/***����ļ�*/
	CONTENT_RULES(1,"����ļ�"),
	/***Ӱ��Ӱ�ӿ�*/
	CONTENT_FILM(2,"Ӱ��Ӱ�ӿ�"),
	/***�����鼮��*/
	CONTENT_BOOKS(3,"�����鼮��"),
	/***���;����*/
	CONTENT_EXPER(4,"���;����");
	private int    key;
	private String desc;
	
	/**
	 * ���췽��
	 * @param eV
	 * @param eD
	 */
	private PtrainContentKindEnum(int key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * ����Integer.valueOf���÷���
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
