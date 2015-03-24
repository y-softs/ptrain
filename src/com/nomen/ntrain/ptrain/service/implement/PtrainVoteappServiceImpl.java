package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseOptLogBean;
import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainVoteappBean;
import com.nomen.ntrain.ptrain.dao.PtrainVoteappDAO;
import com.nomen.ntrain.ptrain.service.PtrainVoteappService;
@SuppressWarnings("all")
public class PtrainVoteappServiceImpl extends BaseServiceImpl implements PtrainVoteappService {

	private PtrainVoteappDAO ptrainVoteappDAO;

	public List<PtrainVoteappBean> findPtrainVoteappList(Map map){
		return ptrainVoteappDAO.findPtrainVoteappList(map);
	}

	public PtrainVoteappBean findPtrainVoteappBeanById(String id){
		return ptrainVoteappDAO.findPtrainVoteappBeanById(id);
	}

	public void savePtrainVoteapp(PtrainVoteappBean ptrainVoteappBean){
		if(func.IsEmpty(func.Trim(ptrainVoteappBean.getId()))){
			ptrainVoteappDAO.insertPtrainVoteapp(ptrainVoteappBean);
		}else{
			ptrainVoteappDAO.updatePtrainVoteapp(ptrainVoteappBean);
		}
	}

	public void deletePtrainVoteappById(String id){
		ptrainVoteappDAO.deletePtrainVoteappById(id);
	}

	public void updatePtrainVoteappDefvote(PtrainVoteappBean ptrainVoteappBean) {
		ptrainVoteappDAO.updatePtrainVoteappDefvote(ptrainVoteappBean);
	}

	public PtrainVoteappBean findDefaultPtrainVoteappBean() { 
		return ptrainVoteappDAO.findDefaultPtrainVoteappBean();
	}

	//Get Set方法
	public PtrainVoteappDAO getPtrainVoteappDAO() {
		return ptrainVoteappDAO;
	}
	public void setPtrainVoteappDAO(PtrainVoteappDAO ptrainVoteappDAO) {
		this.ptrainVoteappDAO = ptrainVoteappDAO;
	}
}
