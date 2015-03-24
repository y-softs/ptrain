package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebCoursDAO;
@SuppressWarnings("all")
public class WebCoursDAOImpl extends NsoftBaseDao implements WebCoursDAO {

	public PtrainCoursBean findPtrainCoursBeanById(String id) {
		return (PtrainCoursBean)this.getSqlMapClientTemplate().queryForObject("webCours.findPtrainCoursBeanById",id);
	}

	public List<PtrainCoursBean> findPtrainCoursChilList(Map map) {
		return this.getSqlMapClientTemplate().queryForList("webCours.findPtrainCoursChilList",map);
	}

	public List<PtrainCoursBean> findPtrainCoursList(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("webCours.findPtrainCoursList", map));
		return this.getSqlMapClientTemplate().queryForList("webCours.findPtrainCoursList",map,page,record);
	}

	
}
