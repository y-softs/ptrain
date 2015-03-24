package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;
import com.nomen.ntrain.ptrain.dao.PtrainPorgDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainPorgDAOImpl extends NsoftBaseDao implements PtrainPorgDAO {

	public List<PtrainPorgBean> findPtrainPorgList(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("PtrainPorg.findPtrainPorgList", map));
		return (List<PtrainPorgBean>)this.getSqlMapClientTemplate().queryForList("PtrainPorg.findPtrainPorgList", map, page, record);
	}

	public List<PtrainPorgBean> findPtrainPorgList(Map map){
		return (List<PtrainPorgBean>)this.getSqlMapClientTemplate().queryForList("PtrainPorg.findPtrainPorgList", map);
	}

	public PtrainPorgBean findPtrainPorgBeanById(String id){
		return (PtrainPorgBean)this.getSqlMapClientTemplate().queryForObject("PtrainPorg.findPtrainPorgBeanById", id);
	}

	public String insertPtrainPorg(PtrainPorgBean ptrainPorgBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainPorg.insertPtrainPorg", ptrainPorgBean);
	}

	public void updatePtrainPorg(PtrainPorgBean ptrainPorgBean){
		this.getSqlMapClientTemplate().update("PtrainPorg.updatePtrainPorg", ptrainPorgBean);
	}

	public void deletePtrainPorgById(String id){
		this.getSqlMapClientTemplate().delete("PtrainPorg.deletePtrainPorgById", id);
	}

	public void deletePtrainPorgByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainPorg.deletePtrainPorgByMap", map);
	}

	public String findPtrainPorgSortnum(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainPorg.findPtrainPorgSortnum", map);
	}

}
