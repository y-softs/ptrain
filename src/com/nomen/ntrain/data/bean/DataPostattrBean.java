package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 机构人员_岗位名称关联POJO
 * @author 丁新良
 * @date 2011-05-31
 */
public class DataPostattrBean implements Serializable{
	private String id;				//id(主键)
	private String nature;			//性质（1共性，2个性）
	private String unitid;			//单位id
	private String postid;			//岗位名称id
	private String gradeid;			//岗位层级id串（多个id间用,间隔）
	private String partyid;			//党团工政id
	private String typeid;			//人员类别id
	private String posttypeid;		//岗位分类id
	private String specid;			//专业类别id
	private String specname;	    //专业名称
	private String natureid;		//工作性质id
	private String partid;			//特殊工种名称id
	private String specpostid;       //特定岗位id
	private String joinid1;			//关联证书岗位岗次
	private String joinid2;			//关联劳保岗位岗次
	private String joinid3;			//关联网络学习岗位岗次
	private String joinid4;			//关联网络考试岗位岗次
	private String joinid5;			//关联cbe岗位岗次
	private String joinid6;			//关联科目岗位岗次
	private String joinid7;			//关联1
	private String joinid8;			//关联2
	private String joinid9;			//关联3
	private String joinid10;		//关联4
	private String estauser;		//创建人
	private String estatime;		//创建时间
	private String mainuser;		//维护人
	private String maintime;		//维护时间
	private String strflag;			//预留字段（字符串）
	private String intflag; 		//预留字段（整型）
	
	//以下是GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getPostid() {
		return postid;
	}
	public void setPostid(String postid) {
		this.postid = postid;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getPartyid() {
		return partyid;
	}
	public void setPartyid(String partyid) {
		this.partyid = partyid;
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
	public String getSpecpostid() {
		return specpostid;
	}
	public void setSpecpostid(String specpostid) {
		this.specpostid = specpostid;
	}
	public String getJoinid1() {
		return joinid1;
	}
	public void setJoinid1(String joinid1) {
		this.joinid1 = joinid1;
	}
	public String getJoinid2() {
		return joinid2;
	}
	public void setJoinid2(String joinid2) {
		this.joinid2 = joinid2;
	}
	public String getJoinid3() {
		return joinid3;
	}
	public void setJoinid3(String joinid3) {
		this.joinid3 = joinid3;
	}
	public String getJoinid4() {
		return joinid4;
	}
	public void setJoinid4(String joinid4) {
		this.joinid4 = joinid4;
	}
	public String getJoinid5() {
		return joinid5;
	}
	public void setJoinid5(String joinid5) {
		this.joinid5 = joinid5;
	}
	public String getJoinid6() {
		return joinid6;
	}
	public void setJoinid6(String joinid6) {
		this.joinid6 = joinid6;
	}
	public String getJoinid7() {
		return joinid7;
	}
	public void setJoinid7(String joinid7) {
		this.joinid7 = joinid7;
	}
	public String getJoinid8() {
		return joinid8;
	}
	public void setJoinid8(String joinid8) {
		this.joinid8 = joinid8;
	}
	public String getJoinid9() {
		return joinid9;
	}
	public void setJoinid9(String joinid9) {
		this.joinid9 = joinid9;
	}
	public String getJoinid10() {
		return joinid10;
	}
	public void setJoinid10(String joinid10) {
		this.joinid10 = joinid10;
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