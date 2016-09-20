/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class Holding {
	
	 private Money costBasis;
	 private String cusipNumber;
	 private String dailyChange;
	 private String description;
	 private String exchange;
	 private String holdingType;
	 private String isin;
	 private Money price;
	 private String quantity;
	 private String symbol;
	 private Money value;
	public Money getCostBasis() {
		return costBasis;
	}
	public void setCostBasis(Money costBasis) {
		this.costBasis = costBasis;
	}
	public String getCusipNumber() {
		return cusipNumber;
	}
	public void setCusipNumber(String cusipNumber) {
		this.cusipNumber = cusipNumber;
	}
	public String getDailyChange() {
		return dailyChange;
	}
	public void setDailyChange(String dailyChange) {
		this.dailyChange = dailyChange;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getHoldingType() {
		return holdingType;
	}
	public void setHoldingType(String holdingType) {
		this.holdingType = holdingType;
	}
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public Money getPrice() {
		return price;
	}
	public void setPrice(Money price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Money getValue() {
		return value;
	}
	public void setValue(Money value) {
		this.value = value;
	}

}
