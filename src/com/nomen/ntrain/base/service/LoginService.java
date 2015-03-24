package com.nomen.ntrain.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nomen.ntrain.annotation.LoginEnums;
import com.nomen.ntrain.base.bean.BaseLogonBean;
import com.nomen.ntrain.base.bean.BaseUnitBean;
import com.nomen.ntrain.base.bean.LoginBean;
import com.nomen.ntrain.base.util.CheckUserBean;
import com.nomen.ntrain.base.util.DataUserInfoBean;
import com.nomen.ntrain.data.bean.DataCodeBean;
import com.nomen.ntrain.data.bean.DataOrganBean;

/**
 * @description ��¼ģ��  
 * @author ������
 * @date 2009-05-18
 */
public interface LoginService{
	/*********************************************************/
	/*********************************************************/
	/******************   ���ⲿ����   *************************/
	/*********************************************************/
	/*********************************************************/
	/**
	 * ��ѯ��Ա��ϸ��Ϣ[2012-04-18]
	 */
	public DataUserInfoBean Comm_findDataUserInfoById(String id);
	
	/**
	 * [2010-11-23 ��Ҫ�������е�¼��֤��]
	 * ��ѯ��¼��Ա����Ϣ��������LoginBean��Ϣ
	 * @param Map
	 * @return Map
	 */
	public Map findLoginUser(Map map); 
	
	
    /**
     * ��ȡ��λ�б�[2011-05-23�޸�]
     * @param login ��¼��Ϣ
     * @param unitid ��ǰѡ�еĵ�λID
     * @return unitlist����λlist��unitid����λID[��unitidΪ���򷵻�login�еĵ�λ]
     */
	public Map findUnitListByUser(LoginBean login,String unitid);
	
	
	/**
	 * ��ѯĳ����λ�Լ����ӵ�λ
	 * @param fatherid
	 * @return
	 */
	public List findBaseUnitListByUnitid(String fatherid);
	
	/**
	 * ��ȡ�����õ�λ�б�[2011-05-23�޸�]
	 * @return List
	 */
	public List<BaseUnitBean> findAllUseUnit();
	
	/**
	 * ��ѯ����λ�б�
	 * @param unitId
	 * @return
	 */
	public List<BaseUnitBean> findOwnUnitListById(String unitId);


	/**
	 * ��ѯֱ����λ�����е�λ�ȣ������ع�˾��
	 * @return
	 */
	public List<BaseUnitBean> findBigUnitList();

	/**
	 * ��ѯ�����е��ع�˾
	 * @return
	 */
	public List<BaseUnitBean> findSmallUnitList();

	/**
	 * ��ѯ��λ��ֱ��������˾
	 * @return
	 */
	public List<BaseUnitBean> findChildUnitList(String unitId);
	
	
	
	/**
	 * ��ȡ�������ʡ�2010-11-23����]
	 * @param unitid,fatherid
	 * @return
	 */
	public List findDeptNatureList(Map map);
	
	/**
	 * ͨ����λ��ѯ�����б�[2013-04-20����]
	 * @param unitid
	 * @return
	 */
	public List findDeptListByUnitId(String unitid);
	
	/**
	 * ��ȡ�����б�[2011-05-23����]
	 * @see 1����ѯ�����б�ʱ���ݣ�unitid�� 2����ѯ��һ����ʱ���ݣ�id��
	 * @param unitid,id
	 * @return
	 */
	public List findDeptListByMap(Map map);

	/**
	 * ��ȡ���顢�Ӱ����б�[2011-05-23����]
	 * @see 1����ѯ���顢�Ӱ����б�ʱ���ݣ�fatherid������/����id�� 2����ѯ��һ����/�Ӱ���ʱ���ݣ�id��
	 * @param fatherid,id
	 * @return
	 */ 
	public List findGroupListByMap(Map map);

	
	/**
	 * ��ȡ�������ʺͲ����б����ú�Ȩ��������Ϣ��
	 * @param unitid ��λID
	 * @param nature ��������ID
	 * @param deptid ��ǰѡ�еĲ���ID����
	 * @param isabled �Ƿ��ܲ������ƣ�ֻ��ʾ��������Ϣ��true��ʾȫ����false��ʾ�����ţ�
	 * @return naturelist����������list��nature����������ID��deptlist������list��deptid������ID��
	 */
	public Map findDeptListByUser(String unitid,String nature,String deptid,boolean isabled); 
	
	/**
	 * ��ȡ�����б����ú�Ȩ��������Ϣ��
	 * @param deptid ����ID
	 * @param groupid ��ǰѡ�еİ���ID
	 * @param isabled �Ƿ��ܲ������ƣ�ֻ��ʾ��������Ϣ��true��ʾȫ����false��ʾ�����飩
	 * @return grouplist������list��groupid������ID��
	 */
	public Map findGroupListByUser(String deptid,String groupid,boolean isabled);
	
