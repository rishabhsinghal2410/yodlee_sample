/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class Transaction {

		String CONTAINER;
		String id;
		Money amount;
		String baseType;
		String category;
		String originalDescription;
		String date;
		String manual;
		String status;
		String cusipNumber;
		Money price;
		String quantity;
		String symbol;
		String tradeDate;
		String accountId;
		public String getCONTAINER() {
			return CONTAINER;
		}
		public void setCONTAINER(String cONTAINER) {
			CONTAINER = cONTAINER;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Money getAmount() {
			return amount;
		}
		public void setAmount(Money amount) {
			this.amount = amount;
		}
		public String getBaseType() {
			return baseType;
		}
		public void setBaseType(String baseType) {
			this.baseType = baseType;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
	
		public String getOriginalDescription() {
			return originalDescription;
		}
		public void setOriginalDescription(String originalDescription) {
			this.originalDescription = originalDescription;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getManual() {
			return manual;
		}
		public void setManual(String manual) {
			this.manual = manual;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCusipNumber() {
			return cusipNumber;
		}
		public void setCusipNumber(String cusipNumber) {
			this.cusipNumber = cusipNumber;
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
		public String getTradeDate() {
			return tradeDate;
		}
		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}
		public String getAccountId() {
			return accountId;
		}
		public void setAccountId(String accountId) {
			this.accountId = accountId;
		}
		
		
}
