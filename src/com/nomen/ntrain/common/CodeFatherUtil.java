package com.nomen.ntrain.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description 子系统代码fatherid设置类
 * @author 连金亮
 * @date 2011-05-20
 */
public class CodeFatherUtil {
	/**********************基础子系统代码设置fatherid************************/
	/**单位类别[fatherid]*/
	public static final String BASE_UNITTYPE = "1";
	/**单位等级[fatherid]*/
	public static final String BASE_UNITLEVEL = "2";
	/**单位性质[fatherid]*/
	public static final String BASE_UNITNATURE = "3";
	/**县供类型[fatherid]*/
	public static final String BASE_COUNTYTYPE = "4";
	/**单位类型[fatherid]*/
	public static final String BASE_UNITCLASS = "5";
	/**单位等级层级[fatherid]*/
	public static final String BASE_LEVELGRADE = "6";
	/**机构类别[fatherid]*/
	public static final String BASE_ORGTYPE = "8001";
	/**机构类型[fatherid]*/
	public static final String BASE_ORGCLASS= "8002";
	/**机构性质[fatherid]*/
	public static final String BASE_ORGNATURE= "8003";
	/**机构隶属[fatherid]*/
	public static final String BASE_ORGUNDER= "8004";
	/**职业类别[fatherid]*/
	public static final String BASE_CLSSOCCU= "9";
	
	/**单位类别--电网类*/
	public static final String BASE_POWERGRID = "1008";
	/**单位等级属性--党政*/
	public static final String BASE_ENTERPRISE = "1001";
	/**省公司级*/
	public static final String BASE_SGSLEVEL = "2025";
	/**地、市公司级*/
	public static final String BASE_DSLEVEL = "2035";
	/**省公司直属单位*/
	public static final String BASE_SGSZSLEVEL = "2036";
	/**县（或县级市）公司级*/
	public static final String BASE_XLEVEL = "2045";
	
	
	/**以下为单位层级的id*/
	public static final String BASE_LAY_B2 = "6022"; //B2级
	public static final String BASE_LAY_C = "6030";  //C级
	public static final String BASE_LAY_D = "6040";  //D级

	/**子系统栏目父亲id*/
	public static final String BASE_COLLFATHER="0";
	/**基础管理子系统id*/
	public static final String BASE_BSYS_ID="1192";
	/**基础管理子系统的上级id*/
	public static final String BASE_BSYS_FATHERID="11";
	/**机构管理子系统id*/
	public static final String DATA_BSYS_ID="1201";
	/**机构管理子系统的上级id==人事栏目id*/
	public static final String DATA_BSYS_FATHERID="12";
	/**个人事务子系统id*/
	public static final String INDIV_BSYS_ID="1191";
	/**电子看板子系统id*/
	public static final String SBOARD_BSYS_ID="1151";
	
	
	/**国网专家子系统id*/
	public static final String EL_GELISYS_ID="1271";
	/**国网农电工子系统id*/
	public static final String EL_GELESYS_ID="1272";
	/**通道人才子系统id*/
	public static final String EL_ELISYS_ID="1273";
	/**星级农电工子系统id*/
	public static final String EL_ELCSYS_ID="1274";
	
	/**********************机构子系统代码设置fatherid************************/
	/**部门性质*/
	public static final String DATA_DEPTNATURE="101";
	/**班组性质*/
	public static final String DATA_TEAMNATURE="102";
	/**层面*/
	public static final String DATA_ORGLAY="103";
	/**层级*/
	public static final String DATA_ORGLEVEL="104";
	
	/**国籍*/
	public static final String DATA_NATION="201";
	/**民族*/
	public static final String DATA_NATALID="202";
	/**性别*/
	public static final String DATA_SEX="203";
	/**政治面貌*/
	public static final String DATA_PROLIT="204";
	/**人员性质*/
	public static final String DATA_KIND="205";
	/**人员状态*/
	public static final String DATA_STATE="206";  
	/**健康状况*/
	public static final String DATA_HEALTH="207";//2013-07-04
	/**宗教信仰*/
	public static final String DATA_FAITH="209";//2013-07-04
	
