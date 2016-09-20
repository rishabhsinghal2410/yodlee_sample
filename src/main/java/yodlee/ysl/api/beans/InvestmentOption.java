/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class InvestmentOption {
	private String holdingType;
	private String cusipNumber;
	private String description;
	private String  isin;
	private String  id;
	private Money price;
	private String sedol;
	private String symbol;
	//private String historicReturns;
	public String getHoldingType() {
		return holdingType;
	}
	public void setHoldingType(String holdingType) {
		this.holdingType = holdingType;
	}
	public String getCusipNumber() {
		return cusipNumber;
	}
	public void setCusipNumber(String cusipNumber) {
		this.cusipNumber = cusipNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSedol() {
		return sedol;
	}
	public void setSedol(String sedol) {
		this.sedol = sedol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	
	
}
