package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员岗位信息表POJO 
 * @author 钱新红
 * @date 2009-10-20
 * @modifier 陈高添-丁新良
 * @date 2010-05-07、2011-06-15
 */

public class DataPostBean implements Serializable{
	private String userid;			//人员id
	private String mgrade;			//主岗位层级id
	private String mpostid;			//主岗位名称id
	private String mpost;			//主岗位名称
	private String mdate;	        //主岗位任职时间 
	private String mstate;			//主岗位状态id
	private String mtype;			//主岗位党团工政id
	private String pgrade1;			//兼职岗位1层级id
	private String ppostid1;		//兼职岗位1名称id
	private String ppost1;			//兼职岗位1名称
	private String pdate1;	 		//兼职岗位1任职时间
	private String pstate1;			//兼职岗位1状态id
	private String ptype1;			//兼职岗位1党团工政id
	private String pgrade2;			//兼职岗位2层级id
	private String ppostid2;		//兼职岗位2名称id
	private String ppost2;			//兼职岗位2名称
	private String pdate2;			//兼职岗位2任职时间
	private String pstate2;			//兼职岗位2状态id
	private String ptype2;			//兼职岗位2党团工政id
	private String pgrade3;			//兼职岗位3层级id
	private String ppostid3;		//兼职岗位3名称id
	private String ppost3;			//兼职岗位3名称
	private String pdate3;			//兼职岗位3任职时间
	private String pstate3;			//兼职岗位3状态id
	private String ptype3;			//兼职岗位3党团工政id
	private String typeid;			//人员类别id
	private String posttypeid;		//岗位分类id
	private String specid;			//专业类别ID
	private String specname;		//专业名称
	private String workyear;		//从事现专业年度
	private String postgrade;		//岗级
	private String dutygrade;		//职级
	private String dutydate;		//职级聘任时间
	private String natureid;		//工作性质id
	private String partid;			//特殊工种名称id
	private String partdate;	    //特殊工种聘任时间
	private String specpost;		//特定岗位ID（ID间用,间隔）
	private String remark;			//备注
	private String estauser;		//创建人
	private String estatime;		//创建时间
	private String mainuser;		//维护人
	private String maintime;		//维护时间
	private String strflag;			//预留字段（字符串）
	private String intflag;			//预留字段（整型）
	
	//辅助字段
	private String posttypefatherid; //岗位分类父级id
	private String posttypeendsign;  //岗位分类末端标志
	private String specfatherid; 	 //专业类别父级id
	private String specidendsign; 	 //专业类别末端标志
	private String partfatherid; 	 //特殊工种父级id
	
	private String posttypename;     //岗位分类[名称]
	private String specpoststr;		//特定岗位[名称]
	private String specnamestr;		//专业/工种类别[名称]
	private String workmonth; 		//从事现专业月份

	//以下是GET 和 SET 方法
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMgrade() {
		return mgrade;
	}
	public void setMgrade(String mgrade) {
		this.mgrade = mgrade;
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
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getPgrade1() {
		return pgrade1;
	}
	public void setPgrade1(String pgrade1) {
		this.pgrade1 = pgrade1;
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
	public String getPdate1() {
		return pdate1;
	}
	public void setPdate1(String pdate1) {
		this.pdate1 = pdate1;
	}
	public String getPstate1() {
		return pstate1;
	}
	public void setPstate1(String pstate1) {
		this.pstate1 = pstate1;
	}
	public String getPtype1() {
		return ptype1;
	}
	public void setPtype1(String ptype1) {
		this.ptype1 = ptype1;
	}
	public String getPgrade2() {
		return pgrade2;
	}
	public void setPgrade2(String pgrade2) {
		this.pgrade2 = pgrade2;
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
	public String getPdate2() {
		return pdate2;
	}
	public void setPdate2(String pdate2) {
		this.pdate2 = pdate2;
	}
	public String getPstate2() {
		return pstate2;
	}
	public void setPstate2(String pstate2) {
		this.pstate2 = pstate2;
	}
	public String getPtype2() {
		return ptype2;
	}
	public void setPtype2(String ptype2) {
		this.ptype2 = ptype2;
	}
	public String getPgrade3() {
		return pgrade3;
	}
	public void setPgrade3(String pgrade3) {
		this.pgrade3 = pgrade3;
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
	public String getPdate3() {
		return pdate3;
	}
	public void setPdate3(String pdate3) {
		this.pdate3 = pdate3;
	}
	public String getPstate3() {
		return pstate3;
	}
	public void setPstate3(String pstate3) {
		this.pstate3 = pstate3;
	}
	public String getPtype3() {
		return ptype3;
	}
	public void setPtype3(String ptype3) {
		this.ptype3 = ptype3;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getPosttypeid() {
		return posttypeid;
	}
	public void setPosttypeid(String posttypeid) {
		this.posttypeid = posttypeid;
	}
	public String getSpecid() {
		return specid;
	}
	public void setSpecid(String specid) {
		this.specid = specid;
	}
	public String getSpecname() {
		return specname;
	}
	public void setSpecname(String specname) {
		this.specname = specname;
	}
	public String getWorkyear() {
		return workyear;
	}
	public void setWorkyear(String workyear) {
		this.workyear = workyear;
	}
	public String getPostgrade() {
		return postgrade;
	}
	public void setPostgrade(String postgrade) {
		this.postgrade = postgrade;
	}
	public String getDutygrade() {
		return dutygrade;
	}
	public void setDutygrade(String dutygrade) {
		this.dutygrade = dutygrade;
	}
	public String getDutydate() {
		return dutydate;
	}
	public void setDutydate(String dutydate) {
		this.dutydate = dutydate;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getPartid() {
		return partid;
	}
	public void setPartid(String partid) {
		this.partid = partid;
	}
	public String getPartdate() {
		return partdate;
	}
	public void setPartdate(String partdate) {
		this.partdate = partdate;
	}
	public String getSpecpost() {
		return specpost;
	}
	public void setSpecpost(String specpost) {
		this.specpost = specpost;
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
	public String getPosttypefatherid() {
		return posttypefatherid;
	}
	public void setPosttypefatherid(String posttypefatherid) {
		this.posttypefatherid = posttypefatherid;
	}
	public String getPosttypeendsign() {
		return posttypeendsign;
	}
	public void setPosttypeendsign(String posttypeendsign) {
		this.posttypeendsign = posttypeendsign;
	}
	public String getPosttypename() {
		return posttypename;
	}
	public void setPosttypename(String posttypename) {
		this.posttypename = posttypename;
	}
	public String getSpecfatherid() {
		return specfatherid;
	}
	public void setSpecfatherid(String specfatherid) {
		this.specfatherid = specfatherid;
	}
	public String getSpecidendsign() {
		return specidendsign;
	}
	public void setSpecidendsign(String specidendsign) {
		this.specidendsign = specidendsign;
	}
	public String getPartfatherid() {
		return partfatherid;
	}
	public void setPartfatherid(String partfatherid) {
		this.partfatherid = partfatherid;
	}
	public String getWorkmonth() {
		return workmonth;
	}
	public void setWorkmonth(String workmonth) {
		this.workmonth = workmonth;
	}
	public String getSpecpoststr() {
		return specpoststr;
	}
	public void setSpecpoststr(String specpoststr) {
		this.specpoststr = specpoststr;
	}
	public String getSpecnamestr() {
		return specnamestr;
	}
	public void setSpecnamestr(String specnamestr) {
		this.specnamestr = specnamestr;
	}
	
	
}