package com.mpri.aio.schoolmate.model;

import com.mpri.aio.base.model.DataEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**   
*  
* @Description:  校友管理-校友地址管理
* @Author:       LZQ
* @project       AIO   
* @CreateDate:   Mon Aug 20 10:21:32 CST 2018
* @Version:      v_1.0
*    
*/
@ExcelTarget("smAddress")
public class SmAddress extends DataEntity<SmAddress> {

	private static final long serialVersionUID = 1534731692884L;
	
	/*是否是籍贯*/
	public static final String IS_NATION_PLACE ="IS_NATION_PLACE";
	public static final String NO_NATION_PLACE ="NO_NATION_PLACE";
	
	private String sysUserId;
	private String username;
	@Excel(name="邮寄地址")
	private String ispost;
	@Excel(name="国家")
	private String country;
	@Excel(name="省份")
	private String province;
	@Excel(name="城市")
	private String city;
	private String district;
	private String detail;
	private String phone;
	private String zipcode;
	//是否籍贯
	private String type;
	
	private String areaId;
	
	
	
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getIspost() {
		return this.ispost;
	}
	public void setIspost(String ispost) {
		this.ispost = ispost;
	}	
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getDistrict() {
		return (null == this.district ? "":this.district);
	}
	public void setDistrict(String district) {
		this.district = district;
	}	
	public String getDetail() {
		return this.detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getZipcode() {
		return this.zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}	
	

}
