package com.nomen.ntrain.res.service;

import java.util.List;
import java.util.Map;

import com.nomen.ntrain.res.bean.ResCodeBean;

/**
 * @description ��Դ����_����� ҵ���
 * @author ���
 * @date 2011-5-18
 */
public interface ResCodeService {
	/**
	 * ��ѯ ��Դ����_����� �б�
	 * 
	 * @return
	 */
    public List findResCodeList(Map param);
}