	/**人员类别*/
	public static final String DATA_PEOTYPE="301";
	/**岗位分类*/
	public static final String DATA_JOBCLASS="302";
	/**岗位层级*/
	public static final String DATA_POSTNAME="303";
	/**岗位名称*/
	public static final String DATA_POSTMC="304";
	/**岗位状态*/
	public static final String DATA_POSTSTATUS="305";
	/**党团工政*/
	public static final String DATA_POSTTYPE="306";
	/**专业类别【属岗位】*/
	public static final String DATA_POSTPROF="307";
	/**岗级*/
	public static final String DATA_POSTLEVEL="308";
	/**职级*/
	public static final String DATA_PROFQUAL="309";
	/**工作性质*/
	public static final String DATA_JOBNATURE="310";
	/**特殊工种*/
	public static final String DATA_SPECTRADE="311";
	/**特定岗位*/
	public static final String DATA_SPECPOST="312";
	
	/**人员身份*/
	public static final String DATA_PEOIDEN="401";
	/**干部职务级别*/
	public static final String DATA_JOBLEVEL="402";
	/**职务配置方式*/
	public static final String DATA_JOBCONFIG="403";
	/**党工职务*/
	public static final String DATA_WORKPARTY="404";
	
	/**户口性质*/
	public static final String DATA_ACCNATURE="501";
	/**增员类型*/
	public static final String DATA_ADDMEMREASON="502";
	/**用工形式*/
	public static final String DATA_EMPFORM="503";
	/**合同类型*/
	public static final String DATA_CONTTYPE="504";
	/**合同期限*/
	public static final String DATA_LABCONT="505";
	/**军转类型*/
	public static final String DATA_EXSOLDIER="506";
	
	/**学历等级*/
	public static final String DATA_EDURECORD="601";
	/**专业类别【属学历】*/
	public static final String DATA_SPECTYPERECORD="602";
	/**专业类别 默认选中 工学 */
	public static final String DATA_SPECTYPE_ENGINEERING="602018";
	/**学习形式*/
	public static final String DATA_STUTYPE="603";
	/**毕业状况*/
	public static final String DATA_GRADSTATUS="604";
	/**学位*/
	public static final String DATA_EDUDEGREE="605";
	
	/**技术职称等级*/
	public static final String DATA_PROFLEVEL="701";
	/**技术职称系列*/
	public static final String DATA_PROFSET="702";
	/**职业资格等级*/
	public static final String DATA_QUALLEVEL="703";
	/**职业工种名称*/
	public static final String DATA_QUALTITLE="704";
	/**外语水平*/
	public static final String DATA_LANGLEVEL="705";
	/**计算机水平*/
	public static final String DATA_COMLEVEL="706";
	/**专家人才称号*/
	public static final String DATA_EXPERTTYPE="707";
	/**称号获得方式*/
	public static final String DATA_WAYOBTAIN="708";
	/**职业工种类别*/
	public static final String DATA_PROFTYPE="709";//2013-07-04
	/**外语种类 */
	public static final String DATA_LANGKIND="710";//2013-07-04
	/**少数民族语种类 */
	public static final String DATA_MINOLANG="711";//2013-07-04
	/**语言掌握程度 */
	public static final String DATA_LANGLEV="712";//2013-07-04
	/**纪律处分类型 */
	public static final String DATA_INFLTYPE="713";//2013-07-04
	
	

	/**办公场所*/
	public static final String DATA_MESSBUREAU="801";
	/**银行简称*/
	public static final String DATA_MESSBANK="802";
	/**行政区域*/
	public static final String DATA_MESSCITY="803";
	/**机构名称*/
	public static final String DATA_MESSORGAN="804";
	/**婚姻状况*/
	public static final String DATA_MESSMARR="805";
	/**成员称谓*/
	public static final String DATA_MESSMEMB="806";
	/**成员现状*/
	public static final String DATA_MESSSTATUS="807";
	
	/**地理区域*/
	public static final String DATA_NATIONALAREA="9201";
	/**银行类别*/
	public static final String DATA_MESSBANKTYPE="9802";

	/**行政区域（省-性质）*/
	public static final String DATA_PROVINCENATURE="980301";
	/**行政区域（市-性质）*/
	public static final String DATA_CITYNATURE="980302";
	/**行政区域（县-性质）*/
	public static final String DATA_COUNTYNATURE="980303";
	/**行政区域（镇-性质）*/
	public static final String DATA_TOWNNATURE="980304";
	/**行政区域（村-性质）*/
	public static final String DATA_VALLAGENATURE="980305";
	
