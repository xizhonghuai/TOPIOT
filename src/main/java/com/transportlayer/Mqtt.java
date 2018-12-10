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
			// hostΪ��������clientid������MQTT�Ŀͻ���ID��һ����Ψһ��ʶ����ʾ��MemoryPersistence����clientid�ı�����ʽ��Ĭ��Ϊ���ڴ汣��
			client = new MqttClient(host, clientid, new MemoryPersistence());
			// MQTT����������
			options = new MqttConnectOptions();
			// �����Ƿ����session,�����������Ϊfalse��ʾ�������ᱣ���ͻ��˵����Ӽ�¼����������Ϊtrue��ʾÿ�����ӵ������������µ��������
			options.setCleanSession(true);
			// �������ӵ��û���
			options.setUserName(userName);
			// �������ӵ�����
			options.setPassword(passWord.toCharArray());
			// ���ó�ʱʱ�� ��λΪ��
			options.setConnectionTimeout(10);
			// ���ûỰ����ʱ�� ��λΪ�� ��������ÿ��1.5*20���ʱ����ͻ��˷��͸���Ϣ�жϿͻ����Ƿ����ߣ������������û�������Ļ���
			options.setKeepAliveInterval(20);
			// ���ûص�
			client.setCallback((MqttCallback) methods.getObjNewInstance(handler));
			@SuppressWarnings("unused")
			MqttTopic mqtttopic = client.getTopic(topic);
			// setWill�����������Ŀ����Ҫ֪���ͻ����Ƿ���߿��Ե��ø÷������������ն˿ڵ�֪ͨ��Ϣ
       // 		options.setWill(topic, "close".getBytes(), 2, true);
			
			options.setMqttVersion(4);
			

			client.connect(options);
			// ������Ϣ
			int[] Qos = { 1 };
			String[] topic1 = { topic };
 		client.subscribe(topic1, Qos);
			status = true;
			
		 
			
			System.out.print("mqtt���ӳɹ���");
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
