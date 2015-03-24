package com.nomen.ntrain.ptrain.service.implement;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.nomen.ntrain.base.service.implement.BaseServiceImpl;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.dao.PtrainQuestionsDAO;
import com.nomen.ntrain.ptrain.dao.PtrainQuestionstempDAO;
import com.nomen.ntrain.ptrain.service.PtrainQuestionstempService;
@SuppressWarnings("all")
public class PtrainQuestionstempServiceImpl extends BaseServiceImpl implements PtrainQuestionstempService {

	private PtrainQuestionstempDAO ptrainQuestionstempDAO;
	private PtrainQuestionsDAO ptrainQuestionsDAO;
	private static  Map<String,String> titleMap = null;
	private static  Map<String,String> propMap = null;

	public List<PtrainQuestionstempBean> findPtrainQuestionstempList(Map map){
		int page = Integer.parseInt((String)map.get("tagpage"));
		int record = Integer.parseInt((String)map.get("record"));
		return ptrainQuestionstempDAO.findPtrainQuestionstempList(map, page, record);
	}

	public PtrainQuestionstempBean findPtrainQuestionstempBeanById(String id){
		return ptrainQuestionstempDAO.findPtrainQuestionstempBeanById(id);
	}

	public void savePtrainQuestionstemp(PtrainQuestionstempBean ptrainQuestionstempBean){
		if(func.IsEmpty(func.Trim(ptrainQuestionstempBean.getId()))){
			ptrainQuestionstempDAO.insertPtrainQuestionstemp(ptrainQuestionstempBean);
		}else{
			ptrainQuestionstempDAO.updatePtrainQuestionstemp(ptrainQuestionstempBean);
		}
	}

	public void deletePtrainQuestionstempByMap(Map map){
		ptrainQuestionstempDAO.deletePtrainQuestionstempByMap(map);
	}

	public void deletePtrainQuestionstempById(String id){
		ptrainQuestionstempDAO.deletePtrainQuestionstempById(id);
	}

