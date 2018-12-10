/**
 * 
 */
package com.common;

/**
 * @author XIZHONGHUAI
 *
 */
public class ServiceProperty {

	private String port;
	private String handler;
	private String handlerMark;
	private String handlerVsersion;
	private String transport;
	private String decodecharset;
	private String encodecharset;
	private Integer idle;
	private Integer baud;
	private boolean debugflag;
	private boolean status;
	private String serviceId;
	private boolean pushflag;
	private String pushUrl;
	private String toServiceId;
	/**
	 * 
	 */
	public ServiceProperty() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param port
	 * @param handler
	 * @param handlerMark
	 * @param handlerVsersion
	 * @param transport
	 * @param decodecharset
	 * @param encodecharset
	 * @param idle
	 * @param baud
	 * @param debugflag
	 * @param status
	 * @param serviceId
	 * @param pushflag
	 * @param pushUrl
	 * @param toServiceId
	 */
	public ServiceProperty(String port, String handler, String handlerMark, String handlerVsersion, String transport,
			String decodecharset, String encodecharset, Integer idle, Integer baud, boolean debugflag, boolean status,
			String serviceId, boolean pushflag, String pushUrl, String toServiceId) {
		super();
		this.port = port;
		this.handler = handler;
		this.handlerMark = handlerMark;
		this.handlerVsersion = handlerVsersion;
		this.transport = transport;
		this.decodecharset = decodecharset;
		this.encodecharset = encodecharset;
		this.idle = idle;
		this.baud = baud;
		this.debugflag = debugflag;
		this.status = status;
		this.serviceId = serviceId;
		this.pushflag = pushflag;
		this.pushUrl = pushUrl;
		this.toServiceId = toServiceId;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the handler
	 */
	public String getHandler() {
		return handler;
	}
	/**
	 * @param handler the handler to set
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}
	/**
	 * @return the handlerMark
	 */
	public String getHandlerMark() {
		return handlerMark;
	}
	/**
	 * @param handlerMark the handlerMark to set
	 */
	public void setHandlerMark(String handlerMark) {
		this.handlerMark = handlerMark;
	}
	/**
	 * @return the handlerVsersion
	 */
	public String getHandlerVsersion() {
		return handlerVsersion;
	}
	/**
	 * @param handlerVsersion the handlerVsersion to set
	 */
	public void setHandlerVsersion(String handlerVsersion) {
		this.handlerVsersion = handlerVsersion;
	}
	/**
	 * @return the transport
	 */
	public String getTransport() {
		return transport;
	}
	/**
	 * @param transport the transport to set
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}
	/**
	 * @return the decodecharset
	 */
	public String getDecodecharset() {
		return decodecharset;
	}
	/**
	 * @param decodecharset the decodecharset to set
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
	 * @param encodecharset the encodecharset to set
	 */
	public void setEncodecharset(String encodecharset) {
		this.encodecharset = encodecharset;
	}
	/**
	 * @return the idle
	 */
	public Integer getIdle() {
		return idle;
	}
	/**
	 * @param idle the idle to set
	 */
	public void setIdle(Integer idle) {
		this.idle = idle;
	}
	/**
	 * @return the baud
	 */
	public Integer getBaud() {
		return baud;
	}
	/**
	 * @param baud the baud to set
	 */
	public void setBaud(Integer baud) {
		this.baud = baud;
	}
	/**
	 * @return the debugflag
	 */
	public boolean isDebugflag() {
		return debugflag;
	}
	/**
	 * @param debugflag the debugflag to set
	 */
	public void setDebugflag(boolean debugflag) {
		this.debugflag = debugflag;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the pushflag
	 */
	public boolean isPushflag() {
		return pushflag;
	}
	/**
	 * @param pushflag the pushflag to set
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
	 * @param pushUrl the pushUrl to set
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServiceProperty [port=" + port + ", handler=" + handler + ", handlerMark=" + handlerMark
				+ ", handlerVsersion=" + handlerVsersion + ", transport=" + transport + ", decodecharset="
				+ decodecharset + ", encodecharset=" + encodecharset + ", idle=" + idle + ", baud=" + baud
				+ ", debugflag=" + debugflag + ", status=" + status + ", serviceId=" + serviceId + ", pushflag="
				+ pushflag + ", pushUrl=" + pushUrl + ", toServiceId=" + toServiceId + "]";
	}
	
	
	
	
	 
 
	
}