	/**全日制(学习形式成员 非父级ID)*/
	public static final String DATA_FULLTIME="603011";
	/**人员状态为在职的*/
	public static final String DATA_USERSTATEFATHER="206001";
	/**岗位类型通用类*/
	public static final String DATA_POSTPROFGENERAL="307007";
	/**岗位类型通用类--技能类id*/
	public static final String DATA_POSTPROFGENERAL_SKILL="30700704";
	/**岗位类型农电类*/
	public static final String DATA_POSTPROFCOMMON="307014";
	
	/**********************证书子系统代码设置fatherid************************/  
	/**证书类别设置*/
	public static final String CERT_TYPE="1";
	/**获取性质证书*/
	public static final String CERT_GETTYPE="2";
	/**定义性质证书*/
	public static final String CERT_DEFTYPE="3";
	/**岗位岗次设置*/
	public static final String CERT_POSTTYPE="4";
	/**应备类别设置*/
	public static final String CERT_PREPAREDTYPE="5";
	/**学历*/
	public static final String CERT_EDURECORD="1011";
	/**学位*/
	public static final String CERT_EDUDEGREE="1012"; 	
	/**技术职称*/
	public static final String CERT_PROFTITLE="1021"; 		
	/**职业资格*/
	public static final String CERT_QUALTITLE="1022";  	 	
	/**特种作业*/
	public static final String CERT_SPECWORD="1031"; 	
	/**执业资格*/
	public static final String CERT_QUALLICEN="1032";  	 	
	/**岗位培训*/
	public static final String CERT_POSTTRAN="1041";  	 	
	/**外语水平*/
	public static final String CERT_LANGLEVEL="1051";  	 	
	/**计算机水平*/
	public static final String CERT_COMLEVEL="1052";  	 	
	/**其他*/
	public static final String CERT_OTHER="1097"; 	
	
	/**********************信息服务子系统代码设置fatherid************************/  
	/**工作动态类别*/
	public static final String INFO_WORKNEW="1";   
	/**新闻信息类别*/
	public static final String INFO_NEWSTYPE="2";    
	/**新闻用稿等级*/
	public static final String INFO_NEWSLEVEL="3"; 
	/**文件制度类别*/
	public static final String INFO_FILELEVEL="4";  
	/**服务指南类别*/
	public static final String INFO_GUIDETYPE="5"; 
	/**表格仓库类别*/
	public static final String INFO_TABLETYPE="6";
	/**专项工作类别*/
	public static final String INFO_SPECTYPE="7";
	/**上报资料类别*/
	public static final String INFO_REPORTTYPE="8";
	/**趣味测试类别*/
	public static final String INFO_TESTTYPE="9";
	/**友情链接类别*/
	public static final String INFO_LINKTYPE="10";
	/**文件制度等级别*/
	public static final String INFO_FILELAWLEVEL="11";
	/**他山之石类别设置*/
	public static final String INFO_TSZSTYPE="12";  
	
	/**********************CBE子系统代码设置fatherid************************/  
	/**科目类别*/
	public static final String CBE_SUBTYPE="1"; 
	/**考核等级*/
	public static final String CBE_ASSLEVEL="2"; 
	/**考核期数*/
	public static final String CBE_ASSPHASE="3"; 
	/**学习方式*/
	public static final String CBE_STUMODE="4"; 
	/**考核方式*/
	public static final String CBE_ASSMODE="5";  
	
	/*******************资源管理子系统代码设置fatherid************************/
	/**文件类别设置*/
	public static final String RES_BTYPE="1101";
	/**文件密级设置*/
	public static final String RES_BLEVEL="1102";
	/**文件类型设置*/
	public static final String RES_BKIND="1103";
	/**课件类别设置2101-05*/
	public static final String RES_CTYPE="2105";
	/**课件类型设置2102-06*/
	public static final String RES_CCLASS="2106";
	/**课件路径设置2505-2109*/
	public static final String RES_PATH="2109";
	/**试题知要设置*/
	public static final String RES_POINT="2501";
	/**试题类别设置*/
	public static final String RES_QTYPE="2505";
	/**试题类型设置*/
	public static final String RES_QSTYPE="2506";
	/**课程类别设置*/
	public static final String RES_STYPE="2901";
	/***场地类别设置 ***/
	public static final String RES_AREA = "11";
	
