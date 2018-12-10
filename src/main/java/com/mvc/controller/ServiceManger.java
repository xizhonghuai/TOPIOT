/**
 * 
 */
package com.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Initialize;
import com.application.SpringBeanUtils;
import com.common.ServiceProperty;
import com.common.TopiotService;
import com.common.methods;

/**
 * @author XIZHONGHUAI
 *
 */
@Controller
public class ServiceManger {

	 
 
	@RequestMapping(value = "/getService", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public List<ServiceProperty> getService() {

		List<ServiceProperty> serviceList = new ArrayList<ServiceProperty>();
		Map<String, ServiceProperty> services = new ConcurrentHashMap<String, ServiceProperty>();

		services = Initialize.serviceList;

		for (Entry<String, ServiceProperty> entry : services.entrySet()) {

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(entry.getKey());
			entry.getValue().setStatus(ts.getStatus());
			serviceList.add(entry.getValue());
		}

		return serviceList;
	}

	/**
	 * @param servicreId
	 * @return
	 */
	@RequestMapping(value = "/stService", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String stService(@RequestParam(value = "servicreId") String servicreId) {
		try {
			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(servicreId);
			if (ts.getStatus()) {
				ts.close();
			} else {
				ts.start();
			}

		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return "ִ�гɹ���";
	}

	/**
	 * @param sp
	 * @return
	 */
	@RequestMapping(value = "/postService", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String postService(ServiceProperty sp) {

		try {
			if (methods.checServiceIdRepetition(sp.getServiceId())) {

				return "����id�Ѵ���";

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}

		try {
			/* ��̬����xml�����ļ� */
			/* methods.createService(sp); */

			return methods.createServiceXml(sp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.toString();
		}

	}

}
