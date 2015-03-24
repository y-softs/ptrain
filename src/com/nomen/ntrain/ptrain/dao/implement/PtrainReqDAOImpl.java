package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.dao.PtrainReqDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainReqDAOImpl extends NsoftBaseDao implements PtrainReqDAO {

	public List<PtrainReqBean> findPtrainReqList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainReq.findPtrainReqList", map));
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("PtrainReq.findPtrainReqList", map, page, record);
	}

	public List<PtrainReqBean> findPtrainReqList(Map map){
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("PtrainReq.findPtrainReqList", map);
	}

	public PtrainReqBean findPtrainReqBeanById(String id){
		return (PtrainReqBean)this.getSqlMapClientTemplate().queryForObject("PtrainReq.findPtrainReqBeanById", id);
	}

	public PtrainReqBean findPtrainReqBeanByMap(Map map){
		return (PtrainReqBean)this.getSqlMapClientTemplate().queryForObject("PtrainReq.findPtrainReqBeanByMap", map);
	}

	public String insertPtrainReq(PtrainReqBean ptrainReqBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainReq.insertPtrainReq", ptrainReqBean);
	}

	public void updatePtrainReq(PtrainReqBean ptrainReqBean){
		this.getSqlMapClientTemplate().update("PtrainReq.updatePtrainReq", ptrainReqBean);
	}

	public void deletePtrainReqById(String id){
		this.getSqlMapClientTemplate().delete("PtrainReq.deletePtrainReqById", id);
	}

	public void deletePtrainReqByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainReq.deletePtrainReqByMap", map);
	}

	public String insertPtrainReqByCopy(PtrainReqBean ptrainReqBean) {
		return (String)this.getSqlMapClientTemplate().insert("PtrainReq.insertPtrainReqByCopy", ptrainReqBean);
	}

}
