package com.nomen.ntrain.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description ��ϵͳ����fatherid������
 * @author ������
 * @date 2011-05-20
 */
public class CodeFatherUtil {
	/**********************������ϵͳ��������fatherid************************/
	/**��λ���[fatherid]*/
	public static final String BASE_UNITTYPE = "1";
	/**��λ�ȼ�[fatherid]*/
	public static final String BASE_UNITLEVEL = "2";
	/**��λ����[fatherid]*/
	public static final String BASE_UNITNATURE = "3";
	/**�ع�����[fatherid]*/
	public static final String BASE_COUNTYTYPE = "4";
	/**��λ����[fatherid]*/
	public static final String BASE_UNITCLASS = "5";
	/**��λ�ȼ��㼶[fatherid]*/
	public static final String BASE_LEVELGRADE = "6";
	/**�������[fatherid]*/
	public static final String BASE_ORGTYPE = "8001";
	/**��������[fatherid]*/
	public static final String BASE_ORGCLASS= "8002";
	/**��������[fatherid]*/
	public static final String BASE_ORGNATURE= "8003";
	/**��������[fatherid]*/
	public static final String BASE_ORGUNDER= "8004";
	/**ְҵ���[fatherid]*/
	public static final String BASE_CLSSOCCU= "9";
	
	/**��λ���--������*/
	public static final String BASE_POWERGRID = "1008";
	/**��λ�ȼ�����--����*/
	public static final String BASE_ENTERPRISE = "1001";
	/**ʡ��˾��*/
	public static final String BASE_SGSLEVEL = "2025";
	/**�ء��й�˾��*/
	public static final String BASE_DSLEVEL = "2035";
	/**ʡ��˾ֱ����λ*/
	public static final String BASE_SGSZSLEVEL = "2036";
	/**�أ����ؼ��У���˾��*/
	public static final String BASE_XLEVEL = "2045";
	
	
	/**����Ϊ��λ�㼶��id*/
	public static final String BASE_LAY_B2 = "6022"; //B2��
	public static final String BASE_LAY_C = "6030";  //C��
	public static final String BASE_LAY_D = "6040";  //D��

	/**��ϵͳ��Ŀ����id*/
	public static final String BASE_COLLFATHER="0";
	/**����������ϵͳid*/
	public static final String BASE_BSYS_ID="1192";
	/**����������ϵͳ���ϼ�id*/
	public static final String BASE_BSYS_FATHERID="11";
	/**����������ϵͳid*/
	public static final String DATA_BSYS_ID="1201";
	/**����������ϵͳ���ϼ�id==������Ŀid*/
	public static final String DATA_BSYS_FATHERID="12";
	/**����������ϵͳid*/
	public static final String INDIV_BSYS_ID="1191";
	/**���ӿ�����ϵͳid*/
	public static final String SBOARD_BSYS_ID="1151";
	
	
	/**����ר����ϵͳid*/
	public static final String EL_GELISYS_ID="1271";
	/**����ũ�繤��ϵͳid*/
	public static final String EL_GELESYS_ID="1272";
	/**ͨ���˲���ϵͳid*/
	public static final String EL_ELISYS_ID="1273";
	/**�Ǽ�ũ�繤��ϵͳid*/
	public static final String EL_ELCSYS_ID="1274";
	
	/**********************������ϵͳ��������fatherid************************/
	/**��������*/
	public static final String DATA_DEPTNATURE="101";
	/**��������*/
	public static final String DATA_TEAMNATURE="102";
	/**����*/
	public static final String DATA_ORGLAY="103";
	/**�㼶*/
	public static final String DATA_ORGLEVEL="104";
	
	/**����*/
	public static final String DATA_NATION="201";
	/**����*/
	public static final String DATA_NATALID="202";
	/**�Ա�*/
	public static final String DATA_SEX="203";
	/**������ò*/
	public static final String DATA_PROLIT="204";
	/**��Ա����*/
	public static final String DATA_KIND="205";
	/**��Ա״̬*/
	public static final String DATA_STATE="206";  
	/**����״��*/
	public static final String DATA_HEALTH="207";//2013-07-04
	/**�ڽ�����*/
	public static final String DATA_FAITH="209";//2013-07-04
	
