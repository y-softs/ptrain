package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.ptrain.service.PtrainFlowService;
@SuppressWarnings("all")
public class PtrainFlowServiceImpl extends BaseServiceImpl implements PtrainFlowService {

	private PtrainFlowDAO ptrainFlowDAO;

	public List<PtrainFlowBean> findPtrainFlowList(Map map){
		return ptrainFlowDAO.findPtrainFlowList(map);
	}

	public void insertPtrainFlow(PtrainFlowBean ptrainFlowBean){
		ptrainFlowDAO.insertPtrainFlow(ptrainFlowBean);
	}

	public void deletePtrainFlowByMap(Map map){
		ptrainFlowDAO.deletePtrainFlowByMap(map);
	}

	//Get和Set方法
	public PtrainFlowDAO getPtrainFlowDAO() {
		return ptrainFlowDAO;
	}
	public void setPtrainFlowDAO(PtrainFlowDAO ptrainFlowDAO) {
		this.ptrainFlowDAO = ptrainFlowDAO;
	}
}
