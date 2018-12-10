/**
 * 
 */
package com.protocol.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.SelectMethodAnnotation;
import com.protocol.test.TestDataModel;
import com.protocol.wxy.WxyDataModel;

/**
 * @author xizhonghuai
 * @date 2018年7月23日
 * @readme
 */

@Service
public class GetData {

	@Autowired
	private DataApiDao dataApiDao;

	/**
	 * @date 2018年7月23日
	 * @readme 测试数据获取
	 * @param reqDataParams
	 * @return
	 */
	@SelectMethodAnnotation(handler = "com.protocol.test.TestHandler")
	public List<TestDataModel> getTestData(ReqDataParams reqDataParams) {

		return dataApiDao.getTestData(reqDataParams);

	}
	
	
	
	/**
	 * @date 2018年11月08日
	 * @readme 危险源数据获取
	 * @param reqDataParams
	 * @return
	 */
	@SelectMethodAnnotation(handler = "com.protocol.wxy.WxyDataModel")
	public List<WxyDataModel> getWxyData(ReqDataParams reqDataParams) {

		return dataApiDao.getWxyData(reqDataParams);

	}

}
