package com.mpri.aio.schoolmate.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;

 /**   
 *  
 * @Description:  校友会管理
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Mon Aug 27 14:36:13 CST 2018
 * @Version:      v_1.0
 *    
 */
public class SmAssociation extends DataEntity<SmAssociation> {

	private static final long serialVersionUID = 1535351773543L;
	
	private String parentId;
	private String parentIds;
	private String name;
	private String logo;
	private String type;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date buildDate;
	private String summary;
	private String master;
	private String email;
	private String phone;
	private String address;
	private Integer sum;
	private String wcaccount;
	private String wcigroup;
	private String constitution;
	private String province;
	private String city;

	
	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	public String getParentIds() {
		return this.parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getLogo() {
		return this.logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public Date getBuildDate() {
		return this.buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}	
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}	
	public String getMaster() {
		return this.master;
	}
	public void setMaster(String master) {
		this.master = master;
	}	
	public String getEmail() {
		return this.email;
	}
	public void setEmaiil(String email) {
		this.email = email;
	}	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public Integer getSum() {
		return this.sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}	
	public String getWcaccount() {
		return this.wcaccount;
	}
	public void setWcaccount(String wcaccount) {
		this.wcaccount = wcaccount;
	}	
	public String getWcigroup() {
		return this.wcigroup;
	}
	public void setWcigroup(String wcigroup) {
		this.wcigroup = wcigroup;
	}	
	public String getConstitution() {
		return this.constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}	
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}	
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}		

}
