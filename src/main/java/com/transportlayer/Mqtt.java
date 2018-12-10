/**
 * 
 */
package com.transportlayer;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;

import org.apache.mina.core.session.IoSession;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.common.TopiotService;
import com.common.methods;

/**
 * @author XIZHONGHUAI
 *
 */
public class Mqtt implements TopiotService {

	private MqttClient client;
	private String clientid = "1234587ks";
	private MqttConnectOptions options;
	@SuppressWarnings("unused")
	private ScheduledExecutorService scheduler;

	public String host = "tcp://127.0.0.1:235";
	public String topic = "4";

	private String userName = "admin";
	private String passWord = "password";
	private String handler = "com.protocol.mq.test";
	@SuppressWarnings("unused")
	private String serviceId = "mqtts";
	private boolean status = false;

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

		try {
			// host为主机名，clientid即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
			client = new MqttClient(host, clientid, new MemoryPersistence());
			// MQTT的连接设置
			options = new MqttConnectOptions();
			// 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
			options.setCleanSession(true);
			// 设置连接的用户名
			options.setUserName(userName);
			// 设置连接的密码
			options.setPassword(passWord.toCharArray());
			// 设置超时时间 单位为秒
			options.setConnectionTimeout(10);
			// 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
			options.setKeepAliveInterval(20);
			// 设置回调
			client.setCallback((MqttCallback) methods.getObjNewInstance(handler));
			@SuppressWarnings("unused")
			MqttTopic mqtttopic = client.getTopic(topic);
			// setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
       // 		options.setWill(topic, "close".getBytes(), 2, true);
			
			options.setMqttVersion(4);
			

			client.connect(options);
			// 订阅消息
			int[] Qos = { 1 };
			String[] topic1 = { topic };
 		client.subscribe(topic1, Qos);
			status = true;
			
		 
			
			System.out.print("mqtt连接成功！");
			return true;

		} catch (Exception e) {
			e.printStackTrace();

			status = false;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#getManagedSessions()
	 */
	@Override
	public Map<Long, IoSession> getManagedSessions() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.application.TopiotService#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
		
		

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
