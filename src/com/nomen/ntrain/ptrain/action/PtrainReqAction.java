package com.nomen.ntrain.ptrain.action;

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
import com.nomen.ntrain.ptrain.bean.PtrainReqBean;
import com.nomen.ntrain.ptrain.bean.PtrainReqtempBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.enums.PtrainFlowModeSignEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqFlowStaEnum;
import com.nomen.ntrain.ptrain.enums.PtrainReqReqtypeEnum;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainFileService;
import com.nomen.ntrain.ptrain.service.PtrainReqService;
import com.nomen.ntrain.ptrain.service.PtrainReqtempService;
import com.nomen.ntrain.util.CheckRemarkEnum;
import com.nomen.ntrain.util.CheckSignConstant;
import com.nomen.ntrain.util.Constant;

/**
 * @description 莆田岗位培训_培训点菜action层
 * @author 林木山
 * @date 2014-3-14
 */
@SuppressWarnings("all")
public class PtrainReqAction extends PtrainAction{

	private PtrainReqService		ptrainReqService;  	//莆田岗位培训_培训点菜业务接口
	private PtrainCodeService		ptrainCodeService;	//代码列表业务接口
	private PtrainFileService		ptrainFileService;	//附件表业务接口
	private PtrainReqtempService	ptrainReqtempService;//莆田岗位培训_培训点菜临时表业务接口
	private LoginService 			loginService;      	//登录信息业务处理类
	private PtrainReqBean			ptrainReqBean;     	//莆田岗位培训_培训点菜信息表
	private PtrainReqtempBean		ptrainReqtempBean;  //莆田岗位培训_培训点菜临时信息表
	private Map<String,String>		querymap;			//传参map
	private List					dataList;			//数据列表
	//=================辅助=================
	private String					sign;				//1培训中心加菜 2兼职教师加菜 3员工加菜 4菜单审批 5汇总维护 6课件培训点菜
	private String 					savePath; 			//保存文件的目录路径(通过依赖注入)
	private String					listpage;			//列表页面
	private String					setpage;			//设置页面
	private String					listtemppage;		//列表页面[临时]
	private String					settemppage;		//设置页面[临时]

