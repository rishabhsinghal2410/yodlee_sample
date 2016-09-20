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
import yodlee.ysl.api.beans.ProviderAccountRefreshStatus;
import yodlee.ysl.api.beans.Providers;
import yodlee.ysl.api.beans.RefreshStatus;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The AddProviderAccount class provides aggregation methods. Aggregation means,
 * adding a bank, insurance/investment etcc.. account to Yodlee.
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
public class AddProviderAccount {
	private static final String fqcn = AddProviderAccount.class.getName();

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
		providerJson=providerJson.replaceAll("\\s+","");
		System.out.println(providerJson);
		String addSiteURL = LoginApp.localURLVer1 + "providers/" + providers.getProvider()[0].getId();
		String jsonResponse = HTTP.doPostUser(addSiteURL, LoginApp.loginTokens,
				providerJson,false);
		/*RefreshStatuss refreshStatus = (RefreshStatuss) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatuss.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus.getProviderAccount();*/
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
	}
	
	
	public static ProviderAccountRefreshStatus addProviderAccountWithWebhooks(Providers providers)
			throws IOException, URISyntaxException {
		String mn = "addProviderAccount( " + providers.getProvider()[0].toString();
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.create();
		//String providerJson = "{\"provider\":["+ gson.toJson(providers.getProvider()[0])+"]}";
		
		String providerJson = "{\"loginForm\":" + gson.toJson(providers.getProvider()[0].getLoginForm())+"}";
		providerJson=providerJson.replaceAll("\\s+","");
		System.out.println(providerJson);
		String addSiteURL = LoginApp.localURLVer1 + "providers/providerAccounts?providerId=" + providers.getProvider()[0].getId();
		String jsonResponse = HTTP.doPostUser(addSiteURL, LoginApp.loginTokens,
				providerJson,false);
		
		ProviderAccountRefreshStatus refreshStatus = (ProviderAccountRefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.ProviderAccountRefreshStatus.class);
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
		String addSiteURL = LoginApp.localURLVer1 + "providers/"+providerAccountId ;
		HTTP.doPut(addSiteURL,providerJson, LoginApp.loginTokens);
	}
	
	
	public static ProviderAccountRefreshStatus doChallengeNew(LoginForm loginForm, String providerAccountId)
			throws IOException, URISyntaxException {
		String mn = "doChallenge( " + loginForm.toString() + " providerAccountId = " + providerAccountId;
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new GsonBuilder().disableHtmlEscaping()
				.create();
		String providerJson = "{\"loginForm\":"+ gson.toJson(loginForm)+"}";
		System.out.println(providerJson);
		String addSiteURL = LoginApp.localURLVer1 + "providers/providerAccounts?providerAccountIds=" + providerAccountId;
		String jsonResponse = HTTP.doPutNew(addSiteURL,providerJson, LoginApp.loginTokens);
		ProviderAccountRefreshStatus refreshStatus = (ProviderAccountRefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.ProviderAccountRefreshStatus.class);
		return refreshStatus;
		
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
		String getRefreshStatusURL = LoginApp.localURLVer1 + "refresh/"
				+ providerAccountId.toString();
		String jsonResponse =  HTTP.doGet(getRefreshStatusURL, LoginApp.loginTokens);
		/*RefreshStatuss refreshStatus = (RefreshStatuss) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatuss.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus.getProviderAccount();*/
		RefreshStatus refreshStatus = (RefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.RefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
	}



	public static void addAcount(String[] a) throws IOException, URISyntaxException, InterruptedException{

		Providers providers = null;
		RefreshStatus refreshStatus = null;
		ProviderAccountRefreshStatus providerAccRefreshStatus=null;
		System.out.println("Add Provider Account App - TEST - START");
		Console con = new Console();
		/*if (con != null) {
			String searchString = con
					.readLine("Enter the name of the  Provider you want to search : ");
			ProviderApp.searchProvider(searchString);
		}*/
		if (con != null) {
			String providerId = con.readLine("Enter the Provider Id : ");
			providers = ProviderApp.getProviderLoginForm(providerId);
		}
		//System.out.println(" Enter 1 to add NonMFA account and 2 to add MFA Account");
		//String choice = con.readLine("choice");
		//adding condition to check new add Account with webhooks or without webhooks.
		if(a[0].equals("1")){
		 refreshStatus = addNonMFA_Account(providers);
		}
		else if(a[0].equals("2")){
			addMFA_Account(providers);	
		}else if(a[0].equals("3")){
			providerAccRefreshStatus = addNonMFA_Provider_Account(providers);
		}
		else if(a[0].equals("4")){
			addMFA_Provider_Account(providers);
		}
	}
	
	
	
	private static void addMFA_Provider_Account(Providers providers) throws IOException, URISyntaxException {

		Console con = new Console();
		
		// Use this block to add an MFA account: 
		// a) MFA means a non multi factored authentication method where the site provides a challenge Q&A or Captcha to validate the request.
		// Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) 
		if (con != null)
		{
			System.out.println(" Add MFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
					
		}
		
		ProviderAccountRefreshStatus refreshStatus = AddProviderAccount.addProviderAccountWithWebhooks(providers);
		while(refreshStatus.getProviderAccount().getLoginForm()==null)
		{
			refreshStatus = AddProviderAccount.getProviderAccRefreshStatus(refreshStatus.getProviderAccount().getId());
			System.out.println("Current Status- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
			if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("FAILED")){
				System.out.println("Account Not Added because  status -- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}else if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
				System.out.println("Account successfully added and current add status  "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}
			
		}
		if(!refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS") && !refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("FAILED")){
		refreshStatus.getProviderAccount().getLoginForm().getRow()[0].getField()[0].setValue("123456");
		refreshStatus = doChallengeNew(refreshStatus.getProviderAccount().getLoginForm(), refreshStatus.getProviderAccount().getId());
		while(refreshStatus.getProviderAccount().getLoginForm()==null)
		{
			refreshStatus = AddProviderAccount.getProviderAccRefreshStatus(refreshStatus.getProviderAccount().getId());
			System.out.println("Current Status- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
			if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("FAILED")){
				System.out.println("Account Not Added because  status -- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}else if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
				System.out.println("Account successfully added and current add status  "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}
		}
		if(!refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
		refreshStatus.getProviderAccount().getLoginForm().getRow()[0].getField()[0].setValue("Texas");
		refreshStatus.getProviderAccount().getLoginForm().getRow()[1].getField()[0].setValue("w3schools");
		refreshStatus = doChallengeNew(refreshStatus.getProviderAccount().getLoginForm(), refreshStatus.getProviderAccount().getId());
		 String refMsg = refreshStatus.getProviderAccount().getRefreshInfo().getStatus();
		while(!refMsg.equals("SUCCESS"))
		{				
			System.out.println("current add status: "+refMsg);
			refreshStatus = AddProviderAccount.getProviderAccRefreshStatus(refreshStatus.getProviderAccount().getId());
			refMsg = refreshStatus.getProviderAccount().getRefreshInfo().getStatus();
			System.out.println("Current Status- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
			if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("FAILED")){
				System.out.println("Account Not Added because  status -- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}else if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
				break;
			}
		}
		}
	}
		if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
			System.out.println("Account successfully added and current add status  "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());}
	
	}

	private static ProviderAccountRefreshStatus addNonMFA_Provider_Account(Providers providers) throws IOException, URISyntaxException {

		
		Console con = new Console();
		/** Use this block to add Non - MFA account: 
		  	a) Non-MFA means a non multi factored authentication method where a simple userName/password suffices. 
		 	Use this method to input Provider (End Site -> credentials e.g. the Bank Of America UserName & Password goes in the next set of methods) **/
		if (con != null)
		{
			System.out.println(" Add addNonMFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
					
		}
			
		ProviderAccountRefreshStatus refreshStatus = AddProviderAccount.addProviderAccountWithWebhooks(providers);
		if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus() != null){
		while(!refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
			System.out.println("current add status: "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
			refreshStatus = AddProviderAccount.getProviderAccRefreshStatus(refreshStatus.getProviderAccount().getId());
			System.out.println("Current Status- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
			if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("FAILED")){
				System.out.println("Account Not Added because  status -- "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());
				break;
			}
		}
		}
		if(refreshStatus.getProviderAccount().getRefreshInfo().getStatus().equals("SUCCESS")){
		System.out.println("Account successfully added and current add status  "+refreshStatus.getProviderAccount().getRefreshInfo().getStatus());}
		return refreshStatus;
	
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
		if (con != null){
			System.out.println(" Add addNonMFA_Account:  ");
			String userName = con.readLine("Enter your provider userName : ");
			// For internal Yodlee Dag tool use this hardcoded user Name = "DBmet1.site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
		}
		if (con != null){
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
		}
			
		RefreshStatus refreshStatus = AddProviderAccount.addProviderAccount(providers);
		while(!refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED")){
			System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
			refreshStatus = AddProviderAccount.getRefreshStatus(refreshStatus.getProviderAccountId());
		}
		System.out.println("Account successfully added");
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
			providers.getProvider()[0].getLoginForm().getRow()[0].getField()[0].setValue(userName);
		}
		if (con != null)
		{
			String password = con.readLine("Enter provider password : ");
			// For internal Yodlee Dag tool use this hardcoded password = "site16441.1"
			providers.getProvider()[0].getLoginForm().getRow()[1].getField()[0].setValue(password);	
					
		}
		
		RefreshStatus refreshStatus = AddProviderAccount.addProviderAccount(providers);
		while(refreshStatus.getLoginForm()==null)
		{
			refreshStatus = AddProviderAccount.getRefreshStatus(refreshStatus.getProviderAccountId());
			if(refreshStatus.getRefreshInfo().getRefreshStatus().equals("FAILED") || refreshStatus.getRefreshInfo().getRefreshStatus().equals("LOGIN_FAILURE") ){
				System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
				break;
			}else if(refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED")){
				System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
				break;
				
			}
		}
		if(!refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED") && !(refreshStatus.getRefreshInfo().getRefreshStatus().equals("FAILED") || refreshStatus.getRefreshInfo().getRefreshStatus().equals("LOGIN_FAILURE"))){
		refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("123456");
		doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
		refreshStatus = AddProviderAccount.getRefreshStatus(refreshStatus.getProviderAccountId());
		while(refreshStatus.getLoginForm()==null)
		{
			refreshStatus = AddProviderAccount.getRefreshStatus(refreshStatus.getProviderAccountId());
			if(refreshStatus.getRefreshInfo().getRefreshStatus().equals("FAILED") || refreshStatus.getRefreshInfo().getRefreshStatus().equals("LOGIN_FAILURE") ){
				System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
				break;
			}else if(refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED")){
				System.out.println("current add status: "+refreshStatus.getRefreshInfo().getRefreshStatus());
				break;
				
			}
		}
		if(!refreshStatus.getRefreshInfo().getRefreshStatus().equals("REFRESH_COMPLETED") && !(refreshStatus.getRefreshInfo().getRefreshStatus().equals("FAILED") || refreshStatus.getRefreshInfo().getRefreshStatus().equals("LOGIN_FAILURE")) ){
		refreshStatus.getLoginForm().getRow()[0].getField()[0].setValue("Texas");
		refreshStatus.getLoginForm().getRow()[1].getField()[0].setValue("w3schools");
		doChallenge(refreshStatus.getLoginForm(), refreshStatus.getProviderAccountId());
		String refMsg = refreshStatus.getRefreshInfo().getRefreshStatus();
		while(!refMsg.equals("REFRESH_COMPLETED"))
		{				
			System.out.println("current add status: "+refMsg);
			refreshStatus = AddProviderAccount.getRefreshStatus(refreshStatus.getProviderAccountId());
			refMsg = refreshStatus.getRefreshInfo().getRefreshStatus();
			if(refMsg.equalsIgnoreCase("FAILED") || refMsg.equalsIgnoreCase("LOGIN_FAILURE")){
				System.out.println("Account Not Added");
				break;
			}else if(refMsg.equals("REFRESH_COMPLETED")){
				System.out.println("Account Added Successfully");
				break;
				
			}
		}}
		}
		System.out.println("Account Added Successfully");
		// Fetch the accounts if you need ! 
	}
	
	public static ProviderAccountRefreshStatus getProviderAccRefreshStatus(String providerAccountId)
			throws IOException, URISyntaxException {
		String mn = "getRefreshStatus( " + providerAccountId.toString() + " )";
		System.out.println(fqcn + " :: " + mn);
		String getRefreshStatusURL = LoginApp.localURLVer1 + "providers/providerAccounts/"
				+ providerAccountId.toString();
		String jsonResponse =  HTTP.doGet(getRefreshStatusURL, LoginApp.loginTokens);
		System.out.println("jsonResponse refersh--"+jsonResponse);
		ProviderAccountRefreshStatus refreshStatus = (ProviderAccountRefreshStatus) GSONParser.handleJson(
				jsonResponse, yodlee.ysl.api.beans.ProviderAccountRefreshStatus.class);
		System.out.println(refreshStatus.toString());
		return refreshStatus;
	}

	
}