	/****************以下为试题题型*******************/
	/**试题类型*/
	public static final String RES_QUE_TYPE = "3";
	/**单选*/
	public static final String RES_QUE_SINGLE = "250601";
	/**多选*/
	public static final String RES_QUE_MULTIPLE = "250602";
	/**判断*/
	public static final String RES_QUE_JUDGE = "250603";
	/**填空*/
	public static final String RES_QUE_FILL = "250604";
	/**解释*/
	public static final String RES_QUE_REMARK = "250605";
	/**简答*/
	public static final String RES_QUE_ANSWER = "250606";
	/**计算*/
	public static final String RES_QUE_CALC = "250607";
	/**绘图*/
	public static final String RES_QUE_DRAW = "250608";
	/**改错*/
	public static final String RES_QUE_ERRCORR = "250609";
	/**纠错*/
	public static final String RES_QUE_CORRECT = "250610";
	/**分析*/
	public static final String RES_QUE_ANALY = "250611";
	/**论述*/
	public static final String RES_QUE_DISCUSS = "250612";
	/**听力*/
	public static final String RES_QUE_HEAR = "250613";
	/**听写*/
	public static final String RES_QUE_DICTATION = "250614";
	/**综合*/
	public static final String RES_QUE_COMPRE = "250615";
	
	/**预定类别设置*/
	public static final String RES_ETYPE="7";
	/**场地fatherid[预定资源中使用]*/
	public static final String RES_PLACE="267";
	/**设备fatherid[预定资源中使用]*/
	public static final String RES_EQUIP="268";
	
	/*******************网络学习管理子系统代码设置fatherid************************/   
	/**专题课程类别*/
	public static final String STUDY_SPECSUBTYPE="1"; 
	/**学习论坛类别*/
	public static final String STUDY_LEARNFORUMTYPE="2"; 
	/**解惑类别*/
	public static final String STUDY_FAQTYPE="3"; 
	/**科目类别*/
	public static final String DRILL_COURSECLASS="4"; 
	/**学习方式*/
	public static final String DRILL_STUDYMODE="5"; 
	/**考核方式*/
	public static final String DRILL_EXAMINEMODE="6"; 
	/**考核等级*/
	public static final String DRILL_EXAMINELEVEL="7";  
	/**岗位岗次*/
	public static final String DRILL_POST_JOB="0";
	/**考核周期*/
	public static final String DRILL_POST_CHECK="9";
	
	/*******************日常事务管理子系统代码设置fatherid************************/  
	/**任务类别*/
	public static final String NOR_TASKTYPE="1"; 
	/**任务质量等级*/
	public static final String NOR_TASKLEVEL="2";
	/**预定类别*/
	public static final String NOR_FACTYPE="3";
	/**发文类别*/
	public static final String NOR_SENDTYPE="4";
	/**收文类别 */
	public static final String NOR_RECETYPE="5";
	/**攻坚类别 */
	public static final String NOR_DIFFTYPE="6";
	/**创新类别 */
	public static final String NOR_INNOVTYPE="7"; 
	/**公文等级 */
	public static final String NOR_OFFICELEVEL="8"; 
	/**发文类型 */
	public static final String NOR_SENDCATEG="9"; 
	/**收文类型 */
	public static final String NOR_RECECATEG="10"; 
	
	/**计量单位  */
	public static final String NOR_MEAS="2501"; 
	/**项目类别   */
	public static final String NOR_ITYPE="2502"; 
	
	/*******************人才管理子系统代码设置fatherid************************/ 
	/**专业类别设置 */
	public static final String ELITE_STYPE = "1";
	/**人才类别名称 */
	public static final String ELITE_PTYPE = "2";
	/**通道类别 */
	public static final String ELITE_CTYPE = "14";
	
