package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ����_��Ա�������ñ�POJO 
 * @author ������
 * @date 2012-10-13
 */
public class DataWageBean implements Serializable{
    private String id;       //ID��������
    private String userid;   //��ԱID
    private String typeid;   //���11������ϵ��21���ʸڼ���25����н����26����н�㣬31����ϵ����41����ϵ����91������Դ��
    private String typevalue;//ֵ
    private String remark;   //��ע
    private String estauser; //������
    private String estatime; //����ʱ��
    private String mainuser; //ά����
    private String maintime; //ά��ʱ��
    private String strflag;  //Ԥ���ֶΣ��ַ�����
    private String intflag;  //Ԥ���ֶΣ����ͣ�

	//Get��Set����
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTypevalue() {
        return typevalue;
    }
    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
