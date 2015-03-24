package com.nomen.ntrain.web.service.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.web.dao.WebCoursDAO;
import com.nomen.ntrain.web.service.WebCoursService;
@SuppressWarnings("all")
public class WebCoursServiceImpl extends BaseServiceImpl implements WebCoursService {
	private WebCoursDAO	webCoursDAO;
	public PtrainCoursBean findPtrainCoursBeanById(String id) {
		return this.webCoursDAO.findPtrainCoursBeanById(id);
	}

	public List<PtrainCoursBean> findPtrainCoursChilList(Map map) {
		return this.webCoursDAO.findPtrainCoursChilList(map);
	}

	public List<PtrainCoursBean> findPtrainCoursList(Map map, int page, int record) {
		return this.webCoursDAO.findPtrainCoursList(map, page, record);
	}

	
	
	
	
	public WebCoursDAO getWebCoursDAO() {
		return webCoursDAO;
	}

	public void setWebCoursDAO(WebCoursDAO webCoursDAO) {
		this.webCoursDAO = webCoursDAO;
	}

	
	
}
