package com.nomen.ntrain.ptrain.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainPostuserService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_岗位人员action层
 * @author 林木山
 * @date 2014-3-10
 */
@SuppressWarnings("all")
public class PtrainPostuserAction extends PtrainAction{

	private PtrainPostuserService		ptrainPostuserService;  	//莆田岗位培训_岗位人员业务接口
	private PtrainCodeService			ptrainCodeService;			//代码列表业务接口
	private LoginService 				loginService;      			//登录信息业务处理类
	private PtrainPostuserBean			ptrainPostuserBean;     	//莆田岗位培训_岗位人员信息表
	private Map<String,String>			querymap;					//传参map
	private List						dataList;					//数据列表
	//	=================辅助参数===================
	private String sign; 					  						//1人员岗位关联 2人员岗位关联(反向设置)
	private String savePath; 					  					//保存文件的目录路径(通过依赖注入)
	
	public String toForwardPuser(){
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		//专业类别
		Map map = new HashMap();
		if("1".equals(this.sign)){
			map.put("nature", "2");
			map.put("unitid", loginBean.getUnitid());
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
		}else if("2".equals(this.sign)){
			/**
			 //人员岗位关联(反向设置)
			List naturelist=null,deptlist=null;
			//部门列表
			map = new HashMap();
			map.put("unitid", this.querymap.get("unitid"));
			map.put("nature", func.Trim(this.querymap.get("nature")));
			deptlist = this.loginService.findDeptListByMap(map);
			req.setAttribute("naturelist", naturelist);
			req.setAttribute("deptlist", deptlist);
			 */
		}
		return SUCCESS;
	}
	
