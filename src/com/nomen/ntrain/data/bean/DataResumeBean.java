package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 机构人员_个人履历变更表
 * @author 李刚
 * @date 2011-9-19
 */
public class DataResumeBean implements Serializable{
	private String id;				//id（主键）
	private String unitid;			//单位id
	private String deptid;			//部门id
	private String groupid;			//班组id
	private String userid;			//人员id
	private String natureid;		//人员性质id
	private String gradeid;			//岗位层级id
	private String rectype;			//"履历类别（304在职经历，311特殊工种，312特定岗位，学历等级：601）变动类别（101部门性质，191部门名称，201国籍，202民族，204政治面貌，205人员性质，206人员状态，301人员类别i，302,岗位分类，303岗位层级，306党团工政，307专业类别，308岗级，309职级，310工作性质，605学位等级，701技术职称等级，703执业资格等级，707专家人才等级，805婚姻状况，925证书岗位岗次，935劳保岗位工种，945网学岗位岗次，946网考岗位岗次，955cbe岗位岗次，956科目岗位岗次）"
	private String startdate;		//起始时间（获取时间，学习起始时间）
	private String enddate;			//结束时间（变动时间：操作时间，获取时间，学习结束时间）
	private String mpostid;			//变动前主名称id
	private String mpost;			//变动前主名称
	private String mstateid;		//变动前主状态id（主岗位状态id，特定岗位类型，学习形式id）
	private String mstate;			//变动前主状态名称
	private String ppostid1;		//变动前次名称1id
	private String ppost1;			//变动前次名称1
	private String pstateid1;		//变动前次状态1id
	private String pstate1;			//变动前次状态1
	private String ppostid2;		//变动前次名称2id
	private String ppost2;			//变动前次名称2
	private String pstateid2;		//变动前次状态2id
	private String pstate2;			//变动前次状态2
	private String ppostid3;		//变动前次名称3id
	private String ppost3;			//变动前次名称3
	private String pstateid3;		//变动前次状态3id
	private String pstate3;			//变动前次状态3
	private String bsign;			//变动前备用标志（1非本单位，0本单位）
	private String bname1;			//变动前备用名称（单位名称，毕业院校）
	private String bname2;			//变动前备用名称（部门名称，专业名称）
	private String bname3;			//变动前备用名称（班组名称，学制，学位数量）
	private String mpostid_n;		//变动后主名称id
	private String mpost_n;			//变动后主名称
	private String mstateid_n;		//变动后主状态id（主岗位状态id，特定岗位类型，学习形式id）（变更后）
	private String mstate_n;		//变动后主状态名称
	private String ppostid1_n;		//变动后次名称1id
	private String ppost1_n;		//变动后次名称1
	private String pstateid1_n;		//变动后次状态1id
	private String pstate1_n;		//变动后次状态1
	private String ppostid2_n;		//变动后次名称2id
	private String ppost2_n;		//变动后次名称2
	private String pstateid2_n;		//变动后次状态2id
	private String pstate2_n;		//变动后次状态2
	private String ppostid3_n;		//变动后次名称3id
	private String ppost3_n;		//变动后次名称3
	private String pstateid3_n;		//变动后次状态3id
	private String pstate3_n;		//变动后次状态3
	private String bsign_n;			//变动后备用标志（1非本单位，0本单位）
	private String bname1_n;		//变动后备用名称（单位名称，毕业院校）
	private String bname2_n;		//变动后备用名称（部门名称，专业名称）
	private String bname3_n;		//变动后备用名称（班组名称，学制，学位数量）
	private String remark;			//备注
	private String flowsta;	        //流程状态（0个人填写，41待人资审核，99归档）
	private String subtime;			//报审时间
	private String fromtype;	    //数据来源类型（1流程（填写），2维护（填写），7引入，8同步，9自动）
	private String fromname;        //数据来源子系统名称
	private String fromid;          //数据来源记录id
	private String hrefurl;         //数据链接url
	private String estauser;		//创建人
	private String estatime;		//创建时间
	private String mainuser;		//维护人
	private String maintime;		//维护时间
	private String strflag;			//预留字段（字符串）
	private String intflag;			//预留字段（整型）
	//界面辅助字段
	private String groupfatherid;   //末端班组的fatherid
	
	
	
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