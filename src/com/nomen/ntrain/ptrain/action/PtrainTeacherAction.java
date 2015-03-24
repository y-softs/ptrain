package com.nomen.ntrain.ptrain.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainTeacherBean;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainTeacherService;
import com.nomen.ntrain.res.service.ResCodeService;

/**
 * @description 莆田岗位培训_讲师团队action层
 * @author 林木山
 * @date 2014-3-14
 */
@SuppressWarnings("all")
public class PtrainTeacherAction extends PtrainAction{

	private PtrainTeacherService		ptrainTeacherService;  	//莆田岗位培训_讲师团队业务接口
	private PtrainCodeService			ptrainCodeService;		//代码列表业务接口
	private LoginService 				loginService;      		//登录信息业务处理类
	private PtrainTeacherBean			ptrainTeacherBean;     	//莆田岗位培训_讲师团队信息表
	private Map<String,String>			querymap;				//传参map
	private List						dataList;				//数据列表

	/**
	 * @description列表信息
	 */
	public String listPtrainTeacher() {
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
			//专业类别列表
			map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("sortfield","t.sortnum,t.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainTeacherService.findPtrainTeacherList(map);
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
	public String setPtrainTeacher() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Map map = new HashMap();
			if(func.IsEmpty(func.Trim(this.ptrainTeacherBean.getId()))) {
				//新增
				map = new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("kindid", this.querymap.get("postid"));
				this.ptrainTeacherBean.setSortnum(this.ptrainTeacherService.findPtrainTeacherSortnum(map));
			} else {
				//修改
				this.ptrainTeacherBean = this.ptrainTeacherService.findPtrainTeacherBeanById(this.ptrainTeacherBean.getId());
			}
			//专业类别列表
			map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
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
	public String savePtrainTeacher() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainTeacherBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainTeacherBean.getId())))
					this.ptrainTeacherBean.setEstauser(loginBean.getId());
				this.ptrainTeacherService.savePtrainTeacher(this.ptrainTeacherBean);
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainTeacherBean=new PtrainTeacherBean();
				this.setPtrainTeacher();
			} else {
				rValue=this.listPtrainTeacher();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainTeacher();
		}
		return rValue;
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainTeacher() {
		try {
			this.ptrainTeacherService.deletePtrainTeacherById(this.ptrainTeacherBean.getId());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainTeacher();
	}

	//Get和Set方法
	public PtrainTeacherBean getPtrainTeacherBean() {
		return ptrainTeacherBean;
	}
	public void setPtrainTeacherBean(PtrainTeacherBean ptrainTeacherBean) {
		this.ptrainTeacherBean = ptrainTeacherBean;
	}
	public PtrainTeacherService getPtrainTeacherService() {
		return ptrainTeacherService;
	}
	public void setPtrainTeacherService(PtrainTeacherService ptrainTeacherService) {
		this.ptrainTeacherService = ptrainTeacherService;
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
}
