package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_咨询公司POJO 
 * @author 林木山
 * @date 2014-3-13
 */
public class PtrainPorgBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String orgname; //公司名称
    private String orgdesc; //公司简介
    private String orgcase; //案例
    private String contact; //联系方式
    private String usesign; //启用标志（1 启用， 0 禁用）
    private String sortnum; //排序号
    private String estauser;//创建人
    private String estatime;//创建时间
    private String mainuser;//维护人
    private String maintime;//维护时间
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

	//Get和Set方法
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
    public String getOrgname() {
        return orgname;
    }
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
    public String getOrgdesc() {
        return orgdesc;
    }
    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc;
    }
    public String getOrgcase() {
        return orgcase;
    }
    public void setOrgcase(String orgcase) {
        this.orgcase = orgcase;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
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
