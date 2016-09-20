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
import yodlee.ysl.api.beans.HoldingSummaryResponse;
import yodlee.ysl.api.beans.UserRegistrationResponse;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

public class UserRegistrationApp {

private static final String fqcn = UserRegistrationApp.class.getName();

	
	public static UserRegistrationResponse userRegistration(String registerParam) throws IOException, URISyntaxException {
		String mn = "userRegistration()";
		/*Console con = new Console();
		String userString="";
		if (con != null) {
			 userString = con.readLine("Enter the user input for register user : ");
			System.out.println("userString---"+userString);
		}*/
		System.out.println(fqcn + " :: " + mn);
		String registerUserURL = LoginApp.localURLVer1 + "user/register";
		//String jsonResponse = HTTP.doPostRegisterUser(registerUserURL,registerUserJson,LoginApp.cobTokens,false);
		String jsonResponse = HTTP.doPostRegisterUser(registerUserURL,registerParam,LoginApp.cobTokens,false);
		
		System.out.println(jsonResponse);
		UserRegistrationResponse userRegistrationResponse =(UserRegistrationResponse) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.UserRegistrationResponse.class);
		if(userRegistrationResponse != null){
		   // System.out.println(userRegistrationResponse.toString());	
		}else{
		   System.out.println("Problem occourd during registeration Or Username already registered..");	
		}
		return userRegistrationResponse;
	}
}
