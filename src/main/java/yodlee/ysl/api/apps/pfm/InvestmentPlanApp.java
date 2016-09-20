package yodlee.ysl.api.apps.pfm;

import java.io.IOException;
import java.net.URISyntaxException;

import yodlee.ysl.api.apps.yaas.LoginApp;
import yodlee.ysl.api.beans.Accounts;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

public class InvestmentPlanApp {
	private static final String fqcn = InvestmentPlanApp.class.getName();
	

	public static Accounts getInvetmentOptions() throws IOException, URISyntaxException {
		String mn = "getInvetmentOptions()";
		System.out.println(fqcn + " :: " + mn);
		String investmentOptionsURL = LoginApp.localURLVer1 + "accounts/investmentPlan/investmentOptions/";
		String jsonResponse = HTTP.doGet(investmentOptionsURL,
				LoginApp.loginTokens);
		System.out.println(jsonResponse);
		Accounts accounts =(Accounts) GSONParser.handleJson(
					jsonResponse, yodlee.ysl.api.beans.Accounts.class);
		if(accounts.getAccounts() != null){
		  System.out.println(accounts.toString());
		}else{
			System.out.println("Empty response.");
		}
		return accounts;
	}

}
