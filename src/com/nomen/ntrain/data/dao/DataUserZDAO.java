package com.nomen.ntrain.data.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.data.bean.DataPostBean;
import com.nomen.ntrain.data.bean.DataUserZBean;
/**
 * 
 * @author Administrator
 * 2014-11-20
 */
@SuppressWarnings("all")
public interface DataUserZDAO {	
	/****************************************************************************/
	/******************** 人员管理 >> 更新维护 [2014-11-20]修改 ********************/
	/****************************************************************************/
	/**
	 * 获取人员列表[根据fatherid]
	 * @param map
	 *        deptid  部门id
	 *        groupid 班组id
	 *        stateid 人员状态
	 *        fields  关键字段
	 *        keyword 关键字
	 * @return
	 */
	public List findDataUserZList(Map map,int page, int record);
	
	/**
	 * 获取人员Bean[根据id]
	 * @param string
	 *        id  主键id
	 * @return
	 */
	public DataUserZBean findDataUserZBeanById(String id);
	
	/**
	 * 新增人员信息
	 * @param dataUserZBean  人员信息Bean
	 */
	public String insertDataUserZBean(DataUserZBean dataUserZBean);
	
	/**
	 * 更新人员信息
	 * @param dataUserZBean  人员信息Bean
	 */
	public void updateDataUserZBean(DataUserZBean dataUserZBean);
	
	/**
	 * 删除人员信息
	 * @param idstr  主键idstr
	 */
	public void deleteDataUserZBeanByIdStr(String idstr);
	
	/**
	 * 获取人员的常用代码列表 （机构代码表）【通用】
	 * @param map
	 * 		  fatherid  父类fatherid
	 *        unitid  单位id
	 * @return
	 */
	public List findDataCodeList(Map map);
	
	/**
	 * 新增岗位信息
	 * @param dataPostBean  人员岗位Bean
	 */
	public String insertDataPostBean(DataPostBean dataPostBean);

	/**
	 * 更新岗位信息
	 * @param dataPostBean  人员岗位Bean
	 */
	public void updateDataPostBean(DataPostBean dataPostBean);
}