package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 人员代码信息表POJO
 * @author 钱新红
 * @date 2010-1-4
 * @modifier 陈高添、丁新良
 * @date 2010-04-26、2010-12-27
 */
public class DataCodeBean implements Serializable{
	private String id;			//id（主键，人员代码）
	private String nature;		//性质（1共性，2个性）
	private String unitid;		//单位id
	private String fatherid;	//父级id
	private String codename;	//代码名称
	private String gradeid;		//属性id/类别
	private String orggrade;	//行政等级[机构名称] 跨级[行政区划]   gradeid2
	private String orgzone;	    //行政区划[机构名称] 省会[行政区划]    gradeid3
	private String orgclass;	//机构类型[机构名称]     gradeid4
	private String orgnature;	//机构性质[机构名称]    gradeid5
	private String orgunder;	//机构隶属[机构名称]    gradeid6
	private String areacode;	//区号  
	private String postcode;	//邮编
	private String carcode;     //车牌号
	private String remark;		//说明
	private String endsign;		//末端标志（1是，0否）
	private String usesign;		//启用标志（1启用，0禁止）
	private String specsign;	//标志（1是，0否）
	private String codevalue;	//编码
	private String sortnum;		//排序号
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）==当数据为等级层面数据时标记为-1
	//辅助字段
	private String fathername;	//父级名称
	
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
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public String getGradeid() {
		return gradeid;
	}
	public void setGradeid(String gradeid) {
		this.gradeid = gradeid;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEndsign() {
		return endsign;
	}
	public void setEndsign(String endsign) {
		this.endsign = endsign;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
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
	public String getUsesign() {
		return usesign;
	}
	public void setUsesign(String usesign) {
		this.usesign = usesign;
	}
	public String getSpecsign() {
		return specsign;
	}
	public void setSpecsign(String specsign) {
		this.specsign = specsign;
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
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	public String getOrgclass() {
		return orgclass;
	}
	public void setOrgclass(String orgclass) {
		this.orgclass = orgclass;
	}
	public String getOrggrade() {
		return orggrade;
	}
	public void setOrggrade(String orggrade) {
		this.orggrade = orggrade;
	}
	public String getOrgnature() {
		return orgnature;
	}
	public void setOrgnature(String orgnature) {
		this.orgnature = orgnature;
	}
	public String getOrgunder() {
		return orgunder;
	}
	public void setOrgunder(String orgunder) {
		this.orgunder = orgunder;
	}
	public String getOrgzone() {
		return orgzone;
	}
	public void setOrgzone(String orgzone) {
		this.orgzone = orgzone;
	}
	public String getCarcode() {
		return carcode;
	}
	public void setCarcode(String carcode) {
		this.carcode = carcode;
	}
	
}