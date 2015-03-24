package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainTeacherBean;
import com.nomen.ntrain.ptrain.dao.PtrainTeacherDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainTeacherDAOImpl extends NsoftBaseDao implements PtrainTeacherDAO {

	public List<PtrainTeacherBean> findPtrainTeacherList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainTeacher.findPtrainTeacherList", map));
		return (List<PtrainTeacherBean>)this.getSqlMapClientTemplate().queryForList("PtrainTeacher.findPtrainTeacherList", map, page, record);
	}

	public PtrainTeacherBean findPtrainTeacherBeanById(String id){
		return (PtrainTeacherBean)this.getSqlMapClientTemplate().queryForObject("PtrainTeacher.findPtrainTeacherBeanById", id);
	}

	public String findPtrainTeacherSortnum(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainTeacher.findPtrainTeacherSortnum", map);
	}

	public String insertPtrainTeacher(PtrainTeacherBean ptrainTeacherBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainTeacher.insertPtrainTeacher", ptrainTeacherBean);
	}

	public void updatePtrainTeacher(PtrainTeacherBean ptrainTeacherBean){
		this.getSqlMapClientTemplate().update("PtrainTeacher.updatePtrainTeacher", ptrainTeacherBean);
	}

	public void deletePtrainTeacherById(String id){
		this.getSqlMapClientTemplate().delete("PtrainTeacher.deletePtrainTeacherById", id);
	}

}
