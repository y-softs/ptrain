package com.nomen.ntrain.data.bean;

import java.io.Serializable;

/**
 * @description ��֯������POJO 
 * @author Ǯ�º�
 * @date 2009-10-06
 * @modifier DXL
 * @date 2011-06-07
 * 
 */

public class DataOrganBean implements Serializable{
	private String id;			//id��������
	private String unitid;		//��λid
	private String fatherid;	//��������id
	private String nature;		//��������
	private String organname;	//��������
	private String shortname;	//�������
	private String py;			//��������ƴ����д
	private String endsign;		//�Ƿ�ĩ�˰��飨1�ǣ�0��
	private String specsign;	//�������0������ְ��12021����λ��׼12022
	private String usesign;		//���ñ�־��1���ã�0��ֹ��
	private String organvalue;	//����
	private String sortnum;		//�����
	private String estauser;	//������
	private String estatime;	//����ʱ��
	private String mainuser;	//ά����
	private String maintime;	//ά��ʱ��
	private String strflag;		//Ԥ���ֶΣ��ַ�����
	private String intflag;		//Ԥ���ֶΣ����ͣ�

	//������GET �� SET ����
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
}