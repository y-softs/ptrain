package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteitemBean;
import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.ptrain.service.PtrainReqtempService;
import com.nomen.ntrain.ptrain.service.PtrainVoteappService;
import com.nomen.ntrain.ptrain.service.PtrainVotesrcService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_投票管理
 * @author 
 * @date 2014-12-15
 */
@SuppressWarnings("all")
public class PtrainVotesrcAction extends PtrainAction{
	
	private PtrainVotesrcService	ptrainVotesrcService;	//莆田岗位培训_投票管理业务接口
	private PtrainVoteappService	ptrainVoteappService;  	//投票_批次业务接口
	private LoginService 			loginService;      	//登录信息业务处理service
	private PtrainVotesrcBean		ptrainVotesrcBean;    //莆田岗位培训_投票管理
	private PtrainVoteitemBean		ptrainVoteitemBean;  //投票_投票详细
	private Map<String,String>		querymap;			//传参map
	private List					dataList;			//数据列表
	
	/**
	 * 跳转到人员列表页面
	 */
	public String toForwardVotesrc(){
		HttpServletRequest req = ServletActionContext.getRequest();
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
		}
		//查询默认投票批次并获取到appid
		PtrainVoteappBean appBean = this.ptrainVoteappService.findDefaultPtrainVoteappBean();
		String appId = "-1";//在列表页面中要判断appId是否为-1 ，若是则提示批次为设置
		if(appBean != null){
			appId = appBean.getId();
		}
		querymap.put("appid", appId);
		//查询批次列表
		req.setAttribute("appList", this.ptrainVoteappService.findPtrainVoteappList(new HashMap()));
		return SUCCESS;
	}
	
	/**
	 * 资源_投票管理文件列表页面
	 * @return
	 */
	public String listPtrainVotesrcByJq(){
		//获取资源文件列表
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			String appid = func.Trim(querymap.get("appid"));
			Map map = new HashMap();
			//数据列表
			map.put("appid", appid);
			map.put("fields", this.getFields());
			map.put("keyword", this.getKeyword());
			this.dataList = this.ptrainVotesrcService.findPtrainVotesrcListByPage(map, func.Cint(this.getTagpage()), func.Cint(this.getRecord()));
			this.print(this.creItemListPage(dataList,String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跳转到资源_投票管理文件页面
	 * @return
	 */
	public String setPtrainVotesrc(){
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//获取资源文件列表
			String votesrcid = func.Trim(this.ptrainVotesrcBean.getId());
			String appid = func.Trim(this.querymap.get("appid"));  //获取批次号
			if(func.IsEmpty(votesrcid)){
				//查询排序号
				String sortnum = this.ptrainVotesrcService.findPtrainVotesrcNextSortnum(appid);
				this.ptrainVotesrcBean = new PtrainVotesrcBean();
				this.ptrainVotesrcBean.setSortnum(sortnum);
				this.ptrainVotesrcBean.setAppid(appid);
				this.ptrainVotesrcBean.setSrcsign("0");//默认PPT
			}else{
				this.ptrainVotesrcBean = this.ptrainVotesrcService.findPtrainVotesrcBean(votesrcid);
			}
			//查询批次列表
			Map map = new HashMap();
			req.setAttribute("appList", this.ptrainVoteappService.findPtrainVoteappList(map));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 资源_投票管理保存
	 * @return
	 */
	public String savePtrainVotesrc(){
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainVotesrcBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainVotesrcBean.getId())))
					this.ptrainVotesrcBean.setEstauser(loginBean.getId());
				this.ptrainVotesrcService.savePtrainVotesrcBean(ptrainVotesrcBean);
			}
			if(func.Trim(this.gosign).equals("1")) {
				ptrainVotesrcBean = new PtrainVotesrcBean();
				this.setPtrainVotesrc();
				this.reloadParentPage2();
			}else{
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setPtrainVotesrc();
		}
		return rValue;
	}
	
	/**
	 * 资源_投票管理保存 [删除]
	 */
	public void deletePtrainVotesrcByJq() {
		try {
			this.ptrainVotesrcService.deletePtrainVotesrcBean(this.ptrainVotesrcBean.getId());
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	
	//生成get,set方法
	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PtrainVoteitemBean getPtrainVoteitemBean() {
		return ptrainVoteitemBean;
	}

	public void setPtrainVoteitemBean(PtrainVoteitemBean ptrainVoteitemBean) {
		this.ptrainVoteitemBean = ptrainVoteitemBean;
	}

	public PtrainVotesrcBean getPtrainVotesrcBean() {
		return ptrainVotesrcBean;
	}

	public void setPtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean) {
		this.ptrainVotesrcBean = ptrainVotesrcBean;
	}

	public PtrainVotesrcService getPtrainVotesrcService() {
		return ptrainVotesrcService;
	}

	public void setPtrainVotesrcService(PtrainVotesrcService ptrainVotesrcService) {
		this.ptrainVotesrcService = ptrainVotesrcService;
	}

	public Map<String, String> getQuerymap() {
		return querymap;
	}

	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}

	public PtrainVoteappService getPtrainVoteappService() {
		return ptrainVoteappService;
	}

	public void setPtrainVoteappService(PtrainVoteappService ptrainVoteappService) {
		this.ptrainVoteappService = ptrainVoteappService;
	}
	
}