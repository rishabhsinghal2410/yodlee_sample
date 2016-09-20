/*
 * Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yodlee, Inc.
 * Use is subject to license terms.
 */
package yodlee.ysl.api.beans;

public class Accounts {

	Account[] account;

	public Account[] getAccounts() {
		return account;
	}

	public void setAccounts(Account[] account) {
		this.account = account;
	}
	
	public String toString()
	{
		StringBuilder accounts = new StringBuilder("");
		if(account.length >0)
		for (int i = 0; i<account.length; i++)
		{ if(account[i].getInvestmentPlan()!= null){
			
			accounts.append("ProviderId- "+account[i].getInvestmentPlan().getProviderId()).append("=> ProviderName- ").append(account[i].getInvestmentPlan().getProviderName()).append("\n");
			accounts.append("PlanNumber- "+account[i].getInvestmentPlan().getPlanNumber()).append("=> PlanName- ").append(account[i].getInvestmentPlan().getPlanName()).append("\n");
			/*InvestmentOption[] investmentOption = account[i].getInvestmentPlan().getInvestmentOption();
			for (int j = 0; j<investmentOption.length; j++){
				accounts.append(investmentOption[j].getIsin()).append("=>").append(investmentOption[j].getHoldingType()).append("\n");	
			}*/
		      
		}else{
			accounts.append("Id- "+account[i].getId()).append("=> AccountName- ").append(account[i].getAccountName()).append("\n");
		}
			
		}
		return accounts.toString();
	}

}
