/**
 * 
 */
package com.protocol.wxy;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.TopiotService;
import com.protocol.wxy.sys.Conf;
import com.protocol.wxy.sys.SysMethod;
import com.protocol.wxy.wxyLmcs.WXY;

/**
 * @author Administrator
 *
 */
public class WxyProcess {

	private static Logger logger = Logger.getLogger(WxyProcess.class);

	public static void Controlmessage(IoSession session, String msg) {
		
		@SuppressWarnings("unused")
		String regId = (String) session.getAttribute("regId", null);

		int dim1_Count = SysMethod.getCharCount(')', msg);
		int dim2_Count = SysMethod.getCharCount('(', msg);
		if ((msg.length() > 2) && (msg.charAt(0) == '(') && (msg.charAt(msg.length() - 1) == ')')
				&& (dim1_Count == dim2_Count)) {

			String cHead = null;
			String DeviceID = null;
			String Detail = null;

			DeviceID = (String) session.getAttribute("regId", null); // 获取注册ID

			String s = msg.replace("(", "~");
			s = s.replace(")", "~");
			String msgs[] = s.split("~");

			for (int i = 0; i < msgs.length; i++) {
				if ((msgs[i].length()) > 0) {

					// ************************************************************************************************************************
					cHead = msgs[i].substring(0, 1); // 取得信息类别字符
					Detail = msgs[i].substring(1, msgs[i].length()); // 获取主信息

					if (cHead.equals("s")) {
						if (DeviceID == null) { // 判断DeviceID是否注册

							session.write(WXY.Addbracket(cHead));

							Conf conf = null;
							try {
								conf = new Conf("wxy_config.properties");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								logger.info("读取配置文件异常:" + e.toString());
							}

							session.setAttribute("regId", Detail);

							String QpDevicesId = conf.GetKeyValue("QpDevicesId", "");

							if (QpDevicesId.indexOf(Detail) > 0) {

								session.setAttribute("qp", "yes");
							}

							logger.info(SysMethod.StrPlus("设备:" + Detail + session.getRemoteAddress(), "---------新注册"));
						}
					} else {
						if (DeviceID != null) {
							String Upackdat = "";

							if (cHead.equals("p")) {
								session.write(WXY.Addbracket(SysMethod.StrPlus(cHead, WXY.FormatSystemDate())));// 回复心跳信息

								if (session.getAttribute("qp", null) != null) {

									// 强排
									WXY.qpProcess(session, Detail);

								}

								logger.info(SysMethod.StrPlus("设备" + DeviceID + "心跳----------" + Detail));

							}

							Upackdat = WXY.UserPackEncode(DeviceID, cHead, Detail);
							SendmsgtoUser(DeviceID, Upackdat);

						}
					}
				}
				// **************************************************************************************************************************
			}
		}

	}

	public static void SendmsgtoUser(String DeviceID, String msg) {

		TopiotService ts = (TopiotService) SpringBeanUtils.getBean("wxydebug");
		Map<Long, IoSession> maplist = ts.getManagedSessions();
		int IDmin = 0;
		int IDmax = 0;
		int ID = Integer.parseInt(DeviceID);
		for (Entry<Long, IoSession> entry : maplist.entrySet()) {

			IoSession debugSession = entry.getValue();
			IDmin = (int) debugSession.getAttribute("regIdMin", 0);
			IDmax = (int) debugSession.getAttribute("regIdMax", 0);

			if ((ID >= IDmin) && (ID <= IDmax)) {
				debugSession.write(msg);
			}

		}
	}
}
