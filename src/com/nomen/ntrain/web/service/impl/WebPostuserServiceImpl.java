package com.nomen.ntrain.web.service.impl;

import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.web.dao.WebPostuserDAO;
import com.nomen.ntrain.web.service.WebPostuserService;
@SuppressWarnings("all")
public class WebPostuserServiceImpl extends BaseServiceImpl implements WebPostuserService {

	private WebPostuserDAO webPostuserDAO;

	public String findPtrainPostuserPostid(Map map) {
		return this.webPostuserDAO.findPtrainPostuserPostid(map);
	}
	
	//Get和Set方法	
	public WebPostuserDAO getWebPostuserDAO() {
		return webPostuserDAO;
	}

	public void setWebPostuserDAO(WebPostuserDAO webPostuserDAO) {
		this.webPostuserDAO = webPostuserDAO;
	}

	
}
