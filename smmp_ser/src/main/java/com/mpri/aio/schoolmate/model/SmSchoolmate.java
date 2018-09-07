package com.mpri.aio.schoolmate.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;
import com.mpri.aio.system.init.InitCache;
import com.mpri.aio.system.model.SysArea;
import com.mpri.aio.system.model.SysDict;
import com.mpri.aio.system.model.SysOrg;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**   
 *  
 * @Description:  校友管理-校友表
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Mon Aug 20 10:32:38 CST 2018
 * @Version:      v_1.0
 *    
 */
@ExcelTarget("smSchoolmate")
public class SmSchoolmate  extends DataEntity<SmSchoolmate> {

	private static final long serialVersionUID = 1534732358165L;
	
	private String sysUserId;
	@Excel(name ="用户名")
	private String username;
	private String cardId;
	private String cardStatus;
	private String openid;
	@Excel(name ="姓名")
	private String name;
	private String pinyin;
	@Excel(name ="性别")
	private String sex;
	private String truePhoto;
	@Excel(name ="生日",format="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date birthday;
	@Excel(name ="证件号")
	private String cardNum;
	@Excel(name ="证件类型")
	private String cardType;
	@Excel(name ="校友类型")
	private String type;
	private Integer level;
	private Integer point;
	@Excel(name ="民族")
	private String nation;
	private Integer complete;
	@ExcelEntity 
	private SmAddress smAddress;
	@ExcelEntity 
	private SmContact smContact;
	@ExcelEntity 
	private SmEducation smEducation;
	
	private List<SmMark> markList = new ArrayList<SmMark>();
	

    public List<SmMark> getMarkList() {
		return markList;
	}

	public void setMarkList(List<SmMark> markList) {
		this.markList = markList;
	}
	
	public SmContact getSmContact() {
		return smContact;
	}
	public void setSmContact(SmContact smContact) {
		this.smContact = smContact;
	}
	public SmEducation getSmEducation() {
		return smEducation;
	}
	public void setSmEducation(SmEducation smEducation) {
		this.smEducation = smEducation;
	}
	public SmAddress getSmAddress() {
		return smAddress;
	}
	public void setSmAddress(SmAddress smAddress) {
		this.smAddress = smAddress;
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
	public String getCardId() {
		return this.cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}	
	public String getCardStatus() {
		return this.cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}	
	public String getOpenid() {
		return this.openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getPinyin() {
		return this.pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}	
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}	
	public String getTruePhoto() {
		return this.truePhoto;
	}
	public void setTruePhoto(String truePhoto) {
		this.truePhoto = truePhoto;
	}	
	public Date getBirthday() {
		return this.birthday;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}	
	public String getCardNum() {
		return this.cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}	
	public String getCardType() {
		return this.cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}	
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public Integer getLevel() {
		return this.level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}	
	public Integer getPoint() {
		return this.point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}	
	public String getNation() {
		return this.nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}	
	public Integer getComplete() {
		return this.complete;
	}
	public void setComplete(Integer complete) {
		this.complete = complete;
	}	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
}
