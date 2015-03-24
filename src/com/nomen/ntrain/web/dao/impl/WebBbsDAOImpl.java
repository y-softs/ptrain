package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebBbsDAO;
@SuppressWarnings("all")
public class WebBbsDAOImpl extends NsoftBaseDao implements WebBbsDAO {

	public PtrainBbsBean findPtrainBbsBeanById(String id){
		return (PtrainBbsBean)this.getSqlMapClientTemplate().queryForObject("webBbs.findPtrainBbsBeanById", id);
	}

	public String insertPtrainBbs(PtrainBbsBean ptrainBbsBean){
		return (String)this.getSqlMapClientTemplate().insert("webBbs.insertPtrainBbs", ptrainBbsBean);
	}

	public void updatePtrainBbs(PtrainBbsBean ptrainBbsBean){
		this.getSqlMapClientTemplate().update("webBbs.updatePtrainBbs", ptrainBbsBean);
	}

	public void deletePtrainBbsById(String id){
		this.getSqlMapClientTemplate().delete("webBbs.deletePtrainBbsById", id);
	}

	public List<PtrainBbsBean> findPtrainBbsChildList(Map map, int tagpage, int record) {
		map.put("total", this.getObjectTotal("webBbs.findPtrainBbsChildList", map));
		return (List<PtrainBbsBean>)this.getSqlMapClientTemplate().queryForList("webBbs.findPtrainBbsChildList", map,tagpage,record);
	}

	public List<PtrainBbsBean> findPtrainBbsList(Map map, int tagpage, int record) {
		map.put("total", this.getObjectTotal("webBbs.findPtrainBbsList", map));
		return (List<PtrainBbsBean>)this.getSqlMapClientTemplate().queryForList("webBbs.findPtrainBbsList",map,tagpage,record);
	}

	public String findPtrainBbsChildCount(Map map) {
		return (String)map.put("total", this.getObjectTotal("webBbs.findPtrainBbsList", map));
	}

	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean) {
		return (String)this.getSqlMapClientTemplate().insert("webBbs.insertPtrainBbsByJq",ptrainBbsBean);
	}

	public String insertPtrainNicelog(String value) {
		return (String)this.getSqlMapClientTemplate().insert("webBbs.insertPtrainNicelog",value);
	}

	public PtrainBbsBean findPtrainBbsBeanByMap(Map map) {
		return (PtrainBbsBean)this.getSqlMapClientTemplate().queryForObject("webBbs.findPtrainBbsBeanByMap",map);
	}

	public void updateDynamicSQL(String usql) {
		this.getSqlMapClientTemplate().update("webBbs.updateDynamicSQL",usql);
	}

	public void deletePtrainNicelogByBbsId(String id) {
		this.getSqlMapClientTemplate().delete("webBbs.deletePtrainNicelogByBbsId",id);
	}
}
