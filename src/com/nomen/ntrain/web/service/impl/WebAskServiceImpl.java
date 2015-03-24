package com.nomen.ntrain.web.service.impl;

import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.web.dao.WebAskDAO;
import com.nomen.ntrain.web.service.WebAskService;
@SuppressWarnings("all")
public class WebAskServiceImpl extends BaseServiceImpl implements WebAskService {

	private WebAskDAO webAskDAO;


	public PtrainAskBean findPtrainAskStat(Map map) {
		return webAskDAO.findPtrainAskStat(map);
	}

	public String findPtrainAskNoAskid(Map map) {
		return webAskDAO.findPtrainAskNoAskid(map);
	}

	public PtrainAskBean findPtrainAskPushnum(Map map) {
		return webAskDAO.findPtrainAskPushnum(map);
	}
	public void savePtrainAsk(PtrainAskBean ptrainAskBean){
		if(func.IsEmpty(func.Trim(ptrainAskBean.getId()))){
			webAskDAO.insertPtrainAsk(ptrainAskBean);
		}else{
			webAskDAO.updatePtrainAsk(ptrainAskBean);
		}
	}

	public void deletePtrainAskById(String id){
		webAskDAO.deletePtrainAskById(id);
	}

	public WebAskDAO getWebAskDAO() {
		return webAskDAO;
	}

	public void setWebAskDAO(WebAskDAO webAskDAO) {
		this.webAskDAO = webAskDAO;
	}

	

}
