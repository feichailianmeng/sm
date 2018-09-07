package com.mpri.aio.schoolmate.model;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;
import cn.afterturn.easypoi.excel.annotation.Excel;

 /**   
 *  
 * @Description:  校友中间表管理
 * @Author:       LZQ
 * @project       AIO   
 * @CreateDate:   Tue Aug 28 16:24:33 CST 2018
 * @Version:      v_1.0
 *    
 */
public class SmSchoolmateTemp extends DataEntity<SmSchoolmateTemp> {

	private static final long serialVersionUID = 1535444673408L;
	
	@Excel(name = "姓名")
	private String name;
	@Excel(name = "性别")
	private String sex;
	@Excel(name = "生日",format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date birthday;
	@Excel(name = "证件类型")
	private String cardNum;
	@Excel(name = "证件号")
	private String cardType;
	@Excel(name = "校友类型")
	private String type;
	@Excel(name = "籍贯")
	private String nativePlace;
	@Excel(name = "民族")
	private String nation;
	@Excel(name = "住址")
	private String address;
	@Excel(name = "学校")
	private String school;
	@Excel(name = "学院")
	private String college;
	@Excel(name = "书院")
	private String academy;
	@Excel(name = "系")
	private String series;
	@Excel(name = "专业")
	private String specialty;
	@Excel(name = "班级")
	private String smclass;
	@Excel(name = "学历")
	private String degree;
	@Excel(name = "学位教育类型")
	private String degreetype;
	@Excel(name = "学号")
	private String studentid;
	@Excel(name = "学制")
	private String schoollen;
	@Excel(name = "入学日期",format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date startdate;
	@Excel(name = "毕业日期",format = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date enddate;
	@Excel(name = "邮箱")
	private String email;
	@Excel(name = "工作单位")
	private String workplace;
	@Excel(name = "电话")
	private String phone;

	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getNativePlace() {
		return this.nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}	
	public String getNation() {
		return this.nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public String getSchool() {
		return this.school;
	}
	public void setSchool(String school) {
		this.school = school;
	}	
	public String getCollege() {
		return this.college;
	}
	public void setCollege(String college) {
		this.college = college;
	}	
	public String getAcademy() {
		return this.academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}	
	public String getSeries() {
		return this.series;
	}
	public void setSeries(String series) {
		this.series = series;
	}	
	public String getSpecialty() {
		return this.specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}	

	public String getSmclass() {
		return smclass;
	}
	public void setSmclass(String smclass) {
		this.smclass = smclass;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}	
	public String getDegreetype() {
		return this.degreetype;
	}
	public void setDegreetype(String degreetype) {
		this.degreetype = degreetype;
	}	
	public String getStudentid() {
		return this.studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}	
	public String getSchoollen() {
		return this.schoollen;
	}
	public void setSchoollen(String schoollen) {
		this.schoollen = schoollen;
	}	
	public Date getStartdate() {
		return this.startdate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}	
	public Date getEnddate() {
		return this.enddate;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getWorkplace() {
		return this.workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}	
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}	

}
