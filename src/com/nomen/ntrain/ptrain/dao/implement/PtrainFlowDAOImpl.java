package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainFlowDAOImpl extends NsoftBaseDao implements PtrainFlowDAO {

	public List<PtrainFlowBean> findPtrainFlowList(Map map){
		return (List<PtrainFlowBean>)this.getSqlMapClientTemplate().queryForList("PtrainFlow.findPtrainFlowList", map);
	}

	public String insertPtrainFlow(PtrainFlowBean ptrainFlowBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainFlow.insertPtrainFlow", ptrainFlowBean);
	}

	public void deletePtrainFlowByMap(Map map){
		this.getSqlMapClientTemplate().delete("PtrainFlow.deletePtrainFlowByMap", map);
	}

}
