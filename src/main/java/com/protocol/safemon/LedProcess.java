/**
 * 
 */
package com.protocol.safemon;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.core.mina.MinaSys;
import com.protocol.database.PutStorageDao;
import com.protocol.safemon.postmsg.LedData;
import com.protocol.safemon.postmsg.PostService;
import com.protocol.safemon.postmsg.Value2;
import com.protocol.safemon.sys.CqswMethods;
import com.protocol.safemon.sys.FindPar;
import com.protocol.safemon.sys.LED;
import com.protocol.safemon.sys.LED_Realtime;
import com.protocol.safemon.sys.SafeMonMapper;

/**
 * @author xizhonghuai
 * @date 2018��8��29��
 * @readme
 */
@Component("LedProcess")
public class LedProcess {

	private Logger logger = Logger.getLogger(LedProcess.class);

	@Autowired
	private SafeMonMapper safeMonMapper;

	@Autowired
	private PutStorageDao putStorageDao;

	/**
	 * @param session
	 * @param msg
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void Controlmessage(IoSession session, String msg) throws IOException {

		String regId = (String) session.getAttribute("regId", null);

		Map<String, String> cmd = new ConcurrentHashMap<String, String>();
		String s = msg.replace("(", "~");
		s = s.replace(")", "~");
		String msgarray[] = s.split("~");

		for (int i = 0; i < msgarray.length; i++) {

			if (msgarray[i].length() > 0) {

				cmd = getCMD(msgarray[i]);

				String Cmdtype = cmd.get("Cmdtype");

				if (Cmdtype.equals("s")) {

					String NewRegId = cmd.get("NewRegId");
					if (regId == null || !(regId.equals(NewRegId))) {

						IoSession oldSession = MinaSys.regIdToSession(session.getService().getManagedSessions(),
								NewRegId);
						if (oldSession != null) {
							oldSession.close();
						}

						session.write("(s)");
						session.setAttribute("regId", NewRegId);
						logger.info(
								"�豸" + NewRegId + session.getRemoteAddress() + "-----------------------------------ע��");

						// ����ز�����session
						FindPar par_LD = new FindPar();
						List<LED> ledlist_LD = new ArrayList<LED>();

						par_LD.setStcd(NewRegId);
						par_LD.setCount(0);
						par_LD.setPage(0);
						try {
							ledlist_LD = safeMonMapper.getLED(par_LD); // ��ȡLED���豸��Ϣ

						} catch (Exception e) {
							// TODO: handle exception
							logger.info("��ȡ��ˮվ�豸" + NewRegId + "�����Ϣʧ��");
						}

						if (ledlist_LD.size() > 0) {
							LED led = new LED();
							led = ledlist_LD.get(0);
							if (led.getEA().intValue() == 2) {
								session.write("(g_0_0)"); // �ر���ʾ���������
							}
						}
					}
				}

				else if (Cmdtype.equals("p")) {
					if (regId != null) {
						session.write("(p)");
						logger.info(regId + "��������������");
						// led������Ϣ����
						LED_Realtime led_realtime = new LED_Realtime();
						led_realtime.setLED_STCD(regId);
						led_realtime.setCard_state(cmd.get("Card_state"));
						led_realtime.setScreen_state(cmd.get("Screen_state"));
						led_realtime.setLight_state(cmd.get("Light_state"));
						led_realtime.setTM(new Date());
						session.setAttribute("led_realtime", led_realtime); // ����LED������״̬��Ϣ��session

						CqswDataModel cqswDataModel = new CqswDataModel();

						// ������⡢
						cqswDataModel.setId(2);// LEDվ
						cqswDataModel.setRegId(regId);
						cqswDataModel.setLedStatus1(cmd.get("Screen_state"));
						cqswDataModel.setLedStatus2(cmd.get("Light_state"));
						cqswDataModel.setTm(new Date());
						putStorageDao.updateCqswRealtimeData(cqswDataModel);

						// ����ת�����ͻ���

						debugHandler.sendMessage(regId + "*" + regId + cmd.toString());

						// �ύ������Զ�̷�����
						Value2 value = new Value2();
						LedData ledData = new LedData();
						String calltext = (String) session.getAttribute("calltext", "����ͨ��");

						if (cqswDataModel.getLedStatus1().equals("��")) {
							value.setStatus(1);
						} else {
							value.setStatus(0);
						}
						value.setText(calltext);
						ledData.setDeviceId(regId);
						SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						ledData.setDataTime(dt.format(cqswDataModel.getTm()));
						ledData.setValue(value);

						new PostService(CqswMethods.POSTURL + "api/submit/led/", JSONObject.toJSONString(ledData))
								.start();

					}
				} else {

					session.write("(" + cmd.get("Dvicetype") + ")");
					// ����ת�����ͻ���
					debugHandler.sendMessage(regId + "*" + regId + cmd.toString());
				}

			}

		}

	}

	private Map<String, String> getCMD(String msg) {

		Map<String, String> CMD = new ConcurrentHashMap<String, String>();
		String arr[] = msg.split("_");
		String cmdtype = arr[0];
		if (cmdtype.equals("m")) {
			cmdtype = "p";
		}

		int value = cmdtype.charAt(0);
		switch (value) {

		case 0x73: {
			CMD.put("Cmdtype", "s");
			CMD.put("NewRegId", arr[1]);
		}

			break;
		case 0x70: {
			CMD.put("Cmdtype", "p");
			CMD.put("Dvicetype", arr[1]);
			if (arr[1].equals("LED")) {

				if (arr[2].equals("1")) {
					arr[2] = "��";
				} else
					arr[2] = "�ر�";

				if (arr[3].equals("1")) {
					arr[3] = "����";
				} else
					arr[3] = "�쳣";

				if (arr[4].equals("1")) {
					arr[4] = "��";
				} else
					arr[4] = "�ر�";

				CMD.put("Screen_state", arr[2]);
				CMD.put("Card_state", arr[3]);
				CMD.put("Light_state", arr[4]);
			} else {

				if (arr[3].equals("1")) {
					arr[3] = "����";
				} else
					arr[3] = "�쳣";

				CMD.put("Waterlevel", arr[2]);
				CMD.put("Sensor_state", arr[3]);
			}

		}

			break;

		default: {
			CMD.put("Cmdtype", cmdtype);
			for (int i = 1; i < arr.length; i++) {
				CMD.put(String.valueOf(i), arr[i]);
			}
		}
			break;
		}
		return CMD;
	}

}
