package com.nomen.ntrain.ptrain.service.implement;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainCoursDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.ptrain.enums.PtrainCoursFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.service.PtrainCoursService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
@SuppressWarnings("all")
public class PtrainCoursServiceImpl extends BaseServiceImpl implements PtrainCoursService {

	private PtrainCoursDAO ptrainCoursDAO;
	private PtrainFlowDAO ptrainFlowDAO;
	private PtrainFileDAO ptrainFileDAO;

	public List<PtrainCoursBean> findPtrainCoursList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainCoursDAO.findPtrainCoursList(map, page, record);
	}
	
	public List<PtrainCoursBean> findPtrainCoursListNoPage(Map map){
		return ptrainCoursDAO.findPtrainCoursList(map);
	}

	public PtrainCoursBean findPtrainCoursBeanById(String id){
		return ptrainCoursDAO.findPtrainCoursBeanById(id);
	}

	public void savePtrainCours(PtrainCoursBean ptrainCoursBean){
		if(func.IsEmpty(func.Trim(ptrainCoursBean.getId()))){
			ptrainCoursDAO.insertPtrainCours(ptrainCoursBean);
		}else{
			ptrainCoursDAO.updatePtrainCours(ptrainCoursBean);
		}
	}

	public void deletePtrainCours(String id,String delsign,String fatherPath){
		if(delsign.equals("1")){
			//删除附件信息
			Map map = new HashMap();
			map.put("modsign", PtrainConstant.FILE_MODSIGN_COURS);
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
				map.put("modsign", PtrainConstant.FILE_MODSIGN_COURS);
				map.put("recid", id);
				ptrainFileDAO.deletePtrainFileByMap(map);
			}
			map = new HashMap();
			map.put("modsign", PtrainConstant.FILE_MODSIGN_COURS_FM);
			map.put("recid", id);
			list = this.ptrainFileDAO.findPtrainFileList(map);
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
				map.put("modsign", PtrainConstant.FILE_MODSIGN_COURS_FM);
				map.put("recid", id);
				ptrainFileDAO.deletePtrainFileByMap(map);
			}
		}
		//删除主表
		ptrainCoursDAO.deletePtrainCoursById(id);
	}
	
	public void updatePtrainCoursChk(Map map) {
		PtrainCoursBean ptrainCoursBean = (PtrainCoursBean) map.get("ptrainCoursBean");
		String dataChange=func.Trim(map.get("dataChange")+"");
		
		PtrainFlowBean ptrainFlowBean = new PtrainFlowBean();
		ptrainFlowBean.setModsign(PtrainFlowModeSignEnum.TRAINCOURS.getKey()+"");
		ptrainFlowBean.setChkmemo(func.Trim(map.get("chkmemo")+""));
		ptrainFlowBean.setChkuser(ptrainCoursBean.getMainuser());
		
		String XQSB=PtrainCoursFlowStaEnum.XQSB.getKey()+"";
		String XQSH=PtrainCoursFlowStaEnum.XQSH.getKey()+"";
		String[] recidArr = ptrainCoursBean.getId().split("\\,");
		String fidStr = "",idStr=ptrainCoursBean.getId();
		String chksign=func.Trim(map.get("chksign")+"");
		//申报 同意 不同意 撤回 退回
		for(String id:recidArr){
			if(CheckSignConstant.SHEN_BAO.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.SHEN_BAO);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainCoursBean.setFlowsta(XQSH);
				ptrainCoursBean.setSubtime(func.dateToString(new Date(), "yyyy-MM-dd"));
				ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_5101.getKey()+"");
			}else if(CheckSignConstant.TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainCoursBean.setFlowsta(PtrainCoursFlowStaEnum.GD.getKey()+"");
				ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_5155.getKey()+"");
			}else if(CheckSignConstant.BU_TONG_YI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.BU_TONG_YI);
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainCoursBean.setFlowsta(XQSB);
				ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_5156.getKey()+"");
			}else if(CheckSignConstant.CHE_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.CHE_HUI);
				ptrainFlowBean.setFlowsta(XQSB);
				
				ptrainCoursBean.setFlowsta(XQSB);
				ptrainCoursBean.setSubtime("");
				ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}else if(CheckSignConstant.TUI_HUI.equals(chksign)){
				ptrainFlowBean.setChksign(CheckSignConstant.TUI_HUI);
				ptrainFlowBean.setRemark("至"+PtrainCoursFlowStaEnum.XQSB.getDesc()+"。");
				ptrainFlowBean.setFlowsta(XQSH);
				
				ptrainCoursBean.setFlowsta(XQSB);
				ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
			}
			//新增 子表流程
			ptrainFlowBean.setRecid(id);
			String fid = this.ptrainFlowDAO.insertPtrainFlow(ptrainFlowBean);
			fidStr += fid+",";
			//更新 主表
			ptrainCoursBean.setId(id);
			if(func.IsEmpty(dataChange)){
				this.ptrainCoursDAO.updatePtrainCours(ptrainCoursBean);
			}else{
				this.savePtrainCours(ptrainCoursBean);
			}
		}
	}

	//Get、Set
	public PtrainCoursDAO getPtrainCoursDAO() {
		return ptrainCoursDAO;
	}
	public void setPtrainCoursDAO(PtrainCoursDAO ptrainCoursDAO) {
		this.ptrainCoursDAO = ptrainCoursDAO;
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
