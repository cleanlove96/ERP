package com.pojo;

public class AccountLeaveInfo {

    /**
	 * 员工姓名
	 */
    private String accountName;
    /**
   	 * 员工工号
   	 */
    private String accountNum;
    /**
   	 * 所属部门
   	 */
    private String sectionName;
    /**
   	 * 员工性别
   	 */
    private String accountSex;
    /**
   	 * 员工居住地
   	 */
    private String accountLocation;
    /**
   	 * 员工电话号码
   	 */
    private String accountPhone;
    /**
   	 * 员工入职时间
   	 */
    private String accountEntryDate;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getAccountSex() {
		return accountSex;
	}
	public void setAccountSex(String accountSex) {
		this.accountSex = accountSex;
	}
	public String getAccountLocation() {
		return accountLocation;
	}
	public void setAccountLocation(String accountLocation) {
		this.accountLocation = accountLocation;
	}
	public String getAccountPhone() {
		return accountPhone;
	}
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}
	public String getAccountEntryDate() {
		return accountEntryDate;
	}
	public void setAccountEntryDate(String accountEntryDate) {
		this.accountEntryDate = accountEntryDate;
	}
	@Override
	public String toString() {
		return "AccountLeaveInfo [accountName=" + accountName + ", accountNum=" + accountNum + ", sectionName="
				+ sectionName + ", accountSex=" + accountSex + ", accountLocation=" + accountLocation
				+ ", accountPhone=" + accountPhone + ", accountEntryDate=" + accountEntryDate + "]";
	}
	public AccountLeaveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountLeaveInfo(String accountName, String accountNum, String sectionName, String accountSex,
			String accountLocation, String accountPhone, String accountEntryDate) {
		super();
		this.accountName = accountName;
		this.accountNum = accountNum;
		this.sectionName = sectionName;
		this.accountSex = accountSex;
		this.accountLocation = accountLocation;
		this.accountPhone = accountPhone;
		this.accountEntryDate = accountEntryDate;
	}
   
}
