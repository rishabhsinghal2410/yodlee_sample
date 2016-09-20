package yodlee.ysl.api.parsers;

import java.io.IOException;

import yodlee.ysl.api.beans.ProviderAccountRefreshStatus;

import com.google.gson.Gson;

public class ProviderAccRefreshStatusParser implements Parser{

	private String fqcn = this.getClass().getName();
	public ProviderAccountRefreshStatus parseJSON(String json) throws IOException 
	{
		String mn = "parseJSON(" + json + ")";
		System.out.println(fqcn + " :: " + mn);
		Gson gson = new Gson();
		return (ProviderAccountRefreshStatus)gson.fromJson(json, ProviderAccountRefreshStatus.class);
	}

}
