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
 * @date 2018��7��23��
 * @readme
 */

@Service
public class GetData {

	@Autowired
	private DataApiDao dataApiDao;

	/**
	 * @date 2018��7��23��
	 * @readme �������ݻ�ȡ
	 * @param reqDataParams
	 * @return
	 */
	@SelectMethodAnnotation(handler = "com.protocol.test.TestHandler")
	public List<TestDataModel> getTestData(ReqDataParams reqDataParams) {

		return dataApiDao.getTestData(reqDataParams);

	}
	
	
	
	/**
	 * @date 2018��11��08��
	 * @readme Σ��Դ���ݻ�ȡ
	 * @param reqDataParams
	 * @return
	 */
	@SelectMethodAnnotation(handler = "com.protocol.wxy.WxyDataModel")
	public List<WxyDataModel> getWxyData(ReqDataParams reqDataParams) {

		return dataApiDao.getWxyData(reqDataParams);

	}

}
