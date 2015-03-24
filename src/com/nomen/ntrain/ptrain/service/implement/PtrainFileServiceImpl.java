package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.PubFunc;
@SuppressWarnings("all")
public class PtrainFileServiceImpl extends BaseServiceImpl implements PtrainFileService {

	private PtrainFileDAO ptrainFileDAO;

	public List<PtrainFileBean> findPtrainFileList(Map map){
		return ptrainFileDAO.findPtrainFileList(map);
	}

	public PtrainFileBean findPtrainFileBeanById(String id){
		return ptrainFileDAO.findPtrainFileBeanById(id);
	}

	public String insertPtrainFile(PtrainFileBean ptrainFileBean) {
		return ptrainFileDAO.insertPtrainFile(ptrainFileBean);
	}

	public void updatePtrainFileByRandomStr(String FINAL_RANDOMSTR, String recId) {
		ptrainFileDAO.updatePtrainFileByRandomStr(FINAL_RANDOMSTR,recId);
	}

	public void deletePtrainFileById(String id){
		ptrainFileDAO.deletePtrainFileById(id);
	}

	public int deletePtrainFileByMap(Map map) {
		try
		{ 
			String fileid=(String)map.get("fileid"); 
			String fatherPath=(String)map.get("fatherPath");   
			//删除文件以及数据库中的数据
			PubFunc func =new PubFunc();
	    	//删除文件
	    	if(!func.Trim(fileid).equals("")){
	    		PtrainFileBean bean = this.findPtrainFileBeanById(fileid);
	    		//获取服务器文件保存路径,并删除文件
	        	String filePath = fatherPath+bean.getSavepath()+bean.getSavename();	
	        	File cware = new File(filePath);
	    		if(cware.exists()) {
	    			cware.delete();
	    		}
	    	}
	    	//删除bean
	    	this.ptrainFileDAO.deletePtrainFileById(fileid);
			return 1;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return -1;
		}
	}

	//Get和Set方法
	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}
	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}
}
