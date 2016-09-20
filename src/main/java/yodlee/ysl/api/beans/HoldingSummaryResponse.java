/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class HoldingSummaryResponse {
	HoldingSummary[] holdingSummary;


	public HoldingSummary[] getHoldingSummary() {
		return holdingSummary;
	}


	public void setHoldingSummary(HoldingSummary[] holdingSummary) {
		this.holdingSummary = holdingSummary;
	}


	public String toString()
	{
		StringBuilder holdings = new StringBuilder("");
		for (int i = 0; i<holdingSummary.length; i++)
		{
			holdings.append("ClassificationType- "+holdingSummary[i].getClassificationType()).append(" ClassificationValue=> ").append(holdingSummary[i].getClassificationValue()).append("\n");
		}
		return holdings.toString();
	}
}
