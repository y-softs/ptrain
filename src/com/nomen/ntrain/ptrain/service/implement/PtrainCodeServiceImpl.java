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

import com.nomen.ntrain.annotation.OptEnums;
import com.nomen.ntrain.annotation.OptResource;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.dao.PtrainCodeDAO;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.util.Constant.OptType;
@SuppressWarnings("all")
public class PtrainCodeServiceImpl extends BaseServiceImpl implements PtrainCodeService {

	private PtrainCodeDAO ptrainCodeDAO;
	private static  Map<String,String> titleMap = null;
	private static  Map<String,String> propMap = null;
	private final String table_name = "tb_ptrain_code";
	
	public List<PtrainCodeBean> findPtrainCodeList(Map map){
		return ptrainCodeDAO.findPtrainCodeList(map);
	}

	public PtrainCodeBean findPtrainCodeBeanByMap(Map map){
		return ptrainCodeDAO.findPtrainCodeBeanByMap(map);
	}

	public String findPtrainCodeInfoByMap(Map map) {
		return ptrainCodeDAO.findPtrainCodeInfoByMap(map);
	}
	public List<PtrainCodeBean> findPtrainCodeList_Comm(Map map) {
		return this.ptrainCodeDAO.findPtrainCodeList_Comm(map);
	}
	@OptResource(optRemark="代码设置",optType=OptEnums.SAVE)
	public void savePtrainCode(PtrainCodeBean PtrainCodeBean){
		if(func.IsEmpty(func.Trim(PtrainCodeBean.getId()))){
			ptrainCodeDAO.insertPtrainCode(PtrainCodeBean);
		}else{
			ptrainCodeDAO.updatePtrainCode(PtrainCodeBean);
		}
	}

	public String findPtrainCodeSortnum(Map map) {
		return this.ptrainCodeDAO.findPtrainCodeSortnum(map);
	}
	
	public String findCodeValueNum(Map map) {
		return this.ptrainCodeDAO.findCodeValueNum(map);
	}
	@OptResource(optRemark="代码设置",optType=OptEnums.DELETE)
	public void deletePtrainCode(String idStr){
		ptrainCodeDAO.deletePtrainCode(idStr);
	}
	@OptResource(optRemark="代码设置-启用|禁用",optType=OptEnums.UPDATE)
	public void updatePtrainCodeUsesign(PtrainCodeBean PtrainCodeBean) {
		this.ptrainCodeDAO.updatePtrainCodeUsesign(PtrainCodeBean);
	}

	public BaseUnitBean findCenterBaseUnitBean(Map map) {
		return this.ptrainCodeDAO.findCenterBaseUnitBean(map);
	}
	@OptResource(optRemark="代码设置-Excel导入",optType=OptEnums.SAVE)
	public void savePtrainCodeExcel(Map map) {
		String nature = func.Trim((String)map.get("nature"));
		String unitid = func.Trim((String)map.get("unitid"));
		String fatherid = func.Trim((String)map.get("fatherid"));
		String sortnum = func.Trim((String)map.get("sortnum"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5更新并追加
		String fileFolder = func.Trim((String) map.get("fileFolder"));
		String operuserid = func.Trim((String) map.get("operuserid"));
		String[] saveNameArr = (String[])map.get("saveNameArr");
		String saveName = "";
		if(null != saveNameArr && 0<saveNameArr.length){
			saveName = saveNameArr[0];
		}
		
		titleMap = new HashMap();
		titleMap.put("LB", "类别");
		titleMap.put("REMARK", "说明");
		titleMap.put("COD", "编码");
		
		propMap = new HashMap();
		propMap.put("LB", "codename");
		propMap.put("REMARK", "remark");
		propMap.put("COD", "codevalue");
		
		this.parseExcle(fileFolder+saveName, nature, unitid, fatherid, sortnum, impsign, operuserid);
	}

	/**
	 * 解析 excel
	 */
	public void parseExcle(String excelFilePath,String nature,String unitid,String fatherid,String sortnum,String impsign,String operuserid){
		jxl.Workbook rwb=null; 
		try{
			//清空后导入[删除主表]!!
			Map param=new HashMap();
			param.put("nature", nature);
			param.put("unitid", unitid);
			param.put("fatherid", fatherid);
			if("1".equals(impsign))this.ptrainCodeDAO.deletePtrainCodeByMap(param);
			
			//读取Excel 
			rwb = Workbook.getWorkbook(new java.io.File(excelFilePath)); 
			Sheet[] sheets = rwb.getSheets();
//			jxl.Sheet st = rwb.getSheet("Sheet1");// 获得名为Sheet1的Sheet含有的行数
			jxl.Sheet st = rwb.getSheet(0);// 获得名为第一个Sheet含有的行数
			int i =1,sortnumTemp=0;
			if(!func.IsEmpty(sortnum))sortnumTemp=Integer.valueOf(sortnum)-1;
			while (i < st.getRows()) {
				PtrainCodeBean ptrainCodeBean=new PtrainCodeBean();
				ptrainCodeBean.setEstauser(operuserid);
				ptrainCodeBean.setMainuser(operuserid);
				ptrainCodeBean.setNature(nature);
				ptrainCodeBean.setUnitid(unitid);
				ptrainCodeBean.setFatherid(fatherid);
				ptrainCodeBean.setSortnum((i+sortnumTemp)+"");
				ptrainCodeBean.setUsesign("1");
				for (int j = 0;j < st.getColumns();j++) {
					String key = func.Trim(st.getCell(j,0).getContents());//第j+1列1行数据
					Cell cell=st.getCell(j,i);
					String value=func.Trim(cell.getContents());
					this.setDataBeanFieldsValue(ptrainCodeBean, key, value);
				}
				this.ptrainCodeDAO.insertPtrainCode(ptrainCodeBean);
				i++;
			}
			//删除类别名称为空
			param.put("codenameNull", "true");
			this.ptrainCodeDAO.deletePtrainCodeByMap(param);
			//删除重复
//			param=new HashMap();
//			param.put("nature", nature);
//			param.put("unitid", unitid);
//			param.put("fatherid", fatherid);
//			this.ptrainCodeDAO.deletePtrainCodeByMap(param);
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
	//====================辅助=========================
	/**
	 * 通过反转机制设置属性值
	 */	
	private void setDataBeanFieldsValue(Object m, String title, String value) {
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
	private void insertJugdeOrgOpt(String id,OptType optType){
		/**************记录日志**************
		BaseOptLogBean optLogBean = new BaseOptLogBean();
		optLogBean.setRecid(id);
		optLogBean.setTablename(table_name);
		this.insertOperLog(optLogBean, optType);*/
	}
	//Get和Set方法
	public PtrainCodeDAO getPtrainCodeDAO() {
		return ptrainCodeDAO;
	}
	public void setPtrainCodeDAO(PtrainCodeDAO PtrainCodeDAO) {
		this.ptrainCodeDAO = PtrainCodeDAO;
	}
}
