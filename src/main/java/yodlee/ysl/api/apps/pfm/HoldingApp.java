/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/

package yodlee.ysl.api.apps.pfm;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import yodlee.ysl.api.apps.yaas.LoginApp;
import yodlee.ysl.api.beans.Holdings;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

/**
 *  The HoldingApp class provides holdings for a member account. 
 *  Holdings are Investment accounts which a member has aggregated using Aggregation Apps.
 * 
 *  
 *   Steps to Use this App: 
 *   i) doCoBrandLogin(String coBrandUserName, String coBrandPassword)
 *   ii) doMemberLogin(String userName, String userPassword)
 *   
 *   Browse all Accounts for member profile: 
 *   Accounts getHoldings() 
 *   
 * @author vshetty
 *
 */

public class HoldingApp {

	private static final String fqcn = HoldingApp.class.getName();

	public static Holdings getHoldings(String a[]) throws IOException,
			URISyntaxException {
		Map<String,String> queryParams =null;
		if(a[0].equals("1")) {
		}
		if(a[0].equals("2")) {
			queryParams = new HashMap<String,String>();
			queryParams.put("include" , "assetClassification");
			queryParams.put("assetClassification.classificationType","COUNTRY");
			queryParams.put("assetClassification.classificationValue","US");
		}
		
		String mn = "getHoldings()";
		System.out.println(fqcn + " :: " + mn);
		String holdingsURL = LoginApp.localURLVer1 + "holdings"; 
		holdingsURL += queryParams != null && queryParams.size() > 0 ? "?" : "/";
		int cntr =0;
		if(queryParams !=null) for( String key : queryParams.keySet())
		{ 
			holdingsURL+= key + (queryParams.get(key).charAt(0) == '!' ? "%60%62" : "=") + "\"" + queryParams.get(key).replaceFirst("!", "") + "\"";
			holdingsURL+= cntr < queryParams.size()-1 ? "&" : "";
			cntr += 1;
		}
		holdingsURL=holdingsURL.replaceAll("\"", "");
		String jsonResponse = HTTP.doGet(holdingsURL,
				LoginApp.loginTokens);
		System.out.println(jsonResponse);
		Holdings holdings =(Holdings) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.Holdings.class);
		if(holdings.getHolding() != null ){
		System.out.println(holdings.toString());
		}else{
			System.out.println("Empty response--");
		}
		return holdings;
	}

}
