package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��Ա��Ϣ�������
 * @author ������
 * @date 2010-12-15
 */
public class DataReqItemBean implements Serializable{
	private String id;			//��������
	private String reqid;		//�������id
	private String itemname;	//�����������
	private String tablename;	//�������
	private String fieldname;	//����ֶ���
	private String oldid;		//���ǰ��Ӧid
	private String oldname;		//���ǰ����
	private String newid;		//������Ӧid
	private String newname;		//���������
	private String oldphoto;	//���ǰ��Ƭ
	private String newphoto;	//�������Ƭ
	private String recid;		//�����¼id
	private String fieldtype;	//����ֶ����ͣ�varchar2��date��number��blob��
	private String usesign;		//ȷ�ϱ�־��1 �� 0 ��
	private String sortnum;     //�����
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�
	private String username;    //�����������ֶΣ�
	
	//������GET �� SET ����
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReqid() {
		return reqid;
	}
	public void setReqid(String reqid) {
		this.reqid = reqid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getOldid() {
		return oldid;
	}
	public void setOldid(String oldid) {
		this.oldid = oldid;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getNewid() {
		return newid;
	}
	public void setNewid(String newid) {
		this.newid = newid;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getOldphoto() {
		return oldphoto;
	}
	public void setOldphoto(String oldphoto) {
		this.oldphoto = oldphoto;
	}
	public String getNewphoto() {
		return newphoto;
	}
	public void setNewphoto(String newphoto) {
		this.newphoto = newphoto;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
