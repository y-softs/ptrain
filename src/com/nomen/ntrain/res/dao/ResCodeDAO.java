package com.nomen.ntrain.res.dao;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.res.bean.ResCodeBean;

/**
 * @description ��Դ����_����� DAO��
 * @author ���
 * @date 2011-5-18
 */
public interface ResCodeDAO {
	/**
	 * ��ѯ ��Դ����_����� �б�
	 * @return
	 */
    public List findResCodeList(Map param);
}