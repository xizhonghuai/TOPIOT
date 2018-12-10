/**
 * 
 */
package com.common;

import java.util.Map;

import org.apache.mina.core.session.IoSession;

/**
 * @author XIZHONGHUAI
 *
 */
public interface TopiotService {

	boolean getStatus();
	
	boolean isDebugflag();

	boolean start();	
	
	boolean isPushflag();
	
	IoSession getSession();
	
	String getPushUrl();
    
	Map<Long, IoSession> getManagedSessions();

	void close();

}
