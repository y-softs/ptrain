package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա������Ϣ��POJO
 * @author Ǯ�º�
 * @date 2010-1-4
 * @modifier �¸���������
 * @date 2010-04-26��2010-12-27
 */
public class DataCodeBean implements Serializable{
	private String id;			//id����������Ա���룩
	private String nature;		//���ʣ�1���ԣ�2���ԣ�
	private String unitid;		//��λid
	private String fatherid;	//����id
	private String codename;	//��������
	private String gradeid;		//����id/���
	private String orggrade;	//�����ȼ�[��������] �缶[��������]   gradeid2
	private String orgzone;	    //��������[��������] ʡ��[��������]    gradeid3
	private String orgclass;	//��������[��������]     gradeid4
	private String orgnature;	//��������[��������]    gradeid5
	private String orgunder;	//��������[��������]    gradeid6
	private String areacode;	//����  
	private String postcode;	//�ʱ�
	private String carcode;     //���ƺ�
	private String remark;		//˵��
	private String endsign;		//ĩ�˱�־��1�ǣ�0��
	private String usesign;		//���ñ�־��1���ã�0��ֹ��
	private String specsign;	//��־��1�ǣ�0��
	private String codevalue;	//����
	private String sortnum;		//�����
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�==������Ϊ�ȼ���������ʱ���Ϊ-1
	//�����ֶ�
	private String fathername;	//��������
	
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
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
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getSpecsign() {
		return specsign;
	}
	public void setSpecsign(String specsign) {
		this.specsign = specsign;
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
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	public String getOrgclass() {
		return orgclass;
	}
	public void setOrgclass(String orgclass) {
		this.orgclass = orgclass;
	}
	public String getOrggrade() {
		return orggrade;
	}
	public void setOrggrade(String orggrade) {
		this.orggrade = orggrade;
	}
	public String getOrgnature() {
		return orgnature;
	}
	public void setOrgnature(String orgnature) {
		this.orgnature = orgnature;
	}
	public String getOrgunder() {
		return orgunder;
	}
	public void setOrgunder(String orgunder) {
		this.orgunder = orgunder;
	}
	public String getOrgzone() {
		return orgzone;
	}
	public void setOrgzone(String orgzone) {
		this.orgzone = orgzone;
	}
	public String getCarcode() {
		return carcode;
	}
	public void setCarcode(String carcode) {
		this.carcode = carcode;
	}
	
}