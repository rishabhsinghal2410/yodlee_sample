/*
* Copyright (c) 2016 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/
package yodlee.ysl.api.beans;

public class User {
String id;
String loginName;
UserSession session;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public UserSession getSession() {
	return session;
}
public void setSession(UserSession session) {
	this.session = session;
}



}
