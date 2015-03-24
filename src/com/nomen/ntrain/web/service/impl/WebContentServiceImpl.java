package com.nomen.ntrain.web.service.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.web.dao.WebContentDAO;
import com.nomen.ntrain.web.service.WebContentService;
@SuppressWarnings("all")
public class WebContentServiceImpl extends BaseServiceImpl implements WebContentService {

	private WebContentDAO webContentDAO;
	public PtrainContentBean findPtrainContentBean(String id) {
		return this.webContentDAO.findPtrainContentBean(id);
	}

	public List<PtrainContentBean> findPtrainContentList(Map map, int tagpage, int record) {
		return this.webContentDAO.findPtrainContentList(map, tagpage, record);
	}

	public WebContentDAO getWebContentDAO() {
		return webContentDAO;
	}

	public void setWebContentDAO(WebContentDAO webContentDAO) {
		this.webContentDAO = webContentDAO;
	}

	
	
}
