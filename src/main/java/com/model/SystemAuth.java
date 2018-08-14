package com.model;


public class SystemAuth {
    private String authId;
    
    private String authName;

    private String authHref;

    private String authGroupId;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthHref() {
        return authHref;
    }

    public void setAuthHref(String authHref) {
        this.authHref = authHref;
    }

    public String getAuthGroupId() {
        return authGroupId;
    }

    public void setAuthGroupId(String authGroupId) {
        this.authGroupId = authGroupId;
    }
}