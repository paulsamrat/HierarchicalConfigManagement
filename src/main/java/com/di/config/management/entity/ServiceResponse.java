package com.di.config.management.entity;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ServiceResponse {
	
	private boolean isSuccessful;
	
	private String errorMsg;
	
	private List<ObjectError> errors;
	
	private String responseMsg;
	
	private List<CouchbaseConfigEntry> resultList;

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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceResponse [isSuccessful=").append(isSuccessful).append(", ");
		if (errorMsg != null)
			builder.append("errorMsg=").append(errorMsg).append(", ");
		if (errors != null)
			builder.append("errors=").append(errors).append(", ");
		if (responseMsg != null)
			builder.append("responseMsg=").append(responseMsg);
		if (resultList != null)
			builder.append("resultList=").append(resultList);
		builder.append("]");
		return builder.toString();
	}

	
	
}
