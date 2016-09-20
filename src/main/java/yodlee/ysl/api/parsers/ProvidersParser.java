/*
* Copyright (c) 2015 Yodlee, Inc. All Rights Reserved.
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/package yodlee.ysl.api.parsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yodlee.ysl.api.beans.Providers;
import yodlee.ysl.api.beans.Transactions;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
public class ProvidersParser implements Parser
{
	private String fqcn = this.getClass().getName();
	public Providers parseJSON(String json) throws IOException 
	{
		String mn = "parseJSON(" + json + ")";
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new Gson();
		return (Providers)gson.fromJson(json, Providers.class);
	}
	
}