	/**��Ա���*/
	public static final String DATA_PEOTYPE="301";
	/**��λ����*/
	public static final String DATA_JOBCLASS="302";
	/**��λ�㼶*/
	public static final String DATA_POSTNAME="303";
	/**��λ����*/
	public static final String DATA_POSTMC="304";
	/**��λ״̬*/
	public static final String DATA_POSTSTATUS="305";
	/**���Ź���*/
	public static final String DATA_POSTTYPE="306";
	/**רҵ�������λ��*/
	public static final String DATA_POSTPROF="307";
	/**�ڼ�*/
	public static final String DATA_POSTLEVEL="308";
	/**ְ��*/
	public static final String DATA_PROFQUAL="309";
	/**��������*/
	public static final String DATA_JOBNATURE="310";
	/**���⹤��*/
	public static final String DATA_SPECTRADE="311";
	/**�ض���λ*/
	public static final String DATA_SPECPOST="312";
	
	/**��Ա���*/
	public static final String DATA_PEOIDEN="401";
	/**�ɲ�ְ�񼶱�*/
	public static final String DATA_JOBLEVEL="402";
	/**ְ�����÷�ʽ*/
	public static final String DATA_JOBCONFIG="403";
	/**����ְ��*/
	public static final String DATA_WORKPARTY="404";
	
	/**��������*/
	public static final String DATA_ACCNATURE="501";
	/**��Ա����*/
	public static final String DATA_ADDMEMREASON="502";
	/**�ù���ʽ*/
	public static final String DATA_EMPFORM="503";
	/**��ͬ����*/
	public static final String DATA_CONTTYPE="504";
	/**��ͬ����*/
	public static final String DATA_LABCONT="505";
	/**��ת����*/
	public static final String DATA_EXSOLDIER="506";
	
	/**ѧ���ȼ�*/
	public static final String DATA_EDURECORD="601";
	/**רҵ�����ѧ����*/
	public static final String DATA_SPECTYPERECORD="602";
	/**רҵ��� Ĭ��ѡ�� ��ѧ */
	public static final String DATA_SPECTYPE_ENGINEERING="602018";
	/**ѧϰ��ʽ*/
	public static final String DATA_STUTYPE="603";
	/**��ҵ״��*/
	public static final String DATA_GRADSTATUS="604";
	/**ѧλ*/
	public static final String DATA_EDUDEGREE="605";
	
	/**����ְ�Ƶȼ�*/
	public static final String DATA_PROFLEVEL="701";
	/**����ְ��ϵ��*/
	public static final String DATA_PROFSET="702";
	/**ְҵ�ʸ�ȼ�*/
	public static final String DATA_QUALLEVEL="703";
	/**ְҵ��������*/
	public static final String DATA_QUALTITLE="704";
	/**����ˮƽ*/
	public static final String DATA_LANGLEVEL="705";
	/**�����ˮƽ*/
	public static final String DATA_COMLEVEL="706";
	/**ר���˲ųƺ�*/
	public static final String DATA_EXPERTTYPE="707";
	/**�ƺŻ�÷�ʽ*/
	public static final String DATA_WAYOBTAIN="708";
	/**ְҵ�������*/
	public static final String DATA_PROFTYPE="709";//2013-07-04
	/**�������� */
	public static final String DATA_LANGKIND="710";//2013-07-04
	/**�������������� */
	public static final String DATA_MINOLANG="711";//2013-07-04
	/**�������ճ̶� */
	public static final String DATA_LANGLEV="712";//2013-07-04
	/**���ɴ������� */
	public static final String DATA_INFLTYPE="713";//2013-07-04
	
	

	/**�칫����*/
	public static final String DATA_MESSBUREAU="801";
	/**���м��*/
	public static final String DATA_MESSBANK="802";
	/**��������*/
	public static final String DATA_MESSCITY="803";
	/**��������*/
	public static final String DATA_MESSORGAN="804";
	/**����״��*/
	public static final String DATA_MESSMARR="805";
	/**��Ա��ν*/
	public static final String DATA_MESSMEMB="806";
	/**��Ա��״*/
	public static final String DATA_MESSSTATUS="807";
	
	/**��������*/
	public static final String DATA_NATIONALAREA="9201";
	/**�������*/
	public static final String DATA_MESSBANKTYPE="9802";

