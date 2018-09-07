package com.mpri.aio.schoolmate.utils;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.github.pagehelper.util.StringUtil;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.model.SmContact;
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.system.init.InitCache;
import com.mpri.aio.system.model.SysArea;
import com.mpri.aio.system.model.SysDict;
import com.mpri.aio.system.model.SysOrg;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;

/**
 * 校友导出数据处理
* <p>Title: SchoolmateImportHandler</p>  
* <p>Description: </p>  
* @author syp  
* @date 2018年9月6日
 */
public class SchoolmateExportHandler{

	static Map<String,SysDict> dictBaseCache  = InitCache.dictBaseCache;
	static Map<String,SysOrg> orgBaseCache= InitCache.orgBaseCache;
	static Map<String,SysArea> areaBaseCache= InitCache.areaBaseCache;
	/**
	 * 导出数据转换
	* <p>Title: smhander</p>  
	* <p>Description: </p>  
	* @param list
	* @return
	 */
	public static List<SmSchoolmate> smhander(List<SmSchoolmate> list) {
		for (SmSchoolmate smSchoolmate : list) {
			smSchoolmate.setSex(setFormatDictValue(smSchoolmate.getSex()));
			smSchoolmate.setType(setFormatDictValue(smSchoolmate.getType()));
			smSchoolmate.setCardType(setFormatDictValue(smSchoolmate.getCardType()));
			smSchoolmate.setNation(setFormatDictValue(smSchoolmate.getNation()));
			
			SmAddress smAddress = smSchoolmate.getSmAddress();
			smAddress.setCity(setFormatAreaValue(smAddress.getCity()));
			smAddress.setIspost(setFormatDictValue(smAddress.getIspost()));
			smAddress.setCountry(setFormatAreaValue(smAddress.getCountry()));
			smAddress.setProvince(setFormatAreaValue(smAddress.getProvince()));
			
			SmContact smContact = smSchoolmate.getSmContact();
			smContact.setType(setFormatDictValue(smContact.getType()));
			
			SmEducation smEducation = smSchoolmate.getSmEducation();
			smEducation.setSchool(setFormatOrgValue(smEducation.getSchool()));
			smEducation.setCollege(setFormatOrgValue(smEducation.getCollege()));
			smEducation.setAcademy(setFormatOrgValue(smEducation.getAcademy()));
			smEducation.setSeries(setFormatOrgValue(smEducation.getSeries()));
			smEducation.setSpecialty(setFormatOrgValue(smEducation.getSpecialty()));
			smEducation.setClassName(setFormatOrgValue(smEducation.getClassName()));
			smEducation.setDegree(setFormatDictValue(smEducation.getDegree()));
			smEducation.setDegreetype(setFormatDictValue(smEducation.getDegreetype()));
			smEducation.setSchoollen(setFormatDictValue(smEducation.getSchoollen()));
		}
		return list;
	}
	
	private static String setFormatDictValue(String value) {
		if(!StringUtil.isEmpty(value)) {
			return dictBaseCache.get(value).getLabel();
		}
		return "";
	}

	private static String setFormatOrgValue(String value) {
		if(!StringUtil.isEmpty(value)) {
			return orgBaseCache.get(value).getName();
		}
		return "";
	}
	
	private static String setFormatAreaValue(String value) {
		if(!StringUtil.isEmpty(value)) {
			return areaBaseCache.get(value).getName();
		}
		return "";
	}
}
