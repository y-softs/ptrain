package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.ptrain.dao.PtrainPolicyDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainPolicyDAOImpl extends NsoftBaseDao implements PtrainPolicyDAO {

	public List<PtrainPolicyBean> findPtrainPolicyList(Map map){
		return (List<PtrainPolicyBean>)this.getSqlMapClientTemplate().queryForList("PtrainPolicy.findPtrainPolicyList", map);
	}

	public PtrainPolicyBean findPtrainPolicyBeanById(String id){
		return (PtrainPolicyBean)this.getSqlMapClientTemplate().queryForObject("PtrainPolicy.findPtrainPolicyBeanById", id);
	}

	public String insertPtrainPolicy(PtrainPolicyBean ptrainPolicyBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainPolicy.insertPtrainPolicy", ptrainPolicyBean);
	}

	public void updatePtrainPolicy(PtrainPolicyBean ptrainPolicyBean){
		this.getSqlMapClientTemplate().update("PtrainPolicy.updatePtrainPolicy", ptrainPolicyBean);
	}

	public void deletePtrainPolicyById(String id){
		this.getSqlMapClientTemplate().delete("PtrainPolicy.deletePtrainPolicyById", id);
	}

	public void deletePtrainPolicyByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainPolicy.deletePtrainPolicyByMap", map);
	}

}
