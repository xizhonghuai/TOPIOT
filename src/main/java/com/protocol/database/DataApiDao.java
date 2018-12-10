/**
 * 
 */
package com.protocol.database;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.protocol.test.TestDataModel;
import com.protocol.wxy.WxyDataModel;

/**
 * @author xizhonghuai
 * @date 2018��7��10��
 * @readme ���ݻ�ȡ�ӿ�
 */
@Repository
public interface DataApiDao {

	List<TestDataModel> getTestData(ReqDataParams reqDataParams);
	
	
	List<WxyDataModel> getWxyData(ReqDataParams reqDataParams);

}
