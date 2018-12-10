/**
 * 
 */
package com.protocol.database;

import org.springframework.stereotype.Repository;

import com.protocol.proxy.ProxyDataModel;
import com.protocol.safemon.CqswDataModel;
import com.protocol.test.TestDataModel;

 

 
/**
 * @author xizhonghuai
 *@date 2018��7��10��
 * @readme �������ӿ�
 */
@Repository
public interface PutStorageDao {
	
	

	 
	/**
	 *@date 2018��7��10��
	 *@readme �����������
	 *@param testDataModel
	 */
	void insertTestRealtimeData(TestDataModel testDataModel);
	void updateTestRealtimeData(TestDataModel testDataModel);
	
	
	
	/**
	 *@date 2018��8��8��
	 *@readme ����������������
	 *@param proxyDataModel
	 */
	void insertProxyRealtimeData(ProxyDataModel proxyDataModel);
	void updateProxyRealtimeData(ProxyDataModel proxyDataModel);
	
	
	
   /**
 *@date 2018��9��3��
 *@readme  ˮ�ⰲȫ���+��·��ˮ
 *@param cqswDataModel
 */
void updateCqswRealtimeData(CqswDataModel cqswDataModel);
	

}
