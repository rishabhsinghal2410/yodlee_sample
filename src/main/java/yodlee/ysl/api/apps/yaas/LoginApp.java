/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/

package yodlee.ysl.api.apps.yaas;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import yodlee.ysl.api.apps.aggregation.AddProviderAccount;
import yodlee.ysl.api.apps.aggregation.ProviderApp;
import yodlee.ysl.api.apps.pfm.AccountApp;
import yodlee.ysl.api.apps.pfm.HoldingApp;
import yodlee.ysl.api.apps.pfm.HoldingSummaryApp;
import yodlee.ysl.api.apps.pfm.InvestmentPlanApp;
import yodlee.ysl.api.apps.pfm.TransactionApp;
import yodlee.ysl.api.apps.pfm.UserRegistrationApp;
import yodlee.ysl.api.beans.CobrandContext;
import yodlee.ysl.api.beans.UserContext;
import yodlee.ysl.api.beans.UserRegistrationResponse;
import yodlee.ysl.api.io.HTTP;
import yodlee.ysl.api.parsers.GSONParser;
import yodlee.ysl.api.util.Console;

public class LoginApp {
	
	private static final String fqcn = LoginApp.class.getName();
	public static Map<String,String> loginTokens = new HashMap<String,String>();
	public static Map<String,String> cobTokens = new HashMap<String,String>();
    //static ResourceBundle resourceBundle = ResourceBundle.getBundle("//resources/config.properties");
    public static final String  localURLVer1= "https://developer.api.yodlee.com/ysl/restserver/v1/";//resourceBundle.getString("yodlee.localURLVer1");
    static String coBrandUserName = "sbCobrishabhsinghal2410";//resourceBundle.getString("yodlee.coBrandUserName");
    static String coBrandPassword = "e31bd297-33ee-410a-919a-72699a375cc3";//resourceBundle.getString("yodlee.coBrandPassword");
    static String userName = "sbMemrishabhsinghal24101";//resourceBundle.getString("yodlee.userName");
    static String userPassword = "sbMemrishabhsinghal24101#123";//resourceBundle.getString("yodlee.userPassword");
	static String registerParam = "{\"user\": {\"loginName\": \"yslResr976\", \"password\": \"TEST@123\", \"email\": \"yslRest69@yodlee.com\", \"name\": {\"first\": \"FNAME\",\"last\": \"LNAME\" },\"address\": {\"address1\": \"200 Lincoln Ave\",\"state\": \"CA\",\"city\": \"Salinas\",\"zip\": \"93901\",\"country\": \"USA\"},\"preferences\": {\"currency\": \"USD\",\"timeZone\": \"PST\",\"dateFormat\": \"MM/dd/yyyy\"}}}";//resourceBundle.getString("yodlee.registerParam");
    

	public static void doCoBrandLogin(String coBrandUserName, String coBrandPassword) throws IOException {
		String mn = "doCoBrandLogin(coBrandUserName " + coBrandUserName + ", coBrandPassword " + coBrandPassword + " )";
		System.out.println(fqcn + " :: " + mn);
		//final String requestBody="cobrandLogin="+coBrandUserName+"&cobrandPassword="+coBrandPassword;
		final String requestBody="{"+
                "\"cobrand\":      {"+
                "\"cobrandLogin\": "+"\""+coBrandUserName+"\""+"," +
                "\"cobrandPassword\": "+"\""+ coBrandPassword +"\""+"," +
                "\"locale\": \"en_US\""+
                "}"+
          "}";

		String coBrandLoginURL = localURLVer1 + "cobrand/login";
		//HTTP.createConnection(coBrandLoginURL);
		String jsonResponse = HTTP.doPost(coBrandLoginURL, requestBody);
		CobrandContext coBrand = (CobrandContext) GSONParser.handleJson(jsonResponse, yodlee.ysl.api.beans.CobrandContext.class);
		// Change the toString() method of the class to decide what to display on the Console.
		System.out.println("Rishabh" + coBrand.toString());
		cobTokens.put("cobSession", coBrand.getSession().getCobSession());
		loginTokens.put("cobSession", coBrand.getSession().getCobSession());
	}
	

	public static void doMemberLogin(String userName, String userPassword) throws IOException {
		String mn = "doMemberLogin(userLogin=" +userName+ ", userPassword = " + userPassword + ", coBrandSessionCredential =" + loginTokens.get("cobSession") + " )";
		System.out.println(fqcn + " :: " + mn);
		//final String requestBody="coBrandSessionCredential="+ loginTokens.get("cobSession")+"&loginName=" + userName + "&password="+ userPassword;
		String userLoginURL = localURLVer1 + "user/login";
        final String requestBody="{"+
            "\"user\":      {"+
           "\"loginName\": " +"\""+userName+"\""+","+
           "\"password\": "+"\""+userPassword+"\""+","+
           "\"locale\": \"en_US\""+
           	"}"+
          "}";

		//HTTP.addHeaders("Authorization" , loginTokens.get("cobSession"));
		String jsonResponse = HTTP.doPostUser(userLoginURL,loginTokens, requestBody,true);
		UserContext member = (UserContext) GSONParser.handleJson(jsonResponse, yodlee.ysl.api.beans.UserContext.class);
		// Change the toString() method of the class to decide what to display on the Console.
		System.out.println(member.toString());
		loginTokens.put("userSession", member.getUser().getSession().getUserSession());
	}
	
