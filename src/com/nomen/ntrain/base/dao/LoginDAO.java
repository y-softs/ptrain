package com.nomen.ntrain.base.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseLogonBean;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.util.CheckUserBean;
import com.nomen.ntrain.base.util.DataUserInfoBean;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganBean;
import com.nomen.ntrain.data.bean.DataUserBean;

/**
 * @description 登录模块  
 * @author 钱新红
 * @date 2009-05-18
 */

public interface LoginDAO 
{

	/**
	 * 查询人员详细信息[2012-04-18]
	 */
	public DataUserInfoBean findDataUserInfoById(String id);
	
	/**
	 * 查询登录人员信息
	 * @param map
	 * @return
	 */
	public List findLoginUser(Map map);
	
	/**
	 * 查询单位信息列表
	 * @return
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
	 * 查询某个单位以及其子单位
	 * @param fatherid
	 * @return
	 */
	public List findBaseUnitListByUnitid(String fatherid);
	
	/**
	 * 查询部门性质列表[OK]
	 * @param unitid,fatherid 性质的fatherid
	 * @return
	 */
	public List<DataCodeBean> findAllUseNature(Map map);
	
	/**
	 * 查询部门列表
	 * @param map： nature，部门性质；unitid，单位ID；id，部门ID
	 * @return
	 */
	public List<DataOrganBean> findAllUseDept(Map map);
	
	/**
	 * 查询班组列表
	 * @param map： deptid，部门ID；id，班组ID
	 * @return
	 */
	public List<DataOrganBean> findAllUseGroup(Map map);
	
	/**
	 * 查询单列班组列表
	 * @param map：fatherid，父级班组ID或者部门ID；id，班组ID
	 * @return
	 */
	public List<DataOrganBean> findUseGroupList(Map map);
	
	/**
	 * 通过班组查询人员列表
	 * @param map：groupid，班组ID
	 * @return
	 */
	public List<LoginBean> findAllUserList(Map map);
	
	/**
	 * 根据条件查询人员信息
	 * @param map：unitid，单位ID；nature，部门性质；deptid，部门ID；
	 * 			  groupid，班组ID；fields，关键字段；keyword，关键字内容
	 * @return
	 */
	public List<LoginBean> findAllUseUser(Map map);

	/**
	 * 查询BLOB流字段
	 * @param map：	queryfield，查询的字段名；table，查询的表格名；
	 * 			  	confield，条件字段；convalue，条件字段值
	 * @return
	 * @throws Exception
	 */
	public List<DataUserBean> findImageBlob(Map map) throws Exception;
	
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
	
	/**
	 * 记录登录日志
	 * @param baseLogonBean
	 * @return
	 */
	public String insertBaseLogon(BaseLogonBean baseLogonBean);
	
	/**
	 * 记录注销日志
	 * @param baseLogonBean
	 * @return
	 */
	public void updateBaseLogon(String id);
	
	/**
	 * 获取登录人员的权限值
	 * @param id
	 * @return
	 */
	public String       findDataUserPurByUserId(String id);
	
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
