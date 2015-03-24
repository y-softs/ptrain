package com.nomen.ntrain.ptrain.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainFileBean;
import com.nomen.ntrain.ptrain.service.PtrainFileService;

/**
 * @description 莆田岗位学习_附件表action层
 * @author 林木山
 * @date 2014-3-14
 */
@SuppressWarnings("all")
public class PtrainFileAction extends PtrainAction{

	private PtrainFileService      ptrainFileService;  //附件业务接口
	
	/*********************************************************/
	/*****************以下为JQUERY方法***************************/
	/*********************************************************/
	/**
	 * 文件列表
	 */
	public String listPtrainFileByJq(){
		try{
			HttpServletRequest req = ServletActionContext.getRequest();
			String modsign   = func.Trim(req.getParameter("modsign"));
			String recid   = func.Trim(req.getParameter("recid"));
			Map map = new HashMap();
			map.put("modsign", modsign);
			map.put("recid", recid);
			List dataList = this.ptrainFileService.findPtrainFileList(map);
			this.print(JSONArray.fromObject(dataList).toString());
		}
		catch(Exception ex){
			this.print("-1");
		}
		return null;		
	}
	/**
	 * 删除文件操作
	 */
	public String deletePtrainFileByJq(){
		try{
			HttpServletRequest req = ServletActionContext.getRequest();
			String fileid   = func.Trim(req.getParameter("fileid"));
			String fatherPath = ServletActionContext.getServletContext().getRealPath("");
			Map map = new HashMap();
			map.put("fileid", fileid);
			map.put("fatherPath", fatherPath);
			int rtn = this.ptrainFileService.deletePtrainFileByMap(map);
			this.print(rtn+"");
		}
		catch(Exception ex){
			this.print("-1");
		}
		return null;		
	}
	
	/**
	 * 新增文件操作--数据库数据操作（不包括文件上传）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String insertPtrainFileByJq(){
		try{
			HttpServletRequest req = ServletActionContext.getRequest();
			String fileStyle = func.Trim(req.getParameter("fileStyle")); //1表示一个主键id只能新增一个附件 --倒置要先删除原文件
			String savePath   = func.Trim(req.getParameter("savePath"));
			String modsign   = func.Trim(req.getParameter("modsign"));
			String recid   = func.Trim(req.getParameter("recid"));
			String savename   = func.Trim(req.getParameter("savename"));
			String filename   = func.Trim(req.getParameter("filename"));
			String FINAL_RANDOMSTR   = func.Trim(req.getParameter("FINAL_RANDOMSTR"));
			filename = URLDecoder.decode(filename,"utf-8");
			String intflag = "";
			
			if("1".equals(fileStyle)){
				Map lMap = new HashMap();
				lMap.put("recid", recid);
				lMap.put("modsign", modsign);
				lMap.put("intflag", "1");			 //替换新增的标志值
				List list = this.ptrainFileService.findPtrainFileList(lMap);
				for(int i=0;i<list.size();i++){
					PtrainFileBean fileBean = (PtrainFileBean)list.get(i);
					this.ptrainFileService.deletePtrainFileById(fileBean.getId());
				}
				intflag = "1";
			}
			
			PtrainFileBean ptrainFileBean = new PtrainFileBean();
			ptrainFileBean.setSavepath(savePath);      //保存路径
			ptrainFileBean.setModsign(modsign);        //模块标志
			ptrainFileBean.setRecid(recid);            //所属id
			ptrainFileBean.setFilename(filename);	  //原文件名
			ptrainFileBean.setSavename(savename);      //保存文件名 
			ptrainFileBean.setStrflag(FINAL_RANDOMSTR);//随机码 
			ptrainFileBean.setIntflag(intflag);
			String pkId = this.ptrainFileService.insertPtrainFile(ptrainFileBean);
			this.print(pkId);
		}
		catch(Exception ex){
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	
	//Get和Set方法
	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}

	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}
	
}
