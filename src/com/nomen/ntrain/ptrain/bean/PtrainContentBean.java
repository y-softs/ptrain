package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_资源表POJO 
 * @author 林木山
 * @date 2014-11-17
 */
public class PtrainContentBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String kind;    //1.规程文件 2.影音影视库 3.经典书籍库 4.典型经验库
    private String typeid;  //类别ID
    private String title;   //标题
    private String content; //文本内容
    private String flowsta; //流程状态（0待报审，51待审核，99归档）
    private String subtime;  //报审时间
    private String flowmark; //流程标记
    private String usesign; //启用标志（1启用，0禁止）
    private String sortnum; //排序号
    private String estauser;//创建人
    private String estatime;//创建时间
    private String mainuser;//维护人
    private String maintime;//维护时间
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

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
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFlowsta() {
        return flowsta;
    }
    public void setFlowsta(String flowsta) {
        this.flowsta = flowsta;
    }
    public String getUsesign() {
        return usesign;
    }
    public void setUsesign(String usesign) {
        this.usesign = usesign;
    }
    public String getSortnum() {
        return sortnum;
    }
    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
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
	public String getFlowmark() {
		return flowmark;
	}
	public void setFlowmark(String flowmark) {
		this.flowmark = flowmark;
	}
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
}