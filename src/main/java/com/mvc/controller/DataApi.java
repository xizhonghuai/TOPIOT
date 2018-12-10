/**
 * 
 */
package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.ResultDataSet;
import com.common.SelectMethodAnnotation;
import com.common.SysInfo;
import com.common.methods;
import com.protocol.database.GetData;
import com.protocol.database.ReqDataParams;

/**
 * @author XIZHONGHUAI
 *
 */
@Controller
@RequestMapping("/data.api")
public class DataApi {

	@Autowired
	private GetData getData;
 
	@RequestMapping(value = "/getdata", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet getData(ReqDataParams reqDataParams) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {

			if (reqDataParams == null) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("参数错误");
				return resultDataSet;
			}

			if (reqDataParams.getHandler() == null) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("参数错误");
				return resultDataSet;
			}
			
			
			System.out.println(reqDataParams.toString());
			System.out.println(getData.getClass().getName());

		 
		 
			 Object	 obj = methods.invoke(getData, SelectMethodAnnotation.class, "handler", reqDataParams.getHandler(),
					reqDataParams);
			
			resultDataSet.setResult(obj);

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}
		return resultDataSet;
	}

}
