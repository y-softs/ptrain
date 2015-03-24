package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionsBean;
import com.nomen.ntrain.ptrain.bean.PtrainQuestionstempBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainQuestionsService;
import com.nomen.ntrain.ptrain.service.PtrainQuestionstempService;
import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.res.service.ResCodeService;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_试题action层
 * @author 林木山
 * @date 2014-3-5
 */
@SuppressWarnings("all")
public class PtrainQuestionsAction extends PtrainAction{

	private PtrainQuestionsService		ptrainQuestionsService; //莆田岗位培训_试题_试题业务接口
	private PtrainCodeService			ptrainCodeService;		//代码列表业务接口
	private ResCodeService				resCodeService;			//代码列表业务接口
	private LoginService 				loginService;      		//登录信息业务处理类
	private PtrainQuestionstempService	ptrainQuestionstempService;//莆田岗位培训_试题临时表业务接口
	private PtrainQuestionsBean			ptrainQuestionsBean;    //莆田岗位培训_试题_试题信息表
	private PtrainQuestionstempBean		ptrainQuestionstempBean;//莆田岗位培训_试题临时表信息表
	private Map<String,String>			querymap;				//传参map
	private List						dataList;				//数据列表
	//	=================辅助参数===================
	private String savePath; 					  			//保存文件的目录路径(通过依赖注入)

