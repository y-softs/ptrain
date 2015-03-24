package com.nomen.ntrain.data.bean;

/**
 * @description 机构人员_工资岗位调动信息POJO 
 * @author 林木山
 * @date 2013-5-31
 */
public class DataWagemoveBean {
    private String id;        //ID（主键）
    private String postmoveid;//岗位调动ID（关联TB_DATA_POSTMOVE）
    private String subid;     //科目ID（0：工资体系，-1财务部门，其他：科目ID）
    private String subname;   //科目名称
    private String oldvalue;  //原科目值ID
    private String oldname;   //原科目值名称
    private String newvalue;  //原科目值ID
    private String newname;   //现科目值名称
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
    public String getPostmoveid() {
        return postmoveid;
    }
    public void setPostmoveid(String postmoveid) {
        this.postmoveid = postmoveid;
    }
    public String getSubid() {
        return subid;
    }
    public void setSubid(String subid) {
        this.subid = subid;
    }
    public String getSubname() {
        return subname;
    }
    public void setSubname(String subname) {
        this.subname = subname;
    }
    public String getOldvalue() {
        return oldvalue;
    }
    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue;
    }
    public String getOldname() {
        return oldname;
    }
    public void setOldname(String oldname) {
        this.oldname = oldname;
    }
    public String getNewvalue() {
        return newvalue;
    }
    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue;
    }
    public String getNewname() {
        return newname;
    }
    public void setNewname(String newname) {
        this.newname = newname;
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
