package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.bean.PtrainFlowBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.dao.PtrainFlowDAO;
import com.nomen.ntrain.ptrain.dao.PtrainReqDAO;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainReqService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.util.PubFunc;
@SuppressWarnings("all")
public class PtrainReqServiceImpl extends BaseServiceImpl implements PtrainReqService {

	private PtrainReqDAO ptrainReqDAO;
	private PtrainFlowDAO ptrainFlowDAO;
	private PtrainFileDAO ptrainFileDAO;

	public List<PtrainReqBean> findPtrainReqList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainReqDAO.findPtrainReqList(map, page, record);
	}
	
	public List<PtrainReqBean> findPtrainReqListNoPage(Map map){
		return ptrainReqDAO.findPtrainReqList(map);
	}

	public PtrainReqBean findPtrainReqBeanById(String id){
		return ptrainReqDAO.findPtrainReqBeanById(id);
	}

	public PtrainReqBean findPtrainReqBeanByMap(Map map){
		return ptrainReqDAO.findPtrainReqBeanByMap(map);
	}

	public void savePtrainReq(PtrainReqBean ptrainReqBean){
		if(func.IsEmpty(func.Trim(ptrainReqBean.getId()))){
			ptrainReqDAO.insertPtrainReq(ptrainReqBean);
		}else{
			ptrainReqDAO.updatePtrainReq(ptrainReqBean);
		}
	}

	public void deletePtrainReqByMap(Map map){
		String recid=(String) map.get("id");
		String fatherPath = func.Trim((String)map.get("fatherPath"));
		String savepath = func.Trim((String)map.get("savepath"));
		//删除附件信息
		map = new HashMap();
		map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
		map.put("recid", recid);
		List list = this.ptrainFileDAO.findPtrainFileList(map);
		for(Object del :list){
			PtrainFileBean bean = (PtrainFileBean)del;
			savepath=bean.getSavepath();
			//删除附件
			String filePath = fatherPath+savepath+bean.getSavename();	
			func.delFile(filePath);
		}
		//删主表
		ptrainReqDAO.deletePtrainReqById(recid);
		//删文件表
		if(!func.isEmpty(list)){
			map = new HashMap();
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			map.put("recid", recid);
			ptrainFileDAO.deletePtrainFileByMap(map);
		}
		//删除流程表
		map = new HashMap();
		map.put("modsign", PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
		map.put("recid", recid);
		this.ptrainFlowDAO.deletePtrainFlowByMap(map);
	}

	public void savePtrainReqExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainReqBean> dataList = this.ptrainReqDAO.findPtrainReqList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expReq(dataList,func.Trim((String)map.get("reqtype")),response);
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
			//更新 主表
			ptrainReqBean.setId(id);
			if(func.IsEmpty(dataChange)){
				this.ptrainReqDAO.updatePtrainReq(ptrainReqBean);
			}else{
				this.savePtrainReq(ptrainReqBean);
			}
		}
	}

	public String insertPtrainReqByCopy(Map map) {
		String recid = func.Trim((String)map.get("id"));
		String reqtype = func.Trim((String)map.get("reqtype"));
		String operuserid = func.Trim((String)map.get("operuserid"));
		//复制主记录
		PtrainReqBean ptrainReqBean = new PtrainReqBean();;
		ptrainReqBean.setIntflag(recid);
		ptrainReqBean.setEstauser(operuserid);
		ptrainReqBean.setMainuser(operuserid);
		String newrecid=ptrainReqDAO.insertPtrainReqByCopy(ptrainReqBean);
		if(reqtype.equals(PtrainReqReqtypeEnum.REQ_COM.getKey()+"")||reqtype.equals(PtrainReqReqtypeEnum.REQ_EXP.getKey()+"")){
			String fatherPath = func.Trim((String)map.get("fatherPath"));
			String savepath = func.Trim((String)map.get("savepath"));
			//附件复制
			Map fMap=new HashMap();
			fMap.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			fMap.put("recid", recid);
			List list = this.ptrainFileDAO.findPtrainFileList(fMap);
			String copyName="C";
			for(Object obj :list){
				PtrainFileBean bean = (PtrainFileBean)obj;
				savepath=bean.getSavepath();
				String filePath = fatherPath+savepath+bean.getSavename();
				copyName=this.getRandomFileName(bean.getSavename());
				this.copyFile(filePath, fatherPath+savepath+copyName);
				
				bean.setRecid(newrecid);
				bean.setSavename(copyName);
				this.ptrainFileDAO.insertPtrainFile(bean);
			}
		}
		return newrecid;
	}
	//============================辅助=============================
	/** 
    * 复制单个文件 
    * @param oldPath String 原文件路径 如：c:/fqf.txt 
    * @param newPath String 复制后路径 如：f:/fqf.txt 
    * @return boolean 
    */ 
	  public void copyFile(String oldPath, String newPath) { 
	      try { 
	          int bytesum = 0;
	          int byteread = 0;
	          File oldfile = new File(oldPath); 
	          if (oldfile.exists()) { //文件存在时
	              InputStream inStream = new FileInputStream(oldPath); //读入原文件 
	              FileOutputStream fs = new FileOutputStream(newPath);
	              byte[] buffer = new byte[1444];
	              int length;
	              while ( (byteread = inStream.read(buffer)) != -1) {
	                  bytesum += byteread; //字节数 文件大小
	                  //System.out.println(bytesum);
	                  fs.write(buffer, 0, byteread);
	              } 
	              inStream.close(); 
	          } 
	      } 
	      catch (Exception e) { 
	          System.out.println("复制单个文件操作出错"); 
	          e.printStackTrace(); 
	      } 
	  } 

	/**
	 * 给文件进行随机命名
	 * @param filename 源文件
	 * @return
	 */
	private String getRandomFileName(String filename) {
		StringBuffer sb = new StringBuffer();
		sb.append(PubFunc.dateToString(new java.util.Date(), "yyyyMMddHHmmss"));
		sb.append((int)(10000+Math.random()*99999));
		int pos = filename.lastIndexOf(".");
		sb.append(filename.substring(pos));
		return sb.toString();
	}

	//Get和Set方法
	public PtrainReqDAO getPtrainReqDAO() {
		return ptrainReqDAO;
	}
	public void setPtrainReqDAO(PtrainReqDAO ptrainReqDAO) {
		this.ptrainReqDAO = ptrainReqDAO;
	}

	public PtrainFlowDAO getPtrainFlowDAO() {
		return ptrainFlowDAO;
	}

	public void setPtrainFlowDAO(PtrainFlowDAO ptrainFlowDAO) {
		this.ptrainFlowDAO = ptrainFlowDAO;
	}

	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}

	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}
}
