package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainParamBean;
import com.nomen.ntrain.ptrain.dao.PtrainParamDAO;
import com.nomen.ntrain.ptrain.service.PtrainParamService;
@SuppressWarnings("all")
public class PtrainParamServiceImpl extends BaseServiceImpl implements PtrainParamService {

	private PtrainParamDAO ptrainParamDAO;

	public List<PtrainParamBean> findPtrainParamList(Map map){
		return ptrainParamDAO.findPtrainParamList(map);
	}

	public PtrainParamBean findPtrainParamBeanById(String id){
		return ptrainParamDAO.findPtrainParamBeanById(id);
	}

	public void savePtrainParam(PtrainParamBean ptrainParamBean){
		if(func.IsEmpty(func.Trim(ptrainParamBean.getId()))){
			ptrainParamDAO.insertPtrainParam(ptrainParamBean);
		}else{
			ptrainParamDAO.updatePtrainParam(ptrainParamBean);
		}
	}

	public void deletePtrainParamById(String id){
		ptrainParamDAO.deletePtrainParamById(id);
	}

	//Get和Set方法
	public PtrainParamDAO getPtrainParamDAO() {
		return ptrainParamDAO;
	}
	public void setPtrainParamDAO(PtrainParamDAO ptrainParamDAO) {
		this.ptrainParamDAO = ptrainParamDAO;
	}
}
