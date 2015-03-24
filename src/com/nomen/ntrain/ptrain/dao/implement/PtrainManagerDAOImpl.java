package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainManagerBean;
import com.nomen.ntrain.ptrain.dao.PtrainManagerDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainManagerDAOImpl extends NsoftBaseDao implements PtrainManagerDAO {

	public List<PtrainManagerBean> findPtrainManagerList(Map map){
		return (List<PtrainManagerBean>)this.getSqlMapClientTemplate().queryForList("PtrainManager.findPtrainManagerList", map);
	}

	public PtrainManagerBean findPtrainManagerBeanById(String id){
		return (PtrainManagerBean)this.getSqlMapClientTemplate().queryForObject("PtrainManager.findPtrainManagerBeanById", id);
	}

	public String insertPtrainManager(PtrainManagerBean ptrainManagerBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainManager.insertPtrainManager", ptrainManagerBean);
	}

	public void updatePtrainManager(PtrainManagerBean ptrainManagerBean){
		this.getSqlMapClientTemplate().update("PtrainManager.updatePtrainManager", ptrainManagerBean);
	}

	public void deletePtrainManagerById(String id){
		this.getSqlMapClientTemplate().delete("PtrainManager.deletePtrainManagerById", id);
	}

	public List findDataUserListHas(Map map) {
		return (List)this.getSqlMapClientTemplate().queryForList("PtrainManager.findDataUserListHas", map);
	}

}
