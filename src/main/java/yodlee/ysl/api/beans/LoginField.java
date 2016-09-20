/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class LoginField {
	
	

	private String id;
	private String name;
	private String type;
	private String value;
	private String size;
	private boolean isOptional;
	private boolean valueEditable;
	private String maxLength;
	private String required;
	private Option[] option;
	private Validation[] validation;
	private String prefix;
	private String suffix;
	private String image;
	
	
	public boolean isOptional() {
		return isOptional;
	}
	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}
	public boolean isValueEditable() {
		return valueEditable;
	}
	public void setValueEditable(boolean valueEditable) {
		this.valueEditable = valueEditable;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public Option[] getOption() {
		return option;
	}
	public void setOption(Option[] option) {
		this.option = option;
	}
	public Validation[] getValidation() {
		return validation;
	}
	public void setValidation(Validation[] validation) {
		this.validation = validation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	
}
