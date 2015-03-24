package com.nomen.ntrain.web.service.impl;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.web.dao.WebQuestionsDAO;
import com.nomen.ntrain.web.service.WebQuestionsService;
@SuppressWarnings("all")
public class WebQuestionsServiceImpl extends BaseServiceImpl implements WebQuestionsService {

	private WebQuestionsDAO webQuestionsDAO;

	public PtrainQuestionsBean findPtrainQuestionsBeanById(String id){
		return webQuestionsDAO.findPtrainQuestionsBeanById(id);
	}


	//Get和Set方法
	public WebQuestionsDAO getWebQuestionsDAO() {
		return webQuestionsDAO;
	}

	public void setWebQuestionsDAO(WebQuestionsDAO webQuestionsDAO) {
		this.webQuestionsDAO = webQuestionsDAO;
	}

	
}
