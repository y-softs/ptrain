package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebAskitemDAO;
@SuppressWarnings("all")
public class WebAskitemDAOImpl extends NsoftBaseDao implements WebAskitemDAO {

	public List<PtrainAskitemBean> findPtrainAskitemList(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("webAskitem.findPtrainAskitemList", map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListQue(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("webAskitem.findPtrainAskitemListQue", map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListSkim(Map map,int page,int record){
		map.put("total", this.getObjectTotal("webAskitem.findPtrainAskitemListSkim", map));
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("webAskitem.findPtrainAskitemListSkim", map, page, record);
	}
	
	public List<PtrainAskitemBean> findPtrainAskitemListSkimExp(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("webAskitem.findPtrainAskitemListSkimExp", map);
	}

	public PtrainAskitemBean findPtrainAskitemBeanByMap(Map map){
		return (PtrainAskitemBean)this.getSqlMapClientTemplate().queryForObject("webAskitem.findPtrainAskitemBeanByMap", map);
	}

	public void insertPtrainAskitem(Map map){
		this.getSqlMapClientTemplate().insert("webAskitem.insertPtrainAskitem", map);
	}

	public void updatePtrainAskitem(PtrainAskitemBean ptrainAskitemBean){
		this.getSqlMapClientTemplate().update("webAskitem.updatePtrainAskitem", ptrainAskitemBean);
	}

}
