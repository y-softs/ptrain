package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskitemBean;
import com.nomen.ntrain.ptrain.dao.PtrainAskitemDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainAskitemDAOImpl extends NsoftBaseDao implements PtrainAskitemDAO {

	public List<PtrainAskitemBean> findPtrainAskitemList(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("PtrainAskitem.findPtrainAskitemList", map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListQue(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("PtrainAskitem.findPtrainAskitemListQue", map);
	}

	public List<PtrainAskitemBean> findPtrainAskitemListSkim(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainAskitem.findPtrainAskitemListSkim", map));
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("PtrainAskitem.findPtrainAskitemListSkim", map, page, record);
	}
	
	public List<PtrainAskitemBean> findPtrainAskitemListSkimExp(Map map){
		return (List<PtrainAskitemBean>)this.getSqlMapClientTemplate().queryForList("PtrainAskitem.findPtrainAskitemListSkimExp", map);
	}

	public PtrainAskitemBean findPtrainAskitemBeanByMap(Map map){
		return (PtrainAskitemBean)this.getSqlMapClientTemplate().queryForObject("PtrainAskitem.findPtrainAskitemBeanByMap", map);
	}

	public void insertPtrainAskitem(Map map){
		this.getSqlMapClientTemplate().insert("PtrainAskitem.insertPtrainAskitem", map);
	}

	public void updatePtrainAskitem(PtrainAskitemBean ptrainAskitemBean){
		this.getSqlMapClientTemplate().update("PtrainAskitem.updatePtrainAskitem", ptrainAskitemBean);
	}

	public void deletePtrainAskitemById(String id){
		this.getSqlMapClientTemplate().delete("PtrainAskitem.deletePtrainAskitemById", id);
	}

}
