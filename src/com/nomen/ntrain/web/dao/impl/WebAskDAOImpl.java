package com.nomen.ntrain.web.dao.impl;

import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebAskDAO;
@SuppressWarnings("all")
public class WebAskDAOImpl extends NsoftBaseDao implements WebAskDAO {
	
	public PtrainAskBean findPtrainAskStat(Map map) {
		return (PtrainAskBean)this.getSqlMapClientTemplate().queryForObject("webAsk.findPtrainAskStat", map);
	}

	public String findPtrainAskNoAskid(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("webAsk.findPtrainAskNoAskid", map);
	}

	public PtrainAskBean findPtrainAskPushnum(Map map) {
		return (PtrainAskBean)this.getSqlMapClientTemplate().queryForObject("webAsk.findPtrainAskPushnum", map);
	}

	public String insertPtrainAsk(PtrainAskBean ptrainAskBean){
		return (String)this.getSqlMapClientTemplate().insert("webAsk.insertPtrainAsk", ptrainAskBean);
	}

	public void updatePtrainAsk(PtrainAskBean ptrainAskBean){
		this.getSqlMapClientTemplate().update("webAsk.updatePtrainAsk", ptrainAskBean);
	}

	public void deletePtrainAskById(String id){
		this.getSqlMapClientTemplate().delete("webAsk.deletePtrainAskById", id);
	}


}
