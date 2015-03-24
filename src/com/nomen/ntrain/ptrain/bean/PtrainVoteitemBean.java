package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_投票管理[投票_投票详细]
 * @author 
 * @date 2014-12-15
 */
public class PtrainVoteitemBean {
    private String srcid;       //资源id
    private String voteip;       //投票人所在的ip
    private String voteflag;     //投票类型（1喜欢，0不喜欢）
    private String voteuserid;   //投票人员userid
    
	
	//Get和Set方法
    public String getSrcid() {
		return srcid;
	}
	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}
	public String getVoteflag() {
		return voteflag;
	}
	public void setVoteflag(String voteflag) {
		this.voteflag = voteflag;
	}
	public String getVoteip() {
		return voteip;
	}
	public void setVoteip(String voteip) {
		this.voteip = voteip;
	}
	public String getVoteuserid() {
		return voteuserid;
	}
	public void setVoteuserid(String voteuserid) {
		this.voteuserid = voteuserid;
	}
}
