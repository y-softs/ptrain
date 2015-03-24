package com.nomen.ntrain.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.web.service.WebAskService;
import com.nomen.ntrain.web.service.WebAskitemService;
import com.nomen.ntrain.web.service.WebPolicyService;
import com.nomen.ntrain.web.service.WebPostuserService;

/**
 * @description 莆田岗位培训_每日三问action层
 * @author 林木山
 * @date 2014-3-24
 */
@SuppressWarnings("all")
public class WebAskAction extends WebAction{

	private WebAskService			webAskService;  	//莆田岗位培训_每日三问业务接口
	private WebAskitemService		webAskitemService;//莆田岗位培训_每日三问业务接口
	private WebPolicyService		webPolicyService;//莆田岗位学习_抽题策略业务接口
	private WebPostuserService		webPostuserService;//岗位人员业务业务接口
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
				map.put("userid", loginBean.getId());
				postid=this.webPostuserService.findPtrainPostuserPostid(map);
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
			map.put("userid", loginBean.getId());
			map.put("year", func.getYear()+"");
			map.put("month", month<10?"0"+month:""+month);
			this.ptrainAskBean=this.webAskService.findPtrainAskStat(map);
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
					Map resMap=this.webAskitemService.updatePtrainAskitem(map);
					String ansright=resMap.get("ansright")+"";
					if("0".equals(ansright))errorNum+=1;
					ansscore+=Double.valueOf(resMap.get("ansscore")+"");
				}
				ansscore=(double)Math.round(ansscore*100)/100;//保留两位
				//更新主表交卷时间
				//if(0==errorNum){
					this.ptrainAskBean.setSubtime(func.dateToString(new Date(), "yyyy-MM-dd"));
					this.ptrainAskBean.setScore("update");
					this.webAskService.savePtrainAsk(this.ptrainAskBean);					
					this.setActMessage("恭喜您已完成今日三问，您本次共答对"+(totalNum-errorNum)+"题，答错"+errorNum+"题。您本次得到学分为"+ansscore+"分！");
					this.listPtrainAskQuery();
					rValue="gotoQuery";
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
			}
			Map map = new HashMap();
			String month="",datetime="";
			if(func.IsEmpty(this.querymap.get("datetime"))){
				month = ((func.Cstr(func.getMonth()).length()<2?"0":"")+func.Cstr(func.getMonth()));
				datetime = func.getYear()+""+month+""+((func.Cstr(func.getDay()).length()<2?"0":"")+func.Cstr(func.getDay()));
			}else{
				month = this.querymap.get("datetime").split("-")[1];
				datetime = this.querymap.get("datetime");
			}
			String postid=func.Trim(this.querymap.get("postid")),dayStr="";
			if(null==this.querymap.get("postid")){
				//查询本人岗位列别ID
				map = new HashMap();
				map.put("userid", loginBean.getId());
				postid=this.webPostuserService.findPtrainPostuserPostid(map);
			}
			this.querymap.put("postid", postid);
			//统计累计信息
			map = new HashMap();
			map.put("userid", loginBean.getId());
			map.put("year", func.getYear()+"");
			map.put("month", month);
			this.ptrainAskBean=this.webAskService.findPtrainAskStat(map);
			//三问答题子表列表
			map = new HashMap();
			map.put("userid", loginBean.getId());
			map.put("subtime", datetime);
			map.put("sortfield","ai.ansdate,ai.id");
			this.dataList =this.webAskitemService.findPtrainAskitemListQue(map);
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
			map.put("id", func.Trim(this.querymap.get("askitemid")));
			this.ptrainAskitemBean=this.webAskitemService.findPtrainAskitemBeanByMap(map);
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
			//试题类型列表
			req.setAttribute("typeListOperMap", PtrainConstant._typeListOperMap());
			PtrainConstant.typeListOper();
			//三问答题子表列表
			map = new HashMap();
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("userid", loginBean.getId());
			map.put("ansright", "1");
			map.put("sortfield","ai.ansdate,ai.id");
			map.put("sortfield","q.sortnum,q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList =this.webAskitemService.findPtrainAskitemListSkim(map);
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
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("userid", loginBean.getId());
			map.put("ansright", "1");
			map.put("sortfield","ai.ansdate,ai.id");
			map.put("sortfield","q.sortnum,q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			this.webAskitemService.savePtrainAskitemSkimExpExcel(map, ServletActionContext.getResponse());
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
			map.put("userid", this.getLoginSessionBean().getId());
			map.put("subtime", func.dateToString(new Date(), "yyyy-MM-dd"));
			map.put("paramname", PtrainConstant.PARAM_MRCT);
			PtrainAskBean pBean=this.webAskService.findPtrainAskPushnum(map);
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
			String todayNoAsk=func.Trim(this.querymap.get("todaynoask")),
				postid=func.Trim(this.querymap.get("postid")),pushnum=func.Trim(this.querymap.get("pushnum")),askid="",ansright="";
			Map map = new HashMap(),resMap = new HashMap();
			if(func.IsEmpty(todayNoAsk)&&func.IsEmpty(pushnum)){
				//今天已经生成答题==查询是否有未答题Id[答案不正确]
				map = new HashMap();
				map.put("userid", loginBean.getId());
				askid=this.webAskService.findPtrainAskNoAskid(map);
				if(!func.IsEmpty(func.Trim(askid))){
					ansright="0";
				}
			}
			resMap.put("TYPE_SINGLE", PtrainConstant.TYPE_SINGLE);
			resMap.put("TYPE_MULT", PtrainConstant.TYPE_MULT);
			resMap.put("TYPE_JUDGE", PtrainConstant.TYPE_JUDGE);
			//抽题策略
			map = new HashMap();
			map.put("typeid", postid);
			List<PtrainPolicyBean> policyList=this.webPolicyService.findPtrainPolicyList(map);
			Map pMap = new HashMap();
			for(PtrainPolicyBean policyBean:policyList){
				pMap.put(policyBean.getTypeid()+"_AMOUNT", policyBean.getAmount());
				pMap.put(policyBean.getTypeid()+"_SCORE", policyBean.getScore());
			}
			resMap.put("policyMap", pMap);
			if(func.IsEmpty(askid)){
				if(null==this.ptrainAskBean)this.ptrainAskBean=new PtrainAskBean();
				//新增三问答题主表
				this.ptrainAskBean.setUserid(loginBean.getId());
				this.ptrainAskBean.setPushnum(pushnum);
				this.ptrainAskBean.setEstauser(loginBean.getId());
				this.ptrainAskBean.setMainuser(loginBean.getId());
				this.webAskService.savePtrainAsk(this.ptrainAskBean);
				askid=this.ptrainAskBean.getId();
				//新增三问答题子表
				map = new HashMap();
				map.put("postid", postid);
				map.put("askid", askid);
				map.put("operuserid", loginBean.getId());
				//map.put("typeListOper", typeListOper);
				map.put("policymap", pMap);
				this.webAskitemService.insertPtrainAskitem(map);
			}
			resMap.put("askid", askid);
			//三问答题子表列表
			map = new HashMap();
			map.put("askid", askid);
			//map.put("ansright", ansright);
			map.put("sortfield","ai.ansdate,ai.id");
			List<PtrainAskitemBean> ptrainAskitemList =this.webAskitemService.findPtrainAskitemList(map);
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
			if(!func.IsEmpty(this.ptrainAskBean.getId()))this.webAskService.deletePtrainAskById(this.ptrainAskBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	
	public List getDataList() {
		return dataList;
	}
	
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public PtrainAskBean getPtrainAskBean() {
		return ptrainAskBean;
	}

	public void setPtrainAskBean(PtrainAskBean ptrainAskBean) {
		this.ptrainAskBean = ptrainAskBean;
	}

	public PtrainAskitemBean getPtrainAskitemBean() {
		return ptrainAskitemBean;
	}

	public void setPtrainAskitemBean(PtrainAskitemBean ptrainAskitemBean) {
		this.ptrainAskitemBean = ptrainAskitemBean;
	}

	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	public WebAskitemService getWebAskitemService() {
		return webAskitemService;
	}
	public void setWebAskitemService(WebAskitemService webAskitemService) {
		this.webAskitemService = webAskitemService;
	}
	public WebAskService getWebAskService() {
		return webAskService;
	}
	public void setWebAskService(WebAskService webAskService) {
		this.webAskService = webAskService;
	}
	public WebPolicyService getWebPolicyService() {
		return webPolicyService;
	}
	public void setWebPolicyService(WebPolicyService webPolicyService) {
		this.webPolicyService = webPolicyService;
	}
	public WebPostuserService getWebPostuserService() {
		return webPostuserService;
	}
	public void setWebPostuserService(WebPostuserService webPostuserService) {
		this.webPostuserService = webPostuserService;
	}

	


	
	
	
}
