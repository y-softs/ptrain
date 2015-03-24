package com.nomen.ntrain.ptrain.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.ptrain.bean.PtrainParamBean;
import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainAskService;
import com.nomen.ntrain.ptrain.service.PtrainAskitemService;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainParamService;
import com.nomen.ntrain.ptrain.service.PtrainPolicyService;
import com.nomen.ntrain.ptrain.service.PtrainPostuserService;
import com.nomen.ntrain.ptrain.util.DateTimeUtil;
import com.nomen.ntrain.ptrain.util.MonthlyCalendar;
import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.res.service.ResCodeService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_每日三问action层
 * @author 林木山
 * @date 2014-3-24
 */
@SuppressWarnings("all")
public class PtrainAskAction extends PtrainAction{

	private PtrainAskService		ptrainAskService;  	//莆田岗位培训_每日三问业务接口
	private PtrainAskitemService	ptrainAskitemService;//莆田岗位培训_每日三问业务接口
	private PtrainPolicyService		ptrainPolicyService;//莆田岗位学习_抽题策略业务接口
	private PtrainPostuserService	ptrainPostuserService;//岗位人员业务业务接口
	private PtrainParamService		ptrainParamService; //莆田岗位学习_抽题策略业务接口
	private PtrainCodeService		ptrainCodeService;	//代码列表业务接口
	private ResCodeService			resCodeService;		//代码列表业务接口
	private LoginService 			loginService;      	//登录信息业务处理类
	private PtrainAskBean			ptrainAskBean;     	//莆田岗位培训_每日三问信息表
	private PtrainAskitemBean		ptrainAskitemBean;  //莆田岗位培训_每日三问答题表
	private Map<String,String>		querymap;			//传参map
	private List					dataList;			//数据列表

