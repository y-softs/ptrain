package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ������Ա_�������������
 * @author ���
 * @date 2011-9-19
 */
public class DataResumeBean implements Serializable{
	private String id;				//id��������
	private String unitid;			//��λid
	private String deptid;			//����id
	private String groupid;			//����id
	private String userid;			//��Աid
	private String natureid;		//��Ա����id
	private String gradeid;			//��λ�㼶id
	private String rectype;			//"�������304��ְ������311���⹤�֣�312�ض���λ��ѧ���ȼ���601���䶯���101�������ʣ�191�������ƣ�201������202���壬204������ò��205��Ա���ʣ�206��Ա״̬��301��Ա���i��302,��λ���࣬303��λ�㼶��306���Ź�����307רҵ���308�ڼ���309ְ����310�������ʣ�605ѧλ�ȼ���701����ְ�Ƶȼ���703ִҵ�ʸ�ȼ���707ר���˲ŵȼ���805����״����925֤���λ�ڴΣ�935�ͱ���λ���֣�945��ѧ��λ�ڴΣ�946������λ�ڴΣ�955cbe��λ�ڴΣ�956��Ŀ��λ�ڴΣ�"
	private String startdate;		//��ʼʱ�䣨��ȡʱ�䣬ѧϰ��ʼʱ�䣩
	private String enddate;			//����ʱ�䣨�䶯ʱ�䣺����ʱ�䣬��ȡʱ�䣬ѧϰ����ʱ�䣩
	private String mpostid;			//�䶯ǰ������id
	private String mpost;			//�䶯ǰ������
	private String mstateid;		//�䶯ǰ��״̬id������λ״̬id���ض���λ���ͣ�ѧϰ��ʽid��
	private String mstate;			//�䶯ǰ��״̬����
	private String ppostid1;		//�䶯ǰ������1id
	private String ppost1;			//�䶯ǰ������1
	private String pstateid1;		//�䶯ǰ��״̬1id
	private String pstate1;			//�䶯ǰ��״̬1
	private String ppostid2;		//�䶯ǰ������2id
	private String ppost2;			//�䶯ǰ������2
	private String pstateid2;		//�䶯ǰ��״̬2id
	private String pstate2;			//�䶯ǰ��״̬2
	private String ppostid3;		//�䶯ǰ������3id
	private String ppost3;			//�䶯ǰ������3
	private String pstateid3;		//�䶯ǰ��״̬3id
	private String pstate3;			//�䶯ǰ��״̬3
	private String bsign;			//�䶯ǰ���ñ�־��1�Ǳ���λ��0����λ��
	private String bname1;			//�䶯ǰ�������ƣ���λ���ƣ���ҵԺУ��
	private String bname2;			//�䶯ǰ�������ƣ��������ƣ�רҵ���ƣ�
	private String bname3;			//�䶯ǰ�������ƣ��������ƣ�ѧ�ƣ�ѧλ������
	private String mpostid_n;		//�䶯��������id
	private String mpost_n;			//�䶯��������
	private String mstateid_n;		//�䶯����״̬id������λ״̬id���ض���λ���ͣ�ѧϰ��ʽid���������
	private String mstate_n;		//�䶯����״̬����
	private String ppostid1_n;		//�䶯�������1id
	private String ppost1_n;		//�䶯�������1
	private String pstateid1_n;		//�䶯���״̬1id
	private String pstate1_n;		//�䶯���״̬1
	private String ppostid2_n;		//�䶯�������2id
	private String ppost2_n;		//�䶯�������2
	private String pstateid2_n;		//�䶯���״̬2id
	private String pstate2_n;		//�䶯���״̬2
	private String ppostid3_n;		//�䶯�������3id
	private String ppost3_n;		//�䶯�������3
	private String pstateid3_n;		//�䶯���״̬3id
	private String pstate3_n;		//�䶯���״̬3
	private String bsign_n;			//�䶯���ñ�־��1�Ǳ���λ��0����λ��
	private String bname1_n;		//�䶯�������ƣ���λ���ƣ���ҵԺУ��
	private String bname2_n;		//�䶯�������ƣ��������ƣ�רҵ���ƣ�
	private String bname3_n;		//�䶯�������ƣ��������ƣ�ѧ�ƣ�ѧλ������
	private String remark;			//��ע
	private String flowsta;	        //����״̬��0������д��41��������ˣ�99�鵵��
	private String subtime;			//����ʱ��
	private String fromtype;	    //������Դ���ͣ�1���̣���д����2ά������д����7���룬8ͬ����9�Զ���
	private String fromname;        //������Դ��ϵͳ����
	private String fromid;          //������Դ��¼id
	private String hrefurl;         //��������url
	private String estauser;		//������
	private String estatime;		//����ʱ��
	private String mainuser;		//ά����
	private String maintime;		//ά��ʱ��
	private String strflag;			//Ԥ���ֶΣ��ַ�����
	private String intflag;			//Ԥ���ֶΣ����ͣ�
	//���渨���ֶ�
	private String groupfatherid;   //ĩ�˰����fatherid
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getRectype() {
		return rectype;
	}
	public void setRectype(String rectype) {
		this.rectype = rectype;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getMpostid() {
		return mpostid;
	}
	public void setMpostid(String mpostid) {
		this.mpostid = mpostid;
	}
	public String getMpost() {
		return mpost;
	}
	public void setMpost(String mpost) {
		this.mpost = mpost;
	}
	public String getMstateid() {
		return mstateid;
	}
	public void setMstateid(String mstateid) {
		this.mstateid = mstateid;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	public String getPpostid1() {
		return ppostid1;
	}
	public void setPpostid1(String ppostid1) {
		this.ppostid1 = ppostid1;
	}
	public String getPpost1() {
		return ppost1;
	}
	public void setPpost1(String ppost1) {
		this.ppost1 = ppost1;
	}
	public String getPstateid1() {
		return pstateid1;
	}
	public void setPstateid1(String pstateid1) {
		this.pstateid1 = pstateid1;
	}
	public String getPstate1() {
		return pstate1;
	}
	public void setPstate1(String pstate1) {
		this.pstate1 = pstate1;
	}
	public String getPpostid2() {
		return ppostid2;
	}
	public void setPpostid2(String ppostid2) {
		this.ppostid2 = ppostid2;
	}
	public String getPpost2() {
		return ppost2;
	}
	public void setPpost2(String ppost2) {
		this.ppost2 = ppost2;
	}
	public String getPstateid2() {
		return pstateid2;
	}
	public void setPstateid2(String pstateid2) {
		this.pstateid2 = pstateid2;
	}
	public String getPstate2() {
		return pstate2;
	}
	public void setPstate2(String pstate2) {
		this.pstate2 = pstate2;
	}
	public String getPpostid3() {
		return ppostid3;
	}
	public void setPpostid3(String ppostid3) {
		this.ppostid3 = ppostid3;
	}
	public String getPpost3() {
		return ppost3;
	}
	public void setPpost3(String ppost3) {
		this.ppost3 = ppost3;
	}
	public String getPstateid3() {
		return pstateid3;
	}
	public void setPstateid3(String pstateid3) {
		this.pstateid3 = pstateid3;
	}
	public String getPstate3() {
		return pstate3;
	}
	public void setPstate3(String pstate3) {
		this.pstate3 = pstate3;
	}
	public String getBsign() {
		return bsign;
	}
	public void setBsign(String bsign) {
		this.bsign = bsign;
	}
	public String getBname1() {
		return bname1;
	}
	public void setBname1(String bname1) {
		this.bname1 = bname1;
	}
	public String getBname2() {
		return bname2;
	}
	public void setBname2(String bname2) {
		this.bname2 = bname2;
	}
	public String getBname3() {
		return bname3;
	}
	public void setBname3(String bname3) {
		this.bname3 = bname3;
	}
	public String getMpostid_n() {
		return mpostid_n;
	}
	public void setMpostid_n(String mpostid_n) {
		this.mpostid_n = mpostid_n;
	}
	public String getMpost_n() {
		return mpost_n;
	}
	public void setMpost_n(String mpost_n) {
		this.mpost_n = mpost_n;
	}
	public String getMstateid_n() {
		return mstateid_n;
	}
	public void setMstateid_n(String mstateid_n) {
		this.mstateid_n = mstateid_n;
	}
	public String getMstate_n() {
		return mstate_n;
	}
	public void setMstate_n(String mstate_n) {
		this.mstate_n = mstate_n;
	}
	public String getPpostid1_n() {
		return ppostid1_n;
	}
	public void setPpostid1_n(String ppostid1_n) {
		this.ppostid1_n = ppostid1_n;
	}
	public String getPpost1_n() {
		return ppost1_n;
	}
	public void setPpost1_n(String ppost1_n) {
		this.ppost1_n = ppost1_n;
	}
	public String getPstateid1_n() {
		return pstateid1_n;
	}
	public void setPstateid1_n(String pstateid1_n) {
		this.pstateid1_n = pstateid1_n;
	}
	public String getPstate1_n() {
		return pstate1_n;
	}
	public void setPstate1_n(String pstate1_n) {
		this.pstate1_n = pstate1_n;
	}
	public String getPpostid2_n() {
		return ppostid2_n;
	}
	public void setPpostid2_n(String ppostid2_n) {
		this.ppostid2_n = ppostid2_n;
	}
	public String getPpost2_n() {
		return ppost2_n;
	}
	public void setPpost2_n(String ppost2_n) {
		this.ppost2_n = ppost2_n;
	}
	public String getPstateid2_n() {
		return pstateid2_n;
	}
	public void setPstateid2_n(String pstateid2_n) {
		this.pstateid2_n = pstateid2_n;
	}
	public String getPstate2_n() {
		return pstate2_n;
	}
	public void setPstate2_n(String pstate2_n) {
		this.pstate2_n = pstate2_n;
	}
	public String getPpostid3_n() {
		return ppostid3_n;
	}
	public void setPpostid3_n(String ppostid3_n) {
		this.ppostid3_n = ppostid3_n;
	}
	public String getPpost3_n() {
		return ppost3_n;
	}
	public void setPpost3_n(String ppost3_n) {
		this.ppost3_n = ppost3_n;
	}
	public String getPstateid3_n() {
		return pstateid3_n;
	}
	public void setPstateid3_n(String pstateid3_n) {
		this.pstateid3_n = pstateid3_n;
	}
	public String getPstate3_n() {
		return pstate3_n;
	}
	public void setPstate3_n(String pstate3_n) {
		this.pstate3_n = pstate3_n;
	}
	public String getBsign_n() {
		return bsign_n;
	}
	public void setBsign_n(String bsign_n) {
		this.bsign_n = bsign_n;
	}
	public String getBname1_n() {
		return bname1_n;
	}
	public void setBname1_n(String bname1_n) {
		this.bname1_n = bname1_n;
	}
	public String getBname2_n() {
		return bname2_n;
	}
	public void setBname2_n(String bname2_n) {
		this.bname2_n = bname2_n;
	}
	public String getBname3_n() {
		return bname3_n;
	}
	public void setBname3_n(String bname3_n) {
		this.bname3_n = bname3_n;
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
	public String getGroupfatherid() {
		return groupfatherid;
	}
	public void setGroupfatherid(String groupfatherid) {
		this.groupfatherid = groupfatherid;
	}
	public String getFromtype() {
		return fromtype;
	}
	public void setFromtype(String fromtype) {
		this.fromtype = fromtype;
	}
	public String getFromname() {
		return fromname;
	}
	public void setFromname(String fromname) {
		this.fromname = fromname;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getHrefurl() {
		return hrefurl;
	}
	public void setHrefurl(String hrefurl) {
		this.hrefurl = hrefurl;
	}
}