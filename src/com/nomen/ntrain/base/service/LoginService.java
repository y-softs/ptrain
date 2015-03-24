package com.nomen.ntrain.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.annotation.LoginEnums;
import com.nomen.ntrain.base.bean.BaseLogonBean;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.util.CheckUserBean;
import com.nomen.ntrain.base.util.DataUserInfoBean;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganBean;

/**
 * @description 登录模块  
 * @author 连金亮
 * @date 2009-05-18
 */
public interface LoginService{
	/*********************************************************/
	/*********************************************************/
	/******************   供外部调用   *************************/
	/*********************************************************/
	/*********************************************************/
	/**
	 * 查询人员详细信息[2012-04-18]
	 */
	public DataUserInfoBean Comm_findDataUserInfoById(String id);
	
	/**
	 * [2010-11-23 主要用来进行登录验证的]
	 * 查询登录人员的信息，并返回LoginBean信息
	 * @param Map
	 * @return Map
	 */
	public Map findLoginUser(Map map); 
	
	
    /**
     * 获取单位列表[2011-05-23修改]
     * @param login 登录信息
     * @param unitid 当前选中的单位ID
     * @return unitlist，单位list；unitid，单位ID[若unitid为空则返回login中的单位]
     */
	public Map findUnitListByUser(LoginBean login,String unitid);
	
	
	/**
	 * 查询某个单位以及其子单位
	 * @param fatherid
	 * @return
	 */
	public List findBaseUnitListByUnitid(String fatherid);
	
	/**
	 * 获取已启用单位列表[2011-05-23修改]
	 * @return List
	 */
	public List<BaseUnitBean> findAllUseUnit();
	
	/**
	 * 查询本单位列表
	 * @param unitId
	 * @return
	 */
	public List<BaseUnitBean> findOwnUnitListById(String unitId);


	/**
	 * 查询直属单位、地市单位等（不含县公司）
	 * @return
	 */
	public List<BaseUnitBean> findBigUnitList();

	/**
	 * 查询出所有的县公司
	 * @return
	 */
	public List<BaseUnitBean> findSmallUnitList();

	/**
	 * 查询单位的直接下属公司
	 * @return
	 */
	public List<BaseUnitBean> findChildUnitList(String unitId);
	
	
	
	/**
	 * 获取部门性质【2010-11-23新增]
	 * @param unitid,fatherid
	 * @return
	 */
	public List findDeptNatureList(Map map);
	
	/**
	 * 通过单位查询部门列表[2013-04-20新增]
	 * @param unitid
	 * @return
	 */
	public List findDeptListByUnitId(String unitid);
	
	/**
	 * 获取部门列表[2011-05-23新增]
	 * @see 1、查询部门列表时传递（unitid） 2、查询单一部门时传递（id）
	 * @param unitid,id
	 * @return
	 */
	public List findDeptListByMap(Map map);

	/**
	 * 获取班组、子班组列表[2011-05-23新增]
	 * @see 1、查询班组、子班组列表时传递（fatherid：部门/班组id） 2、查询单一班组/子班组时传递（id）
	 * @param fatherid,id
	 * @return
	 */ 
	public List findGroupListByMap(Map map);

	
	/**
	 * 获取部门性质和部门列表（启用和权限限制信息）
	 * @param unitid 单位ID
	 * @param nature 部门性质ID
	 * @param deptid 当前选中的部门ID管理
	 * @param isabled 是否受操作限制，只显示本部门信息（true显示全部，false显示本部门）
	 * @return naturelist，部门性质list；nature，部门性质ID；deptlist，部门list；deptid，部门ID；
	 */
	public Map findDeptListByUser(String unitid,String nature,String deptid,boolean isabled); 
	
	/**
	 * 获取班组列表（启用和权限限制信息）
	 * @param deptid 部门ID
	 * @param groupid 当前选中的班组ID
	 * @param isabled 是否受操作限制，只显示本班组信息（true显示全部，false显示本班组）
	 * @return grouplist，班组list；groupid，班组ID；
	 */
	public Map findGroupListByUser(String deptid,String groupid,boolean isabled);
	
