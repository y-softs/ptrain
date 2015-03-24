package com.nomen.ntrain.data.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.data.bean.DataPostBean;
import com.nomen.ntrain.data.bean.DataUserZBean;

/**
 * @description 
 * @author 
 * @date 2014-11-20
 */
@SuppressWarnings("unchecked")
public interface DataUserZService {
	
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
	 * 保存人员信息
	 * @param map
	 * 
	 */
	public String saveDataUserZBean(Map map);
	
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
	
}
