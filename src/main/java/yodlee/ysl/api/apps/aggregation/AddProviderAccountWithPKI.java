/*
 * Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Yodlee, Inc.
 * Use is subject to license terms.
 */
package yodlee.ysl.api.apps.aggregation;

import java.io.IOException;
import java.net.URISyntaxException;

import yodlee.ysl.api.apps.yaas.LoginApp;
import yodlee.ysl.api.beans.LoginForm;
import yodlee.ysl.api.beans.Provider;
import yodlee.ysl.api.beans.Providers;
import yodlee.ysl.api.beans.RefreshStatus;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;
import yodlee.ysl.api.util.EncryptionUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The AddProviderAccountWithPKI class provides aggregation methods. Aggregation means,
 * adding a bank, insurance/investment etcc.. account to Yodlee.
 * 
 * This class will perform account addition using PKI encryption by accepting plain text credentials
 * 
 * Steps to Use this App: 
 * i) ProviderApp.searchSite(searchString); First the Website you want to add the Account from. E.g. "Citibank)  
 * ii) ProviderApp.getSiteLoginForm(site); Get the Login Form for that Website. get the LoginForm for that Website.
 * 
 * Then user the methods of this calls:
 * 
 * i) addSiteAccount(Provider loginFrom)
 * 
 * The SiteId is fetched from the previous response. 
 * ii) getRefreshStatus(Long siteAccountId)
 * 
 * 
 */
public class AddProviderAccountWithPKI {
	private static final String fqcn = AddProviderAccountWithPKI.class.getName();

	/**
	 * loginform is a ValueOject obtained from Provider API.
	 * 
	 * @param loginForm
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */

	public static RefreshStatus addProviderAccount(Providers providers)
			throws IOException, URISyntaxException {
		String mn = "addProviderAccount( " + providers.getProvider()[0].toString();
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.create();
		String providerJson = "{\"provider\":["+ gson.toJson(providers.getProvider()[0])+"]}";
		System.out.println(providerJson);
		String addSiteURL = LoginApp.localURLVer1 + "v1/providers/" + providers.getProvider()[0].getId();
		String jsonResponse = HTTP.doPut(addSiteURL, providerJson,
				LoginApp.loginTokens);
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
	}
	
	public static void doChallenge(LoginForm loginForm, String providerAccountId)
			throws IOException, URISyntaxException {
		String mn = "doChallenge( " + loginForm.toString() + " providerAccountId = " + providerAccountId;
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.create();
		String providerJson = "{\"loginForm\":"+ gson.toJson(loginForm)+"}";
		System.out.println(providerJson);
		String addSiteURL = LoginApp.localURLVer1 + "v1/providers/"+providerAccountId ;
		HTTP.doPostUser(addSiteURL, LoginApp.loginTokens,"MFAChallenge="+ providerJson,true);
	}

	/**
	 * The following api provides refresh status of an account which was
	 * aggregated from the previous api call -
	 * AddProviderAccount.addSiteAccount(Provider loginForm)
	 * 
	 * 
	 * @param providerAccountId
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */

	public static RefreshStatus getRefreshStatus(String providerAccountId)
			throws IOException, URISyntaxException {
		String mn = "getRefreshStatus( " + providerAccountId.toString() + " )";
		System.out.println(fqcn + " :: " + mn);
		String getRefreshStatusURL = LoginApp.localURLVer1 + "v1/refresh/"
				+ providerAccountId.toString();
		String jsonResponse =  HTTP.doGet(getRefreshStatusURL, LoginApp.loginTokens);
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
	}

	public static void main(String[] args) throws IOException,
			URISyntaxException, InterruptedException {
		Providers providers = null;
		RefreshStatus refreshStatus = null;
		System.out.println("Add Provider Account App - TEST - START");
		// / Here invoke this method with hardcoded userName and Passwords
		LoginApp.doLogin("yslsandbox3", "Yodlee@123", "stageJulyUser1", "TEST@123");
		// Alternatively you can also invoke the main() method from the LoginApp
		// class
		//LoginApp.main(null);
		Console con = new Console();
		if (con != null) {
			String searchString = con.readLine("Enter the search string : ");
			ProviderApp.searchProvider(searchString);
		}
		if (con != null) {
			String providerId = con.readLine("Enter the Provider Id : ");
			providers = ProviderApp.getProviderLoginForm(providerId);
		}
		// Call this method to add a Non-MFA Account.
//		refreshStatus = addNonMFA_Account(providers);
		// Call this method to add a MFA Account.
		addMFA_Account(providers);
		
	}

	public static void getRefreshStatus(RefreshStatus refreshStatus) throws IOException, URISyntaxException
	{
		System.out.println(refreshStatus.toString());
		//Keep Polling this api to get RefreshStatus : 
		for(int i=0; i<10; i++)
		{
			getRefreshStatus(refreshStatus.getProviderAccountId());
		}
	}
	public static RefreshStatus addNonMFA_Account(Providers providers) throws IOException, URISyntaxException
	{
		
		Console con = new Console();
		/** Use this block to add Non - MFA account: 
		  	a) Non-MFA means a non multi factored authentication method where a simple userName/password suffices. 
		 	Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) **/
		if (con != null)
		{
			System.out.println(" Add addNonMFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(EncryptionUtil.encrypt(userName));
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(EncryptionUtil.encrypt(password));	
					
		}
			
		RefreshStatus refreshStatus = AddProviderAccountWithPKI.addProviderAccount(providers);
		return refreshStatus;
	}
	
	public static void addMFA_Account(Providers providers) throws IOException, URISyntaxException, InterruptedException
	{
		
		Console con = new Console();
		// Use this block to add an MFA account: 
		// a) MFA means a non multi factored authentication method where the site provides a challenge Q&A or Captcha to validate the request.
		// Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) 
		if (con != null)
		{
			System.out.println(" Add MFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(EncryptionUtil.encrypt(userName));
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(EncryptionUtil.encrypt(password));	
					
		}
		
		RefreshStatus refreshStatus = AddProviderAccountWithPKI.addProviderAccount(providers);
		while(refreshStatus.getLoginForm()==null)
		{
			refreshStatus = AddProviderAccountWithPKI.getRefreshStatus(refreshStatus.getProviderAccountId());
		}
		refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("123456");
		doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
		refreshStatus = AddProviderAccountWithPKI.getRefreshStatus(refreshStatus.getProviderAccountId());
		refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue(EncryptionUtil.encrypt("Texas"));
		refreshStatus.getLoginForm().getRow()[1].getField()[0].setValue(EncryptionUtil.encrypt("w3schools"));
		doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
		
		// Fetch the accounts if you need ! 
	}
	
}
