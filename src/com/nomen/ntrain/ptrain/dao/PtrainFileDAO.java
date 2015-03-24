package com.nomen.ntrain.ptrain.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFileBean;

/**
 * @description 莆田岗位学习_附件表数据库操作接口
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainFileDAO {

	/**
	 * 查找莆田岗位学习_附件表列表信息
	 * @param map
	 * @return
	 */
	public List<PtrainFileBean> findPtrainFileList(Map map);

	/**
	 * 查找莆田岗位学习_附件表信息
	 * @param id
	 * @return
	 */
	public PtrainFileBean findPtrainFileBeanById(String id);

	/**
	 * 添加莆田岗位学习_附件表信息
	 * @param ptrainFileBean
	 * @return
	 */
	public String insertPtrainFile(PtrainFileBean ptrainFileBean);

	/**
	 * 更新莆田岗位学习_附件表信息
	 * 将strflag为页面提交过来的随机值记录recid
	 * @param FINAL_RANDOMSTR
	 * @param recId
	 * @return
	 */
	public void updatePtrainFileByRandomStr(String FINAL_RANDOMSTR,String recId);    

	/**
	 * 删除莆田岗位学习_附件表信息
	 * @param id
	 * @return
	 */
	public void deletePtrainFileById(String id);
	/**
	 * 根据modsign 和recid  删除
	 * @param modsign，recid
	 */
	public void deletePtrainFileByMap(Map map);

	/**
	 * 添加莆田岗位学习_附件表信息 记录复制
	 */
	public void insertPtrainFileByCopy(Map map);

}
