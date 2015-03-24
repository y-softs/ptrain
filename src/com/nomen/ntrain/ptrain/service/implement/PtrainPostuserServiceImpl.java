package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;
import com.nomen.ntrain.ptrain.dao.PtrainPostuserDAO;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainPostuserService;
@SuppressWarnings("all")
public class PtrainPostuserServiceImpl extends BaseServiceImpl implements PtrainPostuserService {

	private PtrainPostuserDAO ptrainPostuserDAO;
	private static  Map<String,String> titleMap = null;
	private static  Map<String,String> propMap = null;

	public List<PtrainPostuserBean> findPtrainPostuserList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainPostuserDAO.findPtrainPostuserList(map, page, record);
	}

	public PtrainPostuserBean findPtrainPostuserBeanById(String id){
		return ptrainPostuserDAO.findPtrainPostuserBeanById(id);
	}

	public void savePtrainPostuser(PtrainPostuserBean ptrainPostuserBean){
		if(func.IsEmpty(func.Trim(ptrainPostuserBean.getId()))){
			String[] useridArr=ptrainPostuserBean.getUserid().split(",");
			for(String userid:useridArr){
				ptrainPostuserBean.setUserid(userid);
				ptrainPostuserDAO.insertPtrainPostuser(ptrainPostuserBean);
			}
		}else{
			ptrainPostuserDAO.updatePtrainPostuser(ptrainPostuserBean);
		}
	}

	public void deletePtrainPostuserById(String id){
		ptrainPostuserDAO.deletePtrainPostuserById(id);
	}

	public void deletePtrainPostuserByMap(Map map) {
		ptrainPostuserDAO.deletePtrainPostuserByMap(map);
	}

	public List findAllUseUser(Map map) {
		return ptrainPostuserDAO.findAllUseUser(map);
	}

	public void savePtrainPostuserExcel(Map map) {
		String unitid = func.Trim((String)map.get("unitid"));
		String postid = func.Trim((String)map.get("postid"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5更新并追加
		String impkey = func.Trim((String)map.get("impkey"));						//关联主键 1身份证号 2工号 
		String fileFolder = func.Trim((String) map.get("fileFolder"));
		String operuserid = func.Trim((String) map.get("operuserid"));
		String[] saveNameArr = (String[])map.get("saveNameArr");
		String saveName = "";
		if(null != saveNameArr && 0<saveNameArr.length){
			saveName = saveNameArr[0];
		}
		
		titleMap = new HashMap();
		titleMap.put("GH", "工号");
		titleMap.put("SFZ", "身份证号");
		
		propMap = new HashMap();
		propMap.put("GH", "strflag");
		propMap.put("SFZ", "strflag");
		
		this.parseExcle(fileFolder+saveName, unitid, postid, impsign, impkey, operuserid);
	}

	/**
	 * 解析 excel
	 */
	public void parseExcle(String excelFilePath,String unitid,String postid,String impsign,String impkey,String operuserid){
		jxl.Workbook rwb=null; 
		try{
			//清空后导入[删除主表]!!
			Map param=new HashMap();
			param.put("unitid", unitid);
			param.put("postid", postid);
			if("1".equals(impsign))this.ptrainPostuserDAO.deletePtrainPostuserByMap(param);
			
			//读取Excel 
			rwb = Workbook.getWorkbook(new java.io.File(excelFilePath)); 
			Sheet[] sheets = rwb.getSheets();
//			jxl.Sheet st = rwb.getSheet("Sheet1");// 获得名为Sheet1的Sheet含有的行数
			jxl.Sheet st = rwb.getSheet(0);// 获得名为第一个Sheet含有的行数
			List<Integer> colList=new ArrayList<Integer>();
			for (int j = 0;j < st.getColumns();j++) {
				String key = func.Trim(st.getCell(j,0).getContents());//第j+1列1行数据
				if("1".equals(impkey)&&"身份证号".equals(key)){
					colList.add(j);
				}else if("2".equals(impkey)&&"工号".equals(key)){
					colList.add(j);
				}
			}
			int i =1;
			while (i < st.getRows()) {
				PtrainPostuserBean ptrainPostuserBean=new PtrainPostuserBean();
				ptrainPostuserBean.setEstauser(operuserid);
				ptrainPostuserBean.setMainuser(operuserid);
				ptrainPostuserBean.setUnitid(unitid);
				ptrainPostuserBean.setPostid(postid);
				for (int col:colList) {
					String key = func.Trim(st.getCell(col,0).getContents());//第j+1列1行数据
					Cell cell=st.getCell(col,i);
					String value=func.Trim(cell.getContents());
					this.setWageTemBeanFieldsValue(ptrainPostuserBean, key, value);
				}
				this.ptrainPostuserDAO.insertPtrainPostuser(ptrainPostuserBean);
				i++;
			}
			//更新人员ID
			if("1".equals(impkey))param.put("linkUserid", "true");//关联主键 1身份证号 2工号 
			else param.put("linkWorkid", "true");
			param.put("useridEmpty", "true");
			this.ptrainPostuserDAO.updatePtrainPostuserUserid(param);
			//删除人员ID为空
			this.ptrainPostuserDAO.deletePtrainPostuserEmpty(param);
			//删除重复
			param=new HashMap();
			if("4".equals(impsign)){//更新并追加==删除旧的那条
				param.put("datafield", "max(id)");
			}else if("5".equals(impsign)){//全部都追加==删除新的那条
				param.put("datafield", "min(id)");
			}
			param.put("unitid", unitid);
			this.ptrainPostuserDAO.deletePtrainPostuserRepe(param);
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

	public void savePtrainPostuserExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainPostuserBean> dataList = this.ptrainPostuserDAO.findPtrainPostuserList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expPostuser(dataList,(String)map.get("postTypeName"),response);
	}

	public String findPtrainPostuserPostid(Map map) {
		return this.ptrainPostuserDAO.findPtrainPostuserPostid(map);
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
	public PtrainPostuserDAO getPtrainPostuserDAO() {
		return ptrainPostuserDAO;
	}
	public void setPtrainPostuserDAO(PtrainPostuserDAO ptrainPostuserDAO) {
		this.ptrainPostuserDAO = ptrainPostuserDAO;
	}
}
