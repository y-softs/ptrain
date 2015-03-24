package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqtempBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainFileDAO;
import com.nomen.ntrain.ptrain.dao.PtrainReqDAO;
import com.nomen.ntrain.ptrain.dao.PtrainReqtempDAO;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.ptrain.service.PtrainReqtempService;
import com.nomen.ntrain.util.CheckRemarkEnum;
@SuppressWarnings("all")
public class PtrainReqtempServiceImpl extends BaseServiceImpl implements PtrainReqtempService {

	private PtrainReqtempDAO ptrainReqtempDAO;
	private PtrainReqDAO ptrainReqDAO;
	private PtrainFileDAO ptrainFileDAO;
	private static  Map<String,String> titleMap = null;
	private static  Map<String,String> propMap = null;

	public List<PtrainReqtempBean> findPtrainReqtempList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainReqtempDAO.findPtrainReqtempList(map, page, record);
	}

	public PtrainReqtempBean findPtrainReqtempBeanById(String id){
		return ptrainReqtempDAO.findPtrainReqtempBeanById(id);
	}

	public void savePtrainReqtemp(PtrainReqtempBean ptrainReqtempBean){
		if(func.IsEmpty(func.Trim(ptrainReqtempBean.getId()))){
			ptrainReqtempDAO.insertPtrainReqtemp(ptrainReqtempBean);
		}else{
			ptrainReqtempDAO.updatePtrainReqtemp(ptrainReqtempBean);
		}
	}

	public void deletePtrainReqtemp(Map map){
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
		//删除主表
		ptrainReqtempDAO.deletePtrainReqtempById(recid);
		//删文件表
		if(!func.isEmpty(list)){
			map = new HashMap();
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			map.put("recid", recid);
			ptrainFileDAO.deletePtrainFileByMap(map);
		}
	}

	public void deletePtrainReqtempByMap(Map map) {
		ptrainReqtempDAO.deletePtrainReqtempByMap(map);
	}

	public void savePtrainReqtempExcel(Map map) {
		String unitid = func.Trim((String)map.get("unitid"));
		String specid = func.Trim((String)map.get("specid"));
		String reqtype = func.Trim((String)map.get("reqtype"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5更新并追加
		String fileFolder = func.Trim((String) map.get("fileFolder"));
		String[] saveNameArr = (String[])map.get("saveNameArr");
		String saveName = "";
		if(null != saveNameArr && 0<saveNameArr.length){
			saveName = saveNameArr[0];
		}
		
		titleMap = new HashMap();
		titleMap.put("ZYLB", "专业类别");
		titleMap.put("XMMC", "培训项目名称");
		titleMap.put("KCJS", "课程介绍");
		titleMap.put("PXTS", "培训天数");
		titleMap.put("XMLY", "项目来源");
		titleMap.put("PXS", "培训师");
		titleMap.put("KJZZR", "课件制作人");
		
		propMap = new HashMap();
		propMap.put("ZYLB", "spectemp");
		propMap.put("XMMC", "itemname");
		propMap.put("KCJS", "itemdesc");
		propMap.put("PXTS", "daycounttemp");
		propMap.put("XMLY", "reqform");
		propMap.put("PXS", "teacher");
		propMap.put("KJZZR", "teacher");
		
		Map dMap=new HashMap();
		dMap.put("unitid", unitid);
		dMap.put("specid", specid);
		dMap.put("reqtype", reqtype);
		this.ptrainReqtempDAO.deletePtrainReqtempByMap(dMap);
		
		this.parseExcle(fileFolder+saveName, unitid, specid, reqtype);
	}

	/**
	 * 解析 excel 
	 */
	public void parseExcle(String excelFilePath,String unitid,String specid,String reqtype){
		jxl.Workbook rwb=null; 
		try{
			//读取Excel 
			rwb = Workbook.getWorkbook(new java.io.File(excelFilePath)); 
			Sheet[] sheets = rwb.getSheets();
//			jxl.Sheet st = rwb.getSheet("Sheet1");// 获得名为Sheet1的Sheet含有的行数
			jxl.Sheet st = rwb.getSheet(0);// 获得名为第一个Sheet含有的行数
			int i =1;
			while (i < st.getRows()) {
				PtrainReqtempBean ptrainReqtempBean=new PtrainReqtempBean();
				ptrainReqtempBean.setUnitid(unitid);
				ptrainReqtempBean.setReqtype(reqtype);
				ptrainReqtempBean.setDatasign("1");
				for (int j = 0;j < st.getColumns();j++) {
					String key = func.Trim(st.getCell(j,0).getContents());//第j+1列1行数据
					key=key.replace(" ", "");
					Cell cell=st.getCell(j,i);
					String value=func.Trim(cell.getContents());
					this.setWageTemBeanFieldsValue(ptrainReqtempBean, key, value);
				}
				this.ptrainReqtempDAO.insertPtrainReqtemp(ptrainReqtempBean);
				i++;
			}
			Map map=new HashMap();
			map.put("unitid", unitid);
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("reqtype", reqtype);
			this.ptrainReqtempDAO.updatePtrainReqtempSpecid(map);
			//清除非本专业数据
			if(!func.IsEmpty(specid)){
				map=new HashMap();
				map.put("unitid", unitid);
				map.put("specidchk", specid);
				this.ptrainReqtempDAO.deletePtrainReqtempByMap(map);
			}
			map=new HashMap();
			map.put("unitid", unitid);
			map.put("specid", specid);
			map.put("reqtype", reqtype);
			if(!reqtype.equals(PtrainReqReqtypeEnum.REQ_COUR.getKey()+"")){
				map.put("chkdaycount", "true");
			}
			this.ptrainReqtempDAO.updatePtrainReqtempExc(map);
		}
		catch (BiffException e1) {
		    e1.printStackTrace();
	   } catch (IOException e2) {
		   e2.printStackTrace();
	   }catch(Exception e3){
		   e3.printStackTrace();
	   }finally{
		   if(rwb!=null)rwb.close();
		   File file=new File(excelFilePath);
		   if(file.exists())file.delete();
	   }
	}

	public void savePtrainReqExcel(Map map) {
		String unitid = func.Trim((String)map.get("unitid"));
		String reqtype = func.Trim((String)map.get("reqtype"));
		String fatherid = func.Trim((String)map.get("fatherid"));
		String specid = func.Trim((String)map.get("specid"));
		String reqtempid = func.Trim((String)map.get("reqtempid"));
		String operuserid = func.Trim((String)map.get("operuserid"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5全部追加

		Map param=new HashMap();
		param.put("unitid", unitid);
		param.put("specid", specid);
		param.put("reqtype", reqtype);
		//清空后导入[删除主表]!!
		if("1".equals(impsign))this.ptrainReqDAO.deletePtrainReqByMap(map);
		param.put("reqtempid", reqtempid);
		param.put("reqtype", reqtype);
		if(reqtype.equals(PtrainReqReqtypeEnum.REQ_EXP.getKey()+"")){
			param.put("flowsta", PtrainReqFlowStaEnum.XQSB.getKey()+"");
			param.put("flowmark", CheckRemarkEnum.C_1001.getKey()+"");
		}
		param.put("operuserid", operuserid);
		//导入
		this.ptrainReqtempDAO.insertPtrainReqExcel(param);
		//更新已导
		if(!func.IsEmpty(reqtempid))this.ptrainReqtempDAO.updatePtrainReqtempDatasign(param);
	}
	//============================辅助================================
	/**
	 * 通过反转机制设置属性值
	 */	
	private void setWageTemBeanFieldsValue(Object m, String title, String value) {
		String filed = ""; 
		for(Map.Entry<String, String> entry:this.titleMap.entrySet()) {
			if(func.IsEmpty(filed)){
				String $mV=entry.getValue();
				String $mK=entry.getKey();
				if(title.equals($mV)){
					filed = this.propMap.get($mK); 
				}
			}
		}
		
		Method[] method = m.getClass().getDeclaredMethods();//使用getMethods()则获取所有的方法
		int len = method.length;
		for (int i = 0; i < len; i++) {
			String name = method[i].getName();
			String tmpName = "set" + filed;
			if (name.toUpperCase().equals(tmpName.toUpperCase())) {
				if (value != null) {
					value = value.trim();
				}
				Object[] obj = { value };
				try {
					method[i].invoke(m, obj);  
				} catch (Exception ex) {
					;
				}
			}
		} 
	}

	//Get和Set方法
	public PtrainReqtempDAO getPtrainReqtempDAO() {
		return ptrainReqtempDAO;
	}
	public void setPtrainReqtempDAO(PtrainReqtempDAO ptrainReqtempDAO) {
		this.ptrainReqtempDAO = ptrainReqtempDAO;
	}

	public PtrainReqDAO getPtrainReqDAO() {
		return ptrainReqDAO;
	}

	public void setPtrainReqDAO(PtrainReqDAO ptrainReqDAO) {
		this.ptrainReqDAO = ptrainReqDAO;
	}

	public PtrainFileDAO getPtrainFileDAO() {
		return ptrainFileDAO;
	}

	public void setPtrainFileDAO(PtrainFileDAO ptrainFileDAO) {
		this.ptrainFileDAO = ptrainFileDAO;
	}
}
