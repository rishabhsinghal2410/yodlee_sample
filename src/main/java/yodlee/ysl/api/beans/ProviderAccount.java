package yodlee.ysl.api.beans;

public class ProviderAccount {
	private String id;
	private RefreshInfo refreshInfo;
	LoginForm loginForm;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RefreshInfo getRefreshInfo() {
		return refreshInfo;
	}
	public void setRefreshInfo(RefreshInfo refreshInfo) {
		this.refreshInfo = refreshInfo;
	}
	
	
	public LoginForm getLoginForm() {
		return loginForm;
	}
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	public String toString()
	{
		StringBuilder refreshStatus = new StringBuilder("");
		refreshStatus.append("providerAccountId = " + id).append(refreshInfo.toString());
		return refreshStatus.toString();
	}
}
