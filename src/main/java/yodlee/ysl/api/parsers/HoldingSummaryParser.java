/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.parsers;

import java.io.IOException;

import yodlee.ysl.api.beans.HoldingSummaryResponse;

import com.google.gson.Gson;

public class HoldingSummaryParser implements Parser {
   
	private String fqcn = this.getClass().getName();

	public <T> Object parseJSON(String json) throws IOException {
		String mn = "parseJSON(" + json + ")";
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new Gson();
		return (HoldingSummaryResponse) gson.fromJson(json, HoldingSummaryResponse.class);
	}
	
	

}
