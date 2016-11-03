package com.di.config.management.entity;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ServiceResponse {
	
	private boolean isSuccessful;
	
	private String errorMsg;
	
	private List<ObjectError> errors;
	
	private String responseMsg;
	
	private List<CouchbaseConfigEntry> resultList;
	
	private String ip;
	
	private int port;
	
	/**
	 * default
	 */
	public ServiceResponse() {
		super();
	}

	public ServiceResponse(boolean isSuccessful, List<ObjectError> errors, String errorMsg) {
		super();
		this.isSuccessful = isSuccessful;
		this.errors = errors;
		this.errorMsg = errorMsg;
	}

	public ServiceResponse(boolean isSuccessful, String responseMsg, List<CouchbaseConfigEntry> resultList) {
		super();
		this.isSuccessful = isSuccessful;
		this.responseMsg = responseMsg;
		this.resultList = resultList;
	}
	
	
	public ServiceResponse(boolean isSuccessful, String responseMsg) {
		super();
		this.isSuccessful = isSuccessful;
		this.responseMsg = responseMsg;
	}
	
	public ServiceResponse(boolean isSuccessful,String ip, int port) {
		super();
		this.isSuccessful = isSuccessful;
		this.ip = ip;
		this.port = port;
	}
	
	/**
	 * @return the isSuccessful
	 */
	public boolean isSuccessful() {
		return isSuccessful;
	}

	/**
	 * @param isSuccessful the isSuccessful to set
	 */
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errors
	 */
	public List<ObjectError> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}

	/**
	 * @return the responseMsg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}

	/**
	 * @param responseMsg the responseMsg to set
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	/**
	 * @return the resultList
	 */
	public List<CouchbaseConfigEntry> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList the resultList to set
	 */
	public void setResultList(List<CouchbaseConfigEntry> resultList) {
		this.resultList = resultList;
	}
	
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceResponse [isSuccessful=").append(isSuccessful).append(", ");
		if (errorMsg != null)
			builder.append("errorMsg=").append(errorMsg).append(", ");
		if (errors != null)
			builder.append("errors=").append(errors).append(", ");
		if (responseMsg != null)
			builder.append("responseMsg=").append(responseMsg).append(", ");
		if (resultList != null)
			builder.append("resultList=").append(resultList).append(", ");
		if (ip != null)
			builder.append("ip=").append(ip).append(", ");
		builder.append("port=").append(port).append("]");
		return builder.toString();
	
	}	
	
}
