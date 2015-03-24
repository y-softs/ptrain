package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainTeacherBean;
import com.nomen.ntrain.ptrain.dao.PtrainTeacherDAO;
import com.nomen.ntrain.ptrain.service.PtrainTeacherService;
@SuppressWarnings("all")
public class PtrainTeacherServiceImpl extends BaseServiceImpl implements PtrainTeacherService {

	private PtrainTeacherDAO ptrainTeacherDAO;

	public List<PtrainTeacherBean> findPtrainTeacherList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainTeacherDAO.findPtrainTeacherList(map, page, record);
	}

	public PtrainTeacherBean findPtrainTeacherBeanById(String id){
		return ptrainTeacherDAO.findPtrainTeacherBeanById(id);
	}

	public String findPtrainTeacherSortnum(Map map) {
		return ptrainTeacherDAO.findPtrainTeacherSortnum(map);
	}

	public void savePtrainTeacher(PtrainTeacherBean ptrainTeacherBean){
		if(func.IsEmpty(func.Trim(ptrainTeacherBean.getId()))){
			ptrainTeacherDAO.insertPtrainTeacher(ptrainTeacherBean);
		}else{
			ptrainTeacherDAO.updatePtrainTeacher(ptrainTeacherBean);
		}
	}

	public void deletePtrainTeacherById(String id){
		ptrainTeacherDAO.deletePtrainTeacherById(id);
	}

	//Get和Set方法
	public PtrainTeacherDAO getPtrainTeacherDAO() {
		return ptrainTeacherDAO;
	}
	public void setPtrainTeacherDAO(PtrainTeacherDAO ptrainTeacherDAO) {
		this.ptrainTeacherDAO = ptrainTeacherDAO;
	}
}
