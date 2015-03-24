package com.nomen.ntrain.base.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseRoleUserBean;

public interface BaseRoleUserDAO {
	/**
	 * 已引入的人员信息
	 * @param map  
	 * @param map
	 *          unitid :单位id （unitscope 当单位id为空时，该值用来判断，单位范围配合userunitid使用）
	 *          userunitid 登录人单位 
	 * @param page
	 * @param record
	 * @return
	 */
	public List<BaseRoleUserBean> findBaseRoleUserList(Map map,int page,int record);
	
	/**
	 * 未引入的人员信息
	 * @param map 
	 *          unitid :单位id （unitscope 当单位id为空时，该值用来判断，单位范围配合userunitid使用）
	 *          userunitid 登录人单位 
	 *          deptname 部门名称
	 * @return
	 */
	public List findBaseRoleUserList2(Map map,int page,int record);
	
	/**
	 * 删除用户信息
	 * @param userid
	 */
	public void deleteBaseRoleUserByUserid(String userid);
	
	/**
	 * 新增用户信息
	 * @param netRoleUserBean
	 * @return
	 */
	public String insertBaseRoleUser(BaseRoleUserBean baseRoleUserBean);
	
	/**
	 * 修改用户信息
	 * @param userid
	 */
	public void updateBaseRoleUser(BaseRoleUserBean baseRoleUserBean);
	
	/**
	 * 查询用户记录信息
	 * @param userid
	 * @return
	 */
	public String findBaseRoleUserCount(String userid);
	
	/**
	 * 查询某个人员是否是超级管理员
	 * @param userId :登录人员
	 * @return 
	 */
	public boolean isSuperManager(String userId);
}
