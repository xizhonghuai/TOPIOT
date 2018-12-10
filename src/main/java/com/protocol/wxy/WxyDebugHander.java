/**
 * 
 */
package com.protocol.wxy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.ClassMsgAnnotation;
import com.common.TopiotService;
import com.core.mina.MinaSys;
import com.protocol.wxy.sys.Conf;
import com.protocol.wxy.sys.SysMethod;
import com.protocol.wxy.wxyLmcs.WXY;
import com.protocol.wxy.wxyLmcs.WXY.CmdType;

/**
 * @author Administrator
 *
 */
@ClassMsgAnnotation(mark = "危险源监控预警系统调试服务")
public class WxyDebugHander extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(WxyDebugHander.class);

	@SuppressWarnings("deprecation")
	public void messageReceived(IoSession session, Object message) throws Exception {

		String msg = message.toString();

		CmdType cmdt = WXY.UserGetChead(msg); // 取得信息类别

		if (cmdt == CmdType.cmdLogin) {

			try {

				int userL = msg.charAt(0); // 用户名长度
				int pssdL = msg.charAt(33);// 序列号长度
				String user = msg.substring(1, userL + 1); // 取得用户名
				String pssd = msg.substring(34, pssdL + 34); // 取得序列号
				Conf con = new Conf("wxy_userlist.properties");
				String str = con.GetKeyValue(user, "");

				if (str.length() > 8) {

					String p = str.substring(0, str.length() - 8);

					if (pssd.equals(p)) {

						String ACK = WXY.UserLoginACK();

						session.write(ACK); // 登录成功响应

						String par = str.substring(p.length()); // 取出用户授权设备范围

						session.setAttribute("regIdMin", Integer.parseInt(par.substring(0, 4)));
						session.setAttribute("regIdMax", Integer.parseInt(par.substring(4)));

					} else
						session.close();

				}

			} catch (Exception e) {
				logger.error("异常...." + e.toString());
			}

		} else {

			try {
				String CMD = WXY.UserDataPackDecode(msg);
				if (CMD.length() > 0) {
					int IDLen = CMD.charAt(0);
					String DeviceID = CMD.substring(1, IDLen + 1);
					String Para = CMD.substring(IDLen + 1);

					if (DeviceID.equals("####")) {

						TopiotService ts = (TopiotService) SpringBeanUtils.getBean("wxy");

						int OnlineCount = ts.getManagedSessions().size();

						String Upackdat = WXY.UserPackEncode("####", "p",
								SysMethod.Encode_GBK("设备在线数量:") + String.valueOf(OnlineCount));
						session.write(Upackdat);

					} else {

						TopiotService ts = (TopiotService) SpringBeanUtils.getBean("wxy");

						char ord = Para.charAt(0);

						if (ord == 'r') {
							Para = "p" + Para.substring(1);
						} else if (ord == 'p') {
							Para = "q" + Para.substring(1);
						} else if (ord == 'q') {
							Para = "r" + Para.substring(1);
						}

						MinaSys.SendMsg(ts.getManagedSessions(), WXY.Addbracket(Para), DeviceID);

						logger.info(SysMethod.StrPlus("客户端数据：", DeviceID, "----", WXY.Addbracket(Para)));

					}

				}

			} catch (Exception e) {
				logger.error("异常...." + e.toString());

			}
		}

	}

	public void sessionClosed(IoSession session) throws Exception {
		// logger.info("连接断开");

	}

	public void sessionOpened(IoSession session) throws Exception {
		// logger.info("服务端与客户端连接打开...");
		// Sys.AddSession(LmcsMain.UserList, session, null);
	}

	public void sessionCreated(IoSession session) throws Exception {
		// logger.info("服务端与客户端创建连接...");

	}

	public void messageSent(IoSession session, Object message) throws Exception {
		// logger.info("服务端发送信息成功...");
	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// logger.info("服务端进入空闲状态...");
		session.close();
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// logger.error("服务端发送异常...", cause);
	}

}