	/**��������ʡ-���ʣ�*/
	public static final String DATA_PROVINCENATURE="980301";
	/**����������-���ʣ�*/
	public static final String DATA_CITYNATURE="980302";
	/**����������-���ʣ�*/
	public static final String DATA_COUNTYNATURE="980303";
	/**����������-���ʣ�*/
	public static final String DATA_TOWNNATURE="980304";
	/**�������򣨴�-���ʣ�*/
	public static final String DATA_VALLAGENATURE="980305";
	
	/**ȫ����(ѧϰ��ʽ��Ա �Ǹ���ID)*/
	public static final String DATA_FULLTIME="603011";
	/**��Ա״̬Ϊ��ְ��*/
	public static final String DATA_USERSTATEFATHER="206001";
	/**��λ����ͨ����*/
	public static final String DATA_POSTPROFGENERAL="307007";
	/**��λ����ͨ����--������id*/
	public static final String DATA_POSTPROFGENERAL_SKILL="30700704";
	/**��λ����ũ����*/
	public static final String DATA_POSTPROFCOMMON="307014";
	
	/**********************֤����ϵͳ��������fatherid************************/  
	/**֤���������*/
	public static final String CERT_TYPE="1";
	/**��ȡ����֤��*/
	public static final String CERT_GETTYPE="2";
	/**��������֤��*/
	public static final String CERT_DEFTYPE="3";
	/**��λ�ڴ�����*/
	public static final String CERT_POSTTYPE="4";
	/**Ӧ���������*/
	public static final String CERT_PREPAREDTYPE="5";
	/**ѧ��*/
	public static final String CERT_EDURECORD="1011";
	/**ѧλ*/
	public static final String CERT_EDUDEGREE="1012"; 	
	/**����ְ��*/
	public static final String CERT_PROFTITLE="1021"; 		
	/**ְҵ�ʸ�*/
	public static final String CERT_QUALTITLE="1022";  	 	
	/**������ҵ*/
	public static final String CERT_SPECWORD="1031"; 	
	/**ִҵ�ʸ�*/
	public static final String CERT_QUALLICEN="1032";  	 	
	/**��λ��ѵ*/
	public static final String CERT_POSTTRAN="1041";  	 	
	/**����ˮƽ*/
	public static final String CERT_LANGLEVEL="1051";  	 	
	/**�����ˮƽ*/
	public static final String CERT_COMLEVEL="1052";  	 	
	/**����*/
	public static final String CERT_OTHER="1097"; 	
	
	/**********************��Ϣ������ϵͳ��������fatherid************************/  
	/**������̬���*/
	public static final String INFO_WORKNEW="1";   
	/**������Ϣ���*/
	public static final String INFO_NEWSTYPE="2";    
	/**�����ø�ȼ�*/
	public static final String INFO_NEWSLEVEL="3"; 
	/**�ļ��ƶ����*/
	public static final String INFO_FILELEVEL="4";  
	/**����ָ�����*/
	public static final String INFO_GUIDETYPE="5"; 
	/**���ֿ����*/
	public static final String INFO_TABLETYPE="6";
	/**ר������*/
	public static final String INFO_SPECTYPE="7";
	/**�ϱ��������*/
	public static final String INFO_REPORTTYPE="8";
	/**Ȥζ�������*/
	public static final String INFO_TESTTYPE="9";
	/**�����������*/
	public static final String INFO_LINKTYPE="10";
	/**�ļ��ƶȵȼ���*/
	public static final String INFO_FILELAWLEVEL="11";
	/**��ɽ֮ʯ�������*/
	public static final String INFO_TSZSTYPE="12";  
	
	/**********************CBE��ϵͳ��������fatherid************************/  
	/**��Ŀ���*/
	public static final String CBE_SUBTYPE="1"; 
	/**���˵ȼ�*/
	public static final String CBE_ASSLEVEL="2"; 
	/**��������*/
	public static final String CBE_ASSPHASE="3"; 
	/**ѧϰ��ʽ*/
	public static final String CBE_STUMODE="4"; 
	/**���˷�ʽ*/
	public static final String CBE_ASSMODE="5";  
	
	/*******************��Դ������ϵͳ��������fatherid************************/
	/**�ļ��������*/
	public static final String RES_BTYPE="1101";
	/**�ļ��ܼ�����*/
	public static final String RES_BLEVEL="1102";
	/**�ļ���������*/
	public static final String RES_BKIND="1103";
	/**�μ��������2101-05*/
	public static final String RES_CTYPE="2105";
	/**�μ���������2102-06*/
	public static final String RES_CCLASS="2106";
	/**�μ�·������2505-2109*/
	public static final String RES_PATH="2109";
	/**����֪Ҫ����*/
	public static final String RES_POINT="2501";
	/**�����������*/
	public static final String RES_QTYPE="2505";
	/**������������*/
	public static final String RES_QSTYPE="2506";
	/**�γ��������*/
	public static final String RES_STYPE="2901";
	/***����������� ***/
	public static final String RES_AREA = "11";
	
