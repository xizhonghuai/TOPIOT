/**
 * 
 */
package com.application;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.common.ServiceProperty;

/**
 * @author XIZHONGHUAI
 *2018
 */
public class Initialize {

	public static Map<String, ServiceProperty> serviceList = new ConcurrentHashMap<String, ServiceProperty>();

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public Initialize() {
		super();
		// TODO Auto-generated constructor stub

	}

	/*
	 * @PostConstruct public void init() {
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @PreDestroy public void dostory() {
	 * 
	 * }
	 */

}