	/**
	 * 获取班组列表（启用和权限限制信息）
	 * @param fatherid 父级班组ID或部门ID
	 * @param groupid 当前选中的班组ID
	 * @param isabled 是否受操作限制，只显示本班组信息（true显示全部，false显示本班组） 
	 * @return grouplist，班组list；groupid，班组ID；
	 */
	public Map findGroupList(String fatherid,String groupid,boolean isabled);
	
	/**
	 * 通过班组查询人员列表
	 * @param groupid 班组ID
	 * @param userid 当前选中的人员ID
	 * @param isabled 是否受操作限制，只显示指定人员信息（true显示全部，false显示指定人员） 
	 * @return userlist，人员list；userid，人员ID；
	 */
	public Map findUserList(String groupid,String userid,boolean isabled);
	
	/**
	 * 根据条件查询人员信息
	 * @param map：unitid，单位ID；nature，部门性质；deptid，部门ID；
	 * 			  groupid，班组ID；groupid2，自班组ID;fields，关键字段；keyword，关键字内容
	 * @return
	 */
	public List<LoginBean> findAllUseUser(Map map);
	
	/**
	 * 显示图片文件
	 * @param map：	queryfield，查询的字段名；table，查询的表格名；
	 * 			  	confield，条件字段；convalue，条件字段值
	 * @param response
	 * @return
	 */
	public void findImageBlob(Map map,HttpServletResponse response);
	
	/**
	 * 同名人员信息检测 当unitid == -1 表示全省人员进行验证
	 * @param unitid，单位id；username，要验证的人员名称
	 * @return “字段_sameuserlist”，同名人员列表；“字段_okuser”，正常人员名字；“字段_okuserNum” 正常人员总人数；“字段_alluser” 所有人员（包含html）；  
	 */
	public Map findPeoNameSameOrNot(String unitid,String username); 
	
	/**
	 * 同名人员信息检测 当unitid == -1 表示全省人员进行验证
	 * @param unitid，单位id/单位id串；fields，检测页面上的字段名称组合；request，请求对象
	 * @return “字段_sameuserlist”，同名人员列表；“字段_okuser”，正常人员名字；“字段_okuserNum” 正常人员总人数；“字段_alluser” 所有人员（包含html）；  
	 */
	public Map findPeoNameSameOrNot(String unitid,String fields,HttpServletRequest request); 

	/**
	 * 获取登录人员的权限值
	 * @param id
	 * @return
	 */
	public String       findDataUserPurByUserId(String id);
	
	/**
	 * 查询人员信息
	 * @param map：unitid，单位ID；username，人员名字
	 * @return
	 */
	public List<CheckUserBean> findUserInfoByMap(Map map);
	
	/**
	 * 查询人员姓名串的数量---同名验证要用到
	 * @param map：unitid，单位ID；inNameStr，人员名字串
	 * @return
	 */
	public List<CheckUserBean>   findUserNameStrCount(Map map);
	
	/*********************************************************/
	/*********************************************************/
	/******************   内部部调用   *************************/
	/*********************************************************/
	/*********************************************************/
	/**
	 * 获取系统登录成功输出的内容
	 * @param login
	 * @return
	 */
	public String findSystemOutStr(LoginBean login); 

	
	/**
	 * 记录登录/注销日志
	 * @param loginBean
	 */
	public void insertLoginLog(LoginBean loginBean,LoginEnums logEnums);


	/*********************************************************/
	/*********************************************************/
	/****************jquery/dwr调用   *************************/
	/*********************************************************/
	/*********************************************************/

	
	/**
	 * 查询部门详细
	 * @param id
	 * @return
	 */
	public DataOrganBean findDataOrganById(String id);
	
	
	/**
	 * 根据人员ID串 查询列表 许东雄-加
	 * @param userids
	 * @return
	 */
	public List findDateUserByUserids(String userids);
	

	
	/**
	 * 修改密码
	 * @param loginBean
	 */
	public void updatePassword(LoginBean loginBean);
}
