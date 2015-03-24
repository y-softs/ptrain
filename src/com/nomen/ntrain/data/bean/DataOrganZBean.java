package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description 组织机构表POJO 
 * @author 钱新红
 * @date 2009-10-06
 * @modifier DXL
 * @date 2011-06-07
 * 
 */

public class DataOrganZBean implements Serializable{
	private String id;			//id（主键）
	private String unitid;		//单位id
	private String fatherid;	//父级机构id
	private String nature;		//机构性质
	private String organname;	//机构名称
	private String shortname;	//机构简称
	private String py;			//机构名称拼音缩写
	private String endsign;		//是否末端班组（1是，0否）
	private String specsign;	//虚拟机构0，机构职责12021，岗位标准12022
	private String usesign;		//启用标志（1启用，0禁止）
	private String organvalue;	//编码
	private String sortnum;		//排序号
	private String estauser;	//创建人
	private String estatime;	//创建时间
	private String mainuser;	//维护人
	private String maintime;	//维护时间
	private String strflag;		//预留字段（字符串）
	private String intflag;		//预留字段（整型）
	
	private String lev;         //层级
	private String chilcount;   //统计子节点的记录数
	private String grandfatherid; //父节点的父节点id
	private String firstr;      //机构树状(部门>>班组>>子班组)形式
	private String secstr;      //机构树状(xx>>xx)形式
	//以下是GET 和 SET 方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getOrganname() {
        return organname;
    }
    public void setOrganname(String organname) {
        this.organname = organname;
    }
    public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getNature() {
        return nature;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public String getFatherid() {
        return fatherid;
    }
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }
    public String getSortnum() {
        return sortnum;
    }
    public void setSortnum(String sortnum) {
        this.sortnum = sortnum;
    }
    public String getUnitid() {
        return unitid;
    }
    public void setUnitid(String unitid) {
        this.unitid = unitid;
    }
    public String getUsesign() {
        return usesign;
    }
    public void setUsesign(String usesign) {
        this.usesign = usesign;
    }
    public String getSpecsign() {
		return specsign;
	}
	public void setSpecsign(String specsign) {
		this.specsign = specsign;
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
	public String getOrganvalue() {
		return organvalue;
	}
	public void setOrganvalue(String organvalue) {
		this.organvalue = organvalue;
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
	public String getLev() {
		return lev;
	}
	public void setLev(String lev) {
		this.lev = lev;
	}
	public String getChilcount() {
		return chilcount;
	}
	public void setChilcount(String chilcount) {
		this.chilcount = chilcount;
	}
	public String getFirstr() {
		return firstr;
	}
	public void setFirstr(String firstr) {
		this.firstr = firstr;
	}
	public String getSecstr() {
		return secstr;
	}
	public void setSecstr(String secstr) {
		this.secstr = secstr;
	}
	public String getGrandfatherid() {
		return grandfatherid;
	}
	public void setGrandfatherid(String grandfatherid) {
		this.grandfatherid = grandfatherid;
	}
}