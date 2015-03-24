package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;

/**
 * @description 莆田岗位培训_岗位人员业务逻辑层
 * @author 林木山
 * @date 2014-3-10
 */
public interface PtrainPostuserService {

	/**
	 * 查找莆田岗位培训_岗位人员列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainPostuserBean> findPtrainPostuserList(Map map);

	/**
	 * 查找莆田岗位培训_岗位人员信息
	 * @param id
	 * @return
	 */
	public PtrainPostuserBean findPtrainPostuserBeanById(String id);

	/**
	 * 保存莆田岗位培训_岗位人员信息
	 * @param ptrainPostuserBean
	 * @return
	 */
	public void savePtrainPostuser(PtrainPostuserBean ptrainPostuserBean);

	/**
	 * 删除莆田岗位培训_岗位人员信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPostuserById(String id);

	/**
	 * 删除莆田岗位培训_岗位人员信息
	 * @param id
	 * @return
	 */
	public void deletePtrainPostuserByMap(Map map);

	/**
	 * 根据条件查询人员信息
	 * @param map
	 * @return
	 */
	public List findAllUseUser(Map map);

	/**
	 * 删除莆田岗位培训_岗位人员信息 excel导入
	 * @param id
	 * @return
	 */
	public void savePtrainPostuserExcel(Map map);
	
	/**
	 * 莆田岗位培训_岗位人员信息 excel导出
	 */
	public void savePtrainPostuserExpExcel(Map map,HttpServletResponse response) throws Exception;

	/**
	 * 查找岗位类别ID 根据人员ID、单位ID
	 */
	public String findPtrainPostuserPostid(Map map);

}
