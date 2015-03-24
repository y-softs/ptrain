package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_知识版主POJO 
 * @author 林木山
 * @date 2014-3-10
 */
public class PtrainManagerBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String kpid;    //知识类别ID
    private String managers;//版主ID串（人员ID间用,间隔）
    private String estauser;//创建人
    private String estatime;//创建时间
    private String mainuser;//维护人
    private String maintime;//维护时间
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

    private String codename; //预留字段（整型）
    

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
    public String getKpid() {
        return kpid;
    }
    public void setKpid(String kpid) {
        this.kpid = kpid;
    }
    public String getManagers() {
        return managers;
    }
    public void setManagers(String managers) {
        this.managers = managers;
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
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
}
