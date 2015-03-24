package com.nomen.ntrain.ptrain.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainFileBean;

/**
 * @description 莆田岗位学习_附件表业务逻辑层
 * @author 林木山
 * @date 2014-3-14
 */
public interface PtrainFileService {

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
	 * @param trainFileBean
	 * @return
	 */
	public String insertPtrainFile(PtrainFileBean ptrainFileBean);

	/**
	 * 更新附件外键
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
	 * 删除莆田岗位学习_附件表信息[参数map]
	 * @param fileid	   主键id
	 * @param fatherPath   绝对路径
	 */
	public int deletePtrainFileByMap(Map map);

}
