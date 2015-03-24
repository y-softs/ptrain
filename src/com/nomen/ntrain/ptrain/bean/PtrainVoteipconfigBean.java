package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_投票管理[投票_合法IP段]
 * @author 
 * @date 2014-12-15
 */
public class PtrainVoteipconfigBean {
	private String id;          //主键ID
    private String appid;       //所在批次
    private String startip;     //ip1（192.168.1.20）
    private String endip;       //ip2（192.168.1.99）
	
	//Get和Set方法
    public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getEndip() {
		return endip;
	}
	public void setEndip(String endip) {
		this.endip = endip;
	}
	public String getStartip() {
		return startip;
	}
	public void setStartip(String startip) {
		this.startip = startip;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
