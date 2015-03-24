package com.nomen.ntrain.data.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.data.bean.DataOrganZBean;

/**
 * @description 部门和班组机构  
 * @author 钱新红
 * @date 2009-06-07
 */
@SuppressWarnings("unchecked")
public interface DataOrganZService {
	
	/****************************************************************************/
	/******************** 机构管理 >> 机构维护 [2014-11-18]修改 ********************/
	/****************************************************************************/
	/**
	 * 获取部门列表[根据fatherid]
	 * @param map
	 *        unitid  单位id
	 *        usesign 启用标志
	 *        nature  性质
	 *        fatherid 父类fatherid
	 *        fields  关键字段
	 *        keyword 关键字
	 * @return
	 */
	public List findDataOrganZListByFatherid(Map map);
	
	/**
	 * 查询部门列表[树状]
	 * @param map
	 *        unitid  单位id
	 *        usesign 启用标志
	 *        nature  性质
	 *        fatherid 父类fatherid
	 *        fields  关键字段
	 *        keyword 关键字
	 * @return
	 */
	public List findDataOrganZTreeList(Map map);
	
	/**
	 * 根据选择idStr查询机构树状列表
	 * @param map
	 *        fatherid 父类fatherid
	 *        idstr    idstr
	 * @return
	 */
	public List findDataOrganZTreeListByIdStr(Map map);
	
	/**
	 * 通过主键查询机构信息
	 * @param 
	 * 		  id 机构主键ID
	 * @return
	 */
	public DataOrganZBean findDataOrganZBeanById(String id);
	
	/**
	 * 通过主键ID查询 
	 * 机构树字符串 (部门>>班组>>子班组 形式)
	 * @param 
	 * 		  id 机构主键ID
	 * @return
	 */
	public DataOrganZBean findDataOrganZTreeBeanById(String id);
	
	/**
	 * 保存机构信息
	 * @param map
	 *        dataOrganZBean  机构信息bean
	 * @throws Exception
	 */
	public String saveDataOrganZBean(Map map) throws Exception;
	
	/**
	 * 保存机构合并信息
	 * @param map
	 *        idstr  
	 *        newdeptid  新的部门id
	 *        newgroupid 新的班组id
	 *        userid     操作人员
	 *        dataOrganZBean 
	 * @return
	 */
	public void saveDataOrganZMerge(Map map)throws Exception;
	
	/**
	 * 添加机构信息
	 * @param dataOrganZBean
	 * @throws Exception
	 */
	public String insertDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception;
	
	/**
	 * 修改机构信息
	 * @param dataOrganZBean
	 * @throws Exception
	 */
	public void updateDataOrganZBean(DataOrganZBean dataOrganZBean) throws Exception;
	
	/**
	 * 级联删除机构信息 
	 * @param 
	 * 		 id 机构主键ID
	 */
	public void deleteDataOrganZBeanById(String id);

	/**
	 * 修改启/禁用标识
	 * @param id  主键id
	 *        usesign 启用|禁止
	 *        mainuser 维护人userid
	 */
	public void updateDataOrganZUseSign(Map param);
	
	/**
	 * 查询同级下的下一个排序号
	 * @param map
	 * 		  unitid，单位ID
	 *        nature，部门性质
	 *        fatherid，机构ID
	 * @return
	 */
	public String findNextSortNum(Map map);
	
	/**
	 * 查询部门同级下最大的机构编号
	 * @param unitid    单位id
	 * @param nature    性质
	 * @param parentid  父类fatherid
	 * @return
	 */
	public String findDeptMaxOrganZCode(String unitid,String nature,String parentid);
	
	/**
	 * 查询班组同级下最大的机构编号
	 * @param unitId       单位id
	 * @param parentOrgId  父类fatherid
	 * @return
	 */
	public String findGroupMaxOrgCode(String unitId,String parentOrgId);
	
	/**
	 * 查询子班组同级下最大的机构编号
	 * @param unitId        单位id
	 * @param parentOrgId   父类fatherid
	 * @return
	 */
	public String findSubGroupMaxOrgCode(String unitId,String parentOrgId);
	
	/**
	 * 验证判断编号是否已存在
	 * @param map
	 *        codevalue  编码
	 *        fatherId   父类fatherid
	 *        unitid     单位id
	 * @return
	 */
	public String chkDataOrganZCodeValueIsExists(Map map);
	
}
