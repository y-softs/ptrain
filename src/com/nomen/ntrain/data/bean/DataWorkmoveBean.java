package com.nomen.ntrain.data.bean;

/**
 * @description ������Ա_���ڸ�λ����POJO 
 * @author ��ľɽ
 * @date 2013-6-7
 */
public class DataWorkmoveBean {
    private String id;         //ID��������
    private String postmoveid; //��λ����ID������TB_DATA_POSTMOVE��
    private String newareaid;  //������λ��ID
    private String oldareaname;//ԭ����λ������
    private String oldareaid;  //ԭ����λ��ID
    private String newareaname;//������λ������
    private String estauser;   //������
    private String estatime;   //����ʱ��
    private String mainuser;   //ά����
    private String maintime;   //ά��ʱ��
    private String strflag;    //Ԥ���ֶΣ��ַ�����
    private String intflag;    //Ԥ���ֶΣ����ͣ�

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
    public String getNewareaid() {
        return newareaid;
    }
    public void setNewareaid(String newareaid) {
        this.newareaid = newareaid;
    }
    public String getOldareaname() {
        return oldareaname;
    }
    public void setOldareaname(String oldareaname) {
        this.oldareaname = oldareaname;
    }
    public String getOldareaid() {
        return oldareaid;
    }
    public void setOldareaid(String oldareaid) {
        this.oldareaid = oldareaid;
    }
    public String getNewareaname() {
        return newareaname;
    }
    public void setNewareaname(String newareaname) {
        this.newareaname = newareaname;
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
