package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位学习_附件表POJO 
 * @author 林木山
 * @date 2014-3-14
 */
public class PtrainFileBean {
    private String id;      //ID（主键）
    private String recid;   //关联记录ID
    private String modsign; //模块标志
    private String filename;//上传文件名
    private String savename;//保存文件名
    private String savepath;//保存路径
    private String content; //文件内容（预留）
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRecid() {
        return recid;
    }
    public void setRecid(String recid) {
        this.recid = recid;
    }
    public String getModsign() {
        return modsign;
    }
    public void setModsign(String modsign) {
        this.modsign = modsign;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getSavename() {
        return savename;
    }
    public void setSavename(String savename) {
        this.savename = savename;
    }
    public String getSavepath() {
        return savepath;
    }
    public void setSavepath(String savepath) {
        this.savepath = savepath;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
