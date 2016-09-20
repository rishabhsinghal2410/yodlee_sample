/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.apps.pfm;

import java.io.IOException;
import java.net.URISyntaxException;

import yodlee.ysl.api.apps.yaas.LoginApp;
import yodlee.ysl.api.beans.HoldingSummaryResponse;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

public class HoldingSummaryApp {
	private static final String fqcn = HoldingSummaryApp.class.getName();
	 
	
	public static HoldingSummaryResponse getHoldingSummary() throws IOException, URISyntaxException {
		String mn = "getHoldingSummary()";
		System.out.println(fqcn + " :: " + mn);
		String holdingSummaryURL = LoginApp.localURLVer1 + "derived/holdingSummary/";
		String jsonResponse = HTTP.doGet(holdingSummaryURL,
				LoginApp.loginTokens);
		System.out.println(jsonResponse);
		HoldingSummaryResponse holdingSummary =(HoldingSummaryResponse) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.HoldingSummaryResponse.class);
		if(holdingSummary.getHoldingSummary() != null ){
		    System.out.println(holdingSummary.toString());	
		}else{
		   System.out.println("Empty Response..");	
		}
		return holdingSummary;
	}
	
}

	