	public static void caller() throws IOException, URISyntaxException, InterruptedException{
		 Console con = new Console();
	        boolean auth = false;
	        boolean loop = true;
	        if (con != null)
	        {	
	        	String[] a = {"1"};
	        	String[] b = {"2"};
	        	String[] c = {"3"};
	        	String[] d = {"4"};
	        	System.out.println( "Select option to Run \n 1- Site Search \n 2- Add Site Account (Non MFA) \n 3- Add Site Account (MFA)  \n 4- Add Provider Account (Non MFA) - New \n 5- Add Provider Account (MFA)- New \n 6- Accounts \n 7- Holdings \n 8- Transactions \n 9- HoldingWithAsset \n 10- InvestmentOptions \n 11- HoldingSummary \n 0- Exit");
	        	int choice = readInt();
	        	switch(choice){
	        	//to register user
	        	/*case "1":
	        		UserRegistrationResponse userResponse=UserRegistrationApp.userRegistration(registerParam);
	        		//overriding old user session
	        		if(userResponse!= null)
	        		loginTokens.put("userSession", userResponse.getUser().getSession().getUserSession().toString());
	        		caller();
	        		break;	*/
	        	
	        	case 1:
	        		String searchString = con.readLine("Enter the name of the  Provider you want to search : ");
	        		ProviderApp.searchProvider(searchString);
	        		caller();
	        		break;
	        		
	        	case 2:
	        		AddProviderAccount.addAcount(a);
	        		
	        		caller();
	        		break;
	        		
	        	case 3:
	        		AddProviderAccount.addAcount(b);
	        		caller();
	        		break;
	        	case 4:
	        		AddProviderAccount.addAcount(c);
	        		caller();
	        		break;
	        	case 5:
	        		AddProviderAccount.addAcount(d);
	        		caller();
	        		break;
	        		
	        	case 6:
	        		AccountApp.getAccounts();
	        		caller();
	        		break;
	        	case 7:
	        		HoldingApp.getHoldings(a);
	        		caller();
	        		break;
	        	case 8:
	        		TransactionApp.getTransactions();
	        		caller();
	        		break;
	        	case 9:
	        		HoldingApp.getHoldings(b);
	        		caller();
	        		break;
	        	case 10:
	        		InvestmentPlanApp.getInvetmentOptions();
	        		caller();
	        		break;
	        		
	        	case 11:
	        		HoldingSummaryApp.getHoldingSummary();
	        		caller();
	        		break;
	        		
	        	case 0:
//	        		caller();
	        		break;	
	        default: 
	        		System.out.println("Select an option from the above list");		
	        		caller();
                 }
	        }
	}
	
	public static void doLogin(String coBrandUserName, String coBrandPassword,String userName, String userPassword) throws IOException
	{
		doCoBrandLogin(coBrandUserName,coBrandPassword);
		doMemberLogin(userName,userPassword);
	}
	
    public static void main( String[] args ) throws IOException, URISyntaxException, InterruptedException {
    	System.out.println( "LoginAPP - TEST - START" );
        int MAX_LOGINS = 1;
9          Console con = new Console();
          boolean auth = false;
          if (con != null){
            int count = 0;
            do{
             LoginApp.doCoBrandLogin(coBrandUserName,coBrandPassword);
             LoginApp.doMemberLogin(userName,userPassword);
             
            } while (!auth && ++count < MAX_LOGINS);
          }
  			System.out.println(LoginApp.loginTokens.toString());
  			caller();
          }
    
public static String readStr() {
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

		String readStr = null;

		try {
			do {
				readStr = br.readLine();
				if (readStr != null) {
					/*readStr = readStr.substring(0,
							readStr.indexOf('#') == -1 ? readStr.length()
									: readStr.indexOf('#'));*/
				}
				readStr = readStr.trim();
			} while (readStr == null || (readStr.equalsIgnoreCase("")));
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw new RuntimeException("Error reading line!");
		}

		return readStr;

	}
  
    public static int readInt() {

		String readStr = readStr();

		try {
			return new Integer(readStr).intValue();
		} catch (NumberFormatException nfEx) {
			// throw new RuntimeException ("Invalid entry: " + readStr + ". You
			// must enter a number.");
			System.out.println("Invalid entry: " + readStr
					+ ". You must enter a number.");
			return -1;
		}
	}
}
