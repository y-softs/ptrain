package com.nomen.ntrain.ptrain.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainContentBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainContentFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainContentKindEnum;
import com.nomen.ntrain.ptrain.enums.PtrainContentFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainContentService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_资源表action层
 * @author 林木山
 * @date 2014-11-17
 */
@SuppressWarnings("all")
public class PtrainContentAction extends PtrainAction{

	private PtrainContentService	ptrainContentService;  	//莆田岗位培训_资源表业务接口
	private PtrainCodeService		ptrainCodeService;		//代码列表业务接口
	private PtrainFileService		ptrainFileService;		//附件表业务接口
	private PtrainContentBean		ptrainContentBean;     	//莆田岗位培训_资源表信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	//=================辅助=================
	private String					sign;					//1影音影视库 2经典书籍库 3规程文件  4典型经验库
	private String					opersign;				//1上传 2归口审核 3维护
	private String					listpage;				//列表页面
	private String					setpage;				//设置页面
	private String 					savePath; 				//保存文件的目录路径(通过依赖注入)

	/**
	 * @description列表信息
	 */
	public String listPtrainContent() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			String kind="";
			if("1".equals(this.sign)){
				//影音影视库
				kind=PtrainContentKindEnum.CONTENT_FILM.getKey()+"";
				this.fatherid=CodeFatherUtil.PTRAIN_MOVIES;
			}else if("2".equals(this.sign)){
				//经典书籍库
				kind=PtrainContentKindEnum.CONTENT_BOOKS.getKey()+"";
				this.fatherid=CodeFatherUtil.PTRAIN_BOOKS;
			}else if("3".equals(this.sign)){
				//规程文件
				kind=PtrainContentKindEnum.CONTENT_RULES.getKey()+"";
				this.fatherid=CodeFatherUtil.PTRAIN_RULES;
			}else if("4".equals(this.sign)){
				//典型经验库
				kind=PtrainContentKindEnum.CONTENT_EXPER.getKey()+"";
				this.fatherid=CodeFatherUtil.PTRAIN_EXPER;
			}
			this.querymap.put("kind", kind);
			//影音影视库/经典书籍库/典型经验库--流程
			String flowsta="";
			if("1".equals(this.opersign)){
				//上传
				flowsta=PtrainContentFlowStaEnum.XQSB.getKey()+"";
				List stateList=new ArrayList();
				for(CheckRemarkEnum d:CheckRemarkEnum.values()){
					stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
				}
				String jsonList=JSONArray.fromObject(stateList).toString();
				req.setAttribute("stateList", jsonList);
				req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINCONT.getKey()+"");
				req.setAttribute("SHEN_BAO", CheckSignConstant.SHEN_BAO);
				req.setAttribute("CHE_HUI", CheckSignConstant.CHE_HUI);
			}else if("2".equals(this.opersign)){
				//归口审核
				flowsta=PtrainContentFlowStaEnum.XQSH.getKey()+"";
				List stateList=new ArrayList();
				for(CheckRemarkEnum d:CheckRemarkEnum.values()){
					stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
				}
				String jsonList=JSONArray.fromObject(stateList).toString();
				req.setAttribute("stateList", jsonList);
				req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINCONT.getKey()+"");
				req.setAttribute("TUI_HUI", CheckSignConstant.TUI_HUI);
			}else if("3".equals(this.opersign)){
				//维护
				flowsta=PtrainContentFlowStaEnum.GD.getKey()+"";
			}
			this.querymap.put("flowsta", flowsta);
			
			req.setAttribute("XQSB", PtrainContentFlowStaEnum.XQSB.getKey()+"");
			req.setAttribute("XQSH", PtrainContentFlowStaEnum.XQSH.getKey()+"");
			req.setAttribute("GD", PtrainContentFlowStaEnum.GD.getKey()+"");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	
	/**
	 * @description新增、修改跳转
	 * @description详细页面
	 */
	public String setPtrainContent() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			if(null!=this.querymap){
				map.put("nature", "2");
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", this.fatherid);
				map.put("usesign", "1");
				req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
			}
			if(func.IsEmpty(func.Trim(this.ptrainContentBean.getId()))) {
				//新增
				map = new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("kind", func.Trim(this.querymap.get("kind")));
				String sortnum=this.ptrainContentService.findPtrainContentSortnum(map);
				this.ptrainContentBean.setSortnum(sortnum);
			} else {
				//修改
				this.ptrainContentBean = this.ptrainContentService.findPtrainContentBeanById(this.ptrainContentBean.getId());
				//附件列表
				map=new HashMap();
				map.put("recid", this.ptrainContentBean.getId());
				map.put("modsign",PtrainConstant.FILE_MODSIGN_CONT);
				req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
			}
			req.setAttribute("GD", PtrainContentFlowStaEnum.GD.getKey()+"");
			req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_CONT);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description新增/修改[保存]
	 */
	public String savePtrainContent() {
		String rValue=INPUT;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainContentBean.setMainuser(loginBean.getId());
				String id=func.Trim(this.ptrainContentBean.getId());
				if(func.IsEmpty(id)){
					if((PtrainContentFlowStaEnum.XQSB.getKey()+"").equals(this.ptrainContentBean.getFlowsta()))
						this.ptrainContentBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
					this.ptrainContentBean.setEstauser(loginBean.getId());
				}
				this.ptrainContentService.savePtrainContent(this.ptrainContentBean);
				String FINAL_RANDOMSTR=req.getParameter("FINAL_RANDOMSTR");
				if(func.IsEmpty(id)&&!func.IsEmpty(FINAL_RANDOMSTR)){
					//更新recId根据FINAL_RANDOMSTR
					this.ptrainFileService.updatePtrainFileByRandomStr(FINAL_RANDOMSTR, this.ptrainContentBean.getId());
				}
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainContentBean=new PtrainContentBean();
				this.setPtrainContent();
				this.reloadParentPage2();
			} else {
				this.reloadParentPage();
				rValue=Constant.NO_DATA;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainContent();
		}
		return rValue;
	}
	//=======================流程相关====================
	/**
	 * 影音影视上传/书籍上传/典型经验上传 申报/撤回
	 * 归口审核 同意(不同意)/退回
	 */
	public String updatePtrainContentChk() {
		String rValue=Constant.NO_DATA;
		String chksign=func.Trim(this.querymap.get("chksign"));
		try {
			if(func.IsEmpty(chksign))return SUCCESS;
			
			Map map=new HashMap();
			this.ptrainContentBean.setMainuser(this.getLoginSessionBean().getId());
			map.put("ptrainContentBean", this.ptrainContentBean);
			map.put("chksign",chksign);
			map.put("chkmemo",func.Trim(this.querymap.get("chkmemo")));
			map.put("dataChange", func.Trim(this.querymap.get("dataChange")));
			this.ptrainContentService.updatePtrainContentChk(map);
			
			if(CheckSignConstant.SHEN_BAO.equals(chksign)||CheckSignConstant.CHE_HUI.equals(chksign)||CheckSignConstant.TUI_HUI.equals(chksign)){
				//申报 撤回 退回
				rValue=this.listPtrainContent();
			}else{//同意 不同意
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return rValue;
	}
	/**
	 * 归口审核 审批/批量审批
	 * 归口审核 批量审批 修改[弹出]
	 */
	public String setPtrainContentChk() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Map map = new HashMap();
			if(null==this.querymap)this.querymap = new HashMap<String,String>();
			if(func.IsEmpty(func.Trim(this.querymap.get("querylist")))){
				//课件类别
				map = new HashMap();
				map.put("nature", "2");
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", this.fatherid);
				map.put("usesign", "1");
				req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
				if(!func.IsEmpty(func.Trim(this.ptrainContentBean.getId()))) {
					this.ptrainContentBean = this.ptrainContentService.findPtrainContentBeanById(this.ptrainContentBean.getId());
				}
				//附件列表
				map=new HashMap();
				map.put("recid", this.ptrainContentBean.getId());
				map.put("modsign",PtrainConstant.FILE_MODSIGN_CONT);
				req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
				req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_CONT);
			}
			req.setAttribute("TONG_YI", CheckSignConstant.TONG_YI);
			req.setAttribute("BU_TONG_YI", CheckSignConstant.BU_TONG_YI);
			this.getOpraterInfoIntoRequest();
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			return Constant.NO_DATA;
		}
		return SUCCESS;
	}
	
	/**
	 * 归口审核 批量审批[通过ID串查询列表]
	 */
	public void listPtrainContentByIds(){
		String contentid  = this.ptrainContentBean.getId();
		//数据列表
		Map map = new HashMap();
		map.put("contentid", contentid);
		this.printList(this.ptrainContentService.findPtrainContentListNoPage(map));
	}

	/**
	 * 归口审核 批量审批 修改[保存]
	 */
	public String savePtrainContentByChk() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainContentBean.setMainuser(loginBean.getId());
			this.ptrainContentService.savePtrainContent(this.ptrainContentBean);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		this.reloadParentPage();
		return Constant.NO_DATA;
	}

	//=====================JQuery相关====================
	/**
	 * @description列表信息
	 */
	public String listPtrainContentByJq() {
		try {
			if(func.IsEmpty(this.getSortfield()))this.setSortfield("co.sortnum desc,co.id desc");
			//数据列表
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("kind", func.Trim(this.querymap.get("kind")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("usesign", func.Trim(this.querymap.get("usesign")));
			map.put("fatherid", this.fatherid);
			map.put("flowsta", func.Trim(this.querymap.get("flowsta")));
			map.put("flowstathis", func.Trim(this.querymap.get("flowstathis")));
			map.put("sortfield",this.getSortfield());
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainContentService.findPtrainContentList(map);
			this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 资源类别
	 */
	public String findSpecListByJq(){
		try{
			//专业类别
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", this.fatherid);
			map.put("usesign", "1");
			this.print(JSONArray.fromObject(this.ptrainCodeService.findPtrainCodeList(map)).toString());
		}catch(Exception e){
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @description删除信息
	 */
	public String deletePtrainContentByJq() {
		try {
			String fatherPath = ServletActionContext.getServletContext().getRealPath("");
			this.ptrainContentService.deletePtrainContent(this.ptrainContentBean.getId(),fatherPath);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 更新[启用、禁止]
	 */
	public void updatePtrainContentUsesignByJq(){
		try {
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainContentBean.setMainuser(loginBean.getId());			
			this.ptrainContentService.savePtrainContent(ptrainContentBean);
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
	}

	//Get和Set方法
	public PtrainContentBean getPtrainContentBean() {
		return ptrainContentBean;
	}
	public void setPtrainContentBean(PtrainContentBean ptrainContentBean) {
		this.ptrainContentBean = ptrainContentBean;
	}
	public PtrainContentService getPtrainContentService() {
		return ptrainContentService;
	}
	public void setPtrainContentService(PtrainContentService ptrainContentService) {
		this.ptrainContentService = ptrainContentService;
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

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}

	public PtrainFileService getPtrainFileService() {
		return ptrainFileService;
	}

	public void setPtrainFileService(PtrainFileService ptrainFileService) {
		this.ptrainFileService = ptrainFileService;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSetpage() {
		return setpage;
	}

	public void setSetpage(String setpage) {
		this.setpage = setpage;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOpersign() {
		return opersign;
	}

	public void setOpersign(String opersign) {
		this.opersign = opersign;
	}

	public String getListpage() {
		return listpage;
	}

	public void setListpage(String listpage) {
		this.listpage = listpage;
	}
}