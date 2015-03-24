package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_讲师团队POJO 
 * @author 林木山
 * @date 2014-3-14
 */
public class PtrainTeacherBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String teacher; //讲师姓名
    private String specid;  //专业类别ID（关联PTRAIN_CODE表）
    private String expert;  //擅长课程
    private String workunit;//所属单位
    private String contact; //联系方式
    private String remark;  //备注
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
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getSpecid() {
        return specid;
    }
    public void setSpecid(String specid) {
        this.specid = specid;
    }
    public String getExpert() {
        return expert;
    }
    public void setExpert(String expert) {
        this.expert = expert;
    }
    public String getWorkunit() {
        return workunit;
    }
    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
