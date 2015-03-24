package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.service.PtrainVoteappService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 投票_批次action层
 * @author 林木山
 * @date 2014-12-15
 */
@SuppressWarnings("all")
public class PtrainVoteappAction extends PtrainAction{

	private PtrainVoteappService		ptrainVoteappService;  	//投票_批次业务接口
	private PtrainVoteappBean			ptrainVoteappBean;     	//投票_批次信息表
	private Map<String,String>			querymap;				//传参map
	private List						dataList;				//数据列表

	/**
	 * @description列表信息
	 */
	public String listPtrainVoteapp() {
		return SUCCESS;
	}
	/**
	 * @description列表信息
	 */
	public String listPtrainVoteappByJq() {
		try {
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
			}
			//数据列表
			Map map = new HashMap();
			map.put("sortfield","id desc");
			this.dataList=this.ptrainVoteappService.findPtrainVoteappList(map);
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
	public String setPtrainVoteapp() {
		try {
			if(!func.IsEmpty(func.Trim(this.ptrainVoteappBean.getId()))) {
				//修改
				this.ptrainVoteappBean = this.ptrainVoteappService.findPtrainVoteappBeanById(this.ptrainVoteappBean.getId());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}

	/**
	 * @description新增/修改[保存]
	 */
	public String savePtrainVoteapp() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				if(func.IsEmpty(this.ptrainVoteappBean.getId())&&func.IsEmpty(this.ptrainVoteappBean.getDefvote())){
					this.ptrainVoteappBean.setDefvote("0");
				} 
				this.ptrainVoteappService.savePtrainVoteapp(this.ptrainVoteappBean);
				if(func.Trim(this.ptrainVoteappBean.getDefvote()).equals("1")){
					//更新其他默认批次为非默认批次
					this.ptrainVoteappService.updatePtrainVoteappDefvote(this.ptrainVoteappBean);
				}
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainVoteappBean=new PtrainVoteappBean();
				this.setPtrainVoteapp();
				this.reloadParentPage2();
			} else {
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainVoteapp();
		}
		return rValue;
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainVoteappByJq() {
		try {
			this.ptrainVoteappService.deletePtrainVoteappById(this.ptrainVoteappBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}

	//Get和Set方法
	public PtrainVoteappBean getPtrainVoteappBean() {
		return ptrainVoteappBean;
	}
	public void setPtrainVoteappBean(PtrainVoteappBean ptrainVoteappBean) {
		this.ptrainVoteappBean = ptrainVoteappBean;
	}
	public PtrainVoteappService getPtrainVoteappService() {
		return ptrainVoteappService;
	}
	public void setPtrainVoteappService(PtrainVoteappService ptrainVoteappService) {
		this.ptrainVoteappService = ptrainVoteappService;
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
}