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
import yodlee.ysl.api.beans.Transactions;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

public class TransactionApp {

	private static final String fqcn = TransactionApp.class.getName();
	
	public static Transactions getTransactions() throws IOException,URISyntaxException {
		String mn = "getTransactions()";
		System.out.println(fqcn + " :: " + mn);
		String transactionsURL = LoginApp.localURLVer1 + "transactions";
		String jsonResponse = HTTP.doGet(transactionsURL,
		LoginApp.loginTokens);
		System.out.println(jsonResponse);
		Transactions transactions =(Transactions) GSONParser.handleJson(jsonResponse, yodlee.ysl.api.beans.Transactions.class);
		if(transactions.getTransaction() != null){
			System.out.println(transactions.toString());
		}else{
			System.out.println("empty respon");	
		}
		return transactions;
		}

}