	public void savePtrainQuestionstempExcel(Map map) {
		String unitid = func.Trim((String)map.get("unitid"));
		String kindid = func.Trim((String)map.get("postid"));
		String kindidtemp = func.Trim((String)map.get("postidtemp"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5更新并追加
		String fileFolder = func.Trim((String) map.get("fileFolder"));
		String[] saveNameArr = (String[])map.get("saveNameArr");
		String saveName = "";
		if(null != saveNameArr && 0<saveNameArr.length){
			saveName = saveNameArr[0];
		}
		
		titleMap = new HashMap();
		titleMap.put("T-X", "题型");
		titleMap.put("T-M", "题目");
		titleMap.put("SPEC", "专业类别");
		titleMap.put("T-X1", "选项A");
		titleMap.put("T-X2", "选项B");
		titleMap.put("T-X3", "选项C");
		titleMap.put("T-X4", "选项D");
		titleMap.put("T-X5", "选项E");
		titleMap.put("T-X6", "选项F");
		titleMap.put("T-X7", "选项G");
		titleMap.put("T-D1", "答案");
		/*titleMap.put("T-D2", "答案2");
		titleMap.put("T-D3", "答案3");
		titleMap.put("T-D4", "答案4");
		titleMap.put("T-D5", "答案5");
		titleMap.put("T-D6", "答案6");
		titleMap.put("T-D7", "答案7");*/
		titleMap.put("T-BZ", "备注");
		
		propMap = new HashMap();
		propMap.put("T-X", "typetemp");
		propMap.put("T-M", "topic");
		propMap.put("SPEC", "kindidtemp");
		propMap.put("T-X1", "option1");
		propMap.put("T-X2", "option2");
		propMap.put("T-X3", "option3");
		propMap.put("T-X4", "option4");
		propMap.put("T-X5", "option5");
		propMap.put("T-X6", "option6");
		propMap.put("T-X7", "option7");
		propMap.put("T-D1", "answer1");
		/*propMap.put("T-D2", "answer2");
		propMap.put("T-D3", "answer3");
		propMap.put("T-D4", "answer4");
		propMap.put("T-D5", "answer5");
		propMap.put("T-D6", "answer6");
		propMap.put("T-D7", "answer7");*/
		propMap.put("T-BZ", "remark");
		
		Map dMap=new HashMap();
		dMap.put("unitid", unitid);
		dMap.put("kindid", kindid);
		this.ptrainQuestionstempDAO.deletePtrainQuestionstempByMap(dMap);
		
		this.parseExcle(fileFolder+saveName, unitid, kindid, kindidtemp);
	}

	/**
	 * 解析 excel xml方式
	 */
	public void parseExcle(String excelFilePath,String unitid,String kindid,String kindidtemp){
		jxl.Workbook rwb=null; 
		try{
			//读取Excel 
			rwb = Workbook.getWorkbook(new java.io.File(excelFilePath)); 
			Sheet[] sheets = rwb.getSheets();
//			jxl.Sheet st = rwb.getSheet("Sheet1");// 获得名为Sheet1的Sheet含有的行数
			jxl.Sheet st = rwb.getSheet(0);// 获得名为第一个Sheet含有的行数
			int i =1;
			while (i < st.getRows()) {
				PtrainQuestionstempBean ptrainQuestionstempBean=new PtrainQuestionstempBean();
				ptrainQuestionstempBean.setUnitid(unitid);
				ptrainQuestionstempBean.setKindid(kindid);
				ptrainQuestionstempBean.setKindidtemp(kindidtemp);
				ptrainQuestionstempBean.setDatasign("1");
				String TOPIC_TEXT = "";
				for (int j = 0;j < st.getColumns();j++) {
					String key = func.Trim(st.getCell(j,0).getContents());//第j+1列1行数据
					key=key.replace(" ", "");
					Cell cell=st.getCell(j,i);
					String value=func.Trim(cell.getContents());
					//去除左边空格（长度至少为2）
					if(value.length()>1)value=func.Ltrim(value);
					if(value.length()>1000){
						value=value.substring(0, 980)+"【文本超过1000字以后的未读取】";
					}
					this.setWageTemBeanFieldsValue(ptrainQuestionstempBean, key, value);
				}
				String answer1=ptrainQuestionstempBean.getAnswer1();
				if(func.Trim(ptrainQuestionstempBean.getTypetemp()).indexOf(PtrainConstant.TYPE_JUDGE)>-1){
					if(answer1.indexOf("正确")>-1||answer1.equals("1")){answer1="1";}
					else if(answer1.indexOf("错误")>-1||answer1.equals("0")){answer1="0";}
					else{answer1="";}
				}
				ptrainQuestionstempBean.setAnswer1(answer1);
				this.ptrainQuestionstempDAO.insertPtrainQuestionstemp(ptrainQuestionstempBean);
				i++;
			}
			Map map=new HashMap();
			map.put("unitid", unitid);
			map.put("fatheridtype", CodeFatherUtil.RES_QUE_TYPE); 
			map.put("fatheridkind", CodeFatherUtil.PTRAIN_SPEC_ASK); 
			map.put("kindid", kindid);
			this.ptrainQuestionstempDAO.updatePtrainQuestionstempTypeid(map);
			if(!func.IsEmpty(kindid)){
				//专业id不为空==删除非本专业数据
				Map dMap=new HashMap();
				dMap.put("unitid", unitid);
				dMap.put("nothiskindid", kindid);
				this.ptrainQuestionstempDAO.deletePtrainQuestionstempByMap(dMap);
			}
			this.ptrainQuestionstempDAO.updatePtrainQuestionstempExc(map);
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

	public void savePrrainQuesExcel(Map map) {
		String unitid = func.Trim((String)map.get("unitid"));
		String fatherid = func.Trim((String)map.get("fatherid"));
		String kindid = func.Trim((String)map.get("postid"));
		String questempid = func.Trim((String)map.get("questempid"));
		String operuserid = func.Trim((String)map.get("operuserid"));
		String impsign = func.Trim((String)map.get("impsign"));						//导入方式 1清空后导入 5全部追加

		Map param=new HashMap();
		param.put("unitid", unitid);
		param.put("kindid", kindid);
		//清空后导入[删除主表]!!
		if("1".equals(impsign))this.ptrainQuestionsDAO.deletePtrainQuestionsByMap(param);
		//排序号
		String sortnum=this.ptrainQuestionsDAO.findPtrainQuestionsSortnum(param);
		param.put("questempid", questempid);
		param.put("sortnum", sortnum);
		param.put("operuserid", operuserid);
		//导入
		this.ptrainQuestionstempDAO.insertPtrainQuesExcel(param);
		//更新已导
		if(!func.IsEmpty(questempid))this.ptrainQuestionstempDAO.updatePtrainQuestionstempDatasign(param);
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
	public PtrainQuestionstempDAO getPtrainQuestionstempDAO() {
		return ptrainQuestionstempDAO;
	}
	public void setPtrainQuestionstempDAO(PtrainQuestionstempDAO ptrainQuestionstempDAO) {
		this.ptrainQuestionstempDAO = ptrainQuestionstempDAO;
	}

	public PtrainQuestionsDAO getPtrainQuestionsDAO() {
		return ptrainQuestionsDAO;
	}

	public void setPtrainQuestionsDAO(PtrainQuestionsDAO ptrainQuestionsDAO) {
		this.ptrainQuestionsDAO = ptrainQuestionsDAO;
	}
}
