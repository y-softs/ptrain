package com.nomen.ntrain.data.bean;

/**
 * @description ������Ա_��λ����POJO 
 * @author ��ľɽ
 * @date 2013-5-30
 */
public class DataPostmoveBean {
    private String id;         //ID��������
    private String oldunitid;  //ԭ��λID
    private String olddeptid;  //ԭ����ID
    private String oldgroupid; //ԭ����ID
    private String oldpostname;//ԭ��λ����
    private String newunitid;  //�ֵ�λID
    private String newdeptid;  //�ֲ���ID
    private String newgroupid; //�ְ���ID
    private String newpostname;//�ָ�λ����
    private String userid;     //��ԱID
    private String movedate;   //����ʱ��
    private String remark;     //��ע
    private String flowsta;    //����״̬��0������д��51������ȷ�ϣ�99�鵵��
    private String subtime;    //�ʱ��
    private String flowmark;   //���̱��ֵ
    private String fromtype;   //������Դ���ͣ�1���̣���д����2ά������д����7���룬8ͬ����9�Զ���
    private String estauser;   //������
    private String estatime;   //����ʱ��
    private String mainuser;   //ά����
    private String maintime;   //ά��ʱ��
    private String strflag;    //Ԥ���ֶΣ��ַ�����
    private String intflag;    //Ԥ���ֶΣ����ͣ�
	//Get��Set����
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getMovedate() {
        return movedate;
    }
    public void setMovedate(String movedate) {
        this.movedate = movedate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getFlowsta() {
        return flowsta;
    }
    public void setFlowsta(String flowsta) {
        this.flowsta = flowsta;
    }
    public String getSubtime() {
        return subtime;
    }
    public void setSubtime(String subtime) {
        this.subtime = subtime;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOldunitid() {
        return oldunitid;
    }
    public void setOldunitid(String oldunitid) {
        this.oldunitid = oldunitid;
    }
    public String getOlddeptid() {
        return olddeptid;
    }
    public void setOlddeptid(String olddeptid) {
        this.olddeptid = olddeptid;
    }
    public String getOldgroupid() {
        return oldgroupid;
    }
    public void setOldgroupid(String oldgroupid) {
        this.oldgroupid = oldgroupid;
    }
    public String getOldpostname() {
        return oldpostname;
    }
    public void setOldpostname(String oldpostname) {
        this.oldpostname = oldpostname;
    }
    public String getNewunitid() {
        return newunitid;
    }
    public void setNewunitid(String newunitid) {
        this.newunitid = newunitid;
    }
    public String getNewdeptid() {
        return newdeptid;
    }
    public void setNewdeptid(String newdeptid) {
        this.newdeptid = newdeptid;
    }
    public String getNewgroupid() {
        return newgroupid;
    }
    public void setNewgroupid(String newgroupid) {
        this.newgroupid = newgroupid;
    }
    public String getNewpostname() {
        return newpostname;
    }
    public void setNewpostname(String newpostname) {
        this.newpostname = newpostname;
    }
	public String getFlowmark() {
		return flowmark;
	}
	public void setFlowmark(String flowmark) {
		this.flowmark = flowmark;
	}
	public String getFromtype() {
		return fromtype;
	}
	public void setFromtype(String fromtype) {
		this.fromtype = fromtype;
	}
}