	/****************����Ϊ��������*******************/
	/**��������*/
	public static final String RES_QUE_TYPE = "3";
	/**��ѡ*/
	public static final String RES_QUE_SINGLE = "250601";
	/**��ѡ*/
	public static final String RES_QUE_MULTIPLE = "250602";
	/**�ж�*/
	public static final String RES_QUE_JUDGE = "250603";
	/**���*/
	public static final String RES_QUE_FILL = "250604";
	/**����*/
	public static final String RES_QUE_REMARK = "250605";
	/**���*/
	public static final String RES_QUE_ANSWER = "250606";
	/**����*/
	public static final String RES_QUE_CALC = "250607";
	/**��ͼ*/
	public static final String RES_QUE_DRAW = "250608";
	/**�Ĵ�*/
	public static final String RES_QUE_ERRCORR = "250609";
	/**����*/
	public static final String RES_QUE_CORRECT = "250610";
	/**����*/
	public static final String RES_QUE_ANALY = "250611";
	/**����*/
	public static final String RES_QUE_DISCUSS = "250612";
	/**����*/
	public static final String RES_QUE_HEAR = "250613";
	/**��д*/
	public static final String RES_QUE_DICTATION = "250614";
	/**�ۺ�*/
	public static final String RES_QUE_COMPRE = "250615";
	
	/**Ԥ���������*/
	public static final String RES_ETYPE="7";
	/**����fatherid[Ԥ����Դ��ʹ��]*/
	public static final String RES_PLACE="267";
	/**�豸fatherid[Ԥ����Դ��ʹ��]*/
	public static final String RES_EQUIP="268";
	
	/*******************����ѧϰ������ϵͳ��������fatherid************************/   
	/**ר��γ����*/
	public static final String STUDY_SPECSUBTYPE="1"; 
	/**ѧϰ��̳���*/
	public static final String STUDY_LEARNFORUMTYPE="2"; 
	/**������*/
	public static final String STUDY_FAQTYPE="3"; 
	/**��Ŀ���*/
	public static final String DRILL_COURSECLASS="4"; 
	/**ѧϰ��ʽ*/
	public static final String DRILL_STUDYMODE="5"; 
	/**���˷�ʽ*/
	public static final String DRILL_EXAMINEMODE="6"; 
	/**���˵ȼ�*/
	public static final String DRILL_EXAMINELEVEL="7";  
	/**��λ�ڴ�*/
	public static final String DRILL_POST_JOB="0";
	/**��������*/
	public static final String DRILL_POST_CHECK="9";
	
	/*******************�ճ����������ϵͳ��������fatherid************************/  
	/**�������*/
	public static final String NOR_TASKTYPE="1"; 
	/**���������ȼ�*/
	public static final String NOR_TASKLEVEL="2";
	/**Ԥ�����*/
	public static final String NOR_FACTYPE="3";
	/**�������*/
	public static final String NOR_SENDTYPE="4";
	/**������� */
	public static final String NOR_RECETYPE="5";
	/**������� */
	public static final String NOR_DIFFTYPE="6";
	/**������� */
	public static final String NOR_INNOVTYPE="7"; 
	/**���ĵȼ� */
	public static final String NOR_OFFICELEVEL="8"; 
	/**�������� */
	public static final String NOR_SENDCATEG="9"; 
	/**�������� */
	public static final String NOR_RECECATEG="10"; 
	
	/**������λ  */
	public static final String NOR_MEAS="2501"; 
	/**��Ŀ���   */
	public static final String NOR_ITYPE="2502"; 
	
	/*******************�˲Ź�����ϵͳ��������fatherid************************/ 
	/**רҵ������� */
	public static final String ELITE_STYPE = "1";
	/**�˲�������� */
	public static final String ELITE_PTYPE = "2";
	/**ͨ����� */
	public static final String ELITE_CTYPE = "14";
	
