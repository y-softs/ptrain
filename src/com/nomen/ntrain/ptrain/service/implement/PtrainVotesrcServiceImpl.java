package com.nomen.ntrain.ptrain.service.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainVotesrcBean;
import com.nomen.ntrain.ptrain.dao.PtrainVotesrcDAO;
import com.nomen.ntrain.ptrain.service.PtrainVotesrcService;
@SuppressWarnings("all")
public class PtrainVotesrcServiceImpl extends BaseServiceImpl implements PtrainVotesrcService {
	private PtrainVotesrcDAO ptrainVotesrcDAO;
	
	public List<PtrainVotesrcBean> findPtrainVotesrcList(String appId) {
		return this.ptrainVotesrcDAO.findPtrainVotesrcList(appId);
	}

	public List<PtrainVotesrcBean> findPtrainVotesrcListByPage(Map map,
			int page, int record) {
		return this.ptrainVotesrcDAO.findPtrainVotesrcListByPage(map,page,record);
	}
	
	public PtrainVotesrcBean findPtrainVotesrcBean(String pkid) {
		return this.ptrainVotesrcDAO.findPtrainVotesrcBean(pkid);
	}
	
	public String savePtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean) {
		String id = ptrainVotesrcBean.getId();
		if(func.IsEmpty(id)){
			id = this.ptrainVotesrcDAO.insertPtrainVotesrcBean(ptrainVotesrcBean);
		}else{
			this.ptrainVotesrcDAO.updatePtrainVotesrcBean(ptrainVotesrcBean);
		}
		return id;
	}
	
	public void updatePtrainVotesrcBean(PtrainVotesrcBean ptrainVotesrcBean) {
		this.ptrainVotesrcDAO.updatePtrainVotesrcBean(ptrainVotesrcBean);
	}
	
	public String findPtrainVotesrcNextSortnum(String appid) {
		return this.ptrainVotesrcDAO.findPtrainVotesrcNextSortnum(appid);
	}
	
	public void deletePtrainVotesrcBean(String idstr) {
		this.ptrainVotesrcDAO.deletePtrainVotesrcBean(idstr);
	}
	
	//以下为set get方法
	public PtrainVotesrcDAO getPtrainVotesrcDAO() {
		return ptrainVotesrcDAO;
	}

	public void setPtrainVotesrcDAO(PtrainVotesrcDAO ptrainVotesrcDAO) {
		this.ptrainVotesrcDAO = ptrainVotesrcDAO;
	}
	
}
