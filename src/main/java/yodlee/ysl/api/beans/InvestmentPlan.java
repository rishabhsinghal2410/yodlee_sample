/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class InvestmentPlan {
	private String providerId;
	private String providerName;
	private String planName;
	private String planNumber;
	private InvestmentOption[] investmentOption;
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public InvestmentOption[] getInvestmentOption() {
		return investmentOption;
	}
	public void setInvestmentOption(InvestmentOption[] investmentOption) {
		this.investmentOption = investmentOption;
	}
	public String getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	
	
	
	
}
