package com.nomen.ntrain.web.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.web.dao.WebBbsDAO;
import com.nomen.ntrain.web.service.WebBbsService;
@SuppressWarnings("all")
public class WebBbsServiceImpl extends BaseServiceImpl implements WebBbsService {
	
	private PtrainFileDAO	ptrainFileDAO;
	private WebBbsDAO webBbsDAO;

	public List<PtrainBbsBean> findPtrainBbsChildList(Map map, int tagpage, int record) {
		return this.webBbsDAO.findPtrainBbsChildList(map, tagpage, record);
	}
	
	public List<PtrainBbsBean> findPtrainBbsList(Map map, int tagpage, int record) {
		return this.webBbsDAO.findPtrainBbsList(map, tagpage, record);
	}
	public PtrainBbsBean findPtrainBbsBeanById(String id){
		return webBbsDAO.findPtrainBbsBeanById(id);
	}
	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean) {
		return this.webBbsDAO.insertPtrainBbsByJq(ptrainBbsBean);
	}
	public String savePtrainBbs(PtrainBbsBean ptrainBbsBean){
		String pkId = "";
		if(func.IsEmpty(func.Trim(ptrainBbsBean.getId()))){
			ptrainBbsBean.setEstauser(ptrainBbsBean.getMainuser()); //创建人
			pkId = webBbsDAO.insertPtrainBbs(ptrainBbsBean);
		}else{
			pkId = ptrainBbsBean.getId();
			webBbsDAO.updatePtrainBbs(ptrainBbsBean);
		}
		return pkId;
	}
	public PtrainBbsBean findPtrainBbsBeanByMap(Map map) {
		return this.webBbsDAO.findPtrainBbsBeanByMap(map);
	}
	public String findPtrainBbsChildCount(Map map) {
		return this.webBbsDAO.findPtrainBbsChildCount(map);
	}
	public void deletePtrainBbsById(String id){
		Map delM = new HashMap();
		delM.put("recid", id);
		delM.put("modsign", PtrainConstant.FILE_MODSIGN_BBS);
		List fileList = this.ptrainFileDAO.findPtrainFileList(delM);
		if(!func.isEmpty(fileList)){
			for(int i=0;i<fileList.size();i++){
				PtrainFileBean fileBean = (PtrainFileBean)fileList.get(i);
				String filePath = ServletActionContext.getServletContext().getRealPath(fileBean.getSavepath()+"\\"+fileBean.getSavename());	
	        	File file = new File(filePath);
	    		if(file.exists()) {
	    			file.delete();
	    		}
	    		this.ptrainFileDAO.deletePtrainFileById(fileBean.getId());
    		}
		}
		this.webBbsDAO.deletePtrainNicelogByBbsId(id);
		this.webBbsDAO.deletePtrainBbsById(id);
	}
	public String insertPtrainNicelog(String pkId,String userid) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ptrain_nicelog (id, bbsid, userid, estauser, estatime, mainuser, maintime) "
				 +"values(seq_ptrain_nicelog.nextval,"+pkId+","+userid+",-1,sysdate,-1,sysdate)");
		return this.webBbsDAO.insertPtrainNicelog(sb.toString());
	}	
	public void updatePtrainBbsByBrowse(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_bbs set browse = (browse+1) where id="+id);
		this.webBbsDAO.updateDynamicSQL(sb.toString());
	}

	public void updatePtrainBbsByDownnum(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_file set intflag = (nvl(intflag,0)+1) where id="+id);
		this.webBbsDAO.updateDynamicSQL(sb.toString());
	}
	public void updatePtrainBbsByEvasign(PtrainBbsBean ptrainBbsBean) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_bbs set evasign = "+ptrainBbsBean.getEvasign()+" where id="+ptrainBbsBean.getId());
		this.webBbsDAO.updateDynamicSQL(sb.toString());
	}
	//Get和Set方法
	
	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}

	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}

	public WebBbsDAO getWebBbsDAO() {
		return webBbsDAO;
	}

	public void setWebBbsDAO(WebBbsDAO webBbsDAO) {
		this.webBbsDAO = webBbsDAO;
	}

}
