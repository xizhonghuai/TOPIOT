/**
 * 
 */
package com.debug;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.common.TopiotService;
import com.core.mina.CodecFactory;

/**
 * @author XIZHONGHUAI
 *
 */
public class DebugService implements TopiotService {

	private Logger logger = Logger.getLogger(DebugService.class);

	private IoAcceptor ioAcceptor = null;
	private List<Integer> port; // 监听端口
 

	/**
	 * @return the ioAcceptor
	 */
	public IoAcceptor getIoAcceptor() {
		return ioAcceptor;
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

 
 
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#getStatus()
	 */
	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#isTranspondflag()
	 */
	@Override
	public boolean isDebugflag() {
		// TODO Auto-generated method stub
		return false;
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

		if (ioAcceptor != null) {
			return true;
		}

		try {

			ioAcceptor = new NioSocketAcceptor();

			// 设置过滤器
			ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
					new CodecFactory(Charset.forName("ISO-8859-1"), Charset.forName("ISO-8859-1"))));
			// 设置读取缓冲区最大值
			ioAcceptor.getSessionConfig().setMaxReadBufferSize(2048);
			ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3600);
			// 绑定逻辑处理器
			ioAcceptor.setHandler(new TcpHandler());

			// 绑定端口
			for (int i = 0; i < port.size(); i++) {

				ioAcceptor.bind(new InetSocketAddress(port.get(i).intValue()));
			}

			 logger.info("转发服务启动成功"); 
			return true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("转发服务启动失败:" + e.getMessage());
			return false;
		}

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

	}

	/* (non-Javadoc)
	 * @see com.common.TopiotService#isPushflag()
	 */
	@Override
	public boolean isPushflag() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.common.TopiotService#getPushUrl()
	 */
	@Override
	public String getPushUrl() {
		// TODO Auto-generated method stub
		return null;
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
