/**
 * 
 */
package com.transportlayer;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialAddress.DataBits;
import org.apache.mina.transport.serial.SerialAddress.FlowControl;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialAddress.StopBits;
import org.apache.mina.transport.serial.SerialConnector;

import com.application.Initialize;
import com.common.HandlerClassInfo;
import com.common.ServiceProperty;
import com.common.TopiotService;
import com.common.methods;
import com.core.mina.CodecFactory;
import com.debug.DebugFilter;
import com.m2m.M2MFilter;
import com.push.PushFilter;

/**
 * @author XIZHONGHUAI
 *
 */

public class Usart implements TopiotService {

	private Logger logger = Logger.getLogger(Usart.class);

	private IoConnector serialConnector;
	private ConnectFuture future;
	private String comName;
	private Integer baud;
	private String decodecharset;
	private String encodecharset;
	private String handler;
	private String serviceId;
	private boolean status = false;
	private boolean debugflag;
	private boolean pushflag;
	private String pushUrl;
	private String toServiceId;

	/**
	 * @return the serialConnector
	 */
	public IoConnector getSerialConnector() {
		return serialConnector;
	}

	/**
	 * @param serialConnector
	 *            the serialConnector to set
	 */
	public void setSerialConnector(IoConnector serialConnector) {
		this.serialConnector = serialConnector;
	}

	/**
	 * @return the comName
	 */
	public String getComName() {
		return comName;
	}

	/**
	 * @param comName
	 *            the comName to set
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}

	/**
	 * @return the baud
	 */
	public Integer getBaud() {
		return baud;
	}

	/**
	 * @param baud
	 *            the baud to set
	 */
	public void setBaud(Integer baud) {
		this.baud = baud;
	}

	/**
	 * @return the decodecharset
	 */
	public String getDecodecharset() {
		return decodecharset;
	}

	/**
	 * @param decodecharset
	 *            the decodecharset to set
	 */
	public void setDecodecharset(String decodecharset) {
		this.decodecharset = decodecharset;
	}

	/**
	 * @return the encodecharset
	 */
	public String getEncodecharset() {
		return encodecharset;
	}

	/**
	 * @param encodecharset
	 *            the encodecharset to set
	 */
	public void setEncodecharset(String encodecharset) {
		this.encodecharset = encodecharset;
	}

	/**
	 * @return the handler
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * @param handler
	 *            the handler to set
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the debugflag
	 */
	public boolean isDebugflag() {
		return debugflag;
	}

	/**
	 * @param debugflag
	 *            the debugflag to set
	 */
	public void setDebugflag(boolean debugflag) {
		this.debugflag = debugflag;
	}

	/**
	 * @return the pushflag
	 */
	public boolean isPushflag() {
		return pushflag;
	}

	/**
	 * @param pushflag
	 *            the pushflag to set
	 */
	public void setPushflag(boolean pushflag) {
		this.pushflag = pushflag;
	}

	/**
	 * @return the pushUrl
	 */
	public String getPushUrl() {
		return pushUrl;
	}

	/**
	 * @param pushUrl
	 *            the pushUrl to set
	 */
	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}
	
	

	/**
	 * @return the toServiceId
	 */
	public String getToServiceId() {
		return toServiceId;
	}

	/**
	 * @param toServiceId the toServiceId to set
	 */
	public void setToServiceId(String toServiceId) {
		this.toServiceId = toServiceId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#getStatus()
	 */
	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#start()
	 */

	@PostConstruct
	@Override
	public boolean start() {
		// TODO Auto-generated method stub

		try {
			serialConnector = new SerialConnector();

			serialConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
					new CodecFactory(Charset.forName(decodecharset), Charset.forName(encodecharset))));

			if (debugflag) {
				serialConnector.getFilterChain().addLast("debug", new DebugFilter(serviceId));
			}
			
			
			if (pushflag){
				serialConnector.getFilterChain().addLast("push", new PushFilter(serviceId));
			}

			if (toServiceId != null) {
				serialConnector.getFilterChain().addLast("m2m", new M2MFilter(toServiceId));
			}

			// 设置读取缓冲区最大值
			serialConnector.getSessionConfig().setMaxReadBufferSize(2048);

			try {
				serialConnector.setHandler((IoHandler) methods.getObjNewInstance(handler));

			} catch (Exception e) {
				// TODO: handle exception
				logger.info("协议绑定失败：" + e.getMessage());
			}

			SerialAddress serialAddress = new SerialAddress(comName, baud, DataBits.DATABITS_8, StopBits.BITS_1,
					Parity.NONE, FlowControl.NONE);

			future = serialConnector.connect(serialAddress);

			future.awaitUninterruptibly();

			logger.info("串口服务打开成功...  端口号：" + comName);

			ServiceProperty sp = new ServiceProperty();
			HandlerClassInfo handlerClassInfo = new HandlerClassInfo();
			handlerClassInfo = methods.getHandlerClassInfo(handler);
			sp.setHandler(handler);
			sp.setHandlerMark(handlerClassInfo.getMark());
			sp.setHandlerVsersion(handlerClassInfo.getVersion());
			sp.setTransport("USART");
			sp.setPort(comName);
			sp.setDebugflag(debugflag);
			sp.setServiceId(serviceId);
			sp.setPushflag(pushflag);
			sp.setPushUrl(pushUrl);
			sp.setToServiceId(toServiceId);
			Initialize.serviceList.put(serviceId, sp);

			status = true;
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("串口服务启动失败:" + e.getMessage());
			status = false;
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#close()
	 */
	@SuppressWarnings("deprecation")
	@PreDestroy
	@Override
	public void close() {
		// TODO Auto-generated method stub

		future.getSession().close();
		serialConnector.dispose();

		future = null;
		serialConnector = null;
		status = false;
		logger.info("串口：" + comName + "关闭");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#getManagedSessions()
	 */
	@Override
	public Map<Long, IoSession> getManagedSessions() {
		// TODO Auto-generated method stub

		Map<Long, IoSession> sessionList = new ConcurrentHashMap<Long, IoSession>();

		sessionList.put(getSession().getId(), getSession());
		return serialConnector.getManagedSessions();
		// return sessionList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.common.TopiotService#getSession()
	 */
	@Override
	public IoSession getSession() {
		// TODO Auto-generated method stub

		return future.getSession();

	}

}
