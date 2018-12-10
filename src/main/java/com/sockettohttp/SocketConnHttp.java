/**
 * 
 */
package com.sockettohttp;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.core.mina.CodecFactory;

/**
 * @author xizhonghuai
 * @date 2018Äê7ÔÂ12ÈÕ
 * @readme
 */
public class SocketConnHttp {

	// private Logger logger = Logger.getLogger(TcpClientTool.class);
	private IoConnector ioConnector = null;
	private IoSession session;

	/**
	 * @param ipAddress
	 * @param port
	 * @param eqSession
	 */
	@SuppressWarnings("deprecation")
	public SocketConnHttp(String ipAddress, int port, IoSession eqSession) {

		ioConnector = new NioSocketConnector();
		ioConnector.setConnectTimeout(3000);
		ioConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
				new CodecFactory(Charset.forName("ISO-8859-1"), Charset.forName("ISO-8859-1"))));

		ioConnector.getSessionConfig().setMaxReadBufferSize(2048);

		ioConnector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);

		ioConnector.setHandler(new TcpClientHandler(eqSession));

		ConnectFuture cf = ioConnector.connect(new InetSocketAddress(ipAddress, port));
		cf.awaitUninterruptibly();

		this.session = cf.getSession();

	}

	public void write(Object obj) {

		session.write(obj);

	}

}