	/*******************��Թ�����ϵͳ��������fatherid************************/ 
	/**��ѵĿ��/�̶� */
	public static final String PAIR_CHKCTYPE= "1";
	/**Ӧ֪���ڵ���Э��word�С� */
	public static final String PAIR_PACT_YZ= "101";
	/**Ӧ�᡾�ڵ���Э��word�С� */
	public static final String PAIR_PACT_YH= "102";
	/**�ƻ������ȼ� */
	public static final String PAIR_CHKFBACK= "2";
	/**�ƻ����۵ȼ� */
	public static final String PAIR_CHKPLEVEL= "3";
	/**�������۵ȼ� */
	public static final String PAIR_CHKMLEVEL= "4";
	/**��ĩ���۵ȼ� */
	public static final String PAIR_CHKFLEVEL= "5";
	/**������۷��� */
	public static final String PAIR_CHKSCHEME= "6";
	/**������۵ȼ� */
	public static final String PAIR_CHKGRADE= "7";
	/**�������� */
	public static final String PAIR_CHKNATUR= "9";
	/**ѧԱ������� */
	public static final String PAIR_FBACK = "10";
	
	/*******************������ѵ������ϵͳ��������fatherid************************/
	/**��ѵ���� */
	public static final String TRAIN_LAYTYPE="1";
	/**�ƻ����*/
	public static final String TRAIN_PLANTYPE="2";
	/**��ѵ��� */
	public static final String TRAIN_TYPE="3";
	/**������� */
	public static final String TRAIN_REPORT="4";
	/**��ѵ���ÿ�Ŀ */
	public static final String TRAIN_FOUNDS="5";
	/**��ѵ������Դ */
	public static final String TRAIN_SOURCE="6";
	/**ͳ�ƿھ����� */
	public static final String TRAIN_COUNT="7";
	
	/*********************��ѵ������ϵͳ ��ѵ��� ��ѵ���fatherid***********/
	/**���� */
	public static final String TRAIN_FINAL_CG="11";
	/**��� */
	public static final String TRAIN_FINAL_ST="21";
	/**���� */
	public static final String TRAIN_FINAL_SD="22";
	/**�ɲ����� */
	public static final String TRAIN_FINAL_JL="23";
	/**ѧ��ѧλ */
	public static final String TRAIN_FINAL_XL="31";
	/**ְ���ʸ� */
	public static final String TRAIN_FINAL_ZC="41";
	/**�������� */
	public static final String TRAIN_FINAL_DK="51";
	/**���ʺ���*/
	public static final String TRAIN_FINAL_GJ="61";
	/**����ص�*/
	public static final String TRAIN_FINAL_SC="0";
	/**������Ч�� */
	public static final String TRAIN_FINAL_SJ="0";
	/**����ѧϰ */
	public static final String TRAIN_FINAL_XX="91";
	/**���翼�� */
	public static final String TRAIN_FINAL_KS="92";
	/******************* ���������ϵͳ fatherid************************/ 
	/**ְ�����*/
	public static final String GROUP_POSTTYPE="1";
	/**��������*/
	public static final String GROUP_NATURE="2";
	
	/*******************���ʹ���ϵͳ��������fatherid************************/
	/**���ʿ�Ŀ */
	public static final String WAGE_SUBJECT="0"; 
	/**������ϵ��� */
	public static final String WAGE_TYPE="1"; 
	/**������Դ */
	public static final String WAGE_SOURCE="2"; 
	/**��ʽ������ְ���ڸڣ�ID */
	public static final String WAGE_REGULAR="185"; 
	/**��λн��ID */
	public static final String WAGE_POSTSALARY="201";
	/**������*/
	public static final String WAGE_BASEPOST="193";
	/**��˰*/
	public static final String WAGE_USERTAX="1";
	/**������*/
	public static final String WAGE_PUBTAX="2";
	/**��������˰˰��*/
	public static final String WAGE_TAXRATE="3";
	/**���ʿ�Ŀ����*/
	public static final String WAGE_NATURE="1";
	/**�����ִ�����*/
	public static final String WAGE_CYC="5";
	/**����������*/
	public static final String WAGE_WDEPT="9";
	/** н���� [���ù��� ��Աн����Ϣ ʱ��] */
	public static final String WAGE_XDTYPE="16";
	/**����_������ϵ���*/
	public static final String WAGE_KIND="0"; 
	/*******************���ʹ���ϵͳ��������fatherid************************/
	/**������Դ */
	public static final String PAY_SOURCE="2";
	
