package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebContentDAO;
@SuppressWarnings("all")
public class WebContentDAOImpl extends NsoftBaseDao implements WebContentDAO {

	public PtrainContentBean findPtrainContentBean(String id) {
		return (PtrainContentBean)this.getSqlMapClientTemplate().queryForObject("webContent.findPtrainContentBean",id);
	}

	public List<PtrainContentBean> findPtrainContentList(Map map, int tagpage, int record) {
		map.put("total", this.getObjectTotal("webContent.findPtrainContentList", map));
		return (List<PtrainContentBean>)this.getSqlMapClientTemplate().queryForList("webContent.findPtrainContentList",map,tagpage,record);
	}
	
	
}
