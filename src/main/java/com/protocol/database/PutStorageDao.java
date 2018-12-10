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
 *@date 2018年7月10日
 * @readme 数据入库接口
 */
@Repository
public interface PutStorageDao {
	
	

	 
	/**
	 *@date 2018年7月10日
	 *@readme 测试数据入库
	 *@param testDataModel
	 */
	void insertTestRealtimeData(TestDataModel testDataModel);
	void updateTestRealtimeData(TestDataModel testDataModel);
	
	
	
	/**
	 *@date 2018年8月8日
	 *@readme 代理服务器测试入库
	 *@param proxyDataModel
	 */
	void insertProxyRealtimeData(ProxyDataModel proxyDataModel);
	void updateProxyRealtimeData(ProxyDataModel proxyDataModel);
	
	
	
   /**
 *@date 2018年9月3日
 *@readme  水库安全监测+道路积水
 *@param cqswDataModel
 */
void updateCqswRealtimeData(CqswDataModel cqswDataModel);
	

}
