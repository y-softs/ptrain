package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.bean.PtrainVoteipconfigBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteappDAO;
import com.nomen.ntrain.ptrain.dao.PtrainVoteipconfigDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainVoteipconfigDAOImpl extends NsoftBaseDao implements PtrainVoteipconfigDAO {

	public List<PtrainVoteipconfigBean> findPtrainVoteipconfigList(Map map){
		return (List<PtrainVoteipconfigBean>)this.getSqlMapClientTemplate().queryForList("PtrainVoteipconfig.findPtrainVoteipconfigList", map);
	}
	
	public PtrainVoteipconfigBean findPtrainVoteipconfigBeanById(String id) {
		return (PtrainVoteipconfigBean) this.getSqlMapClientTemplate().queryForObject("PtrainVoteipconfig.findPtrainVoteipconfigBeanById", id);
	}
	
	public List findPtrainVoteipconfigStrList(String appid){
		return (List<String>)this.getSqlMapClientTemplate().queryForList("PtrainVoteipconfig.findPtrainVoteipconfigStrList", appid);
	}
	
	public String insertPtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainVoteipconfig.insertPtrainVoteipconfigBean", ptrainVoteipconfigBean);
	}

	public void updatePtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean){
		this.getSqlMapClientTemplate().update("PtrainVoteipconfig.updatePtrainVoteipconfigBean", ptrainVoteipconfigBean);
	}

	public void deletePtrainVoteipconfigBean(String id){
		this.getSqlMapClientTemplate().delete("PtrainVoteipconfig.deletePtrainVoteipconfigBean", id);
	}
}