	/**
	 * ��ȡ�����б����ú�Ȩ��������Ϣ��
	 * @param fatherid ��������ID����ID
	 * @param groupid ��ǰѡ�еİ���ID
	 * @param isabled �Ƿ��ܲ������ƣ�ֻ��ʾ��������Ϣ��true��ʾȫ����false��ʾ�����飩 
	 * @return grouplist������list��groupid������ID��
	 */
	public Map findGroupList(String fatherid,String groupid,boolean isabled);
	
	/**
	 * ͨ�������ѯ��Ա�б�
	 * @param groupid ����ID
	 * @param userid ��ǰѡ�е���ԱID
	 * @param isabled �Ƿ��ܲ������ƣ�ֻ��ʾָ����Ա��Ϣ��true��ʾȫ����false��ʾָ����Ա�� 
	 * @return userlist����Աlist��userid����ԱID��
	 */
	public Map findUserList(String groupid,String userid,boolean isabled);
	
	/**
	 * ����������ѯ��Ա��Ϣ
	 * @param map��unitid����λID��nature���������ʣ�deptid������ID��
	 * 			  groupid������ID��groupid2���԰���ID;fields���ؼ��ֶΣ�keyword���ؼ�������
	 * @return
	 */
	public List<LoginBean> findAllUseUser(Map map);
	
	/**
	 * ��ʾͼƬ�ļ�
	 * @param map��	queryfield����ѯ���ֶ�����table����ѯ�ı������
	 * 			  	confield�������ֶΣ�convalue�������ֶ�ֵ
	 * @param response
	 * @return
	 */
	public void findImageBlob(Map map,HttpServletResponse response);
	
	/**
	 * ͬ����Ա��Ϣ��� ��unitid == -1 ��ʾȫʡ��Ա������֤
	 * @param unitid����λid��username��Ҫ��֤����Ա����
	 * @return ���ֶ�_sameuserlist����ͬ����Ա�б����ֶ�_okuser����������Ա���֣����ֶ�_okuserNum�� ������Ա�����������ֶ�_alluser�� ������Ա������html����  
	 */
	public Map findPeoNameSameOrNot(String unitid,String username); 
	
	/**
	 * ͬ����Ա��Ϣ��� ��unitid == -1 ��ʾȫʡ��Ա������֤
	 * @param unitid����λid/��λid����fields�����ҳ���ϵ��ֶ�������ϣ�request���������
	 * @return ���ֶ�_sameuserlist����ͬ����Ա�б����ֶ�_okuser����������Ա���֣����ֶ�_okuserNum�� ������Ա�����������ֶ�_alluser�� ������Ա������html����  
	 */
	public Map findPeoNameSameOrNot(String unitid,String fields,HttpServletRequest request); 

	/**
	 * ��ȡ��¼��Ա��Ȩ��ֵ
	 * @param id
	 * @return
	 */
	public String       findDataUserPurByUserId(String id);
	
	/**
	 * ��ѯ��Ա��Ϣ
	 * @param map��unitid����λID��username����Ա����
	 * @return
	 */
	public List<CheckUserBean> findUserInfoByMap(Map map);
	
	/**
	 * ��ѯ��Ա������������---ͬ����֤Ҫ�õ�
	 * @param map��unitid����λID��inNameStr����Ա���ִ�
	 * @return
	 */
	public List<CheckUserBean>   findUserNameStrCount(Map map);
	
	/*********************************************************/
	/*********************************************************/
	/******************   �ڲ�������   *************************/
	/*********************************************************/
	/*********************************************************/
	/**
	 * ��ȡϵͳ��¼�ɹ����������
	 * @param login
	 * @return
	 */
	public String findSystemOutStr(LoginBean login); 

	
	/**
	 * ��¼��¼/ע����־
	 * @param loginBean
	 */
	public void insertLoginLog(LoginBean loginBean,LoginEnums logEnums);


	/*********************************************************/
	/*********************************************************/
	/****************jquery/dwr����   *************************/
	/*********************************************************/
	/*********************************************************/

	
	/**
	 * ��ѯ������ϸ
	 * @param id
	 * @return
	 */
	public DataOrganBean findDataOrganById(String id);
	
	
	/**
	 * ������ԱID�� ��ѯ�б� ����-��
	 * @param userids
	 * @return
	 */
	public List findDateUserByUserids(String userids);
	

	
	/**
	 * �޸�����
	 * @param loginBean
	 */
	public void updatePassword(LoginBean loginBean);
}
