package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位学习_抽题策略POJO 
 * @author 林木山
 * @date 2014-3-14
 */
public class PtrainParamBean {
    private String id;        //ID（主键）
    private String unitid;    //单位ID
    private String paramname; //参数名称
    private String paramvalue;//参数值
    private String remark;    //参数描述
    private String estauser;  //创建人
    private String estatime;  //创建时间
    private String mainuser;  //维护人
    private String maintime;  //维护时间
    private String strflag;   //预留字段（字符串）
    private String intflag;   //预留字段（整型）

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
    public String getParamname() {
        return paramname;
    }
    public void setParamname(String paramname) {
        this.paramname = paramname;
    }
    public String getParamvalue() {
        return paramvalue;
    }
    public void setParamvalue(String paramvalue) {
        this.paramvalue = paramvalue;
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
