package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description �����ظ�����ڼ�¼���������ʷPOJO
 * @author ������
 * @date 2011-09-05
 */
public class DataOrganhisBean implements Serializable{
	private String id;			//id��������
	private String organid;		//����id
	private String organname;	//��������
	private String typesign;	//������ͣ�1������2������3������9������
	private String checkunit;	//��׼��λ
	private String checkdate;	//��׼ʱ��
	private String filename;	//�ļ�����
	private String fileids;		//�ļ���������id��
	private String remark;		//��ע
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	
	
	//����Ϊset get����
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getCheckunit() {
		return checkunit;
	}
	public void setCheckunit(String checkunit) {
		this.checkunit = checkunit;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getFileids() {
		return fileids;
	}
	public void setFileids(String fileids) {
		this.fileids = fileids;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getOrganid() {
		return organid;
	}
	public void setOrganid(String organid) {
		this.organid = organid;
	}
	public String getOrganname() {
		return organname;
	}
	public void setOrganname(String organname) {
		this.organname = organname;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getTypesign() {
		return typesign;
	}
	public void setTypesign(String typesign) {
		this.typesign = typesign;
	}	
}
