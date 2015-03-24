package com.nomen.ntrain.web.dao;

import java.util.Map;

/**
 * @description 莆田岗位培训_岗位人员数据库操作接�?
 * @author 林木�?
 * @date 2014-3-10
 */
public interface WebPostuserDAO {


	/**
	 * 查找岗位类别ID 根据人员ID、单位ID
	 */
	public String findPtrainPostuserPostid(Map map);

}
