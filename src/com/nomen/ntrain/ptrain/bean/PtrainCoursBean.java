package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_课件资源表POJO 
 * @author 林木山
 * @date 2014-11-15
 */
public class PtrainCoursBean {
    private String id;      //主键ID
    private String typeid;  //课件类别ID
    private String courstype;  //课件类型（1目录式课件 2单个课件）
    private String producer;  //课件制作人
    private String fatherid;//父级ID（0 课件；非0 目录）
    private String title;   //课件名称、目录名称
    private String content; //	课件简介
    private String object;  //课件适用对象
    private String hour;    //课件学时
    private String time;    //课件时长
    private String fileid;  //关联文件ID
    private String endsign; //末端标识
    private String flowsta;  //流程状态（0待需求报审，51待需求审核，99归档）
    private String subtime;  //报审时间
    private String flowmark; //流程标记
    private String estauser;//创建人
    private String estatime;//创建时间
    private String mainuser;//维护人
    private String maintime;//维护时间
    private String strflag; //预留字段（字符串）
    private String intflag; //预留字段（整型）

    

    private String filename;//上传文件名
    private String savepath;//保存路径
	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getFatherid() {
        return fatherid;
    }
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
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
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getFileid() {
        return fileid;
    }
    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
    public String getEndsign() {
        return endsign;
    }
    public void setEndsign(String endsign) {
        this.endsign = endsign;
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
	public String getFlowsta() {
		return flowsta;
	}
	public void setFlowsta(String flowsta) {
		this.flowsta = flowsta;
	}
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public String getCourstype() {
		return courstype;
	}
	public void setCourstype(String courstype) {
		this.courstype = courstype;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	
}