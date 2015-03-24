package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainParamBean;
import com.nomen.ntrain.ptrain.dao.PtrainParamDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainParamDAOImpl extends NsoftBaseDao implements PtrainParamDAO {

	public List<PtrainParamBean> findPtrainParamList(Map map){
		return (List<PtrainParamBean>)this.getSqlMapClientTemplate().queryForList("PtrainParam.findPtrainParamList", map);
	}

	public PtrainParamBean findPtrainParamBeanById(String id){
		return (PtrainParamBean)this.getSqlMapClientTemplate().queryForObject("PtrainParam.findPtrainParamBeanById", id);
	}

	public String insertPtrainParam(PtrainParamBean ptrainParamBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainParam.insertPtrainParam", ptrainParamBean);
	}

	public void updatePtrainParam(PtrainParamBean ptrainParamBean){
		this.getSqlMapClientTemplate().update("PtrainParam.updatePtrainParam", ptrainParamBean);
	}

	public void deletePtrainParamById(String id){
		this.getSqlMapClientTemplate().delete("PtrainParam.deletePtrainParamById", id);
	}

}
