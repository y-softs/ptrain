package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��ԱϵͳӦ����Ϣ��POJO 
 * @author Ǯ�º�
 * @date 2009-10-06
 * @modifier �¸���
 * @date 2010-05-08
 */

public class DataSystemBean implements Serializable{
	private String postuser;    //��ԱID
	private String postid;      //��Ӧ��λID
	private String appsign;     //Ӧ������
    private String estauser;	//������
    private String estatime;	//����ʱ��
    private String mainuser;	//ά����
    private String mainDeptName;//ά�������ڲ���
    private String maintime;	//ά��ʱ��
    private String strflag;     //Ԥ���ֶΣ��ַ�����
    private String intflag;     //Ԥ���ֶΣ����ͣ�
    
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getMainDeptName() {
		return mainDeptName;
	}
	public void setMainDeptName(String mainDeptName) {
		this.mainDeptName = mainDeptName;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getAppsign() {
		return appsign;
	}
	public void setAppsign(String appsign) {
		this.appsign = appsign;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getPostuser() {
		return postuser;
	}
	public void setPostuser(String postuser) {
		this.postuser = postuser;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
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
}