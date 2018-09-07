package com.mpri.aio.donation.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;

 /**   
 *  
 * @Description:  捐赠项目管理
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Wed Aug 29 15:22:22 CST 2018
 * @Version:      v_1.0
 *    
 */
public class DonProject extends DataEntity<DonProject> {

	private static final long serialVersionUID = 1535527342454L;
	
	private String name;
	private String secondName;
	private String pic;
	private String type;
	private String master;
	private String phone;
	private String email;
	private Double targetMoney;
	private Double gotMoney;
	private Integer donatingNum;
	private String summary;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date startdate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date enddate;
	private String status;
	private String remark;

	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getSecondName() {
		return this.secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}	
	public String getPic() {
		return this.pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getMaster() {
		return this.master;
	}
	public void setMaster(String master) {
		this.master = master;
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
	public Double getTargetMoney() {
		return this.targetMoney;
	}
	public void setTargetMoney(Double targetMoney) {
		this.targetMoney = targetMoney;
	}	
	public Double getGotMoney() {
		return this.gotMoney;
	}
	public void setGotMoney(Double gotMoney) {
		this.gotMoney = gotMoney;
	}	
	public Integer getDonatingNum() {
		return this.donatingNum;
	}
	public void setDonatingNum(Integer donatingNum) {
		this.donatingNum = donatingNum;
	}	
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public Date getStartdate() {
		return this.startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}	
	public Date getEnddate() {
		return this.enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	

}
