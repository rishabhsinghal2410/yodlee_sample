package yodlee.ysl.api.beans;

public class ProviderAccountRefreshStatus {
	ProviderAccount  providerAccount;
	LoginForm loginForm;
	public ProviderAccount getProviderAccount() {
		return providerAccount;
	}

	public void setProviderAccount(ProviderAccount providerAccount) {
		this.providerAccount = providerAccount;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	
	/*public String toString()
	{
		StringBuilder refreshStatus = new StringBuilder("");
		refreshStatus.append("ProviderAccountId = " + providerAccount.getId()).append(providerAccount.getRefreshInfo().toString());
		return refreshStatus.toString();
	}*/
}
