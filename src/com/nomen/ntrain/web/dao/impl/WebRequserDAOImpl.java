package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebRequserDAO;
@SuppressWarnings("all")
public class WebRequserDAOImpl extends NsoftBaseDao implements WebRequserDAO {

	public List<PtrainReqBean> findPtrainRequserList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("webRequser.findPtrainRequserList", map));
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("webRequser.findPtrainRequserList", map, page, record);
	}
	
	public List<PtrainReqBean> findPtrainRequserList(Map map){
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("webRequser.findPtrainRequserList", map);
	}

	public List<PtrainRequserBean> findPtrainRequserSignList(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("webRequser.findPtrainRequserSignList", map));
		return (List<PtrainRequserBean>)this.getSqlMapClientTemplate().queryForList("webRequser.findPtrainRequserSignList", map, page, record);
	}

	public List<PtrainRequserBean> findPtrainRequserSignList(Map map) {
		return (List<PtrainRequserBean>)this.getSqlMapClientTemplate().queryForList("webRequser.findPtrainRequserSignList", map);
	}

	public String insertPtrainRequser(PtrainRequserBean ptrainRequserBean){
		return (String)this.getSqlMapClientTemplate().insert("webRequser.insertPtrainRequser", ptrainRequserBean);
	}

	public void updatePtrainRequser(PtrainRequserBean ptrainRequserBean){
		this.getSqlMapClientTemplate().update("webRequser.updatePtrainRequser", ptrainRequserBean);
	}

	public void deletePtrainRequserById(String id){
		this.getSqlMapClientTemplate().delete("webRequser.deletePtrainRequserById", id);
	}
	public PtrainReqBean findPtrainReqBeanByMap(Map map){
		return (PtrainReqBean)this.getSqlMapClientTemplate().queryForObject("webRequser.findPtrainReqBeanByMap", map);
	}

	public List<PtrainReqBean> findPtrainReqList(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("webRequser.findPtrainReqList", map));
		return this.getSqlMapClientTemplate().queryForList("webRequser.findPtrainReqList",map, page, record);
	}

	public void updatePtrainReq(PtrainReqBean ptrainReqBean) {
		this.getSqlMapClientTemplate().update("webRequser.updatePtrainReq",ptrainReqBean);
	}

	public void deletePtrainReqById(String id) {
		this.getSqlMapClientTemplate().delete("webRequser.deletePtrainReqById",id);
	}

	public String insertPtrainReq(PtrainReqBean ptrainReqBean) {
		return (String)this.getSqlMapClientTemplate().insert("webRequser.insertPtrainReq",ptrainReqBean);
	}

	public PtrainReqBean findPtrainReqBeanById(String id) {
		return (PtrainReqBean)this.getSqlMapClientTemplate().queryForObject("webRequser.findPtrainReqBeanById",id);
	}

}
