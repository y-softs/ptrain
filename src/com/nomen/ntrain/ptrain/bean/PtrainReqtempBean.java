package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_培训点菜临时表POJO 
 * @author 林木山
 * @date 2014-3-18
 */
public class PtrainReqtempBean {
    private String id;          //ID（主键）
    private String unitid;      //单位ID
    private String specid;      //专业类别ID
    private String spectemp;    //专业类别（临时）
    private String itemname;    //项目名称
    private String itemdesc;    //项目说明
    private String daycount;    //培训天数
    private String daycounttemp;//培训天数（临时）
    private String reqform;     //项目来源
    private String teacher;     //培训师
    private String reqtype;     //需求类别（1咨询公司需求，2专家授课需求）
    private String datasign;     //状态（-1异常，0以导入，1待导入）

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
    public String getSpecid() {
        return specid;
    }
    public void setSpecid(String specid) {
        this.specid = specid;
    }
    public String getSpectemp() {
        return spectemp;
    }
    public void setSpectemp(String spectemp) {
        this.spectemp = spectemp;
    }
    public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
    public String getItemdesc() {
        return itemdesc;
    }
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }
    public String getDaycount() {
        return daycount;
    }
    public void setDaycount(String daycount) {
        this.daycount = daycount;
    }
    public String getDaycounttemp() {
        return daycounttemp;
    }
    public void setDaycounttemp(String daycounttemp) {
        this.daycounttemp = daycounttemp;
    }
    public String getReqform() {
        return reqform;
    }
    public void setReqform(String reqform) {
        this.reqform = reqform;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
	public String getDatasign() {
		return datasign;
	}
	public void setDatasign(String datasign) {
		this.datasign = datasign;
	}
	public String getReqtype() {
		return reqtype;
	}
	public void setReqtype(String reqtype) {
		this.reqtype = reqtype;
	}
}
