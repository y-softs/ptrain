package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainManagerBean;
import com.nomen.ntrain.ptrain.dao.PtrainManagerDAO;
import com.nomen.ntrain.ptrain.service.PtrainManagerService;
@SuppressWarnings("all")
public class PtrainManagerServiceImpl extends BaseServiceImpl implements PtrainManagerService {

	private PtrainManagerDAO ptrainManagerDAO;

	public List<PtrainManagerBean> findPtrainManagerList(Map map){
		return ptrainManagerDAO.findPtrainManagerList(map);
	}

	public PtrainManagerBean findPtrainManagerBeanById(String id){
		return ptrainManagerDAO.findPtrainManagerBeanById(id);
	}

	public void savePtrainManager(PtrainManagerBean ptrainManagerBean){
		if(func.IsEmpty(func.Trim(ptrainManagerBean.getId()))){
			ptrainManagerDAO.insertPtrainManager(ptrainManagerBean);
		}else{
			ptrainManagerDAO.updatePtrainManager(ptrainManagerBean);
		}
	}

	public void deletePtrainManagerById(String id){
		ptrainManagerDAO.deletePtrainManagerById(id);
	}

	public List findDataUserListHas(Map map) {
		return ptrainManagerDAO.findDataUserListHas(map);
	}

	//Get和Set方法
	public PtrainManagerDAO getPtrainManagerDAO() {
		return ptrainManagerDAO;
	}
	public void setPtrainManagerDAO(PtrainManagerDAO ptrainManagerDAO) {
		this.ptrainManagerDAO = ptrainManagerDAO;
	}
}
