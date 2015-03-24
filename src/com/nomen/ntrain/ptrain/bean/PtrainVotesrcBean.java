package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_投票管理
 * @author 
 * @date 2014-12-15
 */
public class PtrainVotesrcBean {
    private String id;           //ID（主键）
    private String appid;        //所在批次
    private String srcimg;       //资源封面
    private String srctitle;     //投票对象标题
    private String srcsign;      //对象类别（0课件、1视频）
    private String srcurl;       //对象url（如dj/src.htm）
    private String author;       //作者
    private String unitname;     //工作单位
    private String remark;       //作品简介
    private String sortnum;      //排序号
    private String votecount;     //得票数
    private String mainuser;     //维护人
    private String maintime;     //维护时间
    private String estauser;     //创建人id
    private String estatime;     //创建时间
	
    
	//Get和Set方法
    public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEstatime() {
		return estatime;
	}
	public void setEstatime(String estatime) {
		this.estatime = estatime;
	}
	public String getEstauser() {
		return estauser;
	}
	public void setEstauser(String estauser) {
		this.estauser = estauser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaintime() {
		return maintime;
	}
	public void setMaintime(String maintime) {
		this.maintime = maintime;
	}
	public String getMainuser() {
		return mainuser;
	}
	public void setMainuser(String mainuser) {
		this.mainuser = mainuser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSrcimg() {
		return srcimg;
	}
	public void setSrcimg(String srcimg) {
		this.srcimg = srcimg;
	}
	public String getSrcsign() {
		return srcsign;
	}
	public void setSrcsign(String srcsign) {
		this.srcsign = srcsign;
	}
	public String getSrctitle() {
		return srctitle;
	}
	public void setSrctitle(String srctitle) {
		this.srctitle = srctitle;
	}
	public String getSrcurl() {
		return srcurl;
	}
	public void setSrcurl(String srcurl) {
		this.srcurl = srcurl;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getVotecount() {
		return votecount;
	}
	public void setVotecount(String votecount) {
		this.votecount = votecount;
	}
	public String getSortnum() {
		return sortnum;
	}
	public void setSortnum(String sortnum) {
		this.sortnum = sortnum;
	}
}
