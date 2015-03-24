package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_知识学习POJO 
 * @author 许东雄
 * @date 2014-3-24
 */
public class PtrainBbsBean {
    private String id;       //ID（主键）
    private String unitid;   //单位ID
    private String fatherid; //主贴ID
    private String specid;   //专业类别ID
    private String kind;     //帖子类别（1常规帖，2资源共享帖）
    private String title;    //主题
    private String content;  //内容
    private String nice;     //点赞数
    private String browse;   //浏览数
    private String downnum;  //下载数
    private String anssign;  //回答状态（1已答，0未答）
    private String expans;   //专家回答
    private String evasign;  //评价（5非常满意，4满意，1一般，0不满意）
    private String estauser; //创建人
    private String estatime; //创建时间
    private String mainuser; //维护人
    private String maintime; //维护时间
    private String strflag;  //预留字段（字符串）
    private String intflag;  //预留字段（整型）
    
    /** ============ 辅助字段 ============ **/
    private String management; //板块 管理人员
    private String nicecount;	//点赞数
    private String resnum;		//资源数
    private String putnum;		//提问数
    private String relnum;		//规章制度数
    private String ansnum;		//答题数
    private String ansrate;		//答疑率
    private String downrate;	//下载率
    private String evarate;		//满意率
    private String username;	//发帖人
    private String deptname;	//部门名称
    private String groupname;	//班组名称
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
    public String getFatherid() {
        return fatherid;
    }
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }
    public String getSpecid() {
        return specid;
    }
    public void setSpecid(String specid) {
        this.specid = specid;
    }    
    public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
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
    public String getNice() {
        return nice;
    }
    public void setNice(String nice) {
        this.nice = nice;
    }
    public String getBrowse() {
        return browse;
    }
    public void setBrowse(String browse) {
        this.browse = browse;
    }
    public String getDownnum() {
        return downnum;
    }
    public void setDownnum(String downnum) {
        this.downnum = downnum;
    }
    public String getAnssign() {
        return anssign;
    }
    public void setAnssign(String anssign) {
        this.anssign = anssign;
    }
    public String getExpans() {
        return expans;
    }
    public void setExpans(String expans) {
        this.expans = expans;
    }
    public String getEvasign() {
        return evasign;
    }
    public void setEvasign(String evasign) {
        this.evasign = evasign;
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
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	public String getNicecount() {
		return nicecount;
	}
	public void setNicecount(String nicecount) {
		this.nicecount = nicecount;
	}
	public String getAnsnum() {
		return ansnum;
	}
	public void setAnsnum(String ansnum) {
		this.ansnum = ansnum;
	}
	public String getAnsrate() {
		return ansrate;
	}
	public void setAnsrate(String ansrate) {
		this.ansrate = ansrate;
	}
	public String getDownrate() {
		return downrate;
	}
	public void setDownrate(String downrate) {
		this.downrate = downrate;
	}
	public String getEvarate() {
		return evarate;
	}
	public void setEvarate(String evarate) {
		this.evarate = evarate;
	}
	public String getPutnum() {
		return putnum;
	}
	public void setPutnum(String putnum) {
		this.putnum = putnum;
	}
	public String getResnum() {
		return resnum;
	}
	public void setResnum(String resnum) {
		this.resnum = resnum;
	}
	public String getRelnum() {
		return relnum;
	}
	public void setRelnum(String relnum) {
		this.relnum = relnum;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
}
