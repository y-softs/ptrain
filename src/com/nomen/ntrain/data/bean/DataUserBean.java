package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员基本信息表POJO 
 * @author 钱新红
 * @date 2009-09-30
 * @modifier 陈高添-丁新良
 * @date 2010-04-29、2011-07-21
 */

public class DataUserBean implements Serializable{
	private String id;		    //id（主键）
	private String unitid;		//现工作单位id
	private String deptid;		//现工作部门id
	private String groupid;		//现工作班组id
	private String username;	//姓名
	private String oldname;     //曾用名
	private String py;		    //姓名拼音
	private String photo;		//相片
	private String userid;		//身份证号
	private String workid;		//工号
	private String countryid;	//国籍
	private String natalid;		//民族
	private String sex;		    //性别
	private String birthday;	//出生年月
	private String birthplace;  //出生地
	private String religion;    //宗教信仰ID串（多个ID间用,间隔）
	private String healthid;    //健康状况ID
	private String marryid;     //婚姻状况ID
	private String place;		//籍贯
	private String politid;		//政治面貌
	private String politdate;	//加入时间
	private String kindid;		//人员性质
	private String kinddate;	//人员性质变动时间
	private String stateid;		//人员状态
	private String statedate;	//人员状态变动时间
	private String interest;    //兴趣特长
	private String loginpsd;	//登陆密码
	private String changesign;	//身份证号15位→18位标志（1是，0否）
	private String oldunitid;	//原工作单位id
	private String olddeptid;	//原工作部门id
	private String oldgroupid;	//原工作班组id
	private String remark;		//备注
	private String sortnum;		//排序号
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
    private byte[] image;       //图片字节数组
    
    //备注 （辅助字段）
    private String natureid;		    //部门性质id
    private String oldgroupfatherid;    //班组父id
    private String groupfatherid;		//班组父id
    private String statefatherid;		//人员状态父id
    private String marryfatherid;		//婚姻状况父id
    private String religionnames;       //宗教信仰[辅助]
    private String mpost;               //主岗位名称
    
    //以下是GET 和 SET 方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarryfatherid() {
		return marryfatherid;
	}
	public void setMarryfatherid(String marryfatherid) {
		this.marryfatherid = marryfatherid;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
	public String getNatalid() {
		return natalid;
	}
	public void setNatalid(String natalid) {
		this.natalid = natalid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPolitid() {
		return politid;
	}
	public void setPolitid(String politid) {
		this.politid = politid;
	}
	public String getPolitdate() {
		return politdate;
	}
	public void setPolitdate(String politdate) {
		this.politdate = politdate;
	}
	public String getKindid() {
		return kindid;
	}
	public void setKindid(String kindid) {
		this.kindid = kindid;
	}
	public String getKinddate() {
		return kinddate;
	}
	public void setKinddate(String kinddate) {
		this.kinddate = kinddate;
	}
	public String getStateid() {
		return stateid;
	}
	public void setStateid(String stateid) {
		this.stateid = stateid;
	}
	public String getStatedate() {
		return statedate;
	}
	public void setStatedate(String statedate) {
		this.statedate = statedate;
	}
	public String getLoginpsd() {
		return loginpsd;
	}
	public void setLoginpsd(String loginpsd) {
		this.loginpsd = loginpsd;
	}
	public String getChangesign() {
		return changesign;
	}
	public void setChangesign(String changesign) {
		this.changesign = changesign;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getNatureid() {
		return natureid;
	}
	public void setNatureid(String natureid) {
		this.natureid = natureid;
	}
	public String getGroupfatherid() {
		return groupfatherid;
	}
	public void setGroupfatherid(String groupfatherid) {
		this.groupfatherid = groupfatherid;
	}
	public String getStatefatherid() {
		return statefatherid;
	}
	public void setStatefatherid(String statefatherid) {
		this.statefatherid = statefatherid;
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
	public String getOldunitid() {
		return oldunitid;
	}
	public void setOldunitid(String oldunitid) {
		this.oldunitid = oldunitid;
	}
	public String getOldgroupfatherid() {
		return oldgroupfatherid;
	}
	public void setOldgroupfatherid(String oldgroupfatherid) {
		this.oldgroupfatherid = oldgroupfatherid;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getHealthid() {
		return healthid;
	}
	public void setHealthid(String healthid) {
		this.healthid = healthid;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getMarryid() {
		return marryid;
	}
	public void setMarryid(String marryid) {
		this.marryid = marryid;
	}
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getReligionnames() {
		return religionnames;
	}
	public void setReligionnames(String religionnames) {
		this.religionnames = religionnames;
	}
	public String getMpost() {
		return mpost;
	}
	public void setMpost(String mpost) {
		this.mpost = mpost;
	}
}