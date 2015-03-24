package com.nomen.ntrain.ptrain.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.ptrain.dao.PtrainContentDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainContentDAOImpl extends NsoftBaseDao implements PtrainContentDAO {

	public List<PtrainContentBean> findPtrainContentList(Map map,int page,int record){
		map.put("total", this.getObjectTotal("PtrainContent.findPtrainContentList", map));
		return (List<PtrainContentBean>)this.getSqlMapClientTemplate().queryForList("PtrainContent.findPtrainContentList", map, page, record);
	}

	public List<PtrainContentBean> findPtrainContentList(Map map){
		return (List<PtrainContentBean>)this.getSqlMapClientTemplate().queryForList("PtrainContent.findPtrainContentList", map);
	}

	public PtrainContentBean findPtrainContentBeanById(String id){
		return (PtrainContentBean)this.getSqlMapClientTemplate().queryForObject("PtrainContent.findPtrainContentBeanById", id);
	}

	public String insertPtrainContent(PtrainContentBean ptrainContentBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainContent.insertPtrainContent", ptrainContentBean);
	}

	public void updatePtrainContent(PtrainContentBean ptrainContentBean){
		this.getSqlMapClientTemplate().update("PtrainContent.updatePtrainContent", ptrainContentBean);
	}

	public void deletePtrainContentById(String id){
		this.getSqlMapClientTemplate().delete("PtrainContent.deletePtrainContentById", id);
	}

	public String findPtrainContentSortnum(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("PtrainContent.findPtrainContentSortnum", map);
	}

}
