package com.nomen.ntrain.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.web.service.WebQuestionsService;


/**
 * @description 莆田岗位培训_试题action
 * @author 林木�?
 * @date 2014-3-5
 */
@SuppressWarnings("all")
public class WebQuestionsAction extends WebAction{

	private WebQuestionsService		webQuestionsService; //莆田岗位培训_试题_试题业务接口
	private PtrainQuestionsBean		ptrainQuestionsBean;    //莆田岗位培训_试题_试题信息表
	private Map<String,String>			querymap;				//传参map
	private List						dataList;				//数据列表
	/**
	 * 莆田岗位培训_试题 详细信息
	 */
	public String setPtrainQuestionsWin() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			this.ptrainQuestionsBean=this.webQuestionsService.findPtrainQuestionsBeanById(this.ptrainQuestionsBean.getId());
			req.setAttribute("singleSel", PtrainConstant.singleSel);
			req.setAttribute("multSel", PtrainConstant.multSel);
			req.setAttribute("judgeSel", PtrainConstant.judgeSel);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public PtrainQuestionsBean getPtrainQuestionsBean() {
		return ptrainQuestionsBean;
	}
	public void setPtrainQuestionsBean(PtrainQuestionsBean ptrainQuestionsBean) {
		this.ptrainQuestionsBean = ptrainQuestionsBean;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	public WebQuestionsService getWebQuestionsService() {
		return webQuestionsService;
	}
	public void setWebQuestionsService(WebQuestionsService webQuestionsService) {
		this.webQuestionsService = webQuestionsService;
	}
	
	
	
}
