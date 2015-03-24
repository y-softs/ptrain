package com.nomen.ntrain.ptrain.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.common.CodeFatherUtil;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.ptrain.bean.PtrainParamBean;
import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.ptrain.constant.PtrainConstant;
import com.nomen.ntrain.ptrain.service.PtrainCodeService;
import com.nomen.ntrain.ptrain.service.PtrainParamService;
import com.nomen.ntrain.ptrain.service.PtrainPolicyService;
import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.res.service.ResCodeService;

/**
 * @description 莆田岗位学习_抽题策略action层
 * @author 林木山
 * @date 2014-3-14
 */
@SuppressWarnings("all")
public class PtrainPolicyAction extends PtrainAction{

	private PtrainPolicyService		ptrainPolicyService;  	//莆田岗位学习_抽题策略业务接口
	private PtrainParamService		ptrainParamService;  	//莆田岗位学习_抽题策略业务接口
	private PtrainCodeService		ptrainCodeService;		//代码列表业务接口
	private ResCodeService			resCodeService;			//代码列表业务接口
	private PtrainParamBean			ptrainParamBean;     	//莆田岗位学习_抽题策略信息表
	private PtrainPolicyBean		ptrainPolicyBean;     	//莆田岗位学习_抽题策略信息表
	private Map<String,String>		querymap;				//传参map
	private List					dataList;				//数据列表
	
