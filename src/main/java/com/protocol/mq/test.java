/**
 * 
 */
package com.protocol.mq;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author XIZHONGHUAI
 *
 */
public class test implements MqttCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#connectionLost(java.lang.
	 * Throwable)
	 */
	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		
		
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#messageArrived(java.lang.
	 * String, org.eclipse.paho.client.mqttv3.MqttMessage)
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("������Ϣ���� : " + topic);
		System.out.println("������ϢQos : " + message.getQos());
		System.out.println("������Ϣ���� : " + new String(message.getPayload()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.paho.client.mqttv3.MqttCallback#deliveryComplete(org.eclipse.
	 * paho.client.mqttv3.IMqttDeliveryToken)
	 */
	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}