	/*******************��λ������ϵͳ��������fatherid************************/
	/**��λ�������  */
	public static final String POST_RTYPE="1"; 
	/**��ͨЭ�����   */
	public static final String POST_CTYPE="2"; 
	
	/*******************ҵ��������������fatherid************************/
	/**ҵ���������� */
	public static final String SUP_CONFIG="0"; 
	
	/*******************ҵ�������������fatherid************************/
	/**ҵ������ */
	public static final String SUP_TYPE="1";
	/**ҵ�����ƣ����룩 */
	public static final String SUP_NAME="2";
	/**ҵ���������� */
	public static final String SUP_RANKTYPE="3";
	/**ҵ������ */
	public static final String SUP_RANK="4";
	/**ҵ����ɫ */
	public static final String SUP_ROLE="5";
	/**����ѡ�� */
	public static final String SUP_ASSTYPE="6";
	/**��λ����*/
	public static final String SUP_CALCUL="7";
	/**�ڿ����*/
	public static final String SUP_CATE="8";
	/**�������� */
	public static final String SUP_CYCLE="9";

	/**�����ƺ�*/
	public static final String SUP_GLORY ="11";
	/**�ɹ���*/
	public static final String SUP_INNOV ="15";
	/**�����*/
	public static final String SUP_ADVICE ="21";
	/**������*/
	public static final String SUP_MATCH ="25";
	/**��Ȩר��*/
	public static final String SUP_PATENT ="31";
	/**��������*/
	public static final String SUP_THESIS ="35";
	/**�ν�ͨ��*/
	public static final String SUP_CITE ="51";
	/**���;���*/
	public static final String SUP_EXPER ="55";
	/**��������*/
	public static final String SUP_FILE ="61";
	/**ר��ҵ��*/
	public static final String SUP_SPEC ="97";

	/**����*/
	public static final String SUP_HONOR="201";
	/**����*/
	public static final String SUP_AWARD="205";
	/**�ڿ�*/
	public static final String SUP_BOOKS="251";
	/**ר��ҵ��**/
	public static final String SUP_="251";
	
	/*******************�˲Ż�������fatherid************************/
	/**���Ŀ*/
	public static final String EL_CITEM="0";
	
	/******************���ܼ�����ϵͳ��������fatherid****************/
	/**�������*/
	public static final String JUGDE_WORK_TYPE="1";
	/**ʵ�����*/
	public static final String JUGDE_SKILL_TYPE="2";
	/**�շ���Ŀ*/
	public static final String JUGDE_FEE_ITEM="5";
	/**�շ����*/
	public static final String JUGDE_FEE_TYPE="6";
	/**������Ŀ*/
	public static final String JUGDE_ASSE_ITEM="7";
	/**���˷�ʽ*/
	public static final String JUGDE_ASSE_MODE="8";
	/**���˵ȼ�*/
	public static final String JUGDE_ASSE_GRADE="9";
	/**����ְ��*/
	public static final String JUGDE_WORK_GRADE="11";
	
	/**�������*/
	public static final String JUGDE_PLACE_TYPE="10";
	
	/******************��ʱ[����ѧϰ]������������fatherid****************/
	/**�������*/
	public static final String SCORE_EXAM_TYPE="1";
	/**��������*/
	public static final String SCORE_EXAM_KIND="2";
	/**רҵ���*/
	public static final String SCORE_SPEC_TYPE="5";
	
	/**��������󡱿������*/
	public static final String SCORE_SJWD_TYPE="15191";

	/******************���������������fatherid****************/
	/**��Ŀ�㼶*/
	public static final String TEACH_ITEM_LEVEL="11";
	/**��Ŀ���*/
	public static final String TEACH_TERM_TYPE="21";
	/**��ѵ����*/
	public static final String TEACH_TRAIN_STYLE="22";
	/**��ѵ��ʽ*/
	public static final String TEACH_TRAIN_MODE="25";
	/**���ȳ̶�*/
	public static final String TEACH_URGENT_DEGREE="29";
	/**������Դ*/
	public static final String TEACH_PAY_SOURE="35";
	/**��������*/
	public static final String TEACH_PAR_TYPE="39";
	/**ְҵ�ʸ�ȼ�*/
	public static final String TEACH_QUAL_LEVEL="91";
	/**������Ŀ���*/
	public static final String TEACH_REQ_TYPE="97";
	
