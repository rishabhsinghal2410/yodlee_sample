/* * Copyright (c) 2015 Yodlee, Inc. All Rights Reserved. * * This software is the confidential and proprietary information of Yodlee, Inc. * Use is subject to license terms. */package yodlee.ysl.api.apps.aggregation;import java.io.IOException;import java.net.URISyntaxException;import java.util.List;import yodlee.ysl.api.apps.yaas.LoginApp;import yodlee.ysl.api.beans.Provider;import yodlee.ysl.api.beans.Providers;import yodlee.ysl.api.io.HTTP;import yodlee.ysl.api.parsers.GSONParser;import yodlee.ysl.api.util.Console;public class ProviderApp {	private static final String fqcn = ProviderApp.class.getName();	public static void searchProvider(String searchString) throws IOException,			URISyntaxException {		String mn = "searchSite(searchString " + searchString + " )";		System.out.println(fqcn + " :: " + mn);		String searchProviderURL = LoginApp.localURLVer1 + "providers?name="				+ searchString;		String jsonResponse = HTTP.doGet(searchProviderURL, LoginApp.loginTokens);		Providers providers = (Providers) GSONParser.handleJson(				jsonResponse, yodlee.ysl.api.beans.Providers.class);		if(providers != null){			System.out.println(providers.toString());		}else{			System.out.println("Empty Response..");		}			}	public static Providers getProviderLoginForm(String providerId) throws IOException,			URISyntaxException {		String mn = "getProviderLoginForm(providerId " + providerId + " )";		System.out.println(fqcn + " :: " + mn);		String getSiteURL = LoginApp.localURLVer1 + "providers/" + providerId;		String jsonResponse = HTTP.doGet(getSiteURL, LoginApp.loginTokens);		Providers providers = (Providers) GSONParser.handleJson(				jsonResponse, yodlee.ysl.api.beans.Providers.class);		System.out.println(providers.toString());		return providers;	}	/*public static void main(String[] args) throws IOException,			URISyntaxException {		System.out.println("ProviderApp - TEST - START");		//LoginApp.main(null);		Console con = new Console();		if (con != null) {			String searchString = con					.readLine("Enter the site you want to search : ");			searchProvider(searchString);		}		if (con != null) {			String site = con.readLine("Enter the site Id : ");			getProviderLoginForm(site);		}	}*/}