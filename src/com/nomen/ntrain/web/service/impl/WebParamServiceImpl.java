package com.nomen.ntrain.web.service.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainParamBean;
import com.nomen.ntrain.web.dao.WebParamDAO;
import com.nomen.ntrain.web.service.WebParamService;
@SuppressWarnings("all")
public class WebParamServiceImpl extends BaseServiceImpl implements WebParamService {

	private WebParamDAO webParamDAO;

	public List<PtrainParamBean> findPtrainParamList(Map map){
		return webParamDAO.findPtrainParamList(map);
	}

	public PtrainParamBean findPtrainParamBeanById(String id){
		return webParamDAO.findPtrainParamBeanById(id);
	}

	public void savePtrainParam(PtrainParamBean ptrainParamBean){
		if(func.IsEmpty(func.Trim(ptrainParamBean.getId()))){
			webParamDAO.insertPtrainParam(ptrainParamBean);
		}else{
			webParamDAO.updatePtrainParam(ptrainParamBean);
		}
	}

	public void deletePtrainParamById(String id){
		webParamDAO.deletePtrainParamById(id);
	}

	//Get和Set方法
	public WebParamDAO getWebParamDAO() {
		return webParamDAO;
	}

	public void setWebParamDAO(WebParamDAO webParamDAO) {
		this.webParamDAO = webParamDAO;
	}


	
}
