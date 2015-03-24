package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainBbsBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainBbsDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainBbsService;
@SuppressWarnings("all")
public class PtrainBbsServiceImpl extends BaseServiceImpl implements PtrainBbsService {
	
	private PtrainFileDAO	ptrainFileDAO;
	private PtrainBbsDAO ptrainBbsDAO;

	public List<PtrainBbsBean> findPtrainBbsChildList(Map map, int tagpage, int record) {
		return this.ptrainBbsDAO.findPtrainBbsChildList(map, tagpage, record);
	}
	
	public List<PtrainBbsBean> findPtrainBbsList(Map map, int tagpage, int record) {
		return this.ptrainBbsDAO.findPtrainBbsList(map, tagpage, record);
	}
	public PtrainBbsBean findPtrainBbsBeanById(String id){
		return ptrainBbsDAO.findPtrainBbsBeanById(id);
	}
	public String insertPtrainBbsByJq(PtrainBbsBean ptrainBbsBean) {
		return this.ptrainBbsDAO.insertPtrainBbsByJq(ptrainBbsBean);
	}
	public String savePtrainBbs(PtrainBbsBean ptrainBbsBean){
		String pkId = "";
		if(func.IsEmpty(func.Trim(ptrainBbsBean.getId()))){
			ptrainBbsBean.setEstauser(ptrainBbsBean.getMainuser()); //创建人
			pkId = ptrainBbsDAO.insertPtrainBbs(ptrainBbsBean);
		}else{
			pkId = ptrainBbsBean.getId();
			ptrainBbsDAO.updatePtrainBbs(ptrainBbsBean);
		}
		return pkId;
	}
	public PtrainBbsBean findPtrainBbsBeanByMap(Map map) {
		return this.ptrainBbsDAO.findPtrainBbsBeanByMap(map);
	}
	public String findPtrainBbsChildCount(Map map) {
		return this.ptrainBbsDAO.findPtrainBbsChildCount(map);
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
		this.ptrainBbsDAO.deletePtrainNicelogByBbsId(id);
		this.ptrainBbsDAO.deletePtrainBbsById(id);
	}
	public List<PtrainBbsBean> findPtrainAppStatiList(Map map) {
		return this.ptrainBbsDAO.findPtrainAppStatiList(map);
	}
	public String insertPtrainNicelog(String pkId,String userid) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ptrain_nicelog (id, bbsid, userid, estauser, estatime, mainuser, maintime) "
				 +"values(seq_ptrain_nicelog.nextval,"+pkId+","+userid+",-1,sysdate,-1,sysdate)");
		return this.ptrainBbsDAO.insertPtrainNicelog(sb.toString());
	}	

	public List<PtrainBbsBean> findPtrainRankList(Map map) {
		return this.ptrainBbsDAO.findPtrainRankList(map);
	}
	public void updatePtrainBbsByBrowse(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_bbs set browse = (browse+1) where id="+id);
		this.ptrainBbsDAO.updateDynamicSQL(sb.toString());
	}

	public void updatePtrainBbsByDownnum(String id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_bbs set downnum = (downnum+1) where id="+id);
		this.ptrainBbsDAO.updateDynamicSQL(sb.toString());
	}
	public void updatePtrainBbsByEvasign(PtrainBbsBean ptrainBbsBean) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ptrain_bbs set evasign = "+ptrainBbsBean.getEvasign()+" where id="+ptrainBbsBean.getId());
		this.ptrainBbsDAO.updateDynamicSQL(sb.toString());
	}

	public void savePtrainBbsExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainBbsBean> dataList = this.ptrainBbsDAO.findPtrainAppStatiList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expBbsAppStat(dataList,response);
	}

	public void savePtrainBbsRankExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainBbsBean> dataList = this.ptrainBbsDAO.findPtrainRankList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expBbsRank(dataList,response);
	}
	public void savePtrainBbsModeExpExcel(Map map,HttpServletResponse response) throws Exception {
		List dataList = this.ptrainBbsDAO.findPtrainModeList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expBbsMode(dataList,response);
	}
	public List<PtrainBbsBean> findPtrainModeList(Map map) {
		return this.ptrainBbsDAO.findPtrainModeList(map);
	}
	//Get和Set方法
	public PtrainBbsDAO getPtrainBbsDAO() {
		return ptrainBbsDAO;
	}
	public void setPtrainBbsDAO(PtrainBbsDAO ptrainBbsDAO) {
		this.ptrainBbsDAO = ptrainBbsDAO;
	}

	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}

	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}
	
}
