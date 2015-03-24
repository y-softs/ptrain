package com.nomen.ntrain.ptrain.bean;

/**
 * @description 莆田岗位培训_试题临时表POJO 
 * @author 林木山
 * @date 2014-3-5
 */
public class PtrainQuestionstempBean {
    private String id;      //ID（主键）
    private String unitid;  //单位ID
    private String kindid;  //专业类别ID（关联PTRAIN_CODE表）
    private String kindidtemp;  //专业类别ID（文字）
    private String kpid;    //知识点ID
    private String typeid;  //题型ID（关联TB_RES_CODE表）
    private String typetemp;//题型（文字）
    private String topic;   //题目
    private String option1; //选项/题项1
    private String option2; //选项/题项2
    private String option3; //选项/题项3
    private String option4; //选项/题项4
    private String option5; //选项/题项5
    private String option6; //选项/题项6
    private String option7; //选项/题项7
    private String answer1; //答案1（判断题答案（1对，0错））
    private String answer2; //答案2
    private String answer3; //答案3
    private String answer4; //答案4
    private String answer5; //答案5
    private String answer6; //答案6
    private String answer7; //答案7
    private String remark;  //备注
    private String datasign; //状态（-1异常，0以导入，1待导入）

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
    public String getKindid() {
        return kindid;
    }
    public void setKindid(String kindid) {
        this.kindid = kindid;
    }
    public String getKpid() {
        return kpid;
    }
    public void setKpid(String kpid) {
        this.kpid = kpid;
    }
    public String getTypeid() {
        return typeid;
    }
    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }
    public String getTypetemp() {
        return typetemp;
    }
    public void setTypetemp(String typetemp) {
        this.typetemp = typetemp;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
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
    public String getAnswer1() {
        return answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public String getAnswer3() {
        return answer3;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public String getAnswer4() {
        return answer4;
    }
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    public String getAnswer5() {
        return answer5;
    }
    public void setAnswer5(String answer5) {
        this.answer5 = answer5;
    }
    public String getAnswer6() {
        return answer6;
    }
    public void setAnswer6(String answer6) {
        this.answer6 = answer6;
    }
    public String getAnswer7() {
        return answer7;
    }
    public void setAnswer7(String answer7) {
        this.answer7 = answer7;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
	public String getDatasign() {
		return datasign;
	}
	public void setDatasign(String datasign) {
		this.datasign = datasign;
	}
	public String getKindidtemp() {
		return kindidtemp;
	}
	public void setKindidtemp(String kindidtemp) {
		this.kindidtemp = kindidtemp;
	}
}
