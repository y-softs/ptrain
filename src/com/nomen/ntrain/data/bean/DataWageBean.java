package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 工资_人员工资配置表POJO 
 * @author 王怀港
 * @date 2012-10-13
 */
public class DataWageBean implements Serializable{
    private String id;       //ID（主键）
    private String userid;   //人员ID
    private String typeid;   //类别（11工资体系，21工资岗级，25工资薪级，26工资薪点，31保险系数，41奖金系数，91工资来源）
    private String typevalue;//值
    private String remark;   //备注
    private String estauser; //创建人
    private String estatime; //创建时间
    private String mainuser; //维护人
    private String maintime; //维护时间
    private String strflag;  //预留字段（字符串）
    private String intflag;  //预留字段（整型）

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTypevalue() {
        return typevalue;
    }
    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
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
