package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqtempBean;
import com.nomen.ntrain.ptrain.dao.PtrainReqtempDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainReqtempDAOImpl extends NsoftBaseDao implements PtrainReqtempDAO {

	public List<PtrainReqtempBean> findPtrainReqtempList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainReqtemp.findPtrainReqtempList", map));
		return (List<PtrainReqtempBean>)this.getSqlMapClientTemplate().queryForList("PtrainReqtemp.findPtrainReqtempList", map, page, record);
	}

	public PtrainReqtempBean findPtrainReqtempBeanById(String id){
		return (PtrainReqtempBean)this.getSqlMapClientTemplate().queryForObject("PtrainReqtemp.findPtrainReqtempBeanById", id);
	}

	public String insertPtrainReqtemp(PtrainReqtempBean ptrainReqtempBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainReqtemp.insertPtrainReqtemp", ptrainReqtempBean);
	}

	public void updatePtrainReqtemp(PtrainReqtempBean ptrainReqtempBean){
		this.getSqlMapClientTemplate().update("PtrainReqtemp.updatePtrainReqtemp", ptrainReqtempBean);
	}

	public void deletePtrainReqtempById(String id){
		this.getSqlMapClientTemplate().delete("PtrainReqtemp.deletePtrainReqtempById", id);
	}

	public void deletePtrainReqtempByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainReqtemp.deletePtrainReqtempByMap", map);
	}

	public void updatePtrainReqtempSpecid(Map map) {
		this.getSqlMapClientTemplate().update("PtrainReqtemp.updatePtrainReqtempSpecid", map);
	}

	public void updatePtrainReqtempExc(Map map) {
		this.getSqlMapClientTemplate().update("PtrainReqtemp.updatePtrainReqtempExc", map);
	}

	public void insertPtrainReqExcel(Map map) {
		this.getSqlMapClientTemplate().insert("PtrainReqtemp.insertPtrainReqExcel", map);
	}

	public void updatePtrainReqtempDatasign(Map map) {
		this.getSqlMapClientTemplate().update("PtrainReqtemp.updatePtrainReqtempDatasign", map);
	}

}
