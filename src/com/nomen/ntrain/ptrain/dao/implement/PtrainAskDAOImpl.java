package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainAskBean;
import com.nomen.ntrain.ptrain.dao.PtrainAskDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainAskDAOImpl extends NsoftBaseDao implements PtrainAskDAO {
	
	public PtrainAskBean findPtrainAskStat(Map map) {
		return (PtrainAskBean)this.getSqlMapClientTemplate().queryForObject("PtrainAsk.findPtrainAskStat", map);
	}

	public String findPtrainAskNoAskid(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainAsk.findPtrainAskNoAskid", map);
	}

	public PtrainAskBean findPtrainAskPushnum(Map map) {
		return (PtrainAskBean)this.getSqlMapClientTemplate().queryForObject("PtrainAsk.findPtrainAskPushnum", map);
	}

	public List<PtrainAskBean> findPtrainAskSubday(Map map) {
		return (List<PtrainAskBean>)this.getSqlMapClientTemplate().queryForList("PtrainAsk.findPtrainAskSubday", map);
	}

	public PtrainAskBean findPtrainAskBeanById(String id){
		return (PtrainAskBean)this.getSqlMapClientTemplate().queryForObject("PtrainAsk.findPtrainAskBeanById", id);
	}

	public String insertPtrainAsk(PtrainAskBean ptrainAskBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainAsk.insertPtrainAsk", ptrainAskBean);
	}

	public void updatePtrainAsk(PtrainAskBean ptrainAskBean){
		this.getSqlMapClientTemplate().update("PtrainAsk.updatePtrainAsk", ptrainAskBean);
	}

	public void deletePtrainAskById(String id){
		this.getSqlMapClientTemplate().delete("PtrainAsk.deletePtrainAskById", id);
	}

	public List<Map> findPtrainAskAppStat(Map map) {
		return (List<Map>)this.getSqlMapClientTemplate().queryForList("PtrainAsk.findPtrainAskAppStat", map);
	}

	public List<Map> findPtrainAskAppStatSpec(Map map) {
		return (List<Map>)this.getSqlMapClientTemplate().queryForList("PtrainAsk.findPtrainAskAppStatSpec", map);
	}

	public List<Map> findPtrainAskUser(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("PtrainAsk.findPtrainAskUser", map));
		return (List<Map>)this.getSqlMapClientTemplate().queryForList("PtrainAsk.findPtrainAskUser", map, page, record);
	}

	public List<Map> findPtrainAskUser(Map map) {
		return (List<Map>)this.getSqlMapClientTemplate().queryForList("PtrainAsk.findPtrainAskUser", map);
	}

}
