package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainVoteipconfigBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteipconfigDAO;
import com.nomen.ntrain.ptrain.service.PtrainVoteipconfigService;
@SuppressWarnings("all")
public class PtrainVoteipconfigServiceImpl extends BaseServiceImpl implements PtrainVoteipconfigService {

	private PtrainVoteipconfigDAO ptrainVoteipconfigDAO;

	public List<PtrainVoteipconfigBean> findPtrainVoteipconfigList(Map map) {
		return this.ptrainVoteipconfigDAO.findPtrainVoteipconfigList(map);
	}
	
	public PtrainVoteipconfigBean findPtrainVoteipconfigBeanById(String id) {
		return this.ptrainVoteipconfigDAO.findPtrainVoteipconfigBeanById(id);
	}
	
	public List<String> findPtrainVoteipconfigStrList(String appid) {
		return this.ptrainVoteipconfigDAO.findPtrainVoteipconfigStrList(appid);
	}
	
	public String savePtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean) {
		String id = ptrainVoteipconfigBean.getId();
		if(func.IsEmpty(id)){
			id = this.ptrainVoteipconfigDAO.insertPtrainVoteipconfigBean(ptrainVoteipconfigBean);
		}else{
			this.ptrainVoteipconfigDAO.updatePtrainVoteipconfigBean(ptrainVoteipconfigBean);
		}
		return id;
	}
	
	public String insertPtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean) {
		return this.ptrainVoteipconfigDAO.insertPtrainVoteipconfigBean(ptrainVoteipconfigBean);
	}

	public void updatePtrainVoteipconfigBean(PtrainVoteipconfigBean ptrainVoteipconfigBean) {
		this.ptrainVoteipconfigDAO.updatePtrainVoteipconfigBean(ptrainVoteipconfigBean);
	}
	
	public void deletePtrainVoteipconfigBean(String id) {
		this.ptrainVoteipconfigDAO.deletePtrainVoteipconfigBean(id);
	}

	//Get Set方法
	public PtrainVoteipconfigDAO getPtrainVoteipconfigDAO() {
		return ptrainVoteipconfigDAO;
	}

	public void setPtrainVoteipconfigDAO(PtrainVoteipconfigDAO ptrainVoteipconfigDAO) {
		this.ptrainVoteipconfigDAO = ptrainVoteipconfigDAO;
	}
	
}
