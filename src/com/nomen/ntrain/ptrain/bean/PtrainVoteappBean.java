package com.nomen.ntrain.ptrain.bean;

/**
 * @description ͶƱ_����POJO 
 * @author ��ľɽ
 * @date 2014-12-15
 */
public class PtrainVoteappBean {
    private String id;     //ID��������
    private String appname;//��������
    private String defvote;//Ĭ�ϵ�ǰͶƱ����

	//Get��Set����
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
