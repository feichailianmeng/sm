 package com.mpri.aio.schoolmate.model;

import com.mpri.aio.base.model.DataEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

/**   
*  
* @Description:  校友管理-联系方式
* @Author:       LZQ
* @project       AIO   
* @CreateDate:   Tue Aug 21 11:21:24 CST 2018
* @Version:      v_1.0
*    
*/
@ExcelTarget("smContact")
public class SmContact extends DataEntity<SmContact> {

	private static final long serialVersionUID = 1534821684124L;
	
	private String sysUserId;
	private String username;
	@Excel(name ="联系方式")
	private String type;
	@Excel(name ="联系号码")
	private String contact;
	
	
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
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getContact() {
		return this.contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}	


}