	/**
	 * @description列表信息
	 */
	public String listPtrainReq() {
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		//初始化
		if(null==this.querymap){
			this.querymap = new HashMap<String,String>();
			this.querymap.put("unitid", loginBean.getUnitid());
		}
		HttpServletRequest req = ServletActionContext.getRequest();
		String reqtype="",reqtypestr="",reqtypegath="",flowsta="",flowstathis="",flowstagath="",state="",requserid="";
		if("1".equals(this.sign)){
			//培训中心加菜
			reqtype=PtrainReqReqtypeEnum.REQ_COM.getKey()+"";
		}else if("2".equals(this.sign)){
			//兼职教师加菜
			reqtype=PtrainReqReqtypeEnum.REQ_EXP.getKey()+"";
			flowsta=PtrainReqFlowStaEnum.XQSB.getKey()+"";;
			List stateList=new ArrayList();
			for(CheckRemarkEnum d:CheckRemarkEnum.values()){
				stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
			}
			String jsonList=JSONArray.fromObject(stateList).toString();
			req.setAttribute("stateList", jsonList);
			
			req.setAttribute("SHEN_BAO", CheckSignConstant.SHEN_BAO);
			req.setAttribute("CHE_HUI", CheckSignConstant.CHE_HUI);
			req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
		}else if("3".equals(this.sign)){
			//员工加菜
			reqtype=PtrainReqReqtypeEnum.REQ_USER.getKey()+"";
			requserid=loginBean.getId();
			flowsta=PtrainReqFlowStaEnum.XQSB.getKey()+"";
			List stateList=new ArrayList();
			for(CheckRemarkEnum d:CheckRemarkEnum.values()){
				stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
			}
			String jsonList=JSONArray.fromObject(stateList).toString();
			req.setAttribute("stateList", jsonList);
			
			req.setAttribute("SHEN_BAO", CheckSignConstant.SHEN_BAO);
			req.setAttribute("CHE_HUI", CheckSignConstant.CHE_HUI);
			req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
			this.querymap.put("requserid", requserid);
		}else if("4".equals(this.sign)){
			//菜单审批
			reqtypestr=PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey();
			flowsta=PtrainReqFlowStaEnum.XQSH.getKey()+"";
			//if(!func.IsEmpty(func.Trim(this.querymap.get("showsign")))){}
			flowstathis=PtrainReqFlowStaEnum.XQSH.getKey()+"";
			List stateList=new ArrayList();
			for(CheckRemarkEnum d:CheckRemarkEnum.values()){
				stateList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
			}
			String jsonList=JSONArray.fromObject(stateList).toString();
			req.setAttribute("stateList", jsonList);

			this.querymap.put("reqtypestr", reqtypestr);
			req.setAttribute("TUI_HUI", CheckSignConstant.TUI_HUI);
			req.setAttribute("flowModsign", PtrainFlowModeSignEnum.TRAINREQ.getKey()+"");
			req.setAttribute("flowstathis", flowstathis);
		}else if("5".equals(this.sign)){
			//汇总维护
			flowstagath=PtrainReqFlowStaEnum.GD.getKey()+"";
			reqtypegath=PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey();
//			if(!func.IsEmpty(func.Trim(this.querymap.get("showsign"))))
				state="0";
			List reqtypeList=new ArrayList();
			for(PtrainReqReqtypeEnum d:PtrainReqReqtypeEnum.values()){
				reqtypeList.add("{'key':'"+d.getKey()+"','desc':'"+d.getDesc()+"'}");
			}
			String jsonList=JSONArray.fromObject(reqtypeList).toString();
			req.setAttribute("reqtypeList", jsonList);
			
			this.querymap.put("flowstagath", flowstagath);
			this.querymap.put("reqtypegath", reqtypegath);
			this.querymap.put("state", state);
		}else if("6".equals(this.sign)){
			//课件培训点菜
			reqtype=PtrainReqReqtypeEnum.REQ_COUR.getKey()+"";
		}
		this.querymap.put("reqtype", reqtype);
		this.querymap.put("requserid", requserid);
		this.querymap.put("flowsta", flowsta);
		req.setAttribute("XQSB", PtrainReqFlowStaEnum.XQSB.getKey()+"");
		req.setAttribute("XQSH", PtrainReqFlowStaEnum.XQSH.getKey()+"");
		req.setAttribute("GD", PtrainReqFlowStaEnum.GD.getKey()+"");
		
		req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
		return SUCCESS;
	}
	/**
	 * @description列表信息
	 */
	public String listPtrainReqByJq() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			if(func.IsEmpty(this.getSortfield()))this.setSortfield("r.estatime desc,r.id desc");
			Map map = new HashMap();
			String reqtype=func.Trim(this.querymap.get("reqtype")),reqtypestr=func.Trim(this.querymap.get("reqtypestr")),reqtypegath=func.Trim(this.querymap.get("reqtypegath")),flowsta=func.Trim(this.querymap.get("flowsta")),
				flowstathis=func.Trim(this.querymap.get("flowstathis")),flowstagath=func.Trim(this.querymap.get("flowstagath")),state=func.Trim(this.querymap.get("state")),requserid=func.Trim(this.querymap.get("requserid"));
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("reqtype", reqtype);
			map.put("reqtypestr", reqtypestr);
			map.put("specid", func.Trim(this.querymap.get("specid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("flowsta", flowsta);
			map.put("flowstathis", flowstathis);
			map.put("flowstagath", flowstagath);
			map.put("reqtypegath", reqtypegath);
			map.put("state", state);
			map.put("requserid", requserid);
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			map.put("sortfield",this.getSortfield());
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainReqService.findPtrainReqList(map);
			this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 专业类别
	 */
	public String findSpecListByJq(){
		try{
			//专业类别
			Map map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			this.print(JSONArray.fromObject(this.ptrainCodeService.findPtrainCodeList(map)).toString());
//			this.print("{\"request\":\"/old/short_url/shorten.json\",\"error_code\":400,\"error\":\"40022:Error: source paramter(appkey) is missing\"}");
		}catch(Exception e){
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @description新增、修改跳转
	 */
	public String setPtrainReq() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			//专业类别
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
			if(func.IsEmpty(func.Trim(this.ptrainReqBean.getId()))) {
				if(this.querymap.get("reqtype").equals(PtrainReqReqtypeEnum.REQ_EXP.getKey()+""))this.ptrainReqBean.setReqform(this.getLoginSessionBean().getDeptname());
			}else{
				//修改
				this.ptrainReqBean = this.ptrainReqService.findPtrainReqBeanById(this.ptrainReqBean.getId());
				//附件列表
				map=new HashMap();
				map.put("recid", this.ptrainReqBean.getId());
				map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
				req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
				//培训类别
				req.setAttribute("REQ_COM", PtrainReqReqtypeEnum.REQ_COM.getKey()+"");
				req.setAttribute("REQ_EXP", PtrainReqReqtypeEnum.REQ_EXP.getKey()+"");
				req.setAttribute("REQ_COUR", PtrainReqReqtypeEnum.REQ_COUR.getKey()+"");
				req.setAttribute("REQ_USER", PtrainReqReqtypeEnum.REQ_USER.getKey()+"");
			}
			req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
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
	public String savePtrainReq() {
		String rValue=INPUT;
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			if(this.isValidToken()) {
				String id=func.Trim(this.ptrainReqBean.getId());
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				this.ptrainReqBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(id)){
					if(func.Trim(this.ptrainReqBean.getReqtype()).equals(PtrainReqReqtypeEnum.REQ_USER.getKey()+"")||
							func.Trim(this.ptrainReqBean.getReqtype()).equals(PtrainReqReqtypeEnum.REQ_EXP.getKey()+"")){
						this.ptrainReqBean.setFlowsta(PtrainReqFlowStaEnum.XQSB.getKey()+"");
						this.ptrainReqBean.setFlowmark(CheckRemarkEnum.C_1001.getKey()+"");
					}
					this.ptrainReqBean.setState("0");
					this.ptrainReqBean.setEstauser(loginBean.getId());
				}
				this.ptrainReqService.savePtrainReq(this.ptrainReqBean);
				String FINAL_RANDOMSTR=req.getParameter("FINAL_RANDOMSTR");
				if(func.IsEmpty(id)&&!func.IsEmpty(FINAL_RANDOMSTR)){
					//更新recId根据FINAL_RANDOMSTR
					this.ptrainFileService.updatePtrainFileByRandomStr(FINAL_RANDOMSTR, this.ptrainReqBean.getId());
				}
			}
			if(func.Trim(this.gosign).equals("1")) {
				this.ptrainReqBean=new PtrainReqBean();
				this.setPtrainReq();
				this.reloadParentPage2();
			} else {
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainReq();
		}
		return rValue;
	}

	/**
	 * @description详细页面 弹出
	 */
	public String setPtrainReqWin() {
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			Map map = new HashMap();
			//修改
			map.put("id", this.ptrainReqBean.getId());
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			this.ptrainReqBean = this.ptrainReqService.findPtrainReqBeanByMap(map);
			//附件列表
			map = new HashMap();
			map.put("recid", this.ptrainReqBean.getId());
			map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN );
			req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
			//培训类别
			req.setAttribute("REQ_COM", PtrainReqReqtypeEnum.REQ_COM.getKey()+"");
			req.setAttribute("REQ_EXP", PtrainReqReqtypeEnum.REQ_EXP.getKey()+"");
			req.setAttribute("REQ_COUR", PtrainReqReqtypeEnum.REQ_COUR.getKey()+"");
			req.setAttribute("REQ_USER", PtrainReqReqtypeEnum.REQ_USER.getKey()+"");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}

	/**
	 * @description删除信息
	 */
	public String deletePtrainReqByJq() {
		try {
			String fatherPath = ServletActionContext.getServletContext().getRealPath("");
			Map map=new HashMap();
			map.put("id", this.ptrainReqBean.getId());
			map.put("fatherPath", fatherPath);
			map.put("savepath", this.savePath);
			this.ptrainReqService.deletePtrainReqByMap(map);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * @description Excel导出
	 */
	public String savePtrainReqExpExcel() {
		//登录人员信息
		LoginBean loginBean = this.getLoginSessionBean();
		try{
			Map map = new HashMap();
			map.put("unitid", this.querymap.get("unitid"));
			map.put("reqtype", this.querymap.get("reqtype"));
			map.put("specid", this.querymap.get("specid"));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			
			map.put("flowstagath", func.Trim(this.querymap.get("flowstagath")));//汇总维护参数
			map.put("reqtypegath", func.Trim(this.querymap.get("reqtypegath")));//汇总维护参数
			if(!func.IsEmpty(this.querymap.get("showsign")))
				map.put("state", func.Trim(this.querymap.get("state")));//汇总维护参数
			map.put("sortfield","r.estatime,r.id");
			map.put("fields",func.Trim(this.fields));
			map.put("keyword",func.Trim(this.keyword));
			map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
			this.ptrainReqService.savePtrainReqExpExcel(map, ServletActionContext.getResponse());
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return null;
	}
	//==================Excel导入相关====================
	/**
	 * 莆田岗位培训_培训点菜 导入excel 跳转
	 */
	public String setPtrainReqtempExcel() {
		HttpServletRequest req = ServletActionContext.getRequest();
		//专业类别
		Map map = new HashMap();
		map.put("nature", "2");
		map.put("unitid", func.Trim(this.querymap.get("unitid")));
		map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
		map.put("usesign", "1");
		req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	/**
	 * 培训点菜临时表 excel导入[保存]
	 */
	public String savePtrainReqtempExcel() {
		try {
			if(this.isValidToken()) {
				//上传附件并返回保存名
				String[] saveNameArr = this.execUpload(this.getSavePath(),0);
				//导入数据传参
				String fileFolder = ServletActionContext.getServletContext().getRealPath("/")+this.getSavePath();
				Map map=new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("specid", func.Trim(this.ptrainReqtempBean.getSpecid()));
				map.put("reqtype", func.Trim(this.querymap.get("reqtype")));
				map.put("fileFolder", fileFolder);
				map.put("saveNameArr", saveNameArr);  
				//impsign导入标志 1：清空后导入 5：全部都追加 
				map.put("impsign", this.querymap.get("impsign"));
				this.ptrainReqtempService.savePtrainReqtempExcel(map);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			this.setPtrainReqtempExcel();
			return INPUT;
		}
		return this.listPtrainReqtemp();
	}

	/**
	 * 培训点菜临时表 列表信息
	 */
	public String listPtrainReqtemp() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return SUCCESS;
	}

	/**
	 * 培训点菜临时表 列表信息
	 */
	public String listPtrainReqtempByJq() {
		try {
			//数据列表
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("reqtype", func.Trim(this.querymap.get("reqtype")));
			map.put("specid", func.Trim(this.ptrainReqtempBean.getSpecid()));
			map.put("fields",   func.Trim(this.fields));
			map.put("keyword",  func.Trim(this.keyword));
			map.put("showsign1", func.Trim(this.querymap.get("showsign1")));
			map.put("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			map.put("tagpage",this.getTagpage());
			map.put("record",this.getRecord());
			this.dataList=this.ptrainReqtempService.findPtrainReqtempList(map);
			this.print(this.creItemListPage(this.dataList, String.valueOf(map.get("total"))));
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}

	/**
	 * 培训点菜临时表 修改 弹出
	 */
	public String setPtrainReqtempWin() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//修改
			this.ptrainReqtempBean = this.ptrainReqtempService.findPtrainReqtempBeanById(this.ptrainReqtempBean.getId());
			Map map = new HashMap();
			//专业类别
			map.put("nature", "2");
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
			map.put("usesign", "1");
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
			//附件列表
			map=new HashMap();
			map.put("recid", this.ptrainReqtempBean.getId());
			map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
			req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
			req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			//培训类别
			req.setAttribute("REQ_COM", PtrainReqReqtypeEnum.REQ_COM.getKey()+"");
			req.setAttribute("REQ_EXP", PtrainReqReqtypeEnum.REQ_EXP.getKey()+"");
			req.setAttribute("REQ_COUR", PtrainReqReqtypeEnum.REQ_COUR.getKey()+"");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		this.getOpraterInfoIntoRequest();
		return SUCCESS;
	}
	
	/**
	 * 培训点菜临时表 修改[保存]
	 */
	public String savePtrainReqtemp() {
		try {
			this.ptrainReqtempService.savePtrainReqtemp(this.ptrainReqtempBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return Constant.NO_DATA;
		}
		this.reloadParentPage();
		return Constant.NO_DATA;
	}

	/**
	 * 培训点菜临时表 删除信息
	 */
	public String deletePtrainReqtempByJq() {
		try {
			String fatherPath = ServletActionContext.getServletContext().getRealPath("");
			Map map=new HashMap();
			map.put("id", this.ptrainReqtempBean.getId());
			map.put("fatherPath", fatherPath);
			map.put("savepath", this.savePath);
			this.ptrainReqtempService.deletePtrainReqtemp(map);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	/**
	 * 培训点菜 临时表导入到主表
	 */
	public String savePtrainReqExcel(){
		try {
			if(null==this.ptrainReqtempBean)this.ptrainReqtempBean=new PtrainReqtempBean();
			// 登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			if (this.isValidToken()) {
				Map param = new HashMap();
				param.put("unitid", loginBean.getUnitid());
				param.put("reqtype", this.querymap.get("reqtype"));
				param.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
				param.put("specid", func.Trim(this.ptrainReqtempBean.getSpecid()));
				param.put("impsign", this.querymap.get("impsign"));
				param.put("operuserid", loginBean.getId());
				param.put("reqtempid", this.ptrainReqtempBean.getId());
				this.ptrainReqtempService.savePtrainReqExcel(param);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.setActMessage("operate.error");
			return this.listPtrainReqtemp();
		}
		if(func.IsEmpty(this.ptrainReqtempBean.getId())){
			return this.listPtrainReq();
		}
		return this.listPtrainReqtemp();
	}

	/**
	 * 培训点菜临时表 取消
	 */
	public String setPtrainReqtempCan() {
		try {
			Map param = new HashMap();
			param.put("unitid", this.querymap.get("unitid"));
			param.put("specid", func.Trim(this.ptrainReqtempBean.getSpecid()));
			param.put("reqtype", this.querymap.get("reqtype"));
			this.ptrainReqtempService.deletePtrainReqtempByMap(param);
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainReq();
	}
	//=======================流程相关====================
	/**
	 * 员工加菜 申报/撤回
	 * 菜单审批 同意(不同意)/退回
	 */
	public String updatePtrainReqChk() {
		String rValue=Constant.NO_DATA;
		String chksign=func.Trim(this.querymap.get("chksign"));
		try {
			if(func.IsEmpty(chksign))return SUCCESS;
			
			Map map=new HashMap();
			this.ptrainReqBean.setMainuser(this.getLoginSessionBean().getId());
			map.put("ptrainReqBean", this.ptrainReqBean);
			map.put("chksign",chksign);
			map.put("chkmemo",func.Trim(this.querymap.get("chkmemo")));
			map.put("dataChange", func.Trim(this.querymap.get("dataChange")));
			this.ptrainReqService.updatePtrainReqChk(map);
			
			if(CheckSignConstant.SHEN_BAO.equals(chksign)||CheckSignConstant.CHE_HUI.equals(chksign)||CheckSignConstant.TUI_HUI.equals(chksign)){
				//申报 撤回 退回
				rValue=this.listPtrainReq();
			}else{//同意 不同意
				//this.setActMessage("", "opener.querydata();\n window.close();");
				rValue = Constant.NO_DATA;
				this.reloadParentPage();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return rValue;
	}
	/**
	 * 菜单审批 审批/批量审批
	 * 菜单审批 批量审批 修改[弹出]
	 */
	public String setPtrainReqChk() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			Map map = new HashMap();
			if(null==this.querymap)this.querymap = new HashMap<String,String>();
			if(func.IsEmpty(func.Trim(this.querymap.get("querylist")))){
				//专业类别
				map = new HashMap();
				map.put("nature", "2");
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC);
				map.put("usesign", "1");
				req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
				if(!func.IsEmpty(func.Trim(this.ptrainReqBean.getId()))) {
					//修改
					this.ptrainReqBean = this.ptrainReqService.findPtrainReqBeanById(this.ptrainReqBean.getId());
					//附件列表
					map=new HashMap();
					map.put("recid", this.ptrainReqBean.getId());
					map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
					req.setAttribute("fileslist", this.ptrainFileService.findPtrainFileList(map));
				}
				req.setAttribute("modsign", PtrainConstant._TRAINREQ_MODSIGN);
			}else{
				//数据列表
				///map = new HashMap();
				///map.put("unitid", func.Trim(this.querymap.get("unitid")));
				//map.put("reqtypestr", PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey());
				//map.put("reqid", this.ptrainReqBean.getId());
				//map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
				//this.dataList=this.ptrainReqService.findPtrainReqListNoPage(map);
			}
			req.setAttribute("TONG_YI", CheckSignConstant.TONG_YI);
			req.setAttribute("BU_TONG_YI", CheckSignConstant.BU_TONG_YI);
			
			req.setAttribute("REQ_EXP", PtrainReqReqtypeEnum.REQ_EXP.getKey()+"");
			req.setAttribute("REQ_USER", PtrainReqReqtypeEnum.REQ_USER.getKey()+"");
			this.getOpraterInfoIntoRequest();
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error", "window.close();");
			return Constant.NO_DATA;
		}
		return SUCCESS;
	}
	
	/**
	 * 菜单审批 批量审批[通过ID串查询列表]
	 */
	public void listPtrainReqByIds(){
		String unitid = func.Trim(this.querymap.get("unitid"));
		String reqid  = this.ptrainReqBean.getId();
		//数据列表
		Map map = new HashMap();
		map.put("unitid", unitid);
		map.put("reqtypestr", PtrainReqReqtypeEnum.REQ_EXP.getKey()+","+PtrainReqReqtypeEnum.REQ_USER.getKey());
		map.put("reqid", reqid);
		map.put("modsign",PtrainConstant._TRAINREQ_MODSIGN);
		this.printList(this.ptrainReqService.findPtrainReqListNoPage(map));
	}

	/**
	 * 菜单审批 批量审批 修改[保存]
	 */
	public String savePtrainReqByChk() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainReqBean.setMainuser(loginBean.getId());
			this.ptrainReqService.savePtrainReq(this.ptrainReqBean);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		this.reloadParentPage();
		return Constant.NO_DATA;
	}

	/**
	 * 汇总维护 拟办班/再办班/撤回办班
	 */
	public String savePtrainReqClas() {
		try {
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			this.ptrainReqBean.setMainuser(loginBean.getId());
			if("1".equals(this.querymap.get("opersign"))||"2".equals(this.querymap.get("opersign"))){//拟办班
				this.ptrainReqBean.setState("1");
				this.ptrainReqService.savePtrainReq(this.ptrainReqBean);
			}else if("3".equals(this.querymap.get("opersign"))){//再办班
				//复制一条记录
				String fatherPath = ServletActionContext.getServletContext().getRealPath("");
				Map map=new HashMap();
				map.put("unitid", func.Trim(this.querymap.get("unitid")));
				map.put("operuserid", loginBean.getId());
				map.put("id", this.ptrainReqBean.getId());
				map.put("reqtype", func.Trim(this.querymap.get("reqtype")));
				map.put("fatherPath", fatherPath);
				map.put("savepath", this.savePath);
				this.ptrainReqService.insertPtrainReqByCopy(map);
			}else if("4".equals(this.querymap.get("opersign"))){//撤回办班
				this.ptrainReqBean.setState("0");
				this.ptrainReqService.savePtrainReq(this.ptrainReqBean);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
		}
		return this.listPtrainReq();
	}

	//Get和Set方法
	public PtrainReqBean getPtrainReqBean() {
		return ptrainReqBean;
	}
	public void setPtrainReqBean(PtrainReqBean ptrainReqBean) {
		this.ptrainReqBean = ptrainReqBean;
	}
	public PtrainReqService getPtrainReqService() {
		return ptrainReqService;
	}
	public void setPtrainReqService(PtrainReqService ptrainReqService) {
		this.ptrainReqService = ptrainReqService;
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

	public String getSetpage() {
		return setpage;
	}

	public void setSetpage(String setpage) {
		this.setpage = setpage;
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

	public PtrainReqtempService getPtrainReqtempService() {
		return ptrainReqtempService;
	}

	public void setPtrainReqtempService(PtrainReqtempService ptrainReqtempService) {
		this.ptrainReqtempService = ptrainReqtempService;
	}

	public PtrainReqtempBean getPtrainReqtempBean() {
		return ptrainReqtempBean;
	}

	public void setPtrainReqtempBean(PtrainReqtempBean ptrainReqtempBean) {
		this.ptrainReqtempBean = ptrainReqtempBean;
	}

	public String getListtemppage() {
		return listtemppage;
	}

	public void setListtemppage(String listtemppage) {
		this.listtemppage = listtemppage;
	}

	public String getSettemppage() {
		return settemppage;
	}

	public void setSettemppage(String settemppage) {
		this.settemppage = settemppage;
	}
}