package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 岗位培训_代码表action层
 * @author 林木山
 * @date 2014-3-4
 */
@SuppressWarnings("all")
public class PtrainCodeAction extends PtrainAction{

	private PtrainCodeService	ptrainCodeService;	//代码列表业务接口
	private LoginService 		loginService;      	//登录信息业务处理类
	private PtrainCodeBean		ptrainCodeBean;		//代码信息bean
	private Map<String,String>	querymap;			//传参map
	private List				dataList;			//结果集
	//========================辅助========================
	private String 				savePath; 			//保存文件的目录路径(通过依赖注入)
	private String				listpage;			//列表页
	private String				setpage;			//跳转页
	
	public String defaultForward(){
		this.fatherid = this.getStaticValueByName(this.fatherid);
		return SUCCESS;
	}
	
	/**
	 * 岗位培训 列表信息
	 */
	public String listPtrainCode() {
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(this.querymap==null) {
				this.querymap = new HashMap();
				this.sortfield= "sortnum";
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			Map map = new HashMap();
			//数据列表
			map = new HashMap();
			//性质（1共性，2个性）
			map.put("nature", this.nature);
			if("2".equals(this.nature)){
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
			}
			map.put("fatherid", func.Trim(this.fatherid));
			map.put("sortfield",func.Trim(this.sortfield));
			map.put("isLevelList", "true");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("usesign",  func.Trim(this.querymap.get("usesign")));
			this.dataList = this.ptrainCodeService.findPtrainCodeList(map);
			this.print(JSONArray.fromObject(dataList).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}

	/**
	 * 岗位培训 新增、修改[跳转]
	 */
	public String setPtrainCode() {
		try {
			if(null==this.ptrainCodeBean)
				this.ptrainCodeBean = new PtrainCodeBean();
			Map map = new HashMap();
			if(func.IsEmpty(func.Trim(this.ptrainCodeBean.getId()))){
				//新增
				map.put("fatherid", this.ptrainCodeBean.getFatherid());
				map.put("nature", this.nature);
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				this.ptrainCodeBean.setSortnum(this.ptrainCodeService.findPtrainCodeSortnum(map));
				if(!func.IsEmpty(this.querymap.get("priorData"))&&this.fatherid.equals(CodeFatherUtil.PTRAIN_POST)){
					//父级信息
					map = new HashMap();
					map.put("id", this.ptrainCodeBean.getFatherid());
					map.put("linkfield", "c.id");
					map.put("fatherid", this.fatherid);
					PtrainCodeBean pBean = this.ptrainCodeService.findPtrainCodeBeanByMap(map);
					this.ptrainCodeBean.setCodevalue(pBean.getCodevalue()+"-"+this.ptrainCodeBean.getSortnum());
					this.ptrainCodeBean.setStrflag(pBean.getStrflag());
				}
			}else{
				//修改
				String id=this.ptrainCodeBean.getId();
				map.put("id", id);
				if(!func.IsEmpty(this.querymap.get("priorData"))&&this.fatherid.equals(CodeFatherUtil.PTRAIN_POST)){
					//父级信息
					map.put("linkfield", "c.fatherid");
					map.put("fatherid", this.fatherid);
				}
				this.ptrainCodeBean = this.ptrainCodeService.findPtrainCodeBeanByMap(map);
				//判断本级有没有子级
				map = new HashMap();
				map.put("fatherid", id);
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				String subNum=this.ptrainCodeService.findCodeValueNum(map);
				HttpServletRequest req = ServletActionContext.getRequest();
				req.setAttribute("subnum", subNum);
			}
			//维护人、维护时间
			this.getOpraterInfoIntoRequest();
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * 岗位培训 新增、修改[保存]
	 */
	public String savePtrainCode() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainCodeBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainCodeBean.getId())))
					this.ptrainCodeBean.setEstauser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainCodeBean.getEndsign()))&&this.fatherid.equals(CodeFatherUtil.PTRAIN_POST))
					this.ptrainCodeBean.setEndsign("0");
				this.ptrainCodeService.savePtrainCode(ptrainCodeBean);
			}
			if(func.Trim(this.gosign).equals("1")) {
				String fatherid=this.ptrainCodeBean.getFatherid();
				this.ptrainCodeBean = new PtrainCodeBean();
				this.ptrainCodeBean.setFatherid(fatherid);
				this.setPtrainCode();
			}else{
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return rValue;
	}
	/**
	 *  莆田岗位培训_三问专业设置 excel导入[保存]
	 */
	public String savePtrainCodeExcel() {
		try {
			if(this.isValidToken()) {
				//上传附件并返回保存名
				String[] saveNameArr = this.execUpload(this.getSavePath(),0);
				//导入数据传参
				String fileFolder = ServletActionContext.getServletContext().getRealPath("/")+this.getSavePath();
				Map map=new HashMap();
				map.put("nature", this.nature);
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", this.fatherid);
				map.put("sortnum", this.ptrainCodeBean.getSortnum());
				map.put("fileFolder", fileFolder);
				map.put("saveNameArr", saveNameArr);  
				//impsign导入标志 1：清空后导入 5：全部都追加 
				map.put("impsign", this.querymap.get("impsign"));
				map.put("operuserid", this.getLoginSessionBean().getId());
				this.ptrainCodeService.savePtrainCodeExcel(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainCode();
			return INPUT;
		}
//		return this.listPtrainCode();
		return SUCCESS;
	}
	/**
	 * *****************************JQuery相关******************************
	 */
	/**
	 * jq检测节点的编码是否已经存在
	 */
	public void checkCodeValueByJq(){
		try{
			//实例响应类 
			HttpServletRequest req = ServletActionContext.getRequest();
			String fatherid = func.Trim(req.getParameter("fatherid"));
			String codevalue = func.Trim(req.getParameter("codevalue"));
			String unitid = func.Trim(req.getParameter("unitid"));
			Map map = new HashMap();
			map.put("fatherid", fatherid);
			map.put("codevalue", codevalue);
			//map.put("unitid", unitid);
			this.print(this.ptrainCodeService.findCodeValueNum(map));
		}catch(Exception e){
			this.print("-1");
			e.printStackTrace();
		}			
	}
	/**
	 * 岗位培训 [删除]
	 */
	public void deletePtrainCodeByJq() {
		try {
			this.ptrainCodeService.deletePtrainCode(this.ptrainCodeBean.getId());
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	/**
	 * 更新[启用、禁止]
	 */
	public void updatePtrainCodeUsesignByJq(){
		try {
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainCodeBean.setMainuser(loginBean.getId());			
			this.ptrainCodeService.updatePtrainCodeUsesign(ptrainCodeBean);
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	/**
	 * 岗位培训 列表信息
	 */
	public String listPtrainCodeByJq() {
		this.fatherid = this.getStaticValueByName(this.fatherid);
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(this.querymap==null) {
				this.querymap = new HashMap();
				this.sortfield= "sortnum";
				this.querymap.put("unitid", loginBean.getUnitid());
			}
//			Map map = this.loginService.findUnitListByUser(loginBean, loginBean.getUnitid());
//			request.setAttribute("unitlist", (List)map.get("unitlist"));
			//数据列表
			Map map = new HashMap();
			//性质（1共性，2个性）
			map.put("nature", this.nature);
			if("2".equals(this.nature)){
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
			}
			map.put("fatherid", func.Trim("5"));
			map.put("sortfield",func.Trim(this.sortfield));
			map.put("isLevelList", "true");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("usesign",  func.Trim(this.querymap.get("usesign")));
			this.dataList = this.ptrainCodeService.findPtrainCodeList(map);
			this.print(JSONArray.fromObject(dataList).toString());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	/**
	 * 更新排序号
	 */
	public String updatePtrainCodeSortnumByJq() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String dataidstr=req.getParameter("dataidstr");
		if(!func.IsEmpty(dataidstr)){
			String[] dataidArr=dataidstr.split(",");
			PtrainCodeBean pBean=new PtrainCodeBean();
			LoginBean loginBean = this.getLoginSessionBean();
			pBean.setMainuser(loginBean.getId());
			for(int i=0;i<dataidArr.length;i++){
				pBean.setId(dataidArr[i]);
				pBean.setSortnum((i+1)+"");
				this.ptrainCodeService.savePtrainCode(pBean);
			}
		}
		return null;
	}

	//Get和Set方法

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

	public String getListpage() {
		return listpage;
	}

	public void setListpage(String listpage) {
		this.listpage = listpage;
	}

	public String getSetpage() {
		return setpage;
	}

	public void setSetpage(String setpage) {
		this.setpage = setpage;
	}

	public PtrainCodeBean getPtrainCodeBean() {
		return ptrainCodeBean;
	}

	public void setPtrainCodeBean(PtrainCodeBean ptrainCodeBean) {
		this.ptrainCodeBean = ptrainCodeBean;
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

}
