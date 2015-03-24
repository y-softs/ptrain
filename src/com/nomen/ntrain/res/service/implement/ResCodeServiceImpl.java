package com.nomen.ntrain.res.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.res.dao.ResCodeDAO;
import com.nomen.ntrain.res.service.ResCodeService;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFunc;

public class ResCodeServiceImpl extends BaseServiceImpl implements ResCodeService {
	private ResCodeDAO resCodeDAO;


	public List findResCodeList(Map param) {
		return this.resCodeDAO.findResCodeList(param);
	}


	public ResCodeDAO getResCodeDAO() {
		return resCodeDAO;
	}


	public void setResCodeDAO(ResCodeDAO resCodeDAO) {
		this.resCodeDAO = resCodeDAO;
	}
	
	
}
