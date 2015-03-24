package com.nomen.ntrain.base.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nomen.ntrain.base.bean.BaseRoleUserBean;
import com.nomen.ntrain.base.dao.BaseRoleUserDAO;
import com.nomen.ntrain.util.NsoftBaseDao;

public class BaseRoleUserDAOImpl extends NsoftBaseDao implements BaseRoleUserDAO {

	public void deleteBaseRoleUserByUserid(String userid) {
		this.getSqlMapClientTemplate().delete("BaseRoleUser.deleteBaseRoleUserByUserid",userid);
	}

	public String findBaseRoleUserCount(String userid) {
		return (String)this.getSqlMapClientTemplate().queryForObject("BaseRoleUser.findBaseRoleUserCount",userid);
	}
	


	public List<BaseRoleUserBean> findBaseRoleUserList(Map map, int page,
			int record) {
		map.put("total", this.getObjectTotal("BaseRoleUser.findBaseRoleUserList", map));
		return this.getSqlMapClientTemplate().queryForList("BaseRoleUser.findBaseRoleUserList",map,page,record);
	}
	
	public List findBaseRoleUserList2(Map map, int page, int record) {
		map.put("total", this.getObjectTotal("BaseRoleUser.findBaseRoleUserList2", map));
		return this.getSqlMapClientTemplate().queryForList("BaseRoleUser.findBaseRoleUserList2",map,page,record);
	}

	public String insertBaseRoleUser(BaseRoleUserBean baseRoleUserBean) {
		return (String)this.getSqlMapClientTemplate().insert("BaseRoleUser.insertBaseRoleUser",baseRoleUserBean);
	}

	public boolean isSuperManager(String userId) {
		Map map = new HashMap();
		map.put("userid", userId);
		//map.put("rolesuperid", Constant.ROLE_SUPER_ID);
		String rtn  =  (String)this.getSqlMapClientTemplate().queryForObject("BaseRoleUser.isSuperManager",map);
		return Integer.parseInt(rtn)>0?true:false;
	}

	public void updateBaseRoleUser(BaseRoleUserBean baseRoleUserBean) {
		this.getSqlMapClientTemplate().update("BaseRoleUser.updateBaseRoleUser",baseRoleUserBean);

	}



}
