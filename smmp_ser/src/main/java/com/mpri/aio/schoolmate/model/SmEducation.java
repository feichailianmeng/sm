package com.mpri.aio.schoolmate.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mpri.aio.base.model.DataEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**   
*  
* @Description:  校友管理-教育经历
* @Author:       LZQ
* @project       AIO   
* @CreateDate:   Mon Aug 20 10:53:23 CST 2018
* @Version:      v_1.0
*    
*/
@ExcelTarget("smEducation")
public class SmEducation extends DataEntity<SmEducation> {

	private static final long serialVersionUID = 1534733603868L;
	
	public static String SCHOOL = "SCHOOL"; //校级
	public static String SCHOOL_AREA = "SCHOOL_AREA"; //校区
	public static String BIG_COLLEGE = "BIG_COLLEGE"; //书院级
	public static String COLLEGE = "COLLEGE"; // 学院级
	public static String MAJOR = "MAJOR"; //专业级
	public static String DEPARTMENT = "DEPARTMENT"; //系级
	public static String CLASS = "CLASS"; //班级

	
	
	private String sysUserId;
	private String sysUserUsername;
	@Excel(name ="学校")
	private String school;
	@Excel(name ="学院")
	private String college;
	@Excel(name ="书院")
	private String academy;
	@Excel(name ="系")
	private String series;
	@Excel(name ="专业")
	private String specialty;
	@Excel(name ="班级")
	private String className;
	@Excel(name ="学历")
	private String degree;
	@Excel(name ="学位类型")
	private String degreetype;
	@Excel(name ="学号")
	private String studentid;
	@Excel(name ="学制")
	private String schoollen;
	@Excel(name ="入学时间",format ="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date startdate;
	@Excel(name ="毕业时间",format ="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date enddate;
	private String orgId;
	private String orgName;
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getSysUserId() {
		return this.sysUserId;
	}
	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}	
	public String getSysUserUsername() {
		return this.sysUserUsername;
	}
	public void setSysUserUsername(String sysUserUsername) {
		this.sysUserUsername = sysUserUsername;
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
	public String getClassName() {
		return this.className;
	}
	public void setClassName(String className) {
		this.className = className;
	}	
	public String getDegree() {
		return this.degree;
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


}
