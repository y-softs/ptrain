package com.nomen.ntrain.data.bean;

/**
 * @description ������Ա_���ʸ�λ������ϢPOJO 
 * @author ��ľɽ
 * @date 2013-5-31
 */
public class DataWagemoveBean {
    private String id;        //ID��������
    private String postmoveid;//��λ����ID������TB_DATA_POSTMOVE��
    private String subid;     //��ĿID��0��������ϵ��-1�����ţ���������ĿID��
    private String subname;   //��Ŀ����
    private String oldvalue;  //ԭ��ĿֵID
    private String oldname;   //ԭ��Ŀֵ����
    private String newvalue;  //ԭ��ĿֵID
    private String newname;   //�ֿ�Ŀֵ����
    private String estauser;  //������
    private String estatime;  //����ʱ��
    private String mainuser;  //ά����
    private String maintime;  //ά��ʱ��
    private String strflag;   //Ԥ���ֶΣ��ַ�����
    private String intflag;   //Ԥ���ֶΣ����ͣ�

	//Get��Set����
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPostmoveid() {
        return postmoveid;
    }
    public void setPostmoveid(String postmoveid) {
        this.postmoveid = postmoveid;
    }
    public String getSubid() {
        return subid;
    }
    public void setSubid(String subid) {
        this.subid = subid;
    }
    public String getSubname() {
        return subname;
    }
    public void setSubname(String subname) {
        this.subname = subname;
    }
    public String getOldvalue() {
        return oldvalue;
    }
    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }
    public String getOldname() {
        return oldname;
    }
    public void setOldname(String oldname) {
        this.oldname = oldname;
    }
    public String getNewvalue() {
        return newvalue;
    }
    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }
    public String getNewname() {
        return newname;
    }
    public void setNewname(String newname) {
        this.newname = newname;
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
