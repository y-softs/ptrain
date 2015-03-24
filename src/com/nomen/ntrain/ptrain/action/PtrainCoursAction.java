package com.nomen.ntrain.ptrain.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainCoursFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainCoursService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFile;

/**
 * @description 莆田岗位培训_课件资源表action层
 * @author 林木山
 * @date 2014-11-15
 */
@SuppressWarnings("all")
public class PtrainCoursAction extends PtrainAction{

	private PtrainCoursService		ptrainCoursService;  	//莆田岗位培训_课件资源表业务接口
	private PtrainCodeService		ptrainCodeService;		//代码列表业务接口
	private PtrainFileService		ptrainFileService;		//附件表业务接口
	private PtrainCoursBean			ptrainCoursBean;     	//莆田岗位培训_课件资源表信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	//=================辅助=================
	private String					sign;					//1课件编制 2归口审核 3课件维护
	private String					listpage;				//列表页面
	private String					setpage;				//设置页面
	private String 					savePath; 				//保存文件的目录路径(通过依赖注入)
	//=================文件上传==============
	private File     uploads;        //视图层控件名字要与之相同
	private String   uploadsFileName;

	/**
	 * @description列表信息
	 */
	public String listPtrainCours() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			String flowsta="";
			if("1".equals(this.sign)){
				//课件编制
				flowsta=PtrainCoursFlowStaEnum.XQSB.getKey()+"";
				List stateList=new ArrayList();
				for(CheckRemarkEnum d:CheckRemarkEnum.values()){
					stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
				}
				String jsonList=JSONArray.fromObject(stateList).toString();
				req.setAttribute("stateList", jsonList);
				req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINCOURS.getKey()+"");
				req.setAttribute("SHEN_BAO", CheckSignConstant.SHEN_BAO);
				req.setAttribute("CHE_HUI", CheckSignConstant.CHE_HUI);
			}else if("2".equals(this.sign)){
				//归口审核
				flowsta=PtrainCoursFlowStaEnum.XQSH.getKey()+"";
				List stateList=new ArrayList();
				for(CheckRemarkEnum d:CheckRemarkEnum.values()){
					stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
				}
				String jsonList=JSONArray.fromObject(stateList).toString();
				req.setAttribute("stateList", jsonList);
				req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINCOURS.getKey()+"");
				req.setAttribute("TUI_HUI", CheckSignConstant.TUI_HUI);
			}else if("3".equals(this.sign)){
				//课件维护
				flowsta=PtrainCoursFlowStaEnum.GD.getKey()+"";
			}
			this.querymap.put("flowsta", flowsta);
			if(!"0".equals(this.fatherid)){
				//课件目录--查询父级课件名称
				this.ptrainCoursBean = this.ptrainCoursService.findPtrainCoursBeanById(this.fatherid);
			}
			req.setAttribute("XQSB", PtrainCoursFlowStaEnum.XQSB.getKey()+"");
			req.setAttribute("XQSH", PtrainCoursFlowStaEnum.XQSH.getKey()+"");
			req.setAttribute("GD", PtrainCoursFlowStaEnum.GD.getKey()+"");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description新增、修改跳转（目录式课件或子目录）
	 */
	public String setPtrainCours() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if(!func.IsEmpty(func.Trim(this.fatherid))){
				if("0".equals(this.fatherid)){
					//新增/修改课件--课件类别
					Map map = new HashMap();
					if(null!=this.querymap){
						map.put("nature", "2");
						map.put("unitid", func.Trim(this.querymap.get("unitid")));
						map.put("fatherid", CodeFatherUtil.PTRAIN_COURS);
						map.put("usesign", "1");
						req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
					}
					if(!func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))) {
						//附件列表
						map=new HashMap();
						map.put("recid", this.ptrainCoursBean.getId());
						map.put("modsign",PtrainConstant.FILE_MODSIGN_COURS_FM);
						req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
					}
				}else{
					//课件目录--查询父级课件名称
					PtrainCoursBean bean = this.ptrainCoursService.findPtrainCoursBeanById(this.fatherid);
					this.querymap.put("coursname", bean.getTitle());
					if(!this.ptrainCoursBean.getFatherid().equals(this.fatherid)){
						//课件目录--查询父级目录名称
						PtrainCoursBean fBean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getFatherid());
						this.querymap.put("fathername", fBean.getTitle());
					}
				}
			}
			if(!func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))) {
				//修改
				this.ptrainCoursBean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getId());
			}
			req.setAttribute("GD", PtrainCoursFlowStaEnum.GD.getKey()+"");
			req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_COURS_FM);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * @description新增、修改跳转（单个课件）
	 */
	public String setPtrainCours2() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if(!func.IsEmpty(func.Trim(this.fatherid))){
				if("0".equals(this.fatherid)){
					//新增/修改课件--课件类别
					Map map = new HashMap();
					if(null!=this.querymap){
						map.put("nature", "2");
						map.put("unitid", func.Trim(this.querymap.get("unitid")));
						map.put("fatherid", CodeFatherUtil.PTRAIN_COURS);
						map.put("usesign", "1");
						req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
					}
					if(!func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))) {
						//附件列表(封面)
						map=new HashMap();
						map.put("recid", this.ptrainCoursBean.getId());
						map.put("modsign",PtrainConstant.FILE_MODSIGN_COURS_FM);
						req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
						//附件列表(文件)
						map=new HashMap();
						map.put("recid", this.ptrainCoursBean.getId());
						map.put("modsign",PtrainConstant.FILE_MODSIGN_COURS);
						req.setAttribute("fileslist2", this.ptrainFileService.findPtrainFileList(map));
					}
				}
			}
			if(!func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))) {
				//修改
				this.ptrainCoursBean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getId());
			}
			req.setAttribute("GD", PtrainCoursFlowStaEnum.GD.getKey()+"");
			req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_COURS_FM);
			req.setAttribute("modsign2", PtrainConstant.FILE_MODSIGN_COURS);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}


	/**
	 * @description新增/修改[保存]
	 */
	public String savePtrainCours() {
		String rValue=INPUT;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if(this.isValidToken()) {
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				String id=func.Trim(this.ptrainCoursBean.getId());
				this.ptrainCoursBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))){
					if(func.IsEmpty(func.Trim(this.ptrainCoursBean.getEndsign())))
						this.ptrainCoursBean.setEndsign("0");
					if((PtrainCoursFlowStaEnum.XQSB.getKey()+"").equals(this.ptrainCoursBean.getFlowsta()))
						this.ptrainCoursBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
					this.ptrainCoursBean.setEstauser(loginBean.getId());
				}
				this.ptrainCoursService.savePtrainCours(this.ptrainCoursBean);
				String FINAL_RANDOMSTR=req.getParameter("FINAL_RANDOMSTR");
				if(func.IsEmpty(id)&&!func.IsEmpty(FINAL_RANDOMSTR)){
					//更新recId根据FINAL_RANDOMSTR
					this.ptrainFileService.updatePtrainFileByRandomStr(FINAL_RANDOMSTR, this.ptrainCoursBean.getId());
				}
			}
			if(func.Trim(this.gosign).equals("1")) {
				String fatheridV=this.ptrainCoursBean.getFatherid();
				this.ptrainCoursBean=new PtrainCoursBean();
				this.ptrainCoursBean.setFatherid(fatheridV);
				this.setPtrainCours();
				this.reloadParentPage2();
			} else {
				this.reloadParentPage();
				rValue=Constant.NO_DATA;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainCours();
		}
		return rValue;
	}

	/**
	 * 附件列表信息
	 */
	public String listPtrainCoursFile() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//课件名称
			PtrainCoursBean fBean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getFatherid());
			this.ptrainCoursBean.setTitle(fBean.getTitle());
			if(!func.IsEmpty(this.ptrainCoursBean.getId())){
				//目录信息
				PtrainCoursBean bean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getId());
				this.ptrainCoursBean.setStrflag(bean.getTitle());
				this.ptrainCoursBean.setFileid(bean.getFileid());
				this.ptrainCoursBean.setEndsign(bean.getEndsign());
			}
			req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_COURS);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * 附件上传 跳转
	 */
	public String setPtrainCoursFile() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			req.setAttribute("modsign", PtrainConstant.FILE_MODSIGN_COURS);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}
	//=======================流程相关====================
	/**
	 * 课件编制 申报/撤回
	 * 归口审核 同意(不同意)/退回
	 */
	public String updatePtrainCoursChk() {
		String rValue=Constant.NO_DATA;
		String chksign=func.Trim(this.querymap.get("chksign"));
		try {
			if(func.IsEmpty(chksign))return SUCCESS;
			
			Map map=new HashMap();
			this.ptrainCoursBean.setMainuser(this.getLoginSessionBean().getId());
			map.put("ptrainCoursBean", this.ptrainCoursBean);
			map.put("chksign",chksign);
			map.put("chkmemo",func.Trim(this.querymap.get("chkmemo")));
			map.put("dataChange", func.Trim(this.querymap.get("dataChange")));
			this.ptrainCoursService.updatePtrainCoursChk(map);
			
			if(CheckSignConstant.SHEN_BAO.equals(chksign)||CheckSignConstant.CHE_HUI.equals(chksign)||CheckSignConstant.TUI_HUI.equals(chksign)){
				//申报 撤回 退回
				rValue=this.listPtrainCours();
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
	public String setPtrainCoursChk() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Map map = new HashMap();
			if(null==this.querymap)this.querymap = new HashMap<String,String>();
			if(func.IsEmpty(func.Trim(this.querymap.get("querylist")))){
				//课件类别
				map = new HashMap();
				map.put("nature", "2");
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", CodeFatherUtil.PTRAIN_COURS);
				map.put("usesign", "1");
				req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
				if(!func.IsEmpty(func.Trim(this.ptrainCoursBean.getId()))) {
					this.ptrainCoursBean = this.ptrainCoursService.findPtrainCoursBeanById(this.ptrainCoursBean.getId());
				}
				req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
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
	public void listPtrainCoursByIds(){
		String coursid  = this.ptrainCoursBean.getId();
		//数据列表
		Map map = new HashMap();
		map.put("thislev", "true");
		map.put("coursid", coursid);
		this.printList(this.ptrainCoursService.findPtrainCoursListNoPage(map));
	}

	/**
	 * 归口审核 批量审批 修改[保存]
	 */
	public String savePtrainCoursByChk() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainCoursBean.setMainuser(loginBean.getId());
			this.ptrainCoursService.savePtrainCours(this.ptrainCoursBean);
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
	public String listPtrainCoursByJq() {
		try {
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
			}
			if(func.IsEmpty(this.getSortfield()))this.setSortfield("co.estatime,co.id");
			//数据列表
			Map map = new HashMap();
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("fatherid", this.fatherid);
			map.put("flowsta", func.Trim(this.querymap.get("flowsta")));
			map.put("flowstathis", func.Trim(this.querymap.get("flowstathis")));
			map.put("courstype", func.Trim(this.querymap.get("courstype")));
			map.put("sortfield",this.getSortfield());
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			if("0".equals(this.fatherid)){
				//课件列表
				map.put("codefatherid", CodeFatherUtil.PTRAIN_COURS);
				map.put("tagpage",this.getTagpage());
				map.put("record",this.getRecord());
				map.put("thislev","true");
				this.dataList=this.ptrainCoursService.findPtrainCoursList(map);
				this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
			}else{
				//目录列表
				this.printList(this.ptrainCoursService.findPtrainCoursListNoPage(map));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 课件类别
	 */
	public String findSpecListByJq(){
		try{
			//专业类别
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_COURS);
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
	public String deletePtrainCoursByJq() {
		try {
			if(null==this.querymap)this.querymap = new HashMap<String,String>();
			String fatherPath = ServletActionContext.getServletContext().getRealPath("");
			this.ptrainCoursService.deletePtrainCours(this.ptrainCoursBean.getId(),this.querymap.get("delsign"),fatherPath);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	public String uploadFile(){
		  HttpServletResponse res = ServletActionContext.getResponse();
		  res.setCharacterEncoding("utf-8");
		try{
			  String extName = "";//扩展名
			  String newFileName= "";//新文件名
			  String nowTime = new SimpleDateFormat("yyyymmddHHmmss").format(new Date());//当前时间精确到秒+5位随机数
			  String rodomStr = (int)(10000+Math.random()*99999)+"";
			  String temp_savePath = ServletActionContext.getRequest().getRealPath(this.savePath);
			  /*//获取扩展名
			  if(uploadsFileName.lastIndexOf(".")>=0){
			   extName = uploadsFileName.substring(uploadsFileName.lastIndexOf("."));
			  }
			  newFileName = nowTime+rodomStr+extName;*/
			  newFileName = uploadsFileName;
			  //判断该目录是否存在，若不存在，则创建
			  PubFile docFolder = new PubFile(temp_savePath);
			  if(!docFolder.isExists()) {
					  docFolder.createFilefolder();
			  } 
			  uploads.renameTo(new File(temp_savePath+"/"+newFileName));
			  res.getWriter().print(newFileName);
		}
		catch(IOException ex){
			  ex.printStackTrace();
			  return null;
		}
		catch(Exception ex){
			  ex.printStackTrace();
			  return null;
		}
		return null; //这里不需要页面转向，所以返回空就可以了
	}

	//Get和Set方法
	public PtrainCoursBean getPtrainCoursBean() {
		return ptrainCoursBean;
	}
	public void setPtrainCoursBean(PtrainCoursBean ptrainCoursBean) {
		this.ptrainCoursBean = ptrainCoursBean;
	}
	public PtrainCoursService getPtrainCoursService() {
		return ptrainCoursService;
	}
	public void setPtrainCoursService(PtrainCoursService ptrainCoursService) {
		this.ptrainCoursService = ptrainCoursService;
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

	public String getSetpage() {
		return setpage;
	}

	public void setSetpage(String setpage) {
		this.setpage = setpage;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public File getUploads() {
		return uploads;
	}

	public void setUploads(File uploads) {
		this.uploads = uploads;
	}

	public String getUploadsFileName() {
		return uploadsFileName;
	}

	public void setUploadsFileName(String uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getListpage() {
		return listpage;
	}

	public void setListpage(String listpage) {
		this.listpage = listpage;
	}
}