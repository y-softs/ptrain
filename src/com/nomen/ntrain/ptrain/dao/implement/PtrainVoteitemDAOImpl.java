package com.nomen.ntrain.ptrain.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainVoteitemBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteitemDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
public class PtrainVoteitemDAOImpl extends NsoftBaseDao implements PtrainVoteitemDAO {

	public void insertPtrainVoteitemBean(PtrainVoteitemBean bean) {
		this.getSqlMapClientTemplate().insert("PtrainVoteitem.insertPtrainVoteitemBean", bean);
	}

	public int findPtrainVoteitemCount(String appId, String clientIp) {
		Map map = new HashMap();
		map.put("appid",appId);
		map.put("clientip",clientIp);
		return Integer.parseInt((String)this.getSqlMapClientTemplate().queryForObject("PtrainVoteitem.findPtrainVoteitemCount", map));
	}

	public int findPtrainVoteitemCountBySrcId(String srcId, String clientIp) {
		Map map = new HashMap();
		map.put("srcid",srcId);
		map.put("clientip",clientIp);
		return Integer.parseInt((String)this.getSqlMapClientTemplate().queryForObject("PtrainVoteitem.findPtrainVoteitemCountBySrcId", map));
	}
	
	public List<String> findPtrainVotesrcIdList(String appId, String clientIp) {
		Map map = new HashMap();
		map.put("appid",appId);
		map.put("clientip",clientIp);
		return this.getSqlMapClientTemplate().queryForList("PtrainVoteitem.findPtrainVotesrcIdList",map);
	}
	
}
