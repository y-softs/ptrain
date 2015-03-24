package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteipconfigBean;
import com.nomen.ntrain.ptrain.service.PtrainVoteappService;
import com.nomen.ntrain.ptrain.service.PtrainVoteipconfigService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 投票_合法IP段
 * @author 林木山
 * @date 2014-12-15
 */
@SuppressWarnings("all")
public class PtrainVoteipconfigAction extends PtrainAction{

	private PtrainVoteipconfigService	ptrainVoteipconfigService;  	//投票_合法IP段业务接口
	private PtrainVoteappService	    ptrainVoteappService;  	        //投票_批次业务接口
	private PtrainVoteipconfigBean		ptrainVoteipconfigBean;     	//投票_合法IP段信息表
	private Map<String,String>			querymap;				//传参map
	private List						dataList;				//数据列表

	/**
	 * @description列表信息
	 */
	public String listPtrainVoteipconfig() {
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
	 * @description列表信息
	 */
	public String listPtrainVoteipconfigByJq() {
		try {
			String appid = func.Trim(this.querymap.get("appid"));
			//数据列表
			Map map = new HashMap();
			map.put("appid",appid);
			this.dataList=this.ptrainVoteipconfigService.findPtrainVoteipconfigList(map);
			this.printList(this.dataList);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}

	/**
	 * @description新增、修改跳转
	 */
	public String setPtrainVoteipconfig() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			String appid = func.Trim(querymap.get("appid"));
			if(!func.IsEmpty(func.Trim(this.ptrainVoteipconfigBean.getId()))) {
				//修改
				this.ptrainVoteipconfigBean = this.ptrainVoteipconfigService.findPtrainVoteipconfigBeanById(this.ptrainVoteipconfigBean.getId());
			}else{
				this.ptrainVoteipconfigBean = new PtrainVoteipconfigBean();
				this.ptrainVoteipconfigBean.setAppid(appid);
			}
			//查询批次列表
			Map map = new HashMap();
			req.setAttribute("appList", this.ptrainVoteappService.findPtrainVoteappList(map));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description新增/修改[保存]
	 */
	public String savePtrainVoteipconfig() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainVoteipconfigService.savePtrainVoteipconfigBean(this.ptrainVoteipconfigBean);
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainVoteipconfigBean = new PtrainVoteipconfigBean();
				this.setPtrainVoteipconfig();
				this.reloadParentPage2();
			} else {
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainVoteipconfig();
		}
		return rValue;
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainVoteipconfigByJq() {
		try {
			this.ptrainVoteipconfigService.deletePtrainVoteipconfigBean(this.ptrainVoteipconfigBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}

	//Get和Set方法
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

	public PtrainVoteipconfigBean getPtrainVoteipconfigBean() {
		return ptrainVoteipconfigBean;
	}

	public void setPtrainVoteipconfigBean(
			PtrainVoteipconfigBean ptrainVoteipconfigBean) {
		this.ptrainVoteipconfigBean = ptrainVoteipconfigBean;
	}

	public PtrainVoteipconfigService getPtrainVoteipconfigService() {
		return ptrainVoteipconfigService;
	}

	public void setPtrainVoteipconfigService(
			PtrainVoteipconfigService ptrainVoteipconfigService) {
		this.ptrainVoteipconfigService = ptrainVoteipconfigService;
	}

	public PtrainVoteappService getPtrainVoteappService() {
		return ptrainVoteappService;
	}

	public void setPtrainVoteappService(PtrainVoteappService ptrainVoteappService) {
		this.ptrainVoteappService = ptrainVoteappService;
	}
}