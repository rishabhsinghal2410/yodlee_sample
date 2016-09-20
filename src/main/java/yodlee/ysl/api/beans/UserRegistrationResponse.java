/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/

package yodlee.ysl.api.beans;

public class UserRegistrationResponse {
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	/*public String toString()
	{
		StringBuilder userInfo = new StringBuilder("");
		
		//userInfo.append("User Id- "+user.getId()).append(" UserSession => ").append(user.getSession().getUserSession()).append("\n");
		
		return userInfo.toString();
	}*/
}
