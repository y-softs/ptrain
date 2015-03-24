package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteappDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainVoteappDAOImpl extends NsoftBaseDao implements PtrainVoteappDAO {

	public List<PtrainVoteappBean> findPtrainVoteappList(Map map){
		return (List<PtrainVoteappBean>)this.getSqlMapClientTemplate().queryForList("PtrainVoteapp.findPtrainVoteappList", map);
	}

	public PtrainVoteappBean findPtrainVoteappBeanById(String id){
		return (PtrainVoteappBean)this.getSqlMapClientTemplate().queryForObject("PtrainVoteapp.findPtrainVoteappBeanById", id);
	}

	public String insertPtrainVoteapp(PtrainVoteappBean ptrainVoteappBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainVoteapp.insertPtrainVoteapp", ptrainVoteappBean);
	}

	public void updatePtrainVoteapp(PtrainVoteappBean ptrainVoteappBean){
		this.getSqlMapClientTemplate().update("PtrainVoteapp.updatePtrainVoteapp", ptrainVoteappBean);
	}

	public void deletePtrainVoteappById(String id){
		this.getSqlMapClientTemplate().delete("PtrainVoteapp.deletePtrainVoteappById", id);
	}

	public void updatePtrainVoteappDefvote(PtrainVoteappBean ptrainVoteappBean) {
		this.getSqlMapClientTemplate().delete("PtrainVoteapp.updatePtrainVoteappDefvote", ptrainVoteappBean);
	}

	public PtrainVoteappBean findDefaultPtrainVoteappBean() {
		return (PtrainVoteappBean)this.getSqlMapClientTemplate().queryForObject("PtrainVoteapp.findDefaultPtrainVoteappBean", null);
	}

}
