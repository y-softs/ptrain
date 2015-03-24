package com.nomen.ntrain.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.web.dao.WebAskitemDAO;
import com.nomen.ntrain.web.service.WebAskitemService;
@SuppressWarnings("all")
public class WebAskitemServiceImpl extends BaseServiceImpl implements WebAskitemService {

	private WebAskitemDAO webAskitemDAO;

	public List<PtrainAskitemBean> findPtrainAskitemList(Map map){
		return webAskitemDAO.findPtrainAskitemList(map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListQue(Map map) {
		return webAskitemDAO.findPtrainAskitemListQue(map);
	}

	public PtrainAskitemBean findPtrainAskitemBeanByMap(Map map){
		return webAskitemDAO.findPtrainAskitemBeanByMap(map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListSkim(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return webAskitemDAO.findPtrainAskitemListSkim(map, page, record);
	}

	public void insertPtrainAskitem(Map map){
		String unitid=func.Trim(map.get("unitid")+""),postid=func.Trim(map.get("postid")+""),askid=func.Trim(map.get("askid")+""),operuserid=func.Trim(map.get("operuserid")+"");
		List<ResCodeBean> typeListOper=(List<ResCodeBean>) map.get("typeListOper");
		Map policyMap=(Map) map.get("policymap");

		//本月已答题过滤
		String hasAsk=" and id not in(select ai.quesid from ptrain_ask a,ptrain_askitem ai where a.id=ai.askid and unitid="+unitid+" and userid="+operuserid+" and to_char(a.subtime,'yyyy-mm')='"+func.dateToString(new java.util.Date(), "yyyy-MM")+"')";
		//语句构建
		Map iMap=new HashMap();
		StringBuffer sqlstr=new StringBuffer(),reqSql=new StringBuffer();
		sqlstr.append("select seq_ptrain_askitem.nextval,"+askid+",id quesid,typeid,topic,option1,option2,option3,option4,option5,option6,option7,answer1,"+operuserid+",sysdate,"+operuserid+",sysdate from(");
		//新方法
//		sqlstr.append("select id from (select id,typeid,row_number() over(order by dbms_random.value) ro from ptrain_questions where unitid="+unitid+" and kindid = "+postid+" and usesign=1"+hasAsk);
		sqlstr.append("select id,typeid,topic,option1,option2,option3,option4,option5,option6,option7,answer1 from (select id,typeid,topic,option1,option2,option3,option4,option5,option6,option7,answer1,row_number() over(order by dbms_random.value) ro from ptrain_questions where kindid = "+postid+" and usesign=1"+hasAsk);
		/*String typeidStr="";
		for(int i=0;i<typeListOper.size();i++){
			String typeid=typeListOper.get(i).getId(),amount=policyMap.get(typeid+"_AMOUNT")+"";
			if(i>0){
				typeidStr+=",";
				reqSql.append(" or ");
			}
			typeidStr+=typeid;
			reqSql.append("(typeid = "+typeid+" and ro <= "+amount+")");
		}
		sqlstr.append(" and typeid in("+typeidStr+")");*/
		
		reqSql.append("(ro <= "+policyMap.get(postid+"_AMOUNT")+")");//改
		
		sqlstr.append(") where ("+reqSql.toString()+") order by typeid");
		sqlstr.append(")");
		iMap.put("sqlstr", sqlstr.toString());
		webAskitemDAO.insertPtrainAskitem(iMap);
	}

	public Map updatePtrainAskitem(Map map){
		Map resMap=new HashMap();
		String askitemid=func.Trim(map.get("askitemid")+""),operuserid=func.Trim(map.get("operuserid")+"");
		String asknumTemp=func.Trim(map.get("asknum")+""),ansdetail=func.Trim(map.get("ansdetail")+""),
		rightans=func.Trim(map.get("rightans")+""),quescore=func.Trim(map.get("quescore")+"");
		//int asknum=Integer.valueOf(asknumTemp);
		PtrainAskitemBean ptrainAskitemBean=new PtrainAskitemBean();
		ptrainAskitemBean.setId(askitemid);
		ptrainAskitemBean.setAnsdetail(ansdetail.replace(",", ""));
		//ptrainAskitemBean.setAsknum((asknum+1)+"");
		String ansscore="0",ansright="0";
		if(ansdetail.equals(rightans)){
			//ansscore=PtrainConstant.getScore(asknum,Double.valueOf(quescore))+"";
			ansscore=quescore;
			ansright="1";
		}else if(ansdetail.length()>1&&rightans.length()==(ansdetail.replace(",", "").length())){
			int ansdetailNum=0;
			for(String ansdetailTemp:ansdetail.split(",")){
				if(rightans.indexOf(ansdetailTemp)>-1){
					ansdetailNum+=1;
				}
			}
			if(ansdetailNum==rightans.length()){
				//ansscore=PtrainConstant.getScore(asknum,Double.valueOf(quescore))+"";
				ansscore=quescore;
				ansright="1";
			}
		}
		ptrainAskitemBean.setAnsscore(ansscore);
		ptrainAskitemBean.setAnsright(ansright);
		ptrainAskitemBean.setAnsdate("sysdate");
		webAskitemDAO.updatePtrainAskitem(ptrainAskitemBean);
		resMap.put("ansscore", ansscore);
		resMap.put("ansright", ansright);
		return resMap;
	}


	public void savePtrainAskitemSkimExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainAskitemBean> dataList = this.webAskitemDAO.findPtrainAskitemListSkimExp(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expAskitemSkim(dataList,response);
	}

	public WebAskitemDAO getWebAskitemDAO() {
		return webAskitemDAO;
	}

	public void setWebAskitemDAO(WebAskitemDAO webAskitemDAO) {
		this.webAskitemDAO = webAskitemDAO;
	}

	
	
}