	/**
	 * @description三问答题 列表信息
	 */
	public String listPtrainAsk() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			String postid=func.Trim(this.querymap.get("postid"));
			Map map = new HashMap();
			if(func.IsEmpty(postid)){
				//查询本人岗位列别ID
				map.put("unitid", this.querymap.get("unitid"));
				map.put("userid", loginBean.getId());
				postid=this.ptrainPostuserService.findPtrainPostuserPostid(map);
				this.querymap.put("postid", func.Trim(postid));
				if(func.IsEmpty(postid)){
					this.setActMessage("请设置人员专业关联！");
					this.querymap.put("fromask", "true");
					this.listPtrainAskQuery();
					return "gotoQuery";
				}
			}
			int month=func.getMonth();
			//统计累计信息
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("userid", loginBean.getId());
			map.put("year", func.getYear()+"");
			map.put("month", month<10?"0"+month:""+month);
			this.ptrainAskBean=this.ptrainAskService.findPtrainAskStat(map);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return "gotoAsk";
	}

	/**
	 * @description交卷[保存]
	 */
	public String savePtrainAskitem() {
		String rValue="gotoAsk";
		try {
			if(this.isValidToken()) {
				HttpServletRequest req = ServletActionContext.getRequest();
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				if(null==this.ptrainAskBean)this.ptrainAskBean=new PtrainAskBean();
				this.ptrainAskBean.setMainuser(loginBean.getId());
				String[] askitemidArr=req.getParameterValues("askitemid"),
				ansdetailArr=req.getParameterValues("ansdetail"),rightAnsArr=req.getParameterValues("rightans"),quescoreArr=req.getParameterValues("quescore");
				int totalNum=askitemidArr.length,errorNum=0;
				Double ansscore=0D;
				//更新子表答题情况
				for(int i=0;i<totalNum;i++){
					String askitemid=askitemidArr[i],ansdetail=this.querymap.get("ansdetail"+askitemid).replace(" ", ""),rightAns=rightAnsArr[i],quescore=quescoreArr[i];
					Map map = new HashMap();
					map.put("askitemid", askitemid);
					//map.put("asknum", asknum);
					map.put("ansdetail", ansdetail);
					map.put("rightans", rightAns);
					map.put("quescore", quescore);
					map.put("operuserid", loginBean.getId());
					Map resMap=this.ptrainAskitemService.updatePtrainAskitem(map);
					String ansright=resMap.get("ansright")+"";
					if("0".equals(ansright))errorNum+=1;
					ansscore+=Double.valueOf(resMap.get("ansscore")+"");
				}
				ansscore=(double)Math.round(ansscore*100)/100;//保留两位
				//更新主表交卷时间
				//if(0==errorNum){
					this.ptrainAskBean.setSubtime(func.dateToString(new Date(), "yyyy-MM-dd"));
					this.ptrainAskBean.setScore("update");
					this.ptrainAskService.savePtrainAsk(this.ptrainAskBean);
					
					this.setActMessage("恭喜您已完成今日三问，您本次共答对"+(totalNum-errorNum)+"题，答错"+errorNum+"题。您本次得到学分为"+ansscore+"分！");
					this.listPtrainAskQuery();
					rValue="gotoQuery";
				/*}else{
					this.setActMessage("您本次共答对"+(totalNum-errorNum)+"题，答错"+errorNum+"题。");
					this.listPtrainAsk();
				}*/
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.listPtrainAsk();
		}
		return rValue;
	}

	/**
	 * @description个人查询 列表信息
	 */
	public String listPtrainAskQuery() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap||!func.IsEmpty(this.querymap.get("fromask"))){
				if(null==this.querymap)this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
				this.querymap.put("year", func.getYear()+"");//当前年份
				this.querymap.put("month", func.Cstr(func.getMonth()));//当前月份
				this.querymap.put("chkDay", func.Cstr(func.getDay()));//当日
			}
			String year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp,
				dayTemp=this.querymap.get("chkDay"),chkDay=(dayTemp.length()<2?"0":"")+dayTemp;
			int chkDayInt=Integer.valueOf(chkDay);
			if(chkDayInt>27){
				int lastDay=DateTimeUtil.getLastDay(year+"-"+month);
				if(chkDayInt>lastDay)chkDay=""+lastDay;
			}
			Map map = new HashMap();
			req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
			//年度列表
			req.setAttribute("yearList", Constant.getYearList(func.getYear()-2, func.getYear(), 1));
			//月份列表
			req.setAttribute("monthList", Constant.getMonthList());
			String postid=func.Trim(this.querymap.get("postid")),dayStr="";
			if(null==this.querymap.get("postid")){
				//查询本人岗位列别ID
				map = new HashMap();
				map.put("unitid", this.querymap.get("unitid"));
				map.put("userid", loginBean.getId());
				postid=this.ptrainPostuserService.findPtrainPostuserPostid(map);
			}
			this.querymap.put("postid", postid);
			//if(!func.IsEmpty(postid)){
				//已答题日期列表
				map = new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("userid", loginBean.getId());
				map.put("subtime", year+"-"+month);
				List<PtrainAskBean> askList=this.ptrainAskService.findPtrainAskSubday(map);
				for(PtrainAskBean aBean:askList){
					dayStr+=aBean.getSubtime()+",";
				}
				if(!func.IsEmpty(dayStr))dayStr=","+dayStr;
			//}
			//构建日历
			MonthlyCalendar m = new MonthlyCalendar(year,month,dayStr,chkDay);
			req.setAttribute("DATES", m.getText());
			//if(func.IsEmpty(postid)){
			//	return SUCCESS;
			//}
			//统计累计信息
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("userid", loginBean.getId());
			map.put("year", func.getYear()+"");
			map.put("month", month);
			this.ptrainAskBean=this.ptrainAskService.findPtrainAskStat(map);
			//三问答题子表列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("userid", loginBean.getId());
			map.put("subtime", year+"-"+month+"-"+chkDay);
			map.put("sortfield","ai.ansdate,ai.id");
			this.dataList =this.ptrainAskitemService.findPtrainAskitemListQue(map);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	/**
	 * @description个人查询 详细页面[弹出]
	 */
	public String setPtrainAskitemWin() {
		try {
			Map map=new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("id", func.Trim(this.querymap.get("askitemid")));
			this.ptrainAskitemBean=this.ptrainAskitemService.findPtrainAskitemBeanByMap(map);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description试题浏览 列表信息
	 */
	public String listPtrainAskSkim() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				if(null==this.querymap)this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			Map map = new HashMap();
			req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
			//试题类型列表
			map.put("isTypeList", "true");
			map.put("fatherid", CodeFatherUtil.RES_QUE_TYPE);
			map.put("usesign", "1");
			List<ResCodeBean> typeList=this.resCodeService.findResCodeList(map);
//			PtrainConstant.typeListOper(typeList,req);
			//三问答题子表列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("userid", loginBean.getId());
			map.put("ansright", "1");
			map.put("sortfield","ai.ansdate,ai.id");
			map.put("sortfield","q.sortnum,q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList =this.ptrainAskitemService.findPtrainAskitemListSkim(map);
			this.setTotal(String.valueOf(map.get("total")));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	/**
	 * 试题浏览 Excel导出
	 */
	public String savePtrainAskSkimExpExcel() {
		try{
			LoginBean loginBean = this.getLoginSessionBean();
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("userid", loginBean.getId());
			map.put("ansright", "1");
			map.put("sortfield","ai.ansdate,ai.id");
			map.put("sortfield","q.sortnum,q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			this.ptrainAskitemService.savePtrainAskitemSkimExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * ========================查询统计相关======================
	 */
	
	public String toForwardQueryAsk(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("year", func.getYear()+"");//当前年份
			this.querymap.put("month", func.Cstr(func.getMonth()));//当前月份
			this.querymap.put("sortsign", "2");//按专业统计
			this.sortfield="app.sortnum,app.id";
		}
		String year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
		//年度列表
		req.setAttribute("yearList", Constant.getYearList(func.getYear()-2, func.getYear(), 1));
		//月份列表
		req.setAttribute("monthList", Constant.getMonthList());
		//达标次数
		String reachnum=func.Trim(this.querymap.get("reachnum"));
		Map map = new HashMap();
		if(func.IsEmpty(reachnum)){
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			List<PtrainParamBean> paramList=this.ptrainParamService.findPtrainParamList(map);
			for(PtrainParamBean paramBean:paramList){
				if(PtrainConstant.PARAM_MYDB.equals(paramBean.getParamname())){
					reachnum=paramBean.getParamvalue();
					break;
				}
			}
			this.querymap.put("reachnum", reachnum);
		}
		req.setAttribute("reachscore", PtrainConstant.REACH_SCORE);
		return SUCCESS;
	}
	
	/**
	 * 查询统计_每日三问情况_应用统计 按部门、按专业 列表信息
	 */
	public String listPtrainAskAppStat() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
				this.querymap.put("year", func.getYear()+"");//当前年份
				this.querymap.put("month", func.Cstr(func.getMonth()));//当前月份
				this.querymap.put("sortsign", "2");//按专业统计
				this.sortfield="app.sortnum,app.id";
			}
			String year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
			//达标次数
			String reachnum=func.Trim(this.querymap.get("reachnum"));
			Map map = new HashMap();
			if(func.IsEmpty(reachnum)){
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				List<PtrainParamBean> paramList=this.ptrainParamService.findPtrainParamList(map);
				for(PtrainParamBean paramBean:paramList){
					if(PtrainConstant.PARAM_MYDB.equals(paramBean.getParamname())){
						reachnum=paramBean.getParamvalue();
						break;
					}
				}
				this.querymap.put("reachnum", reachnum);
			}
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("subtime", year+"-"+month);
			map.put("reachnum", reachnum);
			map.put("reachscore", PtrainConstant.REACH_SCORE);
			map.put("usesign", "1");
			map.put("sortfield",this.sortfield);
			if("1".equals(this.querymap.get("sortsign"))){
				//按部门
				this.dataList =this.ptrainAskService.findPtrainAskAppStat(map);
			}else{
				//按专业
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
//				String certerUnitid=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId());
				this.querymap.put("centerunitid", func.Trim(this.querymap.get("unitid")));
				map.put("centerunitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
				//map.put("unitid", "");
				this.dataList =this.ptrainAskService.findPtrainAskAppStatSpec(map);
			}
			this.printList(this.dataList);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询统计_每日三问情况_应用统计 按部门、按专业 Excel导出
	 */
	public String savePtrainAskAppStatExpExcel() {
		try{
			String year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("centerunitid", func.Trim(this.querymap.get("centerunitid")));
			map.put("subtime", year+"-"+month);
			map.put("reachnum", func.Trim(this.querymap.get("reachnum")));
			map.put("reachscore", PtrainConstant.REACH_SCORE);
			map.put("sortsign", func.Trim(this.querymap.get("sortsign")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			map.put("sortfield",this.sortfield);
			this.ptrainAskService.savePtrainAskStatExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * 查询统计_每日三问情况_应用统计 答题人员[弹出]
	 */
	public String setPtrainAskUserWin() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			String unitid=this.querymap.get("unitid"),reachnum=this.querymap.get("reachnum"),year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
			if(func.IsEmpty(unitid)||func.IsEmpty(year)||func.IsEmpty(month))
				return SUCCESS;
			Map map = new HashMap();
			//数据列表
			map.put("unitid", unitid);
			map.put("subtime", year+"-"+month);
			map.put("reachnum", reachnum);
			map.put("reachscore", PtrainConstant.REACH_SCORE);
			if(func.IsEmpty(func.Trim(this.querymap.get("subgroupid")))){
				map.put("deptid", this.querymap.get("deptid"));
				map.put("groupid", this.querymap.get("groupid"));
			}
			map.put("subgroupid", this.querymap.get("subgroupid"));
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("showsign1", this.querymap.get("showsign1"));
			map.put("showsign2", this.querymap.get("showsign2"));
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList =this.ptrainAskService.findPtrainAskUser(map);
			this.setTotal(String.valueOf(map.get("total")));
			this.print(this.creItemListPage(dataList,String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳转应用统计用户详细列表页面
	 * @return
	 */
	public String toForwardQueryAskUserWin(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			String unitid=this.querymap.get("unitid"),reachnum=this.querymap.get("reachnum"),year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
			if(func.IsEmpty(unitid)||func.IsEmpty(year)||func.IsEmpty(month))
				return SUCCESS;
			List naturelist=null,deptlist=null;
			Map map = new HashMap();
			/**
			 if("1".equals(this.querymap.get("sortsign"))){
				//部门列表
				map.put("unitid", this.querymap.get("unitid"));
				map.put("nature", func.Trim(this.querymap.get("nature")));
				deptlist = this.loginService.findDeptListByMap(map);
				req.setAttribute("naturelist", naturelist);
				req.setAttribute("deptlist", deptlist);
			}else if("2".equals(this.querymap.get("sortsign"))){
				map = new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				String certerUnitid=func.Trim(this.ptrainCodeService.findCenterBaseUnitBean(map).getId());
				//专业类别
				map = new HashMap();
				map.put("nature", "2");
				map.put("unitid", certerUnitid);
				map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
				map.put("usesign", "1");
				req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
				//unitid="";
			} 
			 */
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询统计_每日三问情况_应用统计 答题人员 Excel导出
	 */
	public String savePtrainAskUserExpExcel() {
		try{
			String unitid=this.querymap.get("unitid"),reachnum=this.querymap.get("reachnum"),year=this.querymap.get("year"),monTemp=this.querymap.get("month"),month=(monTemp.length()<2?"0":"")+monTemp;
			if(func.IsEmpty(unitid)||func.IsEmpty(year)||func.IsEmpty(month))
				return null;
			Map map = new HashMap();
			if("1".equals(this.querymap.get("sortsign"))){
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
			}
			map.put("subtime", year+"-"+month);
			map.put("reachnum", reachnum);
			map.put("reachscore", PtrainConstant.REACH_SCORE);
			if(func.IsEmpty(func.Trim(this.querymap.get("subgroupid")))){
				map.put("deptid", this.querymap.get("deptid"));
				map.put("groupid", this.querymap.get("groupid"));
			}
			map.put("subgroupid", this.querymap.get("subgroupid"));
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("showsign1", this.querymap.get("showsign1"));
			map.put("showsign2", this.querymap.get("showsign2"));
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			this.ptrainAskService.savePtrainAskUserExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	
	/**
	 * 跳转到英雄榜单
	 * @return
	 */
	public String toForwardQueryAskRank(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("year", func.getYear()+"");//当前年份
			this.querymap.put("month", func.Cstr(func.getMonth()));//当前月份
			this.querymap.put("sortsign", "1");//按月份排行
			this.sortfield="o.sortnum,o.id";
		}
		String year=this.querymap.get("year"),monTemp=func.Trim(this.querymap.get("month")),month=(monTemp.length()==1?"0":"")+monTemp;
		//年度列表
		req.setAttribute("yearList", Constant.getYearList(func.getYear()-2, func.getYear(), 1));
		//月份列表
		req.setAttribute("monthList", Constant.getMonthList());
		//达标次数
		String reachnum=func.Trim(this.querymap.get("reachnum"));
		Map map = new HashMap();
		if(func.IsEmpty(reachnum)){
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			List<PtrainParamBean> paramList=this.ptrainParamService.findPtrainParamList(map);
			for(PtrainParamBean paramBean:paramList){
				if(PtrainConstant.PARAM_MYDB.equals(paramBean.getParamname())){
					reachnum=paramBean.getParamvalue();
					break;
				}
			}
			this.querymap.put("reachnum", reachnum);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询统计_每日三问情况_英雄榜单 列表信息
	 */
	public String listPtrainAskRank() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
				this.querymap.put("year", func.getYear()+"");//当前年份
				this.querymap.put("month", func.Cstr(func.getMonth()));//当前月份
				this.querymap.put("sortsign", "1");//按月份排行
				this.sortfield="o.sortnum,o.id";
			}
			String year=this.querymap.get("year"),monTemp=func.Trim(this.querymap.get("month")),month=(monTemp.length()==1?"0":"")+monTemp;
			//达标次数
			String reachnum=func.Trim(this.querymap.get("reachnum"));
			Map map = new HashMap();
			if(func.IsEmpty(reachnum)){
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				List<PtrainParamBean> paramList=this.ptrainParamService.findPtrainParamList(map);
				for(PtrainParamBean paramBean:paramList){
					if(PtrainConstant.PARAM_MYDB.equals(paramBean.getParamname())){
						reachnum=paramBean.getParamvalue();
						break;
					}
				}
				this.querymap.put("reachnum", reachnum);
			}
			//数据列表
			map = new HashMap();
			if("1".equals(func.Trim(this.querymap.get("sortsign")))){
				if(func.IsEmpty(this.querymap.get("month")))this.querymap.put("month", func.Cstr(func.getMonth()));
				monTemp=func.Trim(this.querymap.get("month"));
				month=(monTemp.length()==1?"0":"")+monTemp;
				map.put("subtime", year+"-"+month);//按月份排行
			}else{
				map.put("year", year);//按年度排行
			}
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("reachnum", reachnum);
			map.put("ranknum", "20");//前20名
			map.put("sortfield","to_number(nvl(score, 0)) desc,u.sortnum");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			this.dataList =this.ptrainAskService.findPtrainAskRank(map);
			this.print(JSONArray.fromObject(dataList).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询统计_每日三问情况_英雄榜单 Excel导出
	 */
	public String savePtrainAskRankExpExcel() {
		try{
			String unitid=this.querymap.get("unitid"),reachnum=this.querymap.get("reachnum"),year=this.querymap.get("year"),monTemp=func.Trim(this.querymap.get("month")),month=(monTemp.length()<2?"0":"")+monTemp;
			if(func.IsEmpty(unitid)||func.IsEmpty(reachnum)||func.IsEmpty(year))
				return null;
			Map map = new HashMap();
			if("1".equals(func.Trim(this.querymap.get("sortsign")))){
				if(func.IsEmpty(this.querymap.get("month")))this.querymap.put("month", func.Cstr(func.getMonth()));
				monTemp=func.Trim(this.querymap.get("month"));
				month=(monTemp.length()==1?"0":"")+monTemp;
				map.put("subtime", year+"-"+month);//按月份排行
			}else{
				map.put("year", year);//按年度排行
			}
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("reachnum", reachnum);
			map.put("ranknum", "20");//前20名
			map.put("sortfield","to_number(nvl(score, 0)) desc,u.sortnum");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			this.ptrainAskService.savePtrainAskRankExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * ==========================JQuery======================
	 */
	/**
	 * 查找每日三问 出题轮次 每日出题次数
	 */
	public void findPtrainAskPushnumByJq(){
		try {
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("userid", this.getLoginSessionBean().getId());
			//map.put("pushtime", func.dateToString(new Date(), "yyyy-MM-dd"));
			map.put("subtime", func.dateToString(new Date(), "yyyy-MM-dd"));
			map.put("paramname", PtrainConstant.PARAM_MRCT);
			PtrainAskBean pBean=this.ptrainAskService.findPtrainAskPushnum(map);
			if(null==pBean){
				pBean=new PtrainAskBean();
				pBean.setIntflag("0");
			}
			this.print(JSONArray.fromObject(pBean).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	/**
	 * 查找每日三问 题目列表
	 */
	public String findPtrainAskitemListByJq(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			LoginBean loginBean=this.getLoginSessionBean();
			String todayNoAsk=func.Trim(this.querymap.get("todaynoask")),unitid=func.Trim(this.querymap.get("unitid")),
				postid=func.Trim(this.querymap.get("postid")),pushnum=func.Trim(this.querymap.get("pushnum")),askid="",ansright="";
			Map map = new HashMap(),resMap = new HashMap();
			if(func.IsEmpty(todayNoAsk)&&func.IsEmpty(pushnum)){
				//今天已经生成答题==查询是否有未答题Id[答案不正确]
				map = new HashMap();
				map.put("unitid", unitid);
				map.put("userid", loginBean.getId());
				askid=this.ptrainAskService.findPtrainAskNoAskid(map);
				if(!func.IsEmpty(func.Trim(askid))){
					ansright="0";
				}
			}
			/*//试题类型及操作列表
			map = new HashMap();
			map.put("isTypeList", "true");
			map.put("fatherid", CodeFatherUtil.RES_QUE_TYPE);
			map.put("usesign", "1");
			List<ResCodeBean> typeList=this.resCodeService.findResCodeList(map);
			Map tMap=PtrainConstant.typeListOper(typeList,req);
			List<ResCodeBean> typeListOper=(List<ResCodeBean>) tMap.get("typeListOper");
			resMap.put("typeMap", tMap);
			//抽题策略
			map = new HashMap();
			map.put("unitid", unitid);
			List<PtrainPolicyBean> policyList=this.ptrainPolicyService.findPtrainPolicyList(map);
			Map pMap = new HashMap();
			for(PtrainPolicyBean policyBean:policyList){
				pMap.put(policyBean.getTypeid()+"_AMOUNT", policyBean.getAmount());
				pMap.put(policyBean.getTypeid()+"_SCORE", policyBean.getScore());
			}
			resMap.put("policyMap", pMap);*/
			resMap.put("TYPE_SINGLE", PtrainConstant.TYPE_SINGLE);
			resMap.put("TYPE_MULT", PtrainConstant.TYPE_MULT);
			resMap.put("TYPE_JUDGE", PtrainConstant.TYPE_JUDGE);
			//抽题策略
			map = new HashMap();
			map.put("unitid", unitid);
			map.put("typeid", postid);
			List<PtrainPolicyBean> policyList=this.ptrainPolicyService.findPtrainPolicyList(map);
			Map pMap = new HashMap();
			for(PtrainPolicyBean policyBean:policyList){
				pMap.put(policyBean.getTypeid()+"_AMOUNT", policyBean.getAmount());
				pMap.put(policyBean.getTypeid()+"_SCORE", policyBean.getScore());
			}
			resMap.put("policyMap", pMap);
			if(func.IsEmpty(askid)){
				if(null==this.ptrainAskBean)this.ptrainAskBean=new PtrainAskBean();
				//新增三问答题主表
				this.ptrainAskBean.setUnitid(unitid);
				this.ptrainAskBean.setUserid(loginBean.getId());
				this.ptrainAskBean.setPushnum(pushnum);
				this.ptrainAskBean.setEstauser(loginBean.getId());
				this.ptrainAskBean.setMainuser(loginBean.getId());
				this.ptrainAskService.savePtrainAsk(this.ptrainAskBean);
				askid=this.ptrainAskBean.getId();
				//新增三问答题子表
				map = new HashMap();
				map.put("unitid", unitid);
				map.put("postid", postid);
				map.put("askid", askid);
				map.put("operuserid", loginBean.getId());
				//map.put("typeListOper", typeListOper);
				map.put("policymap", pMap);
				this.ptrainAskitemService.insertPtrainAskitem(map);
			}
			resMap.put("askid", askid);
			//三问答题子表列表
			map = new HashMap();
			map.put("unitid", unitid);
			map.put("askid", askid);
			//map.put("ansright", ansright);
			map.put("sortfield","ai.ansdate,ai.id");
			List<PtrainAskitemBean> ptrainAskitemList =this.ptrainAskitemService.findPtrainAskitemList(map);
			resMap.put("ptrainAskitemList", ptrainAskitemList);
			
			this.print(JSONArray.fromObject(resMap).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
			this.deletePtrainAskByJq();
		}
		return null;
	}
	/**
	 * 每日三问 主表记录[删除]
	 */
	public void deletePtrainAskByJq(){
		try {
			if(!func.IsEmpty(this.ptrainAskBean.getId()))this.ptrainAskService.deletePtrainAskById(this.ptrainAskBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	//Get和Set方法
	public PtrainAskBean getPtrainAskBean() {
		return ptrainAskBean;
	}
	public void setPtrainAskBean(PtrainAskBean ptrainAskBean) {
		this.ptrainAskBean = ptrainAskBean;
	}
	public PtrainAskService getPtrainAskService() {
		return ptrainAskService;
	}
	public void setPtrainAskService(PtrainAskService ptrainAskService) {
		this.ptrainAskService = ptrainAskService;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}

	public PtrainAskitemService getPtrainAskitemService() {
		return ptrainAskitemService;
	}

	public void setPtrainAskitemService(PtrainAskitemService ptrainAskitemService) {
		this.ptrainAskitemService = ptrainAskitemService;
	}

	public PtrainPolicyService getPtrainPolicyService() {
		return ptrainPolicyService;
	}

	public void setPtrainPolicyService(PtrainPolicyService ptrainPolicyService) {
		this.ptrainPolicyService = ptrainPolicyService;
	}

	public ResCodeService getResCodeService() {
		return resCodeService;
	}

	public void setResCodeService(ResCodeService resCodeService) {
		this.resCodeService = resCodeService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PtrainAskitemBean getPtrainAskitemBean() {
		return ptrainAskitemBean;
	}

	public void setPtrainAskitemBean(PtrainAskitemBean ptrainAskitemBean) {
		this.ptrainAskitemBean = ptrainAskitemBean;
	}

	public PtrainPostuserService getPtrainPostuserService() {
		return ptrainPostuserService;
	}

	public void setPtrainPostuserService(PtrainPostuserService ptrainPostuserService) {
		this.ptrainPostuserService = ptrainPostuserService;
	}

	public PtrainParamService getPtrainParamService() {
		return ptrainParamService;
	}

	public void setPtrainParamService(PtrainParamService ptrainParamService) {
		this.ptrainParamService = ptrainParamService;
	}

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
}
