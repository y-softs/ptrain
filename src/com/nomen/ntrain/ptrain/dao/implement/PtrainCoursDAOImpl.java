package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.ptrain.dao.PtrainCoursDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainCoursDAOImpl extends NsoftBaseDao implements PtrainCoursDAO {

	public List<PtrainCoursBean> findPtrainCoursList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainCours.findPtrainCoursList", map));
		return (List<PtrainCoursBean>)this.getSqlMapClientTemplate().queryForList("PtrainCours.findPtrainCoursList", map, page, record);
	}

	public List<PtrainCoursBean> findPtrainCoursList(Map map){
		return (List<PtrainCoursBean>)this.getSqlMapClientTemplate().queryForList("PtrainCours.findPtrainCoursList", map);
	}

	public PtrainCoursBean findPtrainCoursBeanById(String id){
		return (PtrainCoursBean)this.getSqlMapClientTemplate().queryForObject("PtrainCours.findPtrainCoursBeanById", id);
	}

	public String insertPtrainCours(PtrainCoursBean ptrainCoursBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainCours.insertPtrainCours", ptrainCoursBean);
	}

	public void updatePtrainCours(PtrainCoursBean ptrainCoursBean){
		this.getSqlMapClientTemplate().update("PtrainCours.updatePtrainCours", ptrainCoursBean);
	}

	public void deletePtrainCoursById(String id){
		this.getSqlMapClientTemplate().delete("PtrainCours.deletePtrainCoursById", id);
	}

}
