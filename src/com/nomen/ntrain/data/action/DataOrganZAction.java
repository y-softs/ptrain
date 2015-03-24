package com.nomen.ntrain.data.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.service.LoginService;
import com.nomen.ntrain.common.CodeFatherUtil;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganZBean;
import com.nomen.ntrain.data.constant.DataConstant;
import com.nomen.ntrain.data.service.DataOrganZService;
import com.nomen.ntrain.ptrain.bean.PtrainCodeBean;
import com.nomen.ntrain.util.Constant;
import com.nomen.ntrain.util.PubFunc;

/**
 * @description 部门和班组机构
 * @author 
 * @date 2014-11-19
 */
@SuppressWarnings("all")
public class DataOrganZAction extends DataAction {
	private static final Logger log=Logger.getLogger(DataOrganZAction.class);
	private static final long serialVersionUID = 1656646413839836498L;
	
	private DataOrganZService  dataOrganZService;  //业务处理类
	private LoginService       loginService;       //登录信息业务处理类
	private DataOrganZBean     dataOrganZBean;     //部门和班组信息bean
	private Map<String,String> querymap;           //参数
	private List               dataList;           //部门和班组信息列表list
	private final static String FILE_MODUAL = "4";
	private PubFunc func = new PubFunc();
	
	/**
	 * 跳转到机构列表页面
	 * @return
	 */
	public String toForwardOrganZList(){
		HttpServletRequest req=ServletActionContext.getRequest();
		//登录人员信息
		//this.fatherid = this.getStaticValueByName(this.fatherid);
		this.fatherid = "0";
		LoginBean loginBean=this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			//this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("fatherid", fatherid);
			this.sortfield = "sortnum";
		}
		return SUCCESS;
	}
	
	/**
	 * 部门新增、修改页面（跳转）
	 * @return
	 */
	public String setDataOrganZBean() {
		//获取登录信息
		LoginBean loginBean = this.getLoginSessionBean();
		HttpServletRequest req=ServletActionContext.getRequest();

		/*********************获取部门性质列表*********************/
		String unitId  =func.Trim(this.querymap.get("unitid"));
		String nature  =func.Trim(this.querymap.get("nature"));
		
		//操作标志：新增（1），修改（2）
		if("1".equals(this.fun)){
			dataOrganZBean = new DataOrganZBean();
			//设置部门默认最大排序号
			String parentId = func.Trim(querymap.get("fatherid"));
			
			/*********************获取部门的下一个排序号*********************/
			Map sortNumMap = new HashMap();
			sortNumMap.put("unitid", unitId);
			sortNumMap.put("fatherid", parentId);
			sortNumMap.put("nature", nature);
			String sortNum=this.dataOrganZService.findNextSortNum(sortNumMap);

			/*********************获取部门的下一个编码*********************/
			String orgCode=this.dataOrganZService.findDeptMaxOrganZCode(unitId, nature, parentId);
			//默认部门性质
			this.dataOrganZBean.setNature(nature);
			this.dataOrganZBean.setOrganname("");
			this.dataOrganZBean.setSortnum(sortNum);
			this.dataOrganZBean.setOrganvalue(orgCode);
			this.dataOrganZBean.setUnitid(unitId);
			this.dataOrganZBean.setFatherid(parentId);
			this.dataOrganZBean.setUsesign("1");
			
			if("1".equals(this.gosign)){
				this.dataOrganZBean.setShortname("");
				this.dataOrganZBean.setPy("");
			}
		}else {
			this.dataOrganZBean=this.dataOrganZService.findDataOrganZBeanById(func.Trim(this.dataOrganZBean.getId()));
		}
		/*********************获取配置参数中部门过滤的值*********************/
		//String deptKeyword = this.getInitValue(this.dataOrganZBean.getUnitid(),"person_data11");
		//req.setAttribute("deptKeyword", deptKeyword);
		return SUCCESS;
	}
	
	/**
	 * 机构维护保存
	 */
	public String saveDataOrganZ() {
		String rValue=SUCCESS;
		try {
			if(this.isValidToken()) {
				LoginBean loginBean = this.getLoginSessionBean();
				this.dataOrganZBean.setMainuser(loginBean.getId());
				if(func.IsEmpty(func.Trim(this.dataOrganZBean.getId()))){
					this.dataOrganZBean.setEstauser(loginBean.getId());
				}
				Map map = new HashMap();
				map.put("dataOrganZBean", dataOrganZBean);
				this.dataOrganZService.saveDataOrganZBean(map);
			}
			if(func.Trim(this.gosign).equals("1")) {
				String fatherid=this.dataOrganZBean.getFatherid();
				this.dataOrganZBean = new DataOrganZBean();
				this.dataOrganZBean.setFatherid(fatherid);
				this.setDataOrganZBean();
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
	 * 跳转到机构调整列表页面（跳转）
	 * @return
	 */
	public String toForwardOrganZRectList() {
		HttpServletRequest req=ServletActionContext.getRequest();
		//登录人员信息
		//this.fatherid = this.getStaticValueByName(this.fatherid);
		this.fatherid = "0";
		LoginBean loginBean=this.getLoginSessionBean();
		if(null == this.querymap){
			this.querymap = new HashMap();
			//this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("unitid", loginBean.getUnitid());
			this.querymap.put("fatherid", fatherid);
			this.sortfield = "sortnum";
		}
		return SUCCESS;
	}
	
	/**
	 * 部门升级页面（跳转）
	 * @return
	 */
	public String setDataOrganZUpLev() {
		//获取登录信息
		this.dataOrganZBean=this.dataOrganZService.findDataOrganZTreeBeanById(func.Trim(this.dataOrganZBean.getId()));
		//查询新的排序号
		Map map = new HashMap();
		map.put("unitid", dataOrganZBean.getUnitid());
		map.put("fatherid", dataOrganZBean.getGrandfatherid());
		String sortnum = this.dataOrganZService.findNextSortNum(map);
		dataOrganZBean.setSortnum(sortnum);
		/*********************获取配置参数中部门过滤的值*********************/
		//String deptKeyword = this.getInitValue(this.dataOrganZBean.getUnitid(),"person_data11");
		//req.setAttribute("deptKeyword", deptKeyword);
		return SUCCESS;
	}
	
	/**
	 * 部门升级页面保存
	 */
	public String saveDataOrganZUpLev() {
		try {
			if(this.isValidToken()) {
				LoginBean loginBean = this.getLoginSessionBean();
				this.dataOrganZBean.setMainuser(loginBean.getId());
				Map map = new HashMap();
				map.put("dataOrganZBean", dataOrganZBean);
				this.dataOrganZService.saveDataOrganZBean(map);
			}
			this.reloadParentPage();
			return Constant.NO_DATA;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 部门合并页面（跳转）
	 * @return
	 */
	public String setDataOrganZMerge() {
		//获取登录信息
		String idstr = func.Trim(querymap.get("idstr"));
		/*********************获取配置参数中部门过滤的值*********************/
		//String deptKeyword = this.getInitValue(this.dataOrganZBean.getUnitid(),"person_data11");
		//req.setAttribute("deptKeyword", deptKeyword);
		return SUCCESS;
	}
	
	/**
	 * 部门合并页面保存
	 */
	public String saveDataOrganZMerge() {
		try {
			if(this.isValidToken()) {
				LoginBean loginBean = this.getLoginSessionBean();
				//新的部门id
				String newdeptid = func.Trim(querymap.get("newdeptid"));
				//新的班组、子班组id
				String newgroupid = func.Trim(querymap.get("newgroupid"));
				//升级的id串
				String idstr = func.Trim(querymap.get("idstr"));
				
				this.dataOrganZBean.setMainuser(loginBean.getId());
				Map map = new HashMap();
				map.put("dataOrganZBean", dataOrganZBean);
				map.put("idstr", idstr);
				map.put("newdeptid", newdeptid);
				map.put("newgroupid", newgroupid);
				map.put("userid", loginBean.getId());
				this.dataOrganZService.saveDataOrganZMerge(map);
			}
			this.reloadParentPage();
			return Constant.NO_DATA;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/****************************************************************************************/
	/***********************************JQUERY 方法*******************************************/
	/****************************************************************************************/
	
	/**
	 * 查询部门名称列表
	 * @return
	 */
	public String findDataOrganZNoTreeListByJq(){
		String unitid = func.Trim(this.querymap.get("unitid"));      //单位id
		String fatherid = func.Trim(this.querymap.get("fatherid"));  //父类id
		//String nature = func.Trim(this.querymap.get("nature"));     //机构性质
		String usesign = DataConstant.DATA_USESIGN;
		Map map = new HashMap();
		map.put("unitid", unitid);
		map.put("fatherid", fatherid);
		map.put("usesign", usesign);
		map.put("sortfield", this.sortfield);       //排序号
		//map.put("nature", nature);      //机构性质
		List orgList = this.dataOrganZService.findDataOrganZListByFatherid(map);
		this.printList(orgList);
		return null;
	}
	
	/**
	 * 查询树状机构部门列表
	 * @return
	 */
	public String findDataOrganZTreeListByJq(){
		String unitid = func.Trim(this.querymap.get("unitid"));     //单位id
		String fatherid = func.Trim(this.querymap.get("fatherid"));     //父类id
		String showsign = func.Trim(this.querymap.get("showsign"));   //仅显示部门列表数据
		String usesign = func.Trim(this.querymap.get("usesign"));
		String fields = func.Trim(this.fields);
		String keyword = func.Trim(this.keyword);
		Map map = new HashMap();
		if(func.IsEmpty(fatherid)){
			String id = func.Trim(querymap.get("id"));
			map.put("id", id);
		}
		map.put("unitid", unitid);
		map.put("fatherid", fatherid);
		map.put("showsign", showsign);
		map.put("usesign", usesign);
		map.put("fields", fields);
		map.put("keyword", keyword);
		map.put("sortfield", this.sortfield);
		List orgList = this.dataOrganZService.findDataOrganZTreeList(map);
		this.printList(orgList);
		return null;
	}
	
	/**
	 * 根据选中idStr查询机构树状列表
	 * @return
	 */
	public String findDataOrganZTreeListIdStrByJq(){
		String idstr = func.Trim(this.querymap.get("idstr"));     //单位id
		String fatherid = func.Trim(this.querymap.get("fatherid"));     //父类id
		Map map = new HashMap();
		map.put("fatherid", fatherid);
		map.put("idstr", idstr);
		List orgList = this.dataOrganZService.findDataOrganZTreeListByIdStr(map);
		this.printList(orgList);
		return null;
	}
	
	/**
	 * 根据选中id查询机构Bean
	 * @return
	 */
	public String findDataOrganZBeanIdByJq(){
		try {
			String id = func.Trim(this.querymap.get("id"));     //主键id
			this.dataOrganZBean = this.dataOrganZService.findDataOrganZBeanById(id);
			this.printBean(dataOrganZBean);
		} catch (RuntimeException e) {
			this.print("-1");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验证判断编码是否重复
	 * @return
	 */
	public String chkDataOrganZCodeValueByJq(){
		try {
			String unitid = func.Trim(querymap.get("unitid"));        //单位id
			String fatherid = func.Trim(querymap.get("fatherid"));    //父类fatherid
			String organvalue = func.Trim(querymap.get("organvalue"));  //编码organvalue
			Map map = new HashMap();
			map.put("unitid", unitid);
			map.put("fatherid", fatherid);
			map.put("organvalue", organvalue);
			String count = this.dataOrganZService.chkDataOrganZCodeValueIsExists(map);
			this.print(count);
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	
	/**
	 * 更新启用,禁用标志
	 * @return
	 */
	public String updateDataOrganZUseSignByJq(){
		try {
			String id = this.dataOrganZBean.getId();
			String usesign = this.dataOrganZBean.getUsesign();
			Map map = new HashMap();
			map.put("id", id);
			map.put("usesign", usesign);
			this.dataOrganZService.updateDataOrganZUseSign(map);
			this.print("1");
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	
	/**
	 * 删除机构
	 *
	 */
	public void delDataOrganZBeanByJq(){
		try {
			String id = func.Trim(this.querymap.get("id"));
			this.dataOrganZService.deleteDataOrganZBeanById(id);
			this.print("1");
		} catch (RuntimeException e) {
			this.print("-1");
			e.printStackTrace();
		}
	}
	
	/**
	 * 机构更名保存
	 */
	public String saveDataOrganZRenameByJq() {
		try {
			LoginBean loginBean = this.getLoginSessionBean();
			this.dataOrganZBean.setMainuser(loginBean.getId());
			Map map = new HashMap();
			map.put("dataOrganZBean", dataOrganZBean);
			this.dataOrganZService.saveDataOrganZBean(map);
			this.print("1");
		} catch(Exception ex) {
			ex.printStackTrace();
			this.print("-1");
		}
		return null;
	}
	
	//生成get,set方法
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public DataOrganZBean getDataOrganZBean() {
		return dataOrganZBean;
	}
	public void setDataOrganZBean(DataOrganZBean dataOrganZBean) {
		this.dataOrganZBean = dataOrganZBean;
	}
	public DataOrganZService getDataOrganZService() {
		return dataOrganZService;
	}
	public void setDataOrganZService(DataOrganZService dataOrganZService) {
		this.dataOrganZService = dataOrganZService;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public Map<String, String> getQuerymap() {
		return querymap;
	}
	public void setQuerymap(Map<String, String> querymap) {
		this.querymap = querymap;
	}
	
}