	/**
	 * @description新增、修改跳转
	 */
	public String setPtrainPolicy() {
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			//登录人员信息
			LoginBean loginBean = this.getLoginSessionBean();
			//初始化
			if(null==this.querymap){
				this.querymap = new HashMap<String,String>();
				this.querymap.put("unitid", loginBean.getUnitid());
			}
			//策略参数
			req.setAttribute("PARAM_MRCT", PtrainConstant.PARAM_MRCT);
			req.setAttribute("PARAM_MYDB", PtrainConstant.PARAM_MYDB);
			//参数列表
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			String certerUnitid=func.Trim(loginBean.getUnitid());
			this.querymap.put("unitid",certerUnitid);
			map.put("unitid", certerUnitid);
			List<PtrainParamBean> paramList=this.ptrainParamService.findPtrainParamList(map);
			map = new HashMap();
			for(PtrainParamBean paramBean:paramList){
				map.put(paramBean.getParamname()+"_PARAMID", paramBean.getId());
				map.put(paramBean.getParamname()+"_PARAMVALUE", paramBean.getParamvalue());
				map.put(paramBean.getParamname()+"_REMARK", paramBean.getRemark());
			}
			req.setAttribute("paramMap", map);
			/*//试题类型及操作列表
			map = new HashMap();
			map.put("isTypeList", "true");
			map.put("fatherid", CodeFatherUtil.RES_QUE_TYPE);
			map.put("usesign", "1");
			List<ResCodeBean> typeList=this.resCodeService.findResCodeList(map);
			PtrainConstant.typeListOper(typeList,req);
			//数据列表
			map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			List<PtrainPolicyBean> policyList=this.ptrainPolicyService.findPtrainPolicyList(map);
			map = new HashMap();
			for(PtrainPolicyBean policyBean:policyList){
				map.put(policyBean.getTypeid()+"_POLICYID", policyBean.getId());
				map.put(policyBean.getTypeid()+"_AMOUNT", policyBean.getAmount());
				map.put(policyBean.getTypeid()+"_SCORE", policyBean.getScore());
				map.put(policyBean.getTypeid()+"_TOTAL", policyBean.getIntflag());
			}
			req.setAttribute("policyMap", map);*/
			//专业类别
			map = new HashMap();
			map.put("nature", "2");
			map.put("unitid", certerUnitid);
			map.put("fatherid", CodeFatherUtil.PTRAIN_SPEC_ASK);
			map.put("usesign", "1");
			req.setAttribute("specList", this.ptrainCodeService.findPtrainCodeList(map));
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
	public String savePtrainPolicy() {
		String rValue=INPUT;
		try {
			if(this.isValidToken()) {
				HttpServletRequest req = ServletActionContext.getRequest();
				//登录人员信息
				LoginBean loginBean = this.getLoginSessionBean();
				if(null==this.ptrainParamBean)this.ptrainParamBean=new PtrainParamBean();
				this.ptrainParamBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainParamBean.getId())))
					this.ptrainParamBean.setEstauser(loginBean.getId());
				ptrainParamBean.setUnitid(this.querymap.get("unitid"));
				String[] idArr=req.getParameterValues("paramid"),paramnameArr=req.getParameterValues("paramname"),
					paramvalueArr=req.getParameterValues("paramvalue"),remarkArr=req.getParameterValues("remark");
				for(int i=0;i<idArr.length;i++){
					ptrainParamBean.setId(idArr[i]);
					ptrainParamBean.setParamname(paramnameArr[i]);
					ptrainParamBean.setParamvalue(paramvalueArr[i]);
					ptrainParamBean.setRemark(remarkArr[i]);
					this.ptrainParamService.savePtrainParam(this.ptrainParamBean);
				}

				this.ptrainPolicyBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.ptrainPolicyBean.getId()))||!func.IsEmpty(func.Trim(this.querymap.get("alltypeid"))))
					this.ptrainPolicyBean.setEstauser(loginBean.getId());
				/*String[] policyidArr=req.getParameterValues("policyid"),typeidArr=req.getParameterValues("typeid"),amountArr=req.getParameterValues("amount"),
					scoreArr=req.getParameterValues("score");
				for(int i=0;i<policyidArr.length;i++){
					ptrainPolicyBean.setId(policyidArr[i]);
					ptrainPolicyBean.setTypeid(typeidArr[i]);
					ptrainPolicyBean.setAmount(amountArr[i]);
					ptrainPolicyBean.setScore(scoreArr[i]);
					this.ptrainPolicyService.savePtrainPolicy(this.ptrainPolicyBean);
				}*/
				if(!func.IsEmpty(func.Trim(this.querymap.get("alltypeid")))){
					//全部专业类别
					Map map = new HashMap();
					map.put("unitid", func.Trim(this.querymap.get("unitid")));
					this.ptrainPolicyService.deletePtrainPolicyByMap(map);
					String[] typeidArr=this.ptrainPolicyBean.getTypeid().split(",");
					for(String typeid:typeidArr){
						this.ptrainPolicyBean.setId("");
						this.ptrainPolicyBean.setTypeid(typeid);
						this.ptrainPolicyService.savePtrainPolicy(this.ptrainPolicyBean);
					}
					this.ptrainPolicyBean.setTypeid("");
				}else{
					this.ptrainPolicyService.savePtrainPolicy(this.ptrainPolicyBean);
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			this.setActMessage("operate.error");
			return this.setPtrainPolicy();
		}
		this.setActMessage("operate.success");
		return this.setPtrainPolicy();
	}
	/*****************以下为JQUERY方法***************************/
	/**
	 * 查询抽题策略
	 */
	public String findPtrainPolicyByJq(){
		try{
			Map map = new HashMap();
			map.put("unitid", func.Trim(this.querymap.get("unitid")));
			map.put("alltypeid", func.Trim(this.querymap.get("alltypeid")));
			map.put("typeid", func.Trim(this.querymap.get("typeid")));
			map.put("typeidstr", func.Trim(this.querymap.get("typeidstr")));
			List<PtrainPolicyBean> policyList=this.ptrainPolicyService.findPtrainPolicyList(map);
			this.print(JSONArray.fromObject(policyList).toString());
		}
		catch(Exception ex){
			this.print("-1");
		}
		return null;		
	}

	//Get和Set方法
	public PtrainPolicyBean getPtrainPolicyBean() {
		return ptrainPolicyBean;
	}
	public void setPtrainPolicyBean(PtrainPolicyBean ptrainPolicyBean) {
		this.ptrainPolicyBean = ptrainPolicyBean;
	}
	public PtrainPolicyService getPtrainPolicyService() {
		return ptrainPolicyService;
	}
	public void setPtrainPolicyService(PtrainPolicyService ptrainPolicyService) {
		this.ptrainPolicyService = ptrainPolicyService;
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

	public ResCodeService getResCodeService() {
		return resCodeService;
	}

	public void setResCodeService(ResCodeService resCodeService) {
		this.resCodeService = resCodeService;
	}

	public PtrainParamService getPtrainParamService() {
		return ptrainParamService;
	}

	public void setPtrainParamService(PtrainParamService ptrainParamService) {
		this.ptrainParamService = ptrainParamService;
	}

	public PtrainParamBean getPtrainParamBean() {
		return ptrainParamBean;
	}

	public void setPtrainParamBean(PtrainParamBean ptrainParamBean) {
		this.ptrainParamBean = ptrainParamBean;
	}

	public PtrainCodeService getPtrainCodeService() {
		return ptrainCodeService;
	}

	public void setPtrainCodeService(PtrainCodeService ptrainCodeService) {
		this.ptrainCodeService = ptrainCodeService;
	}
}
