package com.nomen.ntrain.data.bean;

/**
 * @description 机构人员_岗位调动POJO 
 * @author 林木山
 * @date 2013-5-30
 */
public class DataPostmoveBean {
    private String id;         //ID（主键）
    private String oldunitid;  //原单位ID
    private String olddeptid;  //原部门ID
    private String oldgroupid; //原班组ID
    private String oldpostname;//原岗位名称
    private String newunitid;  //现单位ID
    private String newdeptid;  //现部门ID
    private String newgroupid; //现班组ID
    private String newpostname;//现岗位名称
    private String userid;     //人员ID
    private String movedate;   //调动时间
    private String remark;     //备注
    private String flowsta;    //流程状态（0调动填写，51待调动确认，99归档）
    private String subtime;    //填报时间
    private String flowmark;   //流程标记值
    private String fromtype;   //数据来源类型（1流程（填写），2维护（填写），7引入，8同步，9自动）
    private String estauser;   //创建人
    private String estatime;   //创建时间
    private String mainuser;   //维护人
    private String maintime;   //维护时间
    private String strflag;    //预留字段（字符串）
    private String intflag;    //预留字段（整型）
	//Get和Set方法
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getMovedate() {
        return movedate;
    }
    public void setMovedate(String movedate) {
        this.movedate = movedate;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOldunitid() {
        return oldunitid;
    }
    public void setOldunitid(String oldunitid) {
        this.oldunitid = oldunitid;
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
    public String getOldpostname() {
        return oldpostname;
    }
    public void setOldpostname(String oldpostname) {
        this.oldpostname = oldpostname;
    }
    public String getNewunitid() {
        return newunitid;
    }
    public void setNewunitid(String newunitid) {
        this.newunitid = newunitid;
    }
    public String getNewdeptid() {
        return newdeptid;
    }
    public void setNewdeptid(String newdeptid) {
        this.newdeptid = newdeptid;
    }
    public String getNewgroupid() {
        return newgroupid;
    }
    public void setNewgroupid(String newgroupid) {
        this.newgroupid = newgroupid;
    }
    public String getNewpostname() {
        return newpostname;
    }
    public void setNewpostname(String newpostname) {
        this.newpostname = newpostname;
    }
	public String getFlowmark() {
		return flowmark;
	}
	public void setFlowmark(String flowmark) {
		this.flowmark = flowmark;
	}
	public String getFromtype() {
		return fromtype;
	}
	public void setFromtype(String fromtype) {
		this.fromtype = fromtype;
	}
}
