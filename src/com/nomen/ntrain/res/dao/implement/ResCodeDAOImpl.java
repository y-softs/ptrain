package com.nomen.ntrain.res.dao.implement;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.res.bean.ResCodeBean;
import com.nomen.ntrain.res.dao.ResCodeDAO;
import com.nomen.ntrain.util.NsoftBaseDao;

public class ResCodeDAOImpl extends NsoftBaseDao implements ResCodeDAO {
	public List findResCodeList(Map param) {
		return this.getSqlMapClientTemplate().queryForList("ResCode.getResCodeList", param);
	}
}