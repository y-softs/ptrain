package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_每日三问答题表POJO 
 * @author 林木山
 * @date 2014-3-24
 */
public class PtrainAskitemBean {
    private String id;       //ID（主键）
    private String askid;    //每日三问ID
    private String quesid;   //试题ID
    private String ansdetail;//学员答题答案
    private String ansdate;  //答题时间
    private String ansscore; //答题学分
    private String ansright; //是否正确（1正确，0错误）
    private String remark;   //备注
    private String estauser; //创建人
    private String estatime; //创建时间
    private String mainuser; //维护人
    private String maintime; //维护时间
    private String strflag;  //预留字段（字符串）
    private String intflag;  //预留字段（整型）

    private String typeid;  //题型ID（关联TB_RES_CODE表）
    private String topic;   //题目
    private String option1; //选项/题项1
    private String option2; //选项/题项2
    private String option3; //选项/题项3
    private String option4; //选项/题项4
    private String option5; //选项/题项5
    private String option6; //选项/题项6
    private String option7; //选项/题项7
    private String answer1; //答案1（判断题答案（1对，0错））

	//Get和Set方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAskid() {
        return askid;
    }
    public void setAskid(String askid) {
        this.askid = askid;
    }
    public String getQuesid() {
        return quesid;
    }
    public void setQuesid(String quesid) {
        this.quesid = quesid;
    }
    public String getAnsdetail() {
        return ansdetail;
    }
    public void setAnsdetail(String ansdetail) {
        this.ansdetail = ansdetail;
    }
    public String getAnsdate() {
        return ansdate;
    }
    public void setAnsdate(String ansdate) {
        this.ansdate = ansdate;
    }
    public String getAnsscore() {
        return ansscore;
    }
    public void setAnsscore(String ansscore) {
        this.ansscore = ansscore;
    }
    public String getAnsright() {
        return ansright;
    }
    public void setAnsright(String ansright) {
        this.ansright = ansright;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getOption6() {
		return option6;
	}
	public void setOption6(String option6) {
		this.option6 = option6;
	}
	public String getOption7() {
		return option7;
	}
	public void setOption7(String option7) {
		this.option7 = option7;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
}
