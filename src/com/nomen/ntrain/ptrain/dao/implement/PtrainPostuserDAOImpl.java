package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;
import com.nomen.ntrain.ptrain.dao.PtrainPostuserDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainPostuserDAOImpl extends NsoftBaseDao implements PtrainPostuserDAO {

	public List<PtrainPostuserBean> findPtrainPostuserList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainPostuser.findPtrainPostuserList", map));
		return (List<PtrainPostuserBean>)this.getSqlMapClientTemplate().queryForList("PtrainPostuser.findPtrainPostuserList", map, page, record);
	}
	
	public List<PtrainPostuserBean> findPtrainPostuserList(Map map){
		return (List<PtrainPostuserBean>)this.getSqlMapClientTemplate().queryForList("PtrainPostuser.findPtrainPostuserList", map);
	}

	public PtrainPostuserBean findPtrainPostuserBeanById(String id){
		return (PtrainPostuserBean)this.getSqlMapClientTemplate().queryForObject("PtrainPostuser.findPtrainPostuserBeanById", id);
	}

	public String insertPtrainPostuser(PtrainPostuserBean ptrainPostuserBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainPostuser.insertPtrainPostuser", ptrainPostuserBean);
	}

	public void updatePtrainPostuser(PtrainPostuserBean ptrainPostuserBean){
		this.getSqlMapClientTemplate().update("PtrainPostuser.updatePtrainPostuser", ptrainPostuserBean);
	}

	public void deletePtrainPostuserById(String id){
		this.getSqlMapClientTemplate().delete("PtrainPostuser.deletePtrainPostuserById", id);
	}

	public void deletePtrainPostuserByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainPostuser.deletePtrainPostuserByMap", map);
	}

	public List findAllUseUser(Map map) {
		return (List)this.getSqlMapClientTemplate().queryForList("PtrainPostuser.findAllUseUser", map);
	}

	public void updatePtrainPostuserUserid(Map map) {
		this.getSqlMapClientTemplate().update("PtrainPostuser.updatePtrainPostuserUserid", map);
	}

	public void deletePtrainPostuserEmpty(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainPostuser.deletePtrainPostuserEmpty", map);
		
	}

	public void deletePtrainPostuserRepe(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainPostuser.deletePtrainPostuserRepe", map);
	}

	public String findPtrainPostuserPostid(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainPostuser.findPtrainPostuserPostid", map);
	}

}
