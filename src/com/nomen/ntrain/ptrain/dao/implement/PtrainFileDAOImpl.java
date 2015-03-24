package com.nomen.ntrain.ptrain.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.util.NsoftBaseDao;
@SuppressWarnings("all")
public class PtrainFileDAOImpl extends NsoftBaseDao implements PtrainFileDAO {

	public List<PtrainFileBean> findPtrainFileList(Map map){
		return (List<PtrainFileBean>)this.getSqlMapClientTemplate().queryForList("PtrainFile.findPtrainFileList", map);
	}

	public PtrainFileBean findPtrainFileBeanById(String id){
		return (PtrainFileBean)this.getSqlMapClientTemplate().queryForObject("PtrainFile.findPtrainFileBeanById", id);
	}

	public String insertPtrainFile(PtrainFileBean ptrainFileBean){
		return (String)this.getSqlMapClientTemplate().insert("PtrainFile.insertPtrainFile", ptrainFileBean);
	}

	public void updatePtrainFileByRandomStr(String FINAL_RANDOMSTR, String recId) {
		Map map = new HashMap();
		map.put("strflag", FINAL_RANDOMSTR);
		map.put("recid", recId);
		this.getSqlMapClientTemplate().update("PtrainFile.updatePtrainFileByRandomStr", map);
	}

	public void deletePtrainFileById(String id){
		this.getSqlMapClientTemplate().delete("PtrainFile.deletePtrainFileById", id);
	}

	public void deletePtrainFileByMap(Map map) {
		this.getSqlMapClientTemplate().delete("PtrainFile.deletePtrainFileByMap", map);
	}

	public void insertPtrainFileByCopy(Map map) {
		this.getSqlMapClientTemplate().insert("PtrainFile.insertPtrainFileByCopy", map);
	}

}
