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
import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;
import com.nomen.ntrain.ptrain.dao.PtrainPorgDAO;
import com.nomen.ntrain.ptrain.excel.PtrainExcelOutForJxlImpl;
import com.nomen.ntrain.ptrain.service.PtrainPorgService;
@SuppressWarnings("all")
public class PtrainPorgServiceImpl extends BaseServiceImpl implements PtrainPorgService {

	private PtrainPorgDAO ptrainPorgDAO;
	private static  Map<String,String> titleMap = null;
	private static  Map<String,String> propMap = null;

	public List<PtrainPorgBean> findPtrainPorgList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainPorgDAO.findPtrainPorgList(map, page, record);
	}

	public PtrainPorgBean findPtrainPorgBeanById(String id){
		return ptrainPorgDAO.findPtrainPorgBeanById(id);
	}

	public void savePtrainPorg(PtrainPorgBean ptrainPorgBean){
		if(func.IsEmpty(func.Trim(ptrainPorgBean.getId()))){
			ptrainPorgDAO.insertPtrainPorg(ptrainPorgBean);
		}else{
			ptrainPorgDAO.updatePtrainPorg(ptrainPorgBean);
		}
	}

	public void deletePtrainPorgById(String id){
		ptrainPorgDAO.deletePtrainPorgById(id);
	}

	public String findPtrainPorgSortnum(Map map) {
		return ptrainPorgDAO.findPtrainPorgSortnum(map);
	}

	public void savePtrainPorgImpExcel(Map map) {
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
		titleMap.put("GSMC", "公司名称");
		titleMap.put("GSJJ", "公司简介");
		titleMap.put("ANLI", "案例");
		titleMap.put("LX", "联系方式");
		
		propMap = new HashMap();
		propMap.put("GSMC", "orgname");
		propMap.put("GSJJ", "orgdesc");
		propMap.put("ANLI", "orgcase");
		propMap.put("LX", "contact");
		
		this.parseExcle(fileFolder+saveName, unitid, impsign, operuserid);
	}

	/**
	 * 解析 excel 
	 */
	public void parseExcle(String excelFilePath,String unitid,String impsign,String operuserid){
		jxl.Workbook rwb=null; 
		try{
			//清空后导入[删除主表]!!
			Map param=new HashMap();
			param.put("unitid", unitid);
			if("1".equals(impsign))this.ptrainPorgDAO.deletePtrainPorgByMap(param);
			//排序号
			String sortnum=this.ptrainPorgDAO.findPtrainPorgSortnum(param);
			
			//读取Excel 
			rwb = Workbook.getWorkbook(new java.io.File(excelFilePath)); 
			Sheet[] sheets = rwb.getSheets();
//			jxl.Sheet st = rwb.getSheet("Sheet1");// 获得名为Sheet1的Sheet含有的行数
			jxl.Sheet st = rwb.getSheet(0);// 获得名为第一个Sheet含有的行数
			int i =1;
			while (i < st.getRows()) {
				PtrainPorgBean ptrainPorgBean=new PtrainPorgBean();
				ptrainPorgBean.setUnitid(unitid);
				ptrainPorgBean.setSortnum((Integer.valueOf(sortnum)+i)+"");
				ptrainPorgBean.setUsesign("1");
				ptrainPorgBean.setEstauser(operuserid);
				ptrainPorgBean.setMainuser(operuserid);
				for (int j = 0;j < st.getColumns();j++) {
					String key = func.Trim(st.getCell(j,0).getContents());//第j+1列1行数据
					Cell cell=st.getCell(j,i);
					String value=func.Trim(cell.getContents());
					this.setWageTemBeanFieldsValue(ptrainPorgBean, key, value);
				}
				this.ptrainPorgDAO.insertPtrainPorg(ptrainPorgBean);
				i++;
			}
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

	public void savePtrainPorgExpExcel(Map map, HttpServletResponse response) throws Exception {
		//数据列表
		List<PtrainPorgBean> dataList = this.ptrainPorgDAO.findPtrainPorgList(map);
		PtrainExcelOutForJxlImpl expJxl=new PtrainExcelOutForJxlImpl();
		expJxl.expPorg(dataList,response);
		
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
	public PtrainPorgDAO getPtrainPorgDAO() {
		return ptrainPorgDAO;
	}
	public void setPtrainPorgDAO(PtrainPorgDAO ptrainPorgDAO) {
		this.ptrainPorgDAO = ptrainPorgDAO;
	}
}
