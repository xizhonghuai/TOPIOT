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
 * @date 2018年7月10日
 * @readme 数据获取接口
 */
@Repository
public interface DataApiDao {

	List<TestDataModel> getTestData(ReqDataParams reqDataParams);
	
	
	List<WxyDataModel> getWxyData(ReqDataParams reqDataParams);

}
