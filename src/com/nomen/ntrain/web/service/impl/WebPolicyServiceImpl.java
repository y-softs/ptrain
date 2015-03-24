package com.nomen.ntrain.web.service.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.web.dao.WebPolicyDAO;
import com.nomen.ntrain.web.service.WebPolicyService;
@SuppressWarnings("all")
public class WebPolicyServiceImpl extends BaseServiceImpl implements WebPolicyService {

	private WebPolicyDAO webPolicyDAO;

	public List<PtrainPolicyBean> findPtrainPolicyList(Map map){
		return webPolicyDAO.findPtrainPolicyList(map);
	}

	//Get和Set方法
	public WebPolicyDAO getWebPolicyDAO() {
		return webPolicyDAO;
	}

	public void setWebPolicyDAO(WebPolicyDAO webPolicyDAO) {
		this.webPolicyDAO = webPolicyDAO;
	}

	

	
}
