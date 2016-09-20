/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class HoldingSummary {
	private String classificationType;
	private String classificationValue;
	//private Money value;
	
	//private String currency;
	public String getClassificationType() {
		return classificationType;
	}
	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}
	public String getClassificationValue() {
		return classificationValue;
	}
	public void setClassificationValue(String classificationValue) {
		this.classificationValue = classificationValue;
	}
/*	public Money getValue() {
		return value;
	}
	public void setValue(Money value) {
		this.value = value;
	}
	*/
	
}
