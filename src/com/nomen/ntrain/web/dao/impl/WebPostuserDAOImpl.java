package com.nomen.ntrain.web.dao.impl;

import java.util.Map;

import com.nomen.ntrain.util.NsoftBaseDao;
import com.nomen.ntrain.web.dao.WebPostuserDAO;
@SuppressWarnings("all")
public class WebPostuserDAOImpl extends NsoftBaseDao implements WebPostuserDAO {


	public String findPtrainPostuserPostid(Map map) {
		return (String)this.getSqlMapClientTemplate().queryForObject("webPostuser.findPtrainPostuserPostid", map);
	}

}
