package com.mpri.aio.donation.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;

 /**   
 *  
 * @Description:  捐赠记录管理
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Wed Aug 29 15:09:37 CST 2018
 * @Version:      v_1.0
 *    
 */
public class DonRecord extends DataEntity<DonRecord> {

	private static final long serialVersionUID = 1535526577051L;
	
	private String donProjectId;
	private String donType;
	private String sysUserId;
	private String username;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Double money;
	private String moneyType;
	private String donationName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date time;
	private String isCertificate;
	private String isInvoice;
	private String customid;
	private String state;
	private String style;

	
	public String getDonProjectId() {
		return this.donProjectId;
	}
	public void setDonProjectId(String donProjectId) {
		this.donProjectId = donProjectId;
	}	
	public String getDonType() {
		return this.donType;
	}
	public void setDonType(String donType) {
		this.donType = donType;
	}	
	public String getSysUserId() {
		return this.sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public Double getMoney() {
		return this.money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}	
	public String getMoneyType() {
		return this.moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}	
	public String getDonationName() {
		return this.donationName;
	}
	public void setDonationName(String donationName) {
		this.donationName = donationName;
	}	
	public Date getTime() {
		return this.time;
	}
	public void setTime(Date time) {
		this.time = time;
	}	
	public String getIsCertificate() {
		return this.isCertificate;
	}
	public void setIsCertificate(String isCertificate) {
		this.isCertificate = isCertificate;
	}	
	public String getIsInvoice() {
		return this.isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}	
	public String getCustomid() {
		return this.customid;
	}
	public void setCustomid(String customid) {
		this.customid = customid;
	}	
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}	
	public String getStyle() {
		return this.style;
	}
	public void setStyle(String style) {
		this.style = style;
	}	

}