	/**
	 * 莆田岗位培训_试题 列表信息
	 */
	public String toForwardQuesList() {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			Map map = new HashMap();
			//专业类别
			map = new HashMap();
			map.put("nature",   "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
			//试题类型列表
			req.setAttribute("typeList", PtrainConstant.typeListOper());
			return SUCCESS;
	}
	
	
	
	/**
	 * 莆田岗位培训_试题 列表信息
	 */
	public String listPtrainQuestions() {
		try {
			Map map = new HashMap();
			//数据列表
			map = new HashMap();
			map.put("kindid", func.Trim(this.querymap.get("specid")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("fatherid", CodeFatherUtil.RES_QUE_TYPE);
			map.put("sortfield","q.sortnum,q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainQuestionsService.findPtrainQuestionsList(map);
			this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}

	/**
	 * 莆田岗位培训_试题 新增、修改跳转
	 */
	public String setPtrainQuestions() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Map map = new HashMap();
			if(func.IsEmpty(func.Trim(this.ptrainQuestionsBean.getId()))) {
				//新增
				map = new HashMap();
				this.ptrainQuestionsBean.setSortnum(this.ptrainQuestionsService.findPtrainQuestionsSortnum(map));
			} else {
				//修改
				this.ptrainQuestionsBean = this.ptrainQuestionsService.findPtrainQuestionsBeanById(this.ptrainQuestionsBean.getId());
			}
			map = new HashMap();
			//专业类别
			map = new HashMap();
			map.put("nature",   "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
			//试题类型列表
			req.setAttribute("typeList", PtrainConstant.typeListOper());
			req.setAttribute("singleSel", PtrainConstant.singleSel);
			req.setAttribute("multSel", PtrainConstant.multSel);
			req.setAttribute("judgeSel", PtrainConstant.judgeSel);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * 莆田岗位培训_试题 新增/修改[保存]
	 */
	public void savePtrainQuestions() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainQuestionsBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainQuestionsBean.getId())))
					this.ptrainQuestionsBean.setEstauser(loginBean.getId());
				String[] optionArr=req.getParameterValues("seloption");
				if(null!=optionArr&&optionArr.length>0){
					for(int i=0;i<optionArr.length;i++){
						String option=optionArr[i];
						if(i==0)this.ptrainQuestionsBean.setOption1(option);
						else if(i==1)this.ptrainQuestionsBean.setOption2(option);
						else if(i==2)this.ptrainQuestionsBean.setOption3(option);
						else if(i==3)this.ptrainQuestionsBean.setOption4(option);
						else if(i==4)this.ptrainQuestionsBean.setOption5(option);
						else if(i==5)this.ptrainQuestionsBean.setOption6(option);
						else if(i==6)this.ptrainQuestionsBean.setOption7(option);
					}
				}else{
					this.ptrainQuestionsBean.setOption1("");
					this.ptrainQuestionsBean.setOption2("");
					this.ptrainQuestionsBean.setOption3("");
					this.ptrainQuestionsBean.setOption4("");
					this.ptrainQuestionsBean.setOption5("");
					this.ptrainQuestionsBean.setOption6("");
					this.ptrainQuestionsBean.setOption7("");
				}
				this.ptrainQuestionsBean.setAnswer1(this.ptrainQuestionsBean.getAnswer1().replace(" ", "").replace(",", ""));
				this.ptrainQuestionsService.savePtrainQuestions(this.ptrainQuestionsBean);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 莆田岗位培训_试题 详细信息
	 */
	public String setPtrainQuestionsWin() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			this.ptrainQuestionsBean=this.ptrainQuestionsService.findPtrainQuestionsBeanById(this.ptrainQuestionsBean.getId());

			Map map = new HashMap();
			//专业类别
			map = new HashMap();
			map.put("nature",   "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			List<PtrainCodeBean> specList=this.ptrainCodeService.findPtrainCodeList(map);
			req.setAttribute("specList", specList);
			//试题类型列表
			req.setAttribute("typeList", PtrainConstant.typeListOper());
			req.setAttribute("singleSel", PtrainConstant.singleSel);
			req.setAttribute("multSel", PtrainConstant.multSel);
			req.setAttribute("judgeSel", PtrainConstant.judgeSel);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * 莆田岗位培训_试题 删除信息
	 */
	public void deletePtrainQuestions() {
		try {
				this.ptrainQuestionsService.deletePtrainQuestionsById(this.ptrainQuestionsBean.getId());
				this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	//==================Excel导入相关====================
	/**
	 * 莆田岗位培训_试题 导入excel 跳转
	 */
	public String setPtrainQuestionstempExcel() {
		HttpServletRequest req = ServletActionContext.getRequest();
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map map = new HashMap();
		//专业类别
		map = new HashMap();
		map.put("nature", "2");
		map.put("unitid", func.Trim(this.querymap.get("unitid")));
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
		map.put("usesign", "1");
		req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
		return SUCCESS;
	}
	
	/**
	 * 试题临时表 excel导入[保存]
	 */
	public String savePtrainQuestionstempExcel() {
		try {
			if(this.isValidToken()) {
				//上传附件并返回保存名
				String[] saveNameArr = this.execUpload(this.getSavePath(),0);
				//导入数据传参
				String fileFolder = ServletActionContext.getServletContext().getRealPath("/")+this.getSavePath();
				Map map=new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("postid", func.Trim(this.querymap.get("specidimp")));
				map.put("postidtemp", func.Trim(this.querymap.get("specname")));
				map.put("fileFolder", fileFolder);
				map.put("saveNameArr", saveNameArr);  
				//impsign导入标志 1：清空后导入 5：全部都追加 
				map.put("impsign", this.querymap.get("impsign"));
				this.ptrainQuestionstempService.savePtrainQuestionstempExcel(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainQuestionstempExcel();
			return INPUT;
		}
		return this.toForwardPtrainQuestionstemp();
	}

	/**
	 * 试题临时表 列表信息[跳转]
	 */
	public String toForwardPtrainQuestionstemp() {
		HttpServletRequest req = ServletActionContext.getRequest();
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		Map map = new HashMap();
		//专业类别
		map = new HashMap();
		map.put("nature", "2");
		map.put("unitid", func.Trim(this.querymap.get("unitid")));
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
		map.put("usesign", "1");
		req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
		//试题类型列表
		req.setAttribute("typeList", PtrainConstant.typeListOper());
		return SUCCESS;
	}
	
	/**
	 * 试题临时表 列表信息
	 */
	public void listPtrainQuestionstemp() {
			//数据列表
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("kindid", func.Trim(this.querymap.get("specidimp")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("showsign1", func.Trim(this.querymap.get("showsign1")));
			map.put("showsign2", func.Trim(this.querymap.get("showsign2")));
			map.put("sortfield", "q.id");
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainQuestionstempService.findPtrainQuestionstempList(map);
			this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
	}

	
	/**
	 * 试题临时表 修改 弹出
	 */
	public String setPtrainQuestionstempWin() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//修改
			this.ptrainQuestionstempBean = this.ptrainQuestionstempService.findPtrainQuestionstempBeanById(this.ptrainQuestionstempBean.getId());
			Map map = new HashMap();
			//专业类别
			map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));

			//试题类型列表
			req.setAttribute("typeList", PtrainConstant.typeListOper());
			req.setAttribute("singleSel", PtrainConstant.singleSel);
			req.setAttribute("multSel", PtrainConstant.multSel);
			req.setAttribute("judgeSel", PtrainConstant.judgeSel);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	
	/**
	 * 试题临时表 修改[保存]
	 */
	public void savePtrainQuestionstemp() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			String[] optionArr=req.getParameterValues("seloption");
			if(null!=optionArr&&optionArr.length>0){
				for(int i=0;i<optionArr.length;i++){
					String option=optionArr[i];
					if(i==0)this.ptrainQuestionstempBean.setOption1(option);
					else if(i==1)this.ptrainQuestionstempBean.setOption2(option);
					else if(i==2)this.ptrainQuestionstempBean.setOption3(option);
					else if(i==3)this.ptrainQuestionstempBean.setOption4(option);
					else if(i==4)this.ptrainQuestionstempBean.setOption5(option);
					else if(i==5)this.ptrainQuestionstempBean.setOption6(option);
					else if(i==6)this.ptrainQuestionstempBean.setOption7(option);
				}
			}else{
				this.ptrainQuestionstempBean.setOption1("");
				this.ptrainQuestionstempBean.setOption2("");
				this.ptrainQuestionstempBean.setOption3("");
				this.ptrainQuestionstempBean.setOption4("");
				this.ptrainQuestionstempBean.setOption5("");
				this.ptrainQuestionstempBean.setOption6("");
				this.ptrainQuestionstempBean.setOption7("");
			}
			this.ptrainQuestionstempBean.setAnswer1(this.ptrainQuestionstempBean.getAnswer1().replace(" ", "").replace(",", ""));
			if(func.Trim(this.ptrainQuestionstempBean.getDatasign()).equals("-1"))this.ptrainQuestionstempBean.setDatasign("1");
			this.ptrainQuestionstempService.savePtrainQuestionstemp(this.ptrainQuestionstempBean);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 试题临时表 删除信息
	 */
	public void deletePtrainQuestionstemp() {
		try {
			this.ptrainQuestionstempService.deletePtrainQuestionstempById(this.ptrainQuestionstempBean.getId());
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}
	/**
	 * 试题  临时表导入到主表
	 */
	public void savePrrainQuesExcel(){
		try {
			if(null==this.ptrainQuestionstempBean)this.ptrainQuestionstempBean=new PtrainQuestionstempBean();
			// 登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			Map param = new HashMap();
			param.put("unitid", loginBean.getUnitid());
			param.put("fatherid", CodeFatherUtil.RES_QUE_TYPE);
			param.put("postid", this.querymap.get("specidimp"));
			param.put("impsign", this.querymap.get("impsign"));
			param.put("operuserid", loginBean.getId());
			param.put("questempid", func.Trim(this.ptrainQuestionstempBean.getId()));
			this.ptrainQuestionstempService.savePrrainQuesExcel(param);		
			this.print("1");
		} catch (Exception e) {
			e.printStackTrace();
			this.print("-1");
		}
	}

	/**
	 * 试题临时表 取消
	 */
	public String setPtrainQuestionstempCan() {
		try {
			Map param = new HashMap();
			param.put("unitid", this.querymap.get("unitid"));
			param.put("kindid", this.querymap.get("postid"));
			this.ptrainQuestionstempService.deletePtrainQuestionstempByMap(param);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.toForwardPtrainQuestionstemp();
	}
	/**
	 * *****************************JQuery相关******************************
	 */
	/**
	 * 更新[启用、禁止]
	 */
	public void updatePtrainQuestionsUsesignByJq(){
		try {
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainQuestionsBean.setMainuser(loginBean.getId());			
			this.ptrainQuestionsService.updatePtrainQuestionsUsesign(ptrainQuestionsBean);
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	//Get和Set方法
	public PtrainQuestionsBean getPtrainQuestionsBean() {
		return ptrainQuestionsBean;
	}
	public void setPtrainQuestionsBean(PtrainQuestionsBean ptrainQuestionsBean) {
		this.ptrainQuestionsBean = ptrainQuestionsBean;
	}
	public PtrainQuestionsService getPtrainQuestionsService() {
		return ptrainQuestionsService;
	}
	public void setPtrainQuestionsService(PtrainQuestionsService ptrainQuestionsService) {
		this.ptrainQuestionsService = ptrainQuestionsService;
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

	public ResCodeService getResCodeService() {
		return resCodeService;
	}

	public void setResCodeService(ResCodeService resCodeService) {
		this.resCodeService = resCodeService;
	}

	public PtrainQuestionstempBean getPtrainQuestionstempBean() {
		return ptrainQuestionstempBean;
	}

	public void setPtrainQuestionstempBean(
			PtrainQuestionstempBean ptrainQuestionstempBean) {
		this.ptrainQuestionstempBean = ptrainQuestionstempBean;
	}

	public PtrainQuestionstempService getPtrainQuestionstempService() {
		return ptrainQuestionstempService;
	}

	public void setPtrainQuestionstempService(
			PtrainQuestionstempService ptrainQuestionstempService) {
		this.ptrainQuestionstempService = ptrainQuestionstempService;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
}
