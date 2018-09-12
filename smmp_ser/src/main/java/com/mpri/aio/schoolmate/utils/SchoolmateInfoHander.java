package com.mpri.aio.schoolmate.utils;

import com.github.pagehelper.util.StringUtil;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.model.SmContact;
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.model.SmSchoolmateInfo;

/**
 * 校友筛选数据处理
* <p>Title: SchoolmateInfoHander</p>  
* <p>Description: </p>  
* @author syp  
* @date 2018年9月6日
 */
public class SchoolmateInfoHander {

	/**
	 * 数据转换
	* <p>Title: covertSmData</p>  
	* <p>Description: </p>  
	* @param smInfo
	* @return
	 */
	public static SmSchoolmate covertSmData(SmSchoolmateInfo smInfo) {
		SmSchoolmate sm =  new SmSchoolmate();
		sm.setName(setProperty(smInfo.getName()));
		sm.setSex(setProperty(smInfo.getSex()));
		sm.setCardType(setProperty(smInfo.getCardType()));
		sm.setCardNum(setProperty(smInfo.getCardNum()));
		sm.setType(setProperty(smInfo.getType()));
		sm.setType(setProperty(smInfo.getType()));
		
		SmAddress smAddress = getSmAddressBy(smInfo);
		if(null != smAddress) {
			smAddress.setAreaId(setProperty(smInfo.getAreaId()));			
		}

		
		SmEducation smEducation = getSmEducationBy(smInfo);
		if(null != smEducation) {
			smEducation.setOrgId(setProperty(smInfo.getOrgId()));
			smEducation.setDegree(setProperty(smInfo.getDegree()));
			smEducation.setDegreetype(setProperty(smInfo.getDegreetype()));
			smEducation.setSchoollen(setProperty(smInfo.getSchoollen()));
			smEducation.setStudentid(setProperty(smInfo.getStudentid()));
			smEducation.setQueryBeginDate(null == smInfo.getQueryBeginDate()? null:smInfo.getQueryBeginDate());
			smEducation.setQueryEndDate(null == smInfo.getQueryEndDate()? null:smInfo.getQueryEndDate());
		}
		
		SmContact smContact = getSmContactBy(smInfo);
		if(null != smContact) {
			smContact.setType(setProperty(smInfo.getContactType()));
			smContact.setContact(setProperty(smInfo.getContact()));			
		}

		sm.setSmEducation(smEducation);
		sm.setSmContact(smContact);
		sm.setSmAddress(smAddress);
		return sm;
	}
	/**
	 * 数据处理
	* <p>Title: setProperty</p>  
	* <p>Description: </p>  
	* @param obj
	* @return
	 */
	private static String setProperty (String val) {
		if(!StringUtil.isEmpty(val)) {
			return val;
		}
		return "";
	}
	
	/**
	 * 数据转换
	 */
	private static SmAddress getSmAddressBy(SmSchoolmateInfo smInfo) {
		if(StringUtil.isNotEmpty(smInfo.getAreaId())) {
			return new SmAddress();
		}
		return null;
	}
	/**
	 * 数据转换
	 */
	private static SmContact getSmContactBy(SmSchoolmateInfo smInfo) {
		if(StringUtil.isNotEmpty(smInfo.getContactType()) || StringUtil.isNotEmpty(smInfo.getContact())) {
			return new SmContact();
		}
		return null;
	}
	/**
	 * 数据转换
	 */
	private static SmEducation getSmEducationBy(SmSchoolmateInfo smInfo) {
		if(StringUtil.isNotEmpty(smInfo.getStudentid()) ||
				StringUtil.isNotEmpty(smInfo.getSchoollen()) ||
				StringUtil.isNotEmpty(smInfo.getDegree()) ||
				StringUtil.isNotEmpty(smInfo.getDegreetype()) ||
				StringUtil.isNotEmpty(smInfo.getOrgId()) ||
				null != smInfo.getQueryEndDate() ||
				null != smInfo.getQueryBeginDate()) {
			return new SmEducation();
		}
		return null;
	}
}
