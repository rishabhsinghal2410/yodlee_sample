README -  First ! 

----------------------------------
Yodlee YSL Sample Apps
----------------------------------
 - This Library or Source files by executing a build  could be used to consume our Yodlee API's which are 
   RESTFul in nature. 
 - This a java maven project need to import as maven existing project.
 - These SampleApps abstract the entire interaction to Yodlee API's, by sequentially executing a series 
   of steps to create a request, interact with an endpoint and parse the JSON response which is returned 
   by the end point. 
 - Most of the SampleApps will provide a Value Object or a simple Java Bean with Setters Getters which can 
   be reused in an Application. 
 - PKI encryption feature - This feature enables the user to pass the encrypted credentials to 
   Yodlee system, while doing account addition.
 - Sample Apps are divided into 3 categories - yaas, pfm & aggregation. 
 
   yaas - yodlee.authentication.authorization.services
   pfm - personal finance management. Information of resources already in the Yodlee Domain. 
   aggregration - aggregate or add accounts/investment accounts/insurance accounts etc... 


-------------------------------------
Start : 
-------------------------------------
  - change the  url in config.properties file
  - provide your cobrand and user credential in config.properties file
  - LoginApp.java abstracts all forms of authentication - basic, oauth, saml 
  - When PKI feature is enabled, please replace the content of the 
  		file - "PublicKey.txt" of this sample app with the content of the public key shared.
  		file - config.properties of sample App contains coBrand & user related informations.
-----------------
-------------------------------------
E.g : An Use Case for the SampleApps
-------------------------------------
  - First start with LoginApp 
  - Invoke Aggregraton API's such as - AddProviderAccount.java to perform account addition using plain text credentials
  - Invoke Aggregraton API's such as - AddProviderAccountWithPKI.java to perform account addition using PKI encrypted credentials
  - Invoke PFM API, AccountApp.java/ BillsApp.java / HoldingApp.java / TransactionApp.java
  
-------------------------------------
Design Considerations	
-------------------------------------
  - The Code has been Structured into the following packages 
  	api 
	   apps -> Find all our Sample Apps here
		     pfm -> Personal Finance Management Apps
		     aggregration -> Aggregration Apps
		     yaas -> Yodlee Authentication Authorization Apps. 
	   beans -> Value Objects which can be reused in the Applications.
	   io -> Input/Output -> HTTP & HTTPS methods and verbs are packaged here. 
	   parsers -> Individual Entity Parsers. Integrate external api provider parsers here ! 
	   util -> Util Classes for the framework. 
		   	EncryptionUtil.java - is the utility to do data encryption using PKI.
  
  
