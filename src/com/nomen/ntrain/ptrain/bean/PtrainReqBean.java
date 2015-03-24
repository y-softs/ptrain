package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_培训点菜POJO 
 * @author 林木山
 * @date 2014-3-14
 */
public class PtrainReqBean {
    private String id;       //ID（主键）
    private String unitid;   //单位ID
    private String specid;   //专业类别ID
    private String itemname; //项目名称
    private String itemdesc; //项目说明
    private String daycount; //培训天数
    private String reqform;  //项目来源
    private String teacher;  //培训师
    private String requserid;//需求提出人员
    private String reqtype;  //需求类别（1咨询公司需求，2专家授课需求，9员工需求）
    private String state;    //办班状态（0待办班，1已办班）
    private String flowsta;  //流程状态（0待需求报审，51待需求审核，99归档）
    private String subtime;  //报审时间
    private String flowmark; //流程标记
    private String estauser; //创建人
    private String estatime; //创建时间
    private String mainuser; //维护人
    private String maintime; //维护时间
    private String strflag;  //预留字段（字符串）
    private String intflag;  //预留字段（整型）

    private String filenum;  //附件数量

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSpecid() {
        return specid;
    }
    public void setSpecid(String specid) {
        this.specid = specid;
    }
    public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getItemdesc() {
        return itemdesc;
    }
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }
    public String getDaycount() {
        return daycount;
    }
    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }
    public String getReqform() {
        return reqform;
    }
    public void setReqform(String reqform) {
        this.reqform = reqform;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getRequserid() {
        return requserid;
    }
    public void setRequserid(String requserid) {
        this.requserid = requserid;
    }
    public String getReqtype() {
        return reqtype;
    }
    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
    public String getFlowmark() {
        return flowmark;
    }
    public void setFlowmark(String flowmark) {
        this.flowmark = flowmark;
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
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getFilenum() {
		return filenum;
	}
	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}
}
