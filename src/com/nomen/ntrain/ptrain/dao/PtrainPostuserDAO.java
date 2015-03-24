package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPostuserBean;

/**
 * @description 莆田岗位培训_岗位人员数据库操作接口
 * @author 林木山
 * @date 2014-3-10
 */
public interface PtrainPostuserDAO {

	/**
	 * 查找莆田岗位培训_岗位人员列表信息[分页]
	 * @param map
	 * @return
	 */
	public List<PtrainPostuserBean> findPtrainPostuserList(Map map,int page,int record);

	/**
	 * 查找莆田岗位培训_岗位人员列表信息[无分页]
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
	 * 添加莆田岗位培训_岗位人员信息
	 * @param ptrainPostuserBean
	 * @return
	 */
	public String insertPtrainPostuser(PtrainPostuserBean ptrainPostuserBean);

	/**
	 * 更新莆田岗位培训_岗位人员信息
	 * @param ptrainPostuserBean
	 * @return
	 */
	public void updatePtrainPostuser(PtrainPostuserBean ptrainPostuserBean);

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
	 * 更新莆田岗位培训_岗位人员信息 人员ID
	 * @param ptrainPostuserBean
	 * @return
	 */
	public void updatePtrainPostuserUserid(Map map);

	/**
	 * 删除莆田岗位培训_岗位人员信息 人员ID为空
	 * @param id
	 * @return
	 */
	public void deletePtrainPostuserEmpty(Map map);

	/**
	 * 删除莆田岗位培训_岗位人员信息 重复人员
	 * @param id
	 * @return
	 */
	public void deletePtrainPostuserRepe(Map map);

	/**
	 * 查找岗位类别ID 根据人员ID、单位ID
	 */
	public String findPtrainPostuserPostid(Map map);

}
