package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ������Ա_��λ���ƹ���POJO
 * @author ������
 * @date 2011-05-31
 */
public class DataPostattrBean implements Serializable{
	private String id;				//id(����)
	private String nature;			//���ʣ�1���ԣ�2���ԣ�
	private String unitid;			//��λid
	private String postid;			//��λ����id
	private String gradeid;			//��λ�㼶id�������id����,�����
	private String partyid;			//���Ź���id
	private String typeid;			//��Ա���id
	private String posttypeid;		//��λ����id
	private String specid;			//רҵ���id
	private String specname;	    //רҵ����
	private String natureid;		//��������id
	private String partid;			//���⹤������id
	private String specpostid;       //�ض���λid
	private String joinid1;			//����֤���λ�ڴ�
	private String joinid2;			//�����ͱ���λ�ڴ�
	private String joinid3;			//��������ѧϰ��λ�ڴ�
	private String joinid4;			//�������翼�Ը�λ�ڴ�
	private String joinid5;			//����cbe��λ�ڴ�
	private String joinid6;			//������Ŀ��λ�ڴ�
	private String joinid7;			//����1
	private String joinid8;			//����2
	private String joinid9;			//����3
	private String joinid10;		//����4
	private String estauser;		//������
	private String estatime;		//����ʱ��
	private String mainuser;		//ά����
	private String maintime;		//ά��ʱ��
	private String strflag;			//Ԥ���ֶΣ��ַ�����
	private String intflag; 		//Ԥ���ֶΣ����ͣ�
	
	//������GET �� SET ����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getPartyid() {
		return partyid;
	}
	public void setPartyid(String partyid) {
		this.partyid = partyid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getPosttypeid() {
		return posttypeid;
	}
	public void setPosttypeid(String posttypeid) {
		this.posttypeid = posttypeid;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getSpecpostid() {
		return specpostid;
	}
	public void setSpecpostid(String specpostid) {
		this.specpostid = specpostid;
	}
	public String getJoinid1() {
		return joinid1;
	}
	public void setJoinid1(String joinid1) {
		this.joinid1 = joinid1;
	}
	public String getJoinid2() {
		return joinid2;
	}
	public void setJoinid2(String joinid2) {
		this.joinid2 = joinid2;
	}
	public String getJoinid3() {
		return joinid3;
	}
	public void setJoinid3(String joinid3) {
		this.joinid3 = joinid3;
	}
	public String getJoinid4() {
		return joinid4;
	}
	public void setJoinid4(String joinid4) {
		this.joinid4 = joinid4;
	}
	public String getJoinid5() {
		return joinid5;
	}
	public void setJoinid5(String joinid5) {
		this.joinid5 = joinid5;
	}
	public String getJoinid6() {
		return joinid6;
	}
	public void setJoinid6(String joinid6) {
		this.joinid6 = joinid6;
	}
	public String getJoinid7() {
		return joinid7;
	}
	public void setJoinid7(String joinid7) {
		this.joinid7 = joinid7;
	}
	public String getJoinid8() {
		return joinid8;
	}
	public void setJoinid8(String joinid8) {
		this.joinid8 = joinid8;
	}
	public String getJoinid9() {
		return joinid9;
	}
	public void setJoinid9(String joinid9) {
		this.joinid9 = joinid9;
	}
	public String getJoinid10() {
		return joinid10;
	}
	public void setJoinid10(String joinid10) {
		this.joinid10 = joinid10;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
}