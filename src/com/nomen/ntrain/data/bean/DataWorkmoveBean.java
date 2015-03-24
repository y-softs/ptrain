package com.nomen.ntrain.data.bean;

/**
 * @description 机构人员_考勤岗位调动POJO 
 * @author 林木山
 * @date 2013-6-7
 */
public class DataWorkmoveBean {
    private String id;         //ID（主键）
    private String postmoveid; //岗位调动ID（关联TB_DATA_POSTMOVE表）
    private String newareaid;  //现区域位置ID
    private String oldareaname;//原区域位置名称
    private String oldareaid;  //原区域位置ID
    private String newareaname;//现区域位置名称
    private String estauser;   //创建人
    private String estatime;   //创建时间
    private String mainuser;   //维护人
    private String maintime;   //维护时间
    private String strflag;    //预留字段（字符串）
    private String intflag;    //预留字段（整型）

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPostmoveid() {
        return postmoveid;
    }
    public void setPostmoveid(String postmoveid) {
        this.postmoveid = postmoveid;
    }
    public String getNewareaid() {
        return newareaid;
    }
    public void setNewareaid(String newareaid) {
        this.newareaid = newareaid;
    }
    public String getOldareaname() {
        return oldareaname;
    }
    public void setOldareaname(String oldareaname) {
        this.oldareaname = oldareaname;
    }
    public String getOldareaid() {
        return oldareaid;
    }
    public void setOldareaid(String oldareaid) {
        this.oldareaid = oldareaid;
    }
    public String getNewareaname() {
        return newareaname;
    }
    public void setNewareaname(String newareaname) {
        this.newareaname = newareaname;
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
