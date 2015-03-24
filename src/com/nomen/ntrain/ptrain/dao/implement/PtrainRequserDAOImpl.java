package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.dao.PtrainRequserDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainRequserDAOImpl extends NsoftBaseDao implements PtrainRequserDAO {

	public List<PtrainReqBean> findPtrainRequserList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainRequser.findPtrainRequserList", map));
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("PtrainRequser.findPtrainRequserList", map, page, record);
	}
	
	public List<PtrainReqBean> findPtrainRequserList(Map map){
		return (List<PtrainReqBean>)this.getSqlMapClientTemplate().queryForList("PtrainRequser.findPtrainRequserList", map);
	}

	public List<PtrainRequserBean> findPtrainRequserSignList(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("PtrainRequser.findPtrainRequserSignList", map));
		return (List<PtrainRequserBean>)this.getSqlMapClientTemplate().queryForList("PtrainRequser.findPtrainRequserSignList", map, page, record);
	}

	public List<PtrainRequserBean> findPtrainRequserSignList(Map map) {
		return (List<PtrainRequserBean>)this.getSqlMapClientTemplate().queryForList("PtrainRequser.findPtrainRequserSignList", map);
	}

	public PtrainRequserBean findPtrainRequserBeanById(String id){
		return (PtrainRequserBean)this.getSqlMapClientTemplate().queryForObject("PtrainRequser.findPtrainRequserBeanById", id);
	}

	public String insertPtrainRequser(PtrainRequserBean ptrainRequserBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainRequser.insertPtrainRequser", ptrainRequserBean);
	}

	public void updatePtrainRequser(PtrainRequserBean ptrainRequserBean){
		this.getSqlMapClientTemplate().update("PtrainRequser.updatePtrainRequser", ptrainRequserBean);
	}

	public void deletePtrainRequserById(String id){
		this.getSqlMapClientTemplate().delete("PtrainRequser.deletePtrainRequserById", id);
	}

}
