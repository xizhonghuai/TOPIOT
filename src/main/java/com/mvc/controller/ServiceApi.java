/**
 * 
 */
package com.mvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.Initialize;
import com.application.SpringBeanUtils;
import com.common.HandlerClassInfo;
import com.common.ResultDataSet;
import com.common.ServiceProperty;
import com.common.SysInfo;
import com.common.TopiotService;
import com.common.methods;
import com.core.mina.MinaSys;
import com.core.mina.SessionsProperty;

/**
 * @author xizhonghuai
 * @date 2018��7��11��
 * @readme
 */

@Controller
@RequestMapping("/service.api")
public class ServiceApi {

	/**
	 * @date 2018��7��17��
	 * @readme ��ȡ������Ϣ
	 * @param servicreId
	 * @return
	 */
	@RequestMapping(value = "/getservice", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet getService(@RequestParam(value = "serviceId", required = false) String serviceId) {

		ResultDataSet resultDataSet = new ResultDataSet();
		List<ServiceProperty> serviceList = new ArrayList<ServiceProperty>();

		Map<String, ServiceProperty> services = new ConcurrentHashMap<String, ServiceProperty>();

		try {

			services = Initialize.serviceList;

			if (serviceId == null) {

				for (Entry<String, ServiceProperty> entry : services.entrySet()) {

					TopiotService ts = (TopiotService) SpringBeanUtils.getBean(entry.getKey());

					entry.getValue().setStatus(ts.getStatus());

					serviceList.add(entry.getValue());
				}

			} else {

				ServiceProperty sp = services.getOrDefault(serviceId, null);
				if (sp != null) {
					TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

					sp.setStatus(ts.getStatus());

					serviceList.add(sp);
				}

			}

			resultDataSet.setResult(serviceList);

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}

		return resultDataSet;
	}

	/**
	 * @date 2018��7��18��
	 * @readme ������ָֹͣ������
	 * @param servicreId
	 * @return
	 */
	@RequestMapping(value = "/controlservice", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet controlService(@RequestParam(value = "serviceId", required = false) String serviceId,
			@RequestParam(value = "cmd", required = false) String cmd) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {
			if ((serviceId == null) || (cmd == null)) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("��������");
				return resultDataSet;
			}
			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);
			boolean flag = ts.getStatus();
			if (cmd.equals("stop")) {

				if (flag) {
					ts.close();
				}

				resultDataSet.setResult("ֹͣ�ɹ�");

			} else if (cmd.equals("start")) {

				if (!flag) {
					ts.start();
				}
				resultDataSet.setResult("�����ɹ�");
			}

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}
		return resultDataSet;
	}

	/**
	 * @date 2018��7��18��
	 * @readme ��ȡָ������ͻ�����Ϣ
	 * @param servicreId
	 * @return
	 */
	@RequestMapping(value = "/getserviceconn", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet getServiceConn(@RequestParam(value = "serviceId", required = false) String serviceId) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {

			if (serviceId == null) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("��������");
				return resultDataSet;
			}

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

			Map<Long, IoSession> sessionsList = ts.getManagedSessions();

			List<SessionsProperty> sessionsPropertyList = new ArrayList<SessionsProperty>();

			for (Entry<Long, IoSession> entry : sessionsList.entrySet()) {

				SessionsProperty sessionsProperty = new SessionsProperty();

				IoSession session = entry.getValue();

				if ((String) session.getAttribute("regId", null) == null) {
					continue;
				}

				sessionsProperty.setRegId((String) session.getAttribute("regId", null));
				sessionsProperty.setRegisterTime(new Date(session.getCreationTime()));
				sessionsProperty.setActivityTime(new Date(session.getLastReadTime()));
				sessionsProperty.setAddress(session.getRemoteAddress().toString());

				sessionsPropertyList.add(sessionsProperty);
			}

			resultDataSet.setResult(sessionsPropertyList);

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}

		return resultDataSet;

	}

	/**
	 * @date 2018��7��24��
	 * @readme ��ȡ����������
	 * @param serviceId
	 * @return
	 */
	@RequestMapping(value = "/getservicecount", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet getServiceCount(@RequestParam(value = "serviceId", required = false) String serviceId) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {
			if (serviceId == null) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("��������");
				return resultDataSet;
			}

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

			resultDataSet.setResult(ts.getManagedSessions().size());

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}
		return resultDataSet;

	}

	/**
	 * @date 2018��7��18��
	 * @readme ��ȡӦ�ò�Э����Ϣ
	 * @param packname
	 * @return
	 */
	@RequestMapping(value = "/getprotocol", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet getProtocol(@RequestParam(value = "handler", required = false) String handler) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {
			if (handler == null) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("��������");
				return resultDataSet;
			}

			HandlerClassInfo handlerClassInfo = new HandlerClassInfo();

			handlerClassInfo = methods.getHandlerClassInfo(handler);

			resultDataSet.setResult(handlerClassInfo);

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}
		return resultDataSet;
	}

	/**
	 * @date 2018��7��19��
	 * @readme ���豸����ָ��
	 * @param servicreId
	 * @param regId
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value = "/sendcmd", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ResultDataSet sendCmd(@RequestParam(value = "serviceId", required = false) String serviceId,
			@RequestParam(value = "regId", required = false) String regId,
			@RequestParam(value = "cmd", required = false) Object cmd) {

		ResultDataSet resultDataSet = new ResultDataSet();

		try {
			if ((serviceId == null) || (regId == null) || (cmd == null)) {
				resultDataSet.setError_code(SysInfo.PARERROR);
				resultDataSet.setReason("��������");
				return resultDataSet;
			}

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

			if (!ts.getStatus()) {
				resultDataSet.setError_code(SysInfo.THROWERROR);
				resultDataSet.setReason("�÷���δ����");
				return resultDataSet;
			}

			if (regId.equals("****")) {

				MinaSys.SendMsg(ts.getManagedSessions(), methods.encodeGBK(cmd.toString()));

			} else {

				IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), regId);

				if (eqSSession == null) {
					resultDataSet.setError_code(SysInfo.THROWERROR);
					resultDataSet.setReason("���豸��������״̬");
					return resultDataSet;
				}

				eqSSession.write(methods.encodeGBK(cmd.toString()));

				// MinaSys.SendMsg(ts.getManagedSessions(),
				// methods.encodeGBK(cmd.toString()), regId);

			}

			resultDataSet.setResult("�����ѷ���");

		} catch (Exception e) {
			// TODO: handle exception

			resultDataSet.setError_code(SysInfo.EXCEPTION);
			resultDataSet.setReason(e.toString());

		}
		return resultDataSet;
	}

}