	/*******************结对管理子系统代码设置fatherid************************/ 
	/**培训目标/程度 */
	public static final String PAIR_CHKCTYPE= "1";
	/**应知【在导出协议word中】 */
	public static final String PAIR_PACT_YZ= "101";
	/**应会【在导出协议word中】 */
	public static final String PAIR_PACT_YH= "102";
	/**计划反馈等级 */
	public static final String PAIR_CHKFBACK= "2";
	/**计划评价等级 */
	public static final String PAIR_CHKPLEVEL= "3";
	/**期中评价等级 */
	public static final String PAIR_CHKMLEVEL= "4";
	/**期末评价等级 */
	public static final String PAIR_CHKFLEVEL= "5";
	/**结果评价方案 */
	public static final String PAIR_CHKSCHEME= "6";
	/**结果评价等级 */
	public static final String PAIR_CHKGRADE= "7";
	/**考核性质 */
	public static final String PAIR_CHKNATUR= "9";
	/**学员反馈结果 */
	public static final String PAIR_FBACK = "10";
	
	/*******************常规培训管理子系统代码设置fatherid************************/
	/**培训层面 */
	public static final String TRAIN_LAYTYPE="1";
	/**计划类别*/
	public static final String TRAIN_PLANTYPE="2";
	/**培训类别 */
	public static final String TRAIN_TYPE="3";
	/**报表关联 */
	public static final String TRAIN_REPORT="4";
	/**培训费用科目 */
	public static final String TRAIN_FOUNDS="5";
	/**培训经费来源 */
	public static final String TRAIN_SOURCE="6";
	/**统计口径设置 */
	public static final String TRAIN_COUNT="7";
	
	/*********************培训管理子系统 培训类别 培训借口fatherid***********/
	/**常规 */
	public static final String TRAIN_FINAL_CG="11";
	/**结对 */
	public static final String TRAIN_FINAL_ST="21";
	/**三带 */
	public static final String TRAIN_FINAL_SD="22";
	/**干部交流 */
	public static final String TRAIN_FINAL_JL="23";
	/**学历学位 */
	public static final String TRAIN_FINAL_XL="31";
	/**职称资格 */
	public static final String TRAIN_FINAL_ZC="41";
	/**竞赛调考 */
	public static final String TRAIN_FINAL_DK="51";
	/**国际合作*/
	public static final String TRAIN_FINAL_GJ="61";
	/**审查重点*/
	public static final String TRAIN_FINAL_SC="0";
	/**数据有效性 */
	public static final String TRAIN_FINAL_SJ="0";
	/**网络学习 */
	public static final String TRAIN_FINAL_XX="91";
	/**网络考试 */
	public static final String TRAIN_FINAL_KS="92";
	/******************* 班组管理子系统 fatherid************************/ 
	/**职务类别*/
	public static final String GROUP_POSTTYPE="1";
	/**班组性质*/
	public static final String GROUP_NATURE="2";
	
	/*******************工资管理系统代码设置fatherid************************/
	/**工资科目 */
	public static final String WAGE_SUBJECT="0"; 
	/**工资体系类别 */
	public static final String WAGE_TYPE="1"; 
	/**工资来源 */
	public static final String WAGE_SOURCE="2"; 
	/**正式工（在职且在岗）ID */
	public static final String WAGE_REGULAR="185"; 
	/**岗位薪金ID */
	public static final String WAGE_POSTSALARY="201";
	/**基础项*/
	public static final String WAGE_BASEPOST="193";
	/**个税*/
	public static final String WAGE_USERTAX="1";
	/**公积金*/
	public static final String WAGE_PUBTAX="2";
	/**个人所得税税率*/
	public static final String WAGE_TAXRATE="3";
	/**工资科目性质*/
	public static final String WAGE_NATURE="1";
	/**发放轮次设置*/
	public static final String WAGE_CYC="5";
	/**财务部门设置*/
	public static final String WAGE_WDEPT="9";
	/** 薪点类 [配置管理 人员薪点信息 时用] */
	public static final String WAGE_XDTYPE="16";
	/**工资_工资体系类别*/
	public static final String WAGE_KIND="0"; 
	/*******************工资管理系统代码设置fatherid************************/
	/**工资来源 */
	public static final String PAY_SOURCE="2";
	
