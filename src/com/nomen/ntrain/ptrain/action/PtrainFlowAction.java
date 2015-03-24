package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainCoursFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainContentFlowStaEnum;
import com.nomen.ntrain.ptrain.service.PtrainFlowService;
import com.nomen.ntrain.util.CheckSignConstant;

/**
 * @description 莆田岗位学习_审核流程action层
 * @author 林木山
 * @date 2014-3-14
 */
@SuppressWarnings("all")
public class PtrainFlowAction extends PtrainAction{

	private PtrainFlowService		ptrainFlowService;  //莆田岗位学习_审核流程业务接口
	private PtrainFlowBean			ptrainFlowBean;     //莆田岗位学习_审核流程信息表
	private Map<String,String>		querymap;			//传参map
	private List					dataList;			//数据列表

	public String toForwardPtrainflow(){
		String modsign = this.ptrainFlowBean.getModsign();
		String recid   = this.ptrainFlowBean.getRecid();
		if(func.IsEmpty(modsign) || func.IsEmpty(recid)){
			return SUCCESS;
		}
		
		HttpServletRequest req = ServletActionContext.getRequest();

		//流程枚举列表
		if(modsign.equals(PtrainFlowModeSignEnum.TRAINREQ.getKey()+"")){
			req.setAttribute("flowStaEnums", PtrainReqFlowStaEnum.values());
		}else if(modsign.equals(PtrainFlowModeSignEnum.TRAINCOURS.getKey()+"")){
			req.setAttribute("flowStaEnums", PtrainCoursFlowStaEnum.values());
		}else if(modsign.equals(PtrainFlowModeSignEnum.TRAINCONT.getKey()+"")){
			req.setAttribute("flowStaEnums", PtrainContentFlowStaEnum.values());
		}
		//操作描述
		req.setAttribute("chksignMap", CheckSignConstant._Constant_ChkMap());
		return SUCCESS;
	}
	/**
	 * @description列表信息
	 */
	public void listPtrainFlow(){
		String modsign = this.ptrainFlowBean.getModsign();
		String recid   = this.ptrainFlowBean.getRecid();
		Map map=new HashMap();
		map.put("modsign", modsign);
		map.put("recid", recid);
		this.dataList = this.ptrainFlowService.findPtrainFlowList(map);
		this.print(JSONArray.fromObject(dataList).toString());
	}

	//Get和Set方法
	public PtrainFlowBean getPtrainFlowBean() {
		return ptrainFlowBean;
	}
	public void setPtrainFlowBean(PtrainFlowBean ptrainFlowBean) {
		this.ptrainFlowBean = ptrainFlowBean;
	}
	public PtrainFlowService getPtrainFlowService() {
		return ptrainFlowService;
	}
	public void setPtrainFlowService(PtrainFlowService ptrainFlowService) {
		this.ptrainFlowService = ptrainFlowService;
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
