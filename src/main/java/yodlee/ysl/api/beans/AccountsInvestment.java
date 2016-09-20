package yodlee.ysl.api.beans;


public class AccountsInvestment {
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
		for (int i = 0; i<account.length; i++)
		{
			accounts.append(account[i].getInvestmentPlan().getProviderId()).append("=>").append(account[i].getInvestmentPlan().getProviderName()).append("\n");
		}
		return accounts.toString();
	}
}
