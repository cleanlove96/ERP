package com.pojo;


import java.util.List;

import com.model.AuthGroup;
import com.model.SystemAuth;

public class AuthGroupPojo {
	
	private AuthGroup authGroup;
	private List<SystemAuth> auths;
	
	public AuthGroup getAuthGroup() {
		return authGroup;
	}
	public void setAuthGroup(AuthGroup authGroup) {
		this.authGroup = authGroup;
	}
	public List<SystemAuth> getAuths() {
		return auths;
	}
	public void setAuths(List<SystemAuth> auths) {
		this.auths = auths;
	}

}
