/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.apps.pfm;

import java.io.IOException;
import java.net.URISyntaxException;

import yodlee.ysl.api.apps.yaas.LoginApp;
import yodlee.ysl.api.beans.Accounts;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;


/**
 *  The AccountApp class provides authentication and authorization services. 
 * 
 *  
 *   Steps to Use this App: 
 *   i) doCoBrandLogin(String coBrandUserName, String coBrandPassword)
 *   ii) doMemberLogin(String userName, String userPassword)
 *   
 *   Browse all Accounts for member profile: 
 *   Accounts getAccounts() 
 *   
 * @author vshetty
 *
 */

public class AccountApp {
	private static final String fqcn = AccountApp.class.getName();

	public static Accounts getAccounts() throws IOException,
			URISyntaxException {
		String mn = "getAccounts()";
		System.out.println(fqcn + " :: " + mn);
		String accountSummaryURL = LoginApp.localURLVer1 + "accounts/";
		String jsonResponse = HTTP.doGet(accountSummaryURL,
				LoginApp.loginTokens);
		System.out.println(jsonResponse);
		Accounts accounts =(Accounts) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.Accounts.class);
		if(accounts.getAccounts() != null){
			System.out.println(accounts.toString());
		}else{
			System.out.println("Empty Response--");
		}
		
		return accounts;
	}

	
}