	/*******************岗位配置子系统代码设置fatherid************************/
	/**岗位责任类别  */
	public static final String POST_RTYPE="1"; 
	/**沟通协调类别   */
	public static final String POST_CTYPE="2"; 
	
	/*******************业绩管理配置设置fatherid************************/
	/**业绩管理配置 */
	public static final String SUP_CONFIG="0"; 
	
	/*******************业绩管理代码设置fatherid************************/
	/**业绩类型 */
	public static final String SUP_TYPE="1";
	/**业绩名称（引入） */
	public static final String SUP_NAME="2";
	/**业绩名次类型 */
	public static final String SUP_RANKTYPE="3";
	/**业绩名次 */
	public static final String SUP_RANK="4";
	/**业绩角色 */
	public static final String SUP_ROLE="5";
	/**评分选项 */
	public static final String SUP_ASSTYPE="6";
	/**排位计算*/
	public static final String SUP_CALCUL="7";
	/**期刊类别*/
	public static final String SUP_CATE="8";
	/**发行周期 */
	public static final String SUP_CYCLE="9";

	/**荣誉称号*/
	public static final String SUP_GLORY ="11";
	/**成果获奖*/
	public static final String SUP_INNOV ="15";
	/**建议获奖*/
	public static final String SUP_ADVICE ="21";
	/**竞调获奖*/
	public static final String SUP_MATCH ="25";
	/**版权专利*/
	public static final String SUP_PATENT ="31";
	/**论文著作*/
	public static final String SUP_THESIS ="35";
	/**嘉奖通报*/
	public static final String SUP_CITE ="51";
	/**典型经验*/
	public static final String SUP_EXPER ="55";
	/**建章立制*/
	public static final String SUP_FILE ="61";
	/**专项业绩*/
	public static final String SUP_SPEC ="97";

	/**荣誉*/
	public static final String SUP_HONOR="201";
	/**奖项*/
	public static final String SUP_AWARD="205";
	/**期刊*/
	public static final String SUP_BOOKS="251";
	/**专项业绩**/
	public static final String SUP_="251";
	
	/*******************人才基础设置fatherid************************/
	/**填报项目*/
	public static final String EL_CITEM="0";
	
	/******************技能鉴定子系统代码设置fatherid****************/
	/**工种类别*/
	public static final String JUGDE_WORK_TYPE="1";
	/**实操类别*/
	public static final String JUGDE_SKILL_TYPE="2";
	/**收费项目*/
	public static final String JUGDE_FEE_ITEM="5";
	/**收费类别*/
	public static final String JUGDE_FEE_TYPE="6";
	/**考核项目*/
	public static final String JUGDE_ASSE_ITEM="7";
	/**考核方式*/
	public static final String JUGDE_ASSE_MODE="8";
	/**考核等级*/
	public static final String JUGDE_ASSE_GRADE="9";
	/**本人职责*/
	public static final String JUGDE_WORK_GRADE="11";
	
	/**考场类别*/
	public static final String JUGDE_PLACE_TYPE="10";
	
	/******************临时[网络学习]基础代码设置fatherid****************/
	/**考试类别*/
	public static final String SCORE_EXAM_TYPE="1";
	/**考试性质*/
	public static final String SCORE_EXAM_KIND="2";
	/**专业类别*/
	public static final String SCORE_SPEC_TYPE="5";
	
	/**“三集五大”考试类别*/
	public static final String SCORE_SJWD_TYPE="15191";

	/******************教培基础代码设置fatherid****************/
	/**项目层级*/
	public static final String TEACH_ITEM_LEVEL="11";
	/**项目类别*/
	public static final String TEACH_TERM_TYPE="21";
	/**培训类型*/
	public static final String TEACH_TRAIN_STYLE="22";
	/**培训形式*/
	public static final String TEACH_TRAIN_MODE="25";
	/**紧迫程度*/
	public static final String TEACH_URGENT_DEGREE="29";
	/**经费来源*/
	public static final String TEACH_PAY_SOURE="35";
	/**费用类型*/
	public static final String TEACH_PAR_TYPE="39";
	/**职业资格等级*/
	public static final String TEACH_QUAL_LEVEL="91";
	/**需求项目类别*/
	public static final String TEACH_REQ_TYPE="97";
	
	/******************考勤管理代码设置fatherid****************/
	/**请假类别*/
	public static final String ATTEND_TYPE_QJ="101";
	/**外出类别*/
	public static final String ATTEND_TYPE_WC="102";
	/**薪酬性质*/
	public static final String ATTEND_PAY_KIND="5";
	/**迟到*/
	public static final String ATTEND_CD="105";
	/**早退*/
	public static final String ATTEND_ZT="106";
	/**矿工*/
	public static final String ATTEND_KG="108";
	/**特殊*/
	public static final String ATTEND_TC="109";
	/**工作日_常班*/
	public static final String ATTEND_WORKDAY_NOR="811";
	/**工作日_轮班*/
	public static final String ATTEND_WORKDAY_TURN="821";
	/**考勤区域位置*/
	public static final String ATTEND_DEVICE_PLACE="9";
	

	/**********************劳保子系统基础设置fatherid************************/
	/**劳保用品类别**/
	public static final String LABOR_APP_TYPE = "1";
	/**体态尺码**/
	public static final String LABOR_BODY_SIZE = "2";
	/**劳保工种**/
	public static final String LABOR_WORK_TYPE = "5";
	/**领用类型**/
	public static final String LABOR_GET_KIND = "7";
	/**仓库名称**/
	public static final String LABOR_STORE_NAME = "9";
	/**服装类**/
	public static final String LABOR_CLOTH_TYPE = "101";
	/**日用品类**/
	public static final String LABOR_DALITY_TYPE = "105";
	/**其他类**/
	public static final String LABOR_OTHER_TYPE= "109";
	
	/**劳保用品大类id**/
	public static final String LABOR_MAT="23";
	/**用品类别父类id**/
	public static final String LABOR_TYPE="0";
	/**劳保工种父类id**/
	public static final String LABOR_CONFIG_WORKID="0";
	
	/*******************  帮带管理_子系统代码设置fatherid  ********************/ 
	/** 帮带形式设置 **/
	public static final String LEAD_WAY="1"; 
	/** 期满考核评价 **/
	public static final String LEAD_EVA="0"; 
	
	/*******************  结对管理_子系统代码设置fatherid  ********************/ 
	/** 培训类别 **/
	public static final String PAIR_TRAIN_CLASS="1"; 
	/** 阶段考核方式 **/
	public static final String PAIR_ASSE_METHODS="3"; 
	/** 阶段考核类别 **/
	public static final String PAIR_ASSE_CLASS="7"; 
	
	/*******************  培训管理代码设置fatherid  ********************/
	/** 获奖类型 **/
	public static final String TRAIN_WINNER_TYPE="13"; 
	/** 获奖名次 **/
	public static final String TRAIN_PLACE_WINNER="14"; 
	
	
	/*************** 法律专项_子系统代码设置fatherid  ********************/ 
	/** 法律性质 **/
	public static final String LAW_NATURE="0"; 
	/** 法律专业 **/
	public static final String LAW_SPEC="1"; 
	/** 岗位分类 **/
	public static final String LAW_POST="2"; 
	/** 课程目录 **/
	public static final String LAW_COURSE_CONTENTS="0"; 
	
	/******************莆田岗位培训代码设置fatherid****************/
	/**岗位名称*/
	public static final String PTRAIN_POST="1";
	/**专业类别*/
	public static final String PTRAIN_SPEC="2";
	/**规程文件*/
	public static final String PTRAIN_RULES="11";
	/**影音影视库*/
	public static final String PTRAIN_MOVIES="12";
	/**经典书籍库*/
	public static final String PTRAIN_BOOKS="13";
	/**典型经验库*/
	public static final String PTRAIN_EXPER="14";
	/**每日三问专业类别*/
	public static final String PTRAIN_SPEC_ASK="3";
	/**课件类别*/
	public static final String PTRAIN_COURS="5";
	
	/**知识学习*/
	public static final String PTRAIN_BBS="0";
	/******************校园网代码表fatherid****************/
	/**场地类型*/
	public static final String CAMPUS_PLACE_TYPE="1";
	
	
	/**
	 * 角色组类别
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map getRoleGroupMap(){
		Map m = new LinkedHashMap();
		m.put("0", "用户组");
		m.put("1", "系统组");
		return m;
	}
}