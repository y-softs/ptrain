package com.nomen.ntrain.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainRequserBean;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.web.dao.WebRequserDAO;
import com.nomen.ntrain.web.service.WebRequserService;
@SuppressWarnings("all")
public class WebRequserServiceImpl extends BaseServiceImpl implements WebRequserService {

	private WebRequserDAO webRequserDAO;
	private PtrainFlowDAO ptrainFlowDAO;

	public List<PtrainReqBean> findPtrainRequserList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return webRequserDAO.findPtrainRequserList(map, page, record);
	}
	public List<PtrainReqBean> findPtrainReqList(Map map, int page, int record) {
		return this.webRequserDAO.findPtrainReqList(map, page, record);
	}
	public List<PtrainRequserBean> findPtrainRequserSignList(Map map) {
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return webRequserDAO.findPtrainRequserSignList(map, page, record);
	}

	public void savePtrainRequser(PtrainRequserBean ptrainRequserBean){
		if(func.IsEmpty(func.Trim(ptrainRequserBean.getId()))){
			webRequserDAO.insertPtrainRequser(ptrainRequserBean);
		}else{
			webRequserDAO.updatePtrainRequser(ptrainRequserBean);
		}
	}

	public void deletePtrainRequserById(String id){
		webRequserDAO.deletePtrainRequserById(id);
	}
	public void updatePtrainReqChk(Map map) {
		PtrainReqBean ptrainReqBean = (PtrainReqBean) map.get("ptrainReqBean");
		String dataChange=func.Trim(map.get("dataChange")+"");
		
		PtrainFlowBean ptrainFlowBean = new PtrainFlowBean();
		ptrainFlowBean.setModsign(PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
		ptrainFlowBean.setChkmemo(func.Trim(map.get("chkmemo")+""));
		ptrainFlowBean.setChkuser(ptrainReqBean.getMainuser());
		
		String XQSB=PtrainReqFlowStaEnum.XQSB.getKey()+"";
		String XQSH=PtrainReqFlowStaEnum.XQSH.getKey()+"";
		String[] recidArr = ptrainReqBean.getId().split("\\,");
		String fidStr = "",idStr=ptrainReqBean.getId();
		String chksign=func.Trim(map.get("chksign")+"");
		//申报 同意 不同意 撤回 退回
		for(String id:recidArr){
			if(CheckSignConstant.SHEN_BAO.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.SHEN_BAO);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainReqBean.setFlowsta(XQSH);
				ptrainReqBean.setSubtime(func.dateToString(new Date(), "yyyy-MM-dd"));
				ptrainReqBean.setFlowmark(CheckRemarkEnum.C_5101.getKey()+"");
			}else if(CheckSignConstant.TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainReqBean.setFlowsta(PtrainReqFlowStaEnum.GD.getKey()+"");
				ptrainReqBean.setFlowmark(CheckRemarkEnum.C_5155.getKey()+"");
			}else if(CheckSignConstant.BU_TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.BU_TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainReqBean.setFlowsta(XQSB);
				ptrainReqBean.setFlowmark(CheckRemarkEnum.C_5156.getKey()+"");
			}else if(CheckSignConstant.CHE_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.CHE_HUI);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainReqBean.setFlowsta(XQSB);
				ptrainReqBean.setSubtime("");
				ptrainReqBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}else if(CheckSignConstant.TUI_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TUI_HUI);
				ptrainFlowBean.setRemark("至"+PtrainReqFlowStaEnum.XQSB.getDesc()+"。");
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainReqBean.setFlowsta(XQSB);
				ptrainReqBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}
			//新增 子表流程
			ptrainFlowBean.setRecid(id);
			String fid = this.ptrainFlowDAO.insertPtrainFlow(ptrainFlowBean);
			fidStr += fid+",";
			this.webRequserDAO.updatePtrainReq(ptrainReqBean);
		}
	}
	public PtrainReqBean findPtrainReqBeanById(String id) {
		return this.webRequserDAO.findPtrainReqBeanById(id);
	}
	public void savePtrainReq(PtrainReqBean ptrainReqBean){
		if(func.IsEmpty(func.Trim(ptrainReqBean.getId()))){
			this.webRequserDAO.insertPtrainReq(ptrainReqBean);
		}else{
			this.webRequserDAO.updatePtrainReq(ptrainReqBean);
		}
	}
	public void deletePtrainReqById(String id) {
		this.webRequserDAO.deletePtrainReqById(id);
	}
	public PtrainReqBean findPtrainReqBeanByMap(Map map) {
		return this.webRequserDAO.findPtrainReqBeanByMap(map);
	}
	//Get和Set方法
	public WebRequserDAO getWebRequserDAO() {
		return webRequserDAO;
	}

	public void setWebRequserDAO(WebRequserDAO webRequserDAO) {
		this.webRequserDAO = webRequserDAO;
	}
	public PtrainFlowDAO getPtrainFlowDAO() {
		return ptrainFlowDAO;
	}
	public void setPtrainFlowDAO(PtrainFlowDAO ptrainFlowDAO) {
		this.ptrainFlowDAO = ptrainFlowDAO;
	}
	
}
