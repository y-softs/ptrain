package com.nomen.ntrain.base.bean;

import java.io.Serializable;

/**
 * @description 基础管理_单位名称设置表
 * @author 连金亮
 * @date 2010-12-26
 */

public class BaseUnitBean implements Serializable{
	private String id;			//ID（主键）
    private String fatherid;	//父级单位ID
    private String unitname;	//单位名称
    private String shortname;   //单位简称
    private String py;          //单位名称拼音缩写
    private String unittype;	//单位分类
    private String unitlevel;	//单位等级
    private String nature;		//单位性质
    private String unitgrade;   //单位行政
    private String countype;    //县供类型
    private String endsign;		//末端标志(1末端，0非末端)
    private String unitcode;	//编码 
    private String servernames; //平台
    private String usename;		//名称启用标志（1启用状态，0禁止状态）
    private String usesign;		//单位启用标志（1启用状态，0禁止状态）
    private String sortnum;		//排序号
    private String estauser;     //创建人
    private String estatime;     //创建时间
    private String mainuser;	 //维护人
    private String maintime;	 //维护时间
    private String strflag;		//预留字段（字符串）
    private String intflag;		//预留字段（整型）
    
    private String funittype;  //辅助[单位分类的faterid]

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getFatherid() {
        return fatherid;
    }

    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
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

	public String getEndsign() {
		return endsign;
	}

	public void setEndsign(String endsign) {
		this.endsign = endsign;
	}

	public String getUnitlevel() {
		return unitlevel;
	}

	public void setUnitlevel(String unitlevel) {
		this.unitlevel = unitlevel;
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

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getServernames() {
		return servernames;
	}

	public void setServernames(String servernames) {
		this.servernames = servernames;
	}

	public String getPy() {
		return py;
	}

	public void setPy(String py) {
		this.py = py;
	}

	public String getUnitgrade() {
		return unitgrade;
	}

	public void setUnitgrade(String unitgrade) {
		this.unitgrade = unitgrade;
	}

	public String getFunittype() {
		return funittype;
	}

	public void setFunittype(String funittype) {
		this.funittype = funittype;
	}

	public String getCountype() {
		return countype;
	}

	public void setCountype(String countype) {
		this.countype = countype;
	}
	
	
}