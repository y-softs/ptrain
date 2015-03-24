package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.dao.PtrainBbsDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainBbsDAOImpl extends NsoftBaseDao implements PtrainBbsDAO {

	public PtrainBbsBean findPtrainBbsBeanById(String id){
		return (PtrainBbsBean)this.getSqlMapClientTemplate().queryForObject("PtrainBbs.findPtrainBbsBeanById", id);
	}

	public String insertPtrainBbs(PtrainBbsBean ptrainBbsBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainBbs.insertPtrainBbs", ptrainBbsBean);
	}

	public void updatePtrainBbs(PtrainBbsBean ptrainBbsBean){
		this.getSqlMapClientTemplate().update("PtrainBbs.updatePtrainBbs", ptrainBbsBean);
	}

	public void deletePtrainBbsById(String id){
		this.getSqlMapClientTemplate().delete("PtrainBbs.deletePtrainBbsById", id);
	}

	public List<PtrainBbsBean> findPtrainBbsChildList(Map map, int tagpage, int record) {
		map.put("total", this.getObjectTotal("PtrainBbs.findPtrainBbsChildList", map));
		return (List<PtrainBbsBean>)this.getSqlMapClientTemplate().queryForList("PtrainBbs.findPtrainBbsChildList", map,tagpage,record);
	}

	public List<PtrainBbsBean> findPtrainBbsList(Map map, int tagpage, int record) {
		map.put("total", this.getObjectTotal("PtrainBbs.findPtrainBbsList", map));
		return (List<PtrainBbsBean>)this.getSqlMapClientTemplate().queryForList("PtrainBbs.findPtrainBbsList",map,tagpage,record);
	}

	public String findPtrainBbsChildCount(Map map) {
		return (String)map.put("total", this.getObjectTotal("PtrainBbs.findPtrainBbsList", map));
	}

	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean) {
		return (String)this.getSqlMapClientTemplate().insert("PtrainBbs.insertPtrainBbsByJq",ptrainBbsBean);
	}

	public String insertPtrainNicelog(String value) {
		return (String)this.getSqlMapClientTemplate().insert("PtrainBbs.insertPtrainNicelog",value);
	}

	public PtrainBbsBean findPtrainBbsBeanByMap(Map map) {
		return (PtrainBbsBean)this.getSqlMapClientTemplate().queryForObject("PtrainBbs.findPtrainBbsBeanByMap",map);
	}

	public void updateDynamicSQL(String usql) {
		this.getSqlMapClientTemplate().update("PtrainBbs.updateDynamicSQL",usql);
	}

	public void deletePtrainNicelogByBbsId(String id) {
		this.getSqlMapClientTemplate().delete("PtrainBbs.deletePtrainNicelogByBbsId",id);
	}

	public List<PtrainBbsBean> findPtrainAppStatiList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("PtrainBbs.findPtrainAppStatiList",map);
	}

	public List<PtrainBbsBean> findPtrainRankList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("PtrainBbs.findPtrainRankList",map);
	}

	public List<PtrainBbsBean> findPtrainModeList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("PtrainBbs.findPtrainModeList",map);
	}

}