	/**
	 * 莆田岗位培训_岗位人员 列表信息
	 */
	public String listPtrainPostuser() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			Map map = new HashMap();
			String specid="",fromReve="";
			if("1".equals(this.sign)){
				specid=func.Trim(this.querymap.get("specid"));
			}else if("2".equals(this.sign)){
				fromReve="true";
			}
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			if(func.IsEmpty(func.Trim(this.querymap.get("subgroupid")))){
				map.put("deptid", this.querymap.get("deptid"));
				map.put("groupid", this.querymap.get("groupid"));
			}
			map.put("subgroupid", this.querymap.get("subgroupid"));
			map.put("postid", specid);
			map.put("fromReve", fromReve);
			map.put("userstatefather", CodeFatherUtil.DATA_USERSTATEFATHER);//人员状态必须为在职的
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainPostuserService.findPtrainPostuserList(map);
			this.setTotal(String.valueOf(map.get("total")));
			this.print(this.creItemListPage(dataList,String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}

	/**
	 * 莆田岗位培训_岗位人员 人员引入 [弹出]
	 */
	public String setPtrainPostuserWin(){
//		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}

	/**
	 * 莆田岗位培训_岗位人员 人员引入[保存]
	 * 莆田岗位培训_岗位人员 反向设置 设置[保存]
	 */
	public String savePtrainPostuser() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainPostuserBean.setEstauser(loginBean.getId());
			this.ptrainPostuserBean.setMainuser(loginBean.getId());
			this.ptrainPostuserService.savePtrainPostuser(this.ptrainPostuserBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error","window.close();");		
			return Constant.NO_DATA;
		}
		// 关闭窗口时，刷父窗体页面
//		String script = "opener.querydata();\n window.close();";
//		this.setActMessage("operate.success",script);		
//		return Constant.NO_DATA;
		return null;
	}

	/**
	 * 莆田岗位培训_岗位人员 删除信息
	 */
	public String deletePtrainPostuser() {
		try {
			if(!func.IsEmpty(this.ptrainPostuserBean.getId()))
				this.ptrainPostuserService.deletePtrainPostuserById(this.ptrainPostuserBean.getId());
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 莆田岗位培训_岗位人员 导入excel 跳转
	 */
	public String setPtrainPostuserExcel() {
		HttpServletRequest req = ServletActionContext.getRequest();
		//专业类别
		Map map = new HashMap();
		map.put("id", this.querymap.get("specid"));
		PtrainCodeBean cBean=this.ptrainCodeService.findPtrainCodeBeanByMap(map);
		req.setAttribute("postname", cBean.getCodename());
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	/**
	 *  莆田岗位培训_岗位人员 excel导入[保存]
	 */
	public String savePtrainPostuserExcel() {
		try {
			if(this.isValidToken()) {
				//上传附件并返回保存名
				String[] saveNameArr = this.execUpload(this.getSavePath(),0);
				//导入数据传参
				String fileFolder = ServletActionContext.getServletContext().getRealPath("/")+this.getSavePath();
				Map map=new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("postid", func.Trim(this.querymap.get("specid")));
				map.put("fileFolder", fileFolder);
				map.put("saveNameArr", saveNameArr);  
				//impsign导入标志 1：清空后导入 4：更新并追加 5:全部都追加
				map.put("impsign", this.querymap.get("impsign"));
				map.put("impkey", func.Trim(this.querymap.get("impkey")));
				map.put("operuserid", this.getLoginSessionBean().getId());
				this.ptrainPostuserService.savePtrainPostuserExcel(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainPostuserExcel();
			return INPUT;
		}
		return this.toForwardPuser();
	}
	/**
	 * 莆田岗位培训_岗位人员 反向设置 设置 弹出
	 */
	public String setPtrainPostuserReve() {
		HttpServletRequest req = ServletActionContext.getRequest();
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		Map map = new HashMap();
		map.put("unitid", func.Trim(this.querymap.get("unitid")));
		String certerUnitid=func.Trim(loginBean.getUnitid());
		//专业类别
		map = new HashMap();
		map.put("nature", "2");
		map.put("unitid", certerUnitid);
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
		map.put("usesign", "1");
		req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	/**
	 * 莆田岗位培训_岗位人员 反向设置 保存
	 *//*
	public String savePtrainPostuserReve() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainPostuserBean.setMainuser(loginBean.getId());
			this.ptrainPostuserService.savePtrainPostuser(this.ptrainPostuserBean);
			if(!this.ptrainPostuserBean.getPostid().equals(this.querymap.get("postid"))){
				//删除 没重设置的记录
				Map map=new HashMap();
				map.put("unitid", this.querymap.get("unitid"));
				map.put("postid", func.Trim(this.querymap.get("postid")));
				map.put("id", func.Trim(this.ptrainPostuserBean.getId()));
				this.ptrainPostuserService.deletePtrainPostuserByMap(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error","window.close();");		
			return Constant.NO_DATA;
		}
		// 关闭窗口时，刷父窗体页面
		String script = "opener.querydata();\n window.close();";
		this.setActMessage("operate.success",script);		
		return Constant.NO_DATA;
	}*/
	/**
	 * 莆田岗位培训_岗位人员 Excel导出
	 */
	public String savePtrainPostuserExpExcel() {
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		try{
			/*//岗位名称
			Map map = new HashMap();
			map.put("id", this.querymap.get("postid"));
			map.put("fatherid", CodeFatherUtil.PTRAIN_POST);
			String postTypeName= this.ptrainCodeService.findPtrainCodeInfoByMap(map);*/
			//专业类别
			Map map = new HashMap();
			map.put("id", this.querymap.get("specid"));
			PtrainCodeBean cBean=this.ptrainCodeService.findPtrainCodeBeanByMap(map);
			String postTypeName= cBean.getCodename();
			map = new HashMap();
			map.put("unitid", this.querymap.get("unitid"));
			map.put("postid", func.Trim(this.querymap.get("specid")));
			map.put("deptid", this.querymap.get("deptid"));
			map.put("groupid", this.querymap.get("groupid"));
			map.put("sortfield","u.deptid,u.groupid,u.sortnum,u.id");
			map.put("fields",func.Trim(this.fields));
			map.put("keyword",func.Trim(this.keyword));
			map.put("postTypeName", postTypeName);
			this.ptrainPostuserService.savePtrainPostuserExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * *****************************JQuery相关******************************
	 */
	/**
	 * 查询岗位类别 列表
	 */
	public void findPtrainCodeListByJq() {
		try{
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			String fatherid=CodeFatherUtil.PTRAIN_POST;
			if(null!=this.querymap&&!func.IsEmpty(func.Trim(this.querymap.get("fatherid"))))
				fatherid=this.querymap.get("fatherid");
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			String certerUnitid=func.Trim(loginBean.getUnitid());
			//专业类别
			map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", certerUnitid);
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			JSONArray json = JSONArray.fromObject(this.ptrainCodeService.findPtrainCodeList(map));
			this.print(json.toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	
	/**
	 * 根据条件查询人员信息
	 * @param map：unitid，单位ID；nature，部门性质；deptid，部门ID；
	 * 			  groupid，班组ID；groupid2，自班组ID;fields，关键字段；keyword，关键字内容
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String listUnitUsersByJq(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			Map qmap = new HashMap(); 
			qmap.put("unitid",func.Trim(req.getParameter("unitid"))); 
			qmap.put("nature",func.Trim(req.getParameter("nature"))); 
			qmap.put("deptid",func.Trim(req.getParameter("deptid"))); 
			qmap.put("groupid",func.Trim(req.getParameter("groupid"))); 
			qmap.put("groupid2",func.Trim(req.getParameter("groupid2"))); 
			qmap.put("kindid", func.Trim(req.getParameter("kindid")));
			qmap.put("fields",func.Trim(req.getParameter("fields")));  
//			qmap.put("postid",func.Trim(req.getParameter("postid"))); //岗位类别
			String keyword = func.Trim(req.getParameter("keyword"));
			keyword = URLDecoder.decode(keyword,"utf-8");
			qmap.put("keyword",keyword); 

			//人员状态必须为在职的
			String userstate = CodeFatherUtil.DATA_USERSTATEFATHER;
			qmap.put("userstatefather",userstate); 
			List rtnlist = this.ptrainPostuserService.findAllUseUser(qmap);
			JSONArray json = JSONArray.fromObject(rtnlist);
			this.print(json.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询三问专业类别
	 */
	public String findThreeSpecListByJq(){
		try{
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			this.print(JSONArray.fromObject(this.ptrainCodeService.findPtrainCodeList(map)).toString());
		}catch(Exception e){
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询三问专业类别
	 */
	public String findPtrainDeptByJq(){
		try{
			Map map = new HashMap();
			//人员岗位关联(反向设置)
			List deptlist=null;
			//部门列表
			map.put("unitid", this.querymap.get("unitid"));
			map.put("nature", func.Trim(this.querymap.get("nature")));
			deptlist = this.loginService.findDeptListByMap(map);
			this.printList(deptlist);
		}catch(Exception e){
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 查询部门班组列表
	 * @return
	 */
	public void findPtrainDeptGroupByJq(){
		try {
			String deptid = func.Trim(this.querymap.get("deptid"));
			String groupid = func.Trim(this.querymap.get("groupid"));
			Map map = new HashMap();
			if(!func.IsEmpty(groupid)){
				//子班组列表
				map.put("fatherid", groupid);
				List subgrouplist = this.loginService.findGroupListByMap(map);
				this.printList(subgrouplist);
			}else{
				map.put("fatherid", deptid);
				List grouplist = this.loginService.findGroupListByMap(map);
				this.printList(grouplist);
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	//Get和Set方法
	public PtrainPostuserBean getPtrainPostuserBean() {
		return ptrainPostuserBean;
	}
	public void setPtrainPostuserBean(PtrainPostuserBean ptrainPostuserBean) {
		this.ptrainPostuserBean = ptrainPostuserBean;
	}
	public PtrainPostuserService getPtrainPostuserService() {
		return ptrainPostuserService;
	}
	public void setPtrainPostuserService(PtrainPostuserService ptrainPostuserService) {
		this.ptrainPostuserService = ptrainPostuserService;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