	/******************���ڹ����������fatherid****************/
	/**������*/
	public static final String ATTEND_TYPE_QJ="101";
	/**������*/
	public static final String ATTEND_TYPE_WC="102";
	/**н������*/
	public static final String ATTEND_PAY_KIND="5";
	/**�ٵ�*/
	public static final String ATTEND_CD="105";
	/**����*/
	public static final String ATTEND_ZT="106";
	/**��*/
	public static final String ATTEND_KG="108";
	/**����*/
	public static final String ATTEND_TC="109";
	/**������_����*/
	public static final String ATTEND_WORKDAY_NOR="811";
	/**������_�ְ�*/
	public static final String ATTEND_WORKDAY_TURN="821";
	/**��������λ��*/
	public static final String ATTEND_DEVICE_PLACE="9";
	

	/**********************�ͱ���ϵͳ��������fatherid************************/
	/**�ͱ���Ʒ���**/
	public static final String LABOR_APP_TYPE = "1";
	/**��̬����**/
	public static final String LABOR_BODY_SIZE = "2";
	/**�ͱ�����**/
	public static final String LABOR_WORK_TYPE = "5";
	/**��������**/
	public static final String LABOR_GET_KIND = "7";
	/**�ֿ�����**/
	public static final String LABOR_STORE_NAME = "9";
	/**��װ��**/
	public static final String LABOR_CLOTH_TYPE = "101";
	/**����Ʒ��**/
	public static final String LABOR_DALITY_TYPE = "105";
	/**������**/
	public static final String LABOR_OTHER_TYPE= "109";
	
	/**�ͱ���Ʒ����id**/
	public static final String LABOR_MAT="23";
	/**��Ʒ�����id**/
	public static final String LABOR_TYPE="0";
	/**�ͱ����ָ���id**/
	public static final String LABOR_CONFIG_WORKID="0";
	
	/*******************  �������_��ϵͳ��������fatherid  ********************/ 
	/** �����ʽ���� **/
	public static final String LEAD_WAY="1"; 
	/** ������������ **/
	public static final String LEAD_EVA="0"; 
	
	/*******************  ��Թ���_��ϵͳ��������fatherid  ********************/ 
	/** ��ѵ��� **/
	public static final String PAIR_TRAIN_CLASS="1"; 
	/** �׶ο��˷�ʽ **/
	public static final String PAIR_ASSE_METHODS="3"; 
	/** �׶ο������ **/
	public static final String PAIR_ASSE_CLASS="7"; 
	
	/*******************  ��ѵ�����������fatherid  ********************/
	/** ������ **/
	public static final String TRAIN_WINNER_TYPE="13"; 
	/** ������ **/
	public static final String TRAIN_PLACE_WINNER="14"; 
	
	
	/*************** ����ר��_��ϵͳ��������fatherid  ********************/ 
	/** �������� **/
	public static final String LAW_NATURE="0"; 
	/** ����רҵ **/
	public static final String LAW_SPEC="1"; 
	/** ��λ���� **/
	public static final String LAW_POST="2"; 
	/** �γ�Ŀ¼ **/
	public static final String LAW_COURSE_CONTENTS="0"; 
	
	/******************�����λ��ѵ��������fatherid****************/
	/**��λ����*/
	public static final String PTRAIN_POST="1";
	/**רҵ���*/
	public static final String PTRAIN_SPEC="2";
	/**����ļ�*/
	public static final String PTRAIN_RULES="11";
	/**Ӱ��Ӱ�ӿ�*/
	public static final String PTRAIN_MOVIES="12";
	/**�����鼮��*/
	public static final String PTRAIN_BOOKS="13";
	/**���;����*/
	public static final String PTRAIN_EXPER="14";
	/**ÿ������רҵ���*/
	public static final String PTRAIN_SPEC_ASK="3";
	/**�μ����*/
	public static final String PTRAIN_COURS="5";
	
	/**֪ʶѧϰ*/
	public static final String PTRAIN_BBS="0";
	/******************У԰�������fatherid****************/
	/**��������*/
	public static final String CAMPUS_PLACE_TYPE="1";
	
	
	/**
	 * ��ɫ�����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map getRoleGroupMap(){
		Map m = new LinkedHashMap();
		m.put("0", "�û���");
		m.put("1", "ϵͳ��");
		return m;
	}
}