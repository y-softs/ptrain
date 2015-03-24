package com.nomen.ntrain.web.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainCoursBean;

/**
 * @description 莆田岗位培训_课件资源表数据库操作接口
 * @author 许东雄
 * @date 2014-11-16
 */
public interface WebCoursService {

	/**
	 * 课件列表
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursList(Map map,int page,int record);

	/**
	 * 目录列表
	 * @param map
	 * @return
	 */
	public List<PtrainCoursBean> findPtrainCoursChilList(Map map);

	/**
	 * Bean 查询
	 * @param id
	 * @return
	 */
	public PtrainCoursBean findPtrainCoursBeanById(String id);


}
