package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.ptrain.dao.PtrainPolicyDAO;
import com.nomen.ntrain.ptrain.service.PtrainPolicyService;
@SuppressWarnings("all")
public class PtrainPolicyServiceImpl extends BaseServiceImpl implements PtrainPolicyService {

	private PtrainPolicyDAO ptrainPolicyDAO;

	public List<PtrainPolicyBean> findPtrainPolicyList(Map map){
		return ptrainPolicyDAO.findPtrainPolicyList(map);
	}

	public PtrainPolicyBean findPtrainPolicyBeanById(String id){
		return ptrainPolicyDAO.findPtrainPolicyBeanById(id);
	}

	public void savePtrainPolicy(PtrainPolicyBean ptrainPolicyBean){
		if(func.IsEmpty(func.Trim(ptrainPolicyBean.getId()))){
			ptrainPolicyDAO.insertPtrainPolicy(ptrainPolicyBean);
		}else{
			ptrainPolicyDAO.updatePtrainPolicy(ptrainPolicyBean);
		}
	}

	public void deletePtrainPolicyById(String id){
		ptrainPolicyDAO.deletePtrainPolicyById(id);
	}

	public void deletePtrainPolicyByMap(Map map){
		ptrainPolicyDAO.deletePtrainPolicyByMap(map);
	}

	//Get和Set方法
	public PtrainPolicyDAO getPtrainPolicyDAO() {
		return ptrainPolicyDAO;
	}
	public void setPtrainPolicyDAO(PtrainPolicyDAO ptrainPolicyDAO) {
		this.ptrainPolicyDAO = ptrainPolicyDAO;
	}
}
