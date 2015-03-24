package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainPorgBean;
import com.nomen.ntrain.ptrain.service.PtrainPorgService;

/**
 * @description 莆田岗位培训_咨询公司action层
 * @author 林木山
 * @date 2014-3-13
 */
@SuppressWarnings("all")
public class PtrainPorgAction extends PtrainAction{

	private PtrainPorgService		ptrainPorgService;  	//莆田岗位培训_咨询公司业务接口
	private LoginService 			loginService;      		//登录信息业务处理类
	private PtrainPorgBean			ptrainPorgBean;     	//莆田岗位培训_咨询公司信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	//	=================辅助参数===================
	private String savePath; 					  					//保存文件的目录路径(通过依赖注入)


	/**
	 * @description列表信息
	 */
	public String listPtrainPorg() {
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
			req.setAttribute("unitlist", this.loginService.findOwnUnitListById(loginBean.getUnitid()));
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("sortfield","sortnum,id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainPorgService.findPtrainPorgList(map);
			this.setTotal(String.valueOf(map.get("total")));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description新增、修改跳转
	 */
	public String setPtrainPorg() {
		try {
			if(func.IsEmpty(func.Trim(this.ptrainPorgBean.getId()))) {
				//新增
				Map map = new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				this.ptrainPorgBean.setSortnum(this.ptrainPorgService.findPtrainPorgSortnum(map));
			} else {
				//修改
				this.ptrainPorgBean = this.ptrainPorgService.findPtrainPorgBeanById(this.ptrainPorgBean.getId());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}

	/**
	 * @description新增/修改[保存]
	 */
	public String savePtrainPorg() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainPorgBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainPorgBean.getId())))
					this.ptrainPorgBean.setEstauser(loginBean.getId());
				this.ptrainPorgService.savePtrainPorg(this.ptrainPorgBean);
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainPorgBean=new PtrainPorgBean();
				this.setPtrainPorg();
			} else {
				rValue=this.listPtrainPorg();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainPorg();
		}
		return rValue;
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainPorg() {
		try {
			this.ptrainPorgService.deletePtrainPorgById(this.ptrainPorgBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainPorg();
	}
	/**
	 *@description 导入excel 跳转
	 */
	public String setPtrainPorgExcel() {
		HttpServletRequest req = ServletActionContext.getRequest();
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	/**
	 *  @description excel导入[保存]
	 */
	public String savePtrainPorgExcel() {
		try {
			if(this.isValidToken()) {
				//上传附件并返回保存名
				String[] saveNameArr = this.execUpload(this.getSavePath(),0);
				//导入数据传参
				String fileFolder = ServletActionContext.getServletContext().getRealPath("/")+this.getSavePath();
				Map map=new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fileFolder", fileFolder);
				map.put("saveNameArr", saveNameArr);  
				//impsign导入标志 1：清空后导入 5：全部都追加 
				map.put("impsign", this.querymap.get("impsign"));
				map.put("operuserid", this.getLoginSessionBean().getId());
				this.ptrainPorgService.savePtrainPorgImpExcel(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainPorgExcel();
			return INPUT;
		}
		return this.listPtrainPorg();
	}
	/**
	 * @description Excel导出
	 */
	public String savePtrainPorgExpExcel() {
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		try{
			//岗位名称
			Map map = new HashMap();
			map.put("unitid", this.querymap.get("unitid"));
			map.put("sortfield","sortnum,id");
			map.put("fields",func.Trim(this.fields));
			map.put("keyword",func.Trim(this.keyword));
			this.ptrainPorgService.savePtrainPorgExpExcel(map, ServletActionContext.getResponse());
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
	 * 更新[启用、禁止]
	 */
	public void updatePtrainPorgUsesignByJq(){
		try {
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainPorgBean.setMainuser(loginBean.getId());			
			this.ptrainPorgService.savePtrainPorg(ptrainPorgBean);
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	//Get和Set方法
	public PtrainPorgBean getPtrainPorgBean() {
		return ptrainPorgBean;
	}
	public void setPtrainPorgBean(PtrainPorgBean ptrainPorgBean) {
		this.ptrainPorgBean = ptrainPorgBean;
	}
	public PtrainPorgService getPtrainPorgService() {
		return ptrainPorgService;
	}
	public void setPtrainPorgService(PtrainPorgService ptrainPorgService) {
		this.ptrainPorgService = ptrainPorgService;
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

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
