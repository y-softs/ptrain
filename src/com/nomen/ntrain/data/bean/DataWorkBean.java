package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 户籍用工信息表POJO 
 * @author 连金亮
 * @date 2013-06-21
 */
public class DataWorkBean implements Serializable{
	private String userid;		//人员id
	private String acckindid;	//户口性质id
	private String accplace;	//户籍所在地
	private String accaddr;	    //户籍地址
	private String workdate;	//参加工作时间
	private String powerdate;	//进入系统时间
	private String unitdate;	//进入单位时间
	private String formid;		//用工形式id
	private String contdate;	//合同签订时间
	private String contypeid;	//合同类型id
	private String limitid;		//合同期限类型ID
	private String limitdate;	//合同期限
	private String contunit;	//合同用工单位
	private String soldid;		//军转类型ID
	private String solddate;	//军转时间
	private String insertid;	//增员类型ID
	private String remark;      //备注
    private String estauser;	//创建人
    private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	//辅助字段
	private String insertdesc;      //增员类型名称[辅助]
	private String insertfatherid;	//增员类型父级id
	//以下是 GET 和 SET 方法
	public String getAccaddr() {
		return accaddr;
	}
	public void setAccaddr(String accaddr) {
		this.accaddr = accaddr;
	}
	public String getAcckindid() {
		return acckindid;
	}
	public void setAcckindid(String acckindid) {
		this.acckindid = acckindid;
	}
	public String getAccplace() {
		return accplace;
	}
	public void setAccplace(String accplace) {
		this.accplace = accplace;
	}
	public String getContdate() {
		return contdate;
	}
	public void setContdate(String contdate) {
		this.contdate = contdate;
	}
	public String getContunit() {
		return contunit;
	}
	public void setContunit(String contunit) {
		this.contunit = contunit;
	}
	public String getContypeid() {
		return contypeid;
	}
	public void setContypeid(String contypeid) {
		this.contypeid = contypeid;
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
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public String getInsertdesc() {
		return insertdesc;
	}
	public void setInsertdesc(String insertdesc) {
		this.insertdesc = insertdesc;
	}
	public String getInsertfatherid() {
		return insertfatherid;
	}
	public void setInsertfatherid(String insertfatherid) {
		this.insertfatherid = insertfatherid;
	}
	public String getInsertid() {
		return insertid;
	}
	public void setInsertid(String insertid) {
		this.insertid = insertid;
	}
	public String getIntflag() {
		return intflag;
	}
	public void setIntflag(String intflag) {
		this.intflag = intflag;
	}
	public String getLimitdate() {
		return limitdate;
	}
	public void setLimitdate(String limitdate) {
		this.limitdate = limitdate;
	}
	public String getLimitid() {
		return limitid;
	}
	public void setLimitid(String limitid) {
		this.limitid = limitid;
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
	public String getPowerdate() {
		return powerdate;
	}
	public void setPowerdate(String powerdate) {
		this.powerdate = powerdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSolddate() {
		return solddate;
	}
	public void setSolddate(String solddate) {
		this.solddate = solddate;
	}
	public String getSoldid() {
		return soldid;
	}
	public void setSoldid(String soldid) {
		this.soldid = soldid;
	}
	public String getStrflag() {
		return strflag;
	}
	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}
	public String getUnitdate() {
		return unitdate;
	}
	public void setUnitdate(String unitdate) {
		this.unitdate = unitdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	
}