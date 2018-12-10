/**
 * 
 */
package com.transportlayer;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
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
public class Udp implements TopiotService {

	private Logger logger = Logger.getLogger(Udp.class);

	private IoAcceptor ioAcceptor;
	private List<Integer> port; // 监听端口
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
	 * @return the ioAcceptor
	 */
	public IoAcceptor getIoAcceptor() {
		return ioAcceptor;
	}

	/**
	 * @param ioAcceptor
	 *            the ioAcceptor to set
	 */
	public void setIoAcceptor(IoAcceptor ioAcceptor) {
		this.ioAcceptor = ioAcceptor;
	}

	/**
	 * @return the port
	 */
	public List<Integer> getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(List<Integer> port) {
		this.port = port;
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

			ioAcceptor = new NioDatagramAcceptor();

			// 设置过滤器
			ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
					new CodecFactory(Charset.forName(decodecharset), Charset.forName(encodecharset))));

			if (debugflag) {
				ioAcceptor.getFilterChain().addLast("debug", new DebugFilter(serviceId));
			}
			
			
			if (pushflag){
				ioAcceptor.getFilterChain().addLast("push", new PushFilter(serviceId));
			}
			
			if (toServiceId != null) {
				ioAcceptor.getFilterChain().addLast("m2m", new M2MFilter(toServiceId));
			}
			
			// 设置读取缓冲区最大值
			ioAcceptor.getSessionConfig().setMaxReadBufferSize(2048);
			// 读写通道X秒内无操作进入空闲状态
			/*
			 * ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,
			 * idle);
			 */

			try {
				// 绑定逻辑处理器
				ioAcceptor.setHandler((IoHandler) methods.getObjNewInstance(handler));

			} catch (Exception e) {
				// TODO: handle exception

				logger.info("协议绑定失败：" + e.getMessage());
			}

			// 绑定端口
			for (int i = 0; i < port.size(); i++) {
				ioAcceptor.bind(new InetSocketAddress(port.get(i).intValue()));
			}

			ServiceProperty sp = new ServiceProperty();
			HandlerClassInfo handlerClassInfo = new HandlerClassInfo();
			handlerClassInfo =  methods.getHandlerClassInfo(handler);
			sp.setHandler(handler);
			sp.setHandlerMark(handlerClassInfo.getMark());
			sp.setHandlerVsersion(handlerClassInfo.getVersion());
			sp.setTransport("UDP");
			sp.setPort(port.toString());
			sp.setDebugflag(debugflag);
			sp.setServiceId(serviceId);
			sp.setPushflag(pushflag);
			sp.setPushUrl(pushUrl);
			sp.setToServiceId(toServiceId);
			Initialize.serviceList.put(serviceId, sp);

			logger.info("udp服务启动成功...  端口号：" + port.toString());
			status = true;
			return true;

		} catch (Exception e) {
			// TODO: handle exception

			logger.info("udp服务启动失败:" + e.getMessage());
			status = false;
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#close()
	 */
	@PreDestroy
	@Override
	public void close() {
		// TODO Auto-generated method stub
		ioAcceptor.dispose();
		ioAcceptor = null;
		status = false;
		logger.info("udp：" + port.toString() + "关闭");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#getManagedSessions()
	 */
	@Override
	public Map<Long, IoSession> getManagedSessions() {
		// TODO Auto-generated method stub
		return ioAcceptor.getManagedSessions();
	}

	/* (non-Javadoc)
	 * @see com.common.TopiotService#getSession()
	 */
	@Override
	public IoSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

}
