package com.nomen.ntrain.ptrain.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;
import com.nomen.ntrain.ptrain.dao.PtrainVotesrcDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainVotesrcDAOImpl extends NsoftBaseDao implements PtrainVotesrcDAO {

	public List<PtrainVotesrcBean> findPtrainVotesrcList(String appId) {
		Map map = new HashMap();
		map.put("appid",appId);
		return (List<PtrainVotesrcBean>)this.getSqlMapClientTemplate().queryForList("PtrainVotesrc.findPtrainVotesrcList", map);
	}

	public List<PtrainVotesrcBean> findPtrainVotesrcListByPage(Map map,
			int page, int record) {
		map.put("total", this.getObjectTotal("PtrainVotesrc.findPtrainVotesrcList", map));
		return (List<PtrainVotesrcBean>)this.getSqlMapClientTemplate().queryForList("PtrainVotesrc.findPtrainVotesrcList", map, page, record);
	}
	
	public void updatePtrainVoteSrcVoteCount(String id) {
		this.getSqlMapClientTemplate().update("PtrainVotesrc.updatePtrainVoteSrcVoteCount",id);
	}
	
	public PtrainVotesrcBean findPtrainVotesrcBean(String pkid) {
		return (PtrainVotesrcBean) this.getSqlMapClientTemplate().queryForObject("PtrainVotesrc.findPtrainVotesrcBean", pkid);
	}
	
	public String insertPtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean) {
		return (String) this.getSqlMapClientTemplate().insert("PtrainVotesrc.insertPtrainVotesrcBean", ptrainVotesrcBean);
	}
	
	public void updatePtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean) {
		this.getSqlMapClientTemplate().update("PtrainVotesrc.updatePtrainVotesrcBean", ptrainVotesrcBean);
	}

	public String findPtrainVotesrcNextSortnum(String appid) {
		return (String) this.getSqlMapClientTemplate().queryForObject("PtrainVotesrc.findPtrainVotesrcNextSortnum", appid);
	}

	public void deletePtrainVotesrcBean(String idstr) {
		this.getSqlMapClientTemplate().delete("PtrainVotesrc.deletePtrainVotesrcBean", idstr);
	}
	
}
