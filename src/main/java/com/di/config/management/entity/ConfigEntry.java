
package com.di.config.management.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigEntry {
	
	public enum Space {
		FI("FI"),GLOBAL("GLOBAL"),USER("USER");
		
		private String code;
		private Space(String code){
			this.code = code;
		}
		
		public String getCode(){
			return this.code;
		}
	}
	
	@NotNull
	@NotEmpty(message=" key name must be provided")
	private String keyName;
	
	@NotNull
	@NotEmpty(message=" a value should be associated with a corresponding key ")
	private String value;	
    
	/**
	 * default
	 */
	public ConfigEntry() {
		super();
	}

	/**
	 * @param keyName
	 * @param value
	 */
	public ConfigEntry(String keyName, String value) {
		super();
		this.keyName = keyName;
		this.value = value;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigEntry [");
		if (keyName != null)
			builder.append("keyName=").append(keyName).append(", ");
		if (value != null)
			builder.append("value=").append(value).append(", ");
		builder.append("]");
		return builder.toString();
	}
	
}
