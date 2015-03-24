package com.nomen.ntrain.ptrain.bean;

/**
 * @description 投票_批次POJO 
 * @author 林木山
 * @date 2014-12-15
 */
public class PtrainVoteappBean {
    private String id;     //ID（主键）
    private String appname;//批次名称
    private String defvote;//默认当前投票批次

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAppname() {
        return appname;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
    public String getDefvote() {
        return defvote;
    }
    public void setDefvote(String defvote) {
        this.defvote = defvote;
    }
}
