package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_每日三问POJO 
 * @author 林木山
 * @date 2014-3-24
 */
public class PtrainAskBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String userid;  //人员ID
    private String pushnum; //抽题轮次
    private String pushtime;//抽题时间
    private String score;   //本轮得分
    private String subtime; //交卷时间
    private String estauser;//创建人
    private String estatime;//创建时间
    private String mainuser;//维护人
    private String maintime;//维护时间
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

    private String asktotal;  //累计答题次数

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
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getPushnum() {
        return pushnum;
    }
    public void setPushnum(String pushnum) {
        this.pushnum = pushnum;
    }
    public String getPushtime() {
        return pushtime;
    }
    public void setPushtime(String pushtime) {
        this.pushtime = pushtime;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
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
	public String getAsktotal() {
		return asktotal;
	}
	public void setAsktotal(String asktotal) {
		this.asktotal = asktotal;
	}
}
