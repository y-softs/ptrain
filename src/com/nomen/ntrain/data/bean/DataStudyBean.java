package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ѧ��ѧλ��Ϣ��POJO 
 * @author ������
 * @date 2013-06-21
 */
public class DataStudyBean implements Serializable{
	private String userid;		//��Աid
	private String eduid;		//��ҵѧ���ȼ�id
	private String certno;      //��ҵѧ��֤����
	private String spectypeid;	//��ҵѧ��רҵ���id
	private String specname;	//��ҵѧ��רҵ����
	private String startdate;	//��ҵѧ��ѧϰ��ʼʱ��
	private String enddate;		//��ҵѧ��ѧϰ����ʱ��
	private String schoollen;	//��ҵѧ��ѧ��
	private String modeid1;		//��ҵѧ��ѧϰ��ʽid
	private String fintypeid;	//��ҵѧ����ҵ״��id
	private String findate;		//��ҵѧ����֤ʱ��
	private String schoolname;	//��ҵѧ����֤����
	private String eduid2;		//���ѧ��id
	private String certno2;     //���ѧ��֤����
	private String spectypeid2;	//���ѧ��רҵ���id
	private String specname2;	//���ѧ��רҵ����
	private String startdate2;	//���ѧ��ѧϰ��ʼʱ��
	private String enddate2;	//���ѧ��ѧϰ����ʱ��
	private String schoollen2;	//���ѧ��ѧ��
	private String modeid2;		//���ѧ��ѧϰ��ʽid
	private String fintypeid2;	//���ѧ����ҵ״��id
	private String findate2;	//���ѧ����֤ʱ��
	private String schoolname2;	//���ѧ����֤����
	private String degreeid;	//ѧλ�ȼ�id
	private String degreecertno;//���ѧ��֤����
	private String degreespec;  //ѧλרҵ����
	private String degreedate;	//��֤ʱ��
	private String degreeschool;//��֤����
	private String banum;       //ѧʿѧλ��
    private String estauser;	//������
    private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�

	//�����ֶ�
	private String eduidfatherid1;    //��ҵѧ��fatherid
	private String eduidfatherid2;    //���ѧ��fatherid
	private String degreefatherid;    //���ѧλfatherid
	
	//������GET ��SET ����
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEduid() {
		return eduid;
	}
	public void setEduid(String eduid) {
		this.eduid = eduid;
	}
	public String getSpectypeid() {
		return spectypeid;
	}
	public void setSpectypeid(String spectypeid) {
		this.spectypeid = spectypeid;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
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
	public String getSchoollen() {
		return schoollen;
	}
	public void setSchoollen(String schoollen) {
		this.schoollen = schoollen;
	}
	public String getModeid1() {
		return modeid1;
	}
	public void setModeid1(String modeid1) {
		this.modeid1 = modeid1;
	}
	public String getSchoolname() {
		return schoolname;
	}
	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}
	public String getFindate() {
		return findate;
	}
	public void setFindate(String findate) {
		this.findate = findate;
	}
	public String getFintypeid() {
		return fintypeid;
	}
	public void setFintypeid(String fintypeid) {
		this.fintypeid = fintypeid;
	}
	public String getEduid2() {
		return eduid2;
	}
	public void setEduid2(String eduid2) {
		this.eduid2 = eduid2;
	}
	public String getSpectypeid2() {
		return spectypeid2;
	}
	public void setSpectypeid2(String spectypeid2) {
		this.spectypeid2 = spectypeid2;
	}
	public String getSpecname2() {
		return specname2;
	}
	public void setSpecname2(String specname2) {
		this.specname2 = specname2;
	}
	public String getStartdate2() {
		return startdate2;
	}
	public void setStartdate2(String startdate2) {
		this.startdate2 = startdate2;
	}
	public String getEnddate2() {
		return enddate2;
	}
	public void setEnddate2(String enddate2) {
		this.enddate2 = enddate2;
	}
	public String getSchoollen2() {
		return schoollen2;
	}
	public void setSchoollen2(String schoollen2) {
		this.schoollen2 = schoollen2;
	}
	public String getModeid2() {
		return modeid2;
	}
	public void setModeid2(String modeid2) {
		this.modeid2 = modeid2;
	}
	public String getSchoolname2() {
		return schoolname2;
	}
	public void setSchoolname2(String schoolname2) {
		this.schoolname2 = schoolname2;
	}
	public String getFindate2() {
		return findate2;
	}
	public void setFindate2(String findate2) {
		this.findate2 = findate2;
	}
	public String getFintypeid2() {
		return fintypeid2;
	}
	public void setFintypeid2(String fintypeid2) {
		this.fintypeid2 = fintypeid2;
	}
	public String getDegreeid() {
		return degreeid;
	}
	public void setDegreeid(String degreeid) {
		this.degreeid = degreeid;
	}
	public String getDegreedate() {
		return degreedate;
	}
	public void setDegreedate(String degreedate) {
		this.degreedate = degreedate;
	}
	public String getDegreespec() {
		return degreespec;
	}
	public void setDegreespec(String degreespec) {
		this.degreespec = degreespec;
	}
	public String getDegreeschool() {
		return degreeschool;
	}
	public void setDegreeschool(String degreeschool) {
		this.degreeschool = degreeschool;
	}
	public String getBanum() {
		return banum;
	}
	public void setBanum(String banum) {
		this.banum = banum;
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
	public String getEduidfatherid1() {
		return eduidfatherid1;
	}
	public void setEduidfatherid1(String eduidfatherid1) {
		this.eduidfatherid1 = eduidfatherid1;
	}
	public String getEduidfatherid2() {
		return eduidfatherid2;
	}
	public void setEduidfatherid2(String eduidfatherid2) {
		this.eduidfatherid2 = eduidfatherid2;
	}
	public String getCertno() {
		return certno;
	}
	public void setCertno(String certno) {
		this.certno = certno;
	}
	public String getCertno2() {
		return certno2;
	}
	public void setCertno2(String certno2) {
		this.certno2 = certno2;
	}
	public String getDegreecertno() {
		return degreecertno;
	}
	public void setDegreecertno(String degreecertno) {
		this.degreecertno = degreecertno;
	}
	public String getDegreefatherid() {
		return degreefatherid;
	}
	public void setDegreefatherid(String degreefatherid) {
		this.degreefatherid = degreefatherid;
	}
}