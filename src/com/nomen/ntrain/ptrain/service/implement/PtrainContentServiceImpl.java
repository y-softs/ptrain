package com.nomen.ntrain.ptrain.service.implement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseOptLogBean;
import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainContentDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.ptrain.enums.PtrainContentFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.service.PtrainContentService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
@SuppressWarnings("all")
public class PtrainContentServiceImpl extends BaseServiceImpl implements PtrainContentService {

	private PtrainContentDAO ptrainContentDAO;
	private PtrainFlowDAO ptrainFlowDAO;
	private PtrainFileDAO ptrainFileDAO;

	public List<PtrainContentBean> findPtrainContentList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainContentDAO.findPtrainContentList(map, page, record);
	}

	public List<PtrainContentBean> findPtrainContentListNoPage(Map map) {
		return ptrainContentDAO.findPtrainContentList(map);
	}

	public PtrainContentBean findPtrainContentBeanById(String id){
		return ptrainContentDAO.findPtrainContentBeanById(id);
	}

	public void savePtrainContent(PtrainContentBean ptrainContentBean){
		if(func.IsEmpty(func.Trim(ptrainContentBean.getId()))){
			ptrainContentDAO.insertPtrainContent(ptrainContentBean);
		}else{
			ptrainContentDAO.updatePtrainContent(ptrainContentBean);
		}
	}

	public void deletePtrainContent(String id,String fatherPath){
		//删除附件信息
		Map map = new HashMap();
		map.put("modsign", PtrainConstant.FILE_MODSIGN_CONT);
		map.put("recid", id);
		List list = this.ptrainFileDAO.findPtrainFileList(map);
		if(!func.isEmpty(list)){
			for(Object del :list){
				PtrainFileBean bean = (PtrainFileBean)del;
				String savepath=bean.getSavepath();
				//删除附件
				String filePath = fatherPath+savepath+bean.getSavename();	
				func.delFile(filePath);
			}
			//删文件表
			map = new HashMap();
			map.put("modsign", PtrainConstant.FILE_MODSIGN_CONT);
			map.put("recid", id);
			ptrainFileDAO.deletePtrainFileByMap(map);
		}
		//删除主表
		ptrainContentDAO.deletePtrainContentById(id);
	}

	public String findPtrainContentSortnum(Map map) {
		return ptrainContentDAO.findPtrainContentSortnum(map);
	}
	
	public void updatePtrainContentChk(Map map) {
		PtrainContentBean ptrainContentBean = (PtrainContentBean) map.get("ptrainContentBean");
		String dataChange=func.Trim(map.get("dataChange")+"");
		
		PtrainFlowBean ptrainFlowBean = new PtrainFlowBean();
		ptrainFlowBean.setModsign(PtrainFlowModeSignEnum.TRAINCONT.getKey()+"");
		ptrainFlowBean.setChkmemo(func.Trim(map.get("chkmemo")+""));
		ptrainFlowBean.setChkuser(ptrainContentBean.getMainuser());
		
		String XQSB=PtrainContentFlowStaEnum.XQSB.getKey()+"";
		String XQSH=PtrainContentFlowStaEnum.XQSH.getKey()+"";
		String[] recidArr = ptrainContentBean.getId().split("\\,");
		String fidStr = "",idStr=ptrainContentBean.getId();
		String chksign=func.Trim(map.get("chksign")+"");
		//申报 同意 不同意 撤回 退回
		for(String id:recidArr){
			if(CheckSignConstant.SHEN_BAO.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.SHEN_BAO);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainContentBean.setFlowsta(XQSH);
				ptrainContentBean.setSubtime(func.dateToString(new Date(), "yyyy-MM-dd"));
				ptrainContentBean.setFlowmark(CheckRemarkEnum.C_5101.getKey()+"");
			}else if(CheckSignConstant.TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainContentBean.setFlowsta(PtrainContentFlowStaEnum.GD.getKey()+"");
				ptrainContentBean.setFlowmark(CheckRemarkEnum.C_5155.getKey()+"");
			}else if(CheckSignConstant.BU_TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.BU_TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainContentBean.setFlowsta(XQSB);
				ptrainContentBean.setFlowmark(CheckRemarkEnum.C_5156.getKey()+"");
			}else if(CheckSignConstant.CHE_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.CHE_HUI);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainContentBean.setFlowsta(XQSB);
				ptrainContentBean.setSubtime("");
				ptrainContentBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}else if(CheckSignConstant.TUI_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TUI_HUI);
				ptrainFlowBean.setRemark("至"+PtrainContentFlowStaEnum.XQSB.getDesc()+"。");
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainContentBean.setFlowsta(XQSB);
				ptrainContentBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}
			//新增 子表流程
			ptrainFlowBean.setRecid(id);
			String fid = this.ptrainFlowDAO.insertPtrainFlow(ptrainFlowBean);
			fidStr += fid+",";
			//更新 主表
			ptrainContentBean.setId(id);
			if(func.IsEmpty(dataChange)){
				this.ptrainContentDAO.updatePtrainContent(ptrainContentBean);
			}else{
				this.savePtrainContent(ptrainContentBean);
			}
		}
	}

	//Get/Set
	public PtrainContentDAO getPtrainContentDAO() {
		return ptrainContentDAO;
	}
	public void setPtrainContentDAO(PtrainContentDAO ptrainContentDAO) {
		this.ptrainContentDAO = ptrainContentDAO;
	}

	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}

	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}

	public PtrainFlowDAO getPtrainFlowDAO() {
		return ptrainFlowDAO;
	}

	public void setPtrainFlowDAO(PtrainFlowDAO ptrainFlowDAO) {
		this.ptrainFlowDAO = ptrainFlowDAO;
	}
}
