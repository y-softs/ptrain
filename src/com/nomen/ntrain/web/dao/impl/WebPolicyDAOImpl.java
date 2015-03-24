package com.nomen.ntrain.web.dao.impl;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.ptrain.bean.PtrainPolicyBean;
import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebPolicyDAO;
@SuppressWarnings("all")
public class WebPolicyDAOImpl extends NsoftBaseDao implements WebPolicyDAO {

	public List<PtrainPolicyBean> findPtrainPolicyList(Map map){
		return (List<PtrainPolicyBean>)this.getSqlMapClientTemplate().queryForList("webPolicy.findPtrainPolicyList", map);
	}
}
