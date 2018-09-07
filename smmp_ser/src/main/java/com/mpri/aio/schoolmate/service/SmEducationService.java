package com.mpri.aio.schoolmate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.schoolmate.mapper.SmEducationMapper;
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.system.model.SysOrg;
import com.mpri.aio.system.service.SysDictService;
import com.mpri.aio.system.service.SysOrgService;

import tk.mybatis.mapper.util.StringUtil;

 /**   
 *  
 * @Description:  校友管理-教育经历——Service
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 20 10:53:23 CST 2018
 * @Version:      v_1.0
 *    
 */
@Service
public class SmEducationService extends CrudService<SmEducationMapper, SmEducation>  {

	@Autowired
	private SysOrgService sysOrgService;
	
	
	/**
	 *  根据所选机构ID 
	 */
	public List<SysOrg> getParentOrgs (String orgId) {
		//
		SysOrg sysOrg = new SysOrg();
		//获取所有机构
		List<SysOrg>  allList = sysOrgService.loadAllListBy(sysOrg);
		sysOrg.setId(orgId);
		List<SysOrg>  selectOrgs = new ArrayList<SysOrg>();

		sysOrg = sysOrgService.get(sysOrg);

		//获取所有父级组
		String [] Ids = sysOrg.getParentIds().split(",");
		
		for(int i=0;i<Ids.length;i++) {
			for(SysOrg org : allList) {
				if(org.getId().equals(Ids[i])) {
					selectOrgs.add(org);
				}
			}
		}
		selectOrgs.add(sysOrg);
		return selectOrgs;
	}
	
	/**
	 * 将机构数据转变为校友教育经历数据
	* <p>Title: saveOrgs</p>  
	* <p>Description: </p>  
	* @param sysOrgs
	* @param smEducation
	* @return
	 */
	public void saveOrgs(SmEducation smEducation) {
		List<SysOrg> sysOrgs = getParentOrgs(smEducation.getOrgId());
		for (SysOrg sysOrg : sysOrgs) {
			if(SmEducation.SCHOOL.equals(sysOrg.getType())) {
				smEducation.setSchool(sysOrg.getId());
			}else if(SmEducation.BIG_COLLEGE.equals(sysOrg.getType())) {
				smEducation.setAcademy(sysOrg.getId());
			}else if(SmEducation.COLLEGE.equals(sysOrg.getType())) {
				smEducation.setCollege(sysOrg.getId());
			}else if(SmEducation.DEPARTMENT.equals(sysOrg.getType())) {
				smEducation.setSeries(sysOrg.getId());
			}else if(SmEducation.MAJOR.equals(sysOrg.getType())) {
				smEducation.setSpecialty(sysOrg.getId());
			}else if(SmEducation.CLASS.equals(sysOrg.getType())) {
				smEducation.setClassName(sysOrg.getId());
			}
		}
		
		this.save(smEducation);
	}
	
	/**
	 * 设置机构ＩＤ
	* <p>Title: setOrgId</p>  
	* <p>Description: </p>  
	* @param smEducations
	* @return
	 */
	public List<SmEducation> setAllOrgId (List<SmEducation> smEducations){
		for (SmEducation smEducation : smEducations) {
			if(!StringUtil.isEmpty(smEducation.getClassName())) {
				smEducation.setOrgId(smEducation.getClassName());
				smEducation.setOrgName(getOrgNameById(smEducation.getClassName()));
			}else if(!StringUtil.isEmpty(smEducation.getSpecialty())) {
				smEducation.setOrgId(smEducation.getSpecialty());
				smEducation.setOrgName(getOrgNameById(smEducation.getSpecialty()));
			}else if(!StringUtil.isEmpty(smEducation.getSeries())) {
				smEducation.setOrgId(smEducation.getSeries());
				smEducation.setOrgName(getOrgNameById(smEducation.getSeries()));
			}else if(!StringUtil.isEmpty(smEducation.getCollege())) {
				smEducation.setOrgId(smEducation.getCollege());
				smEducation.setOrgName(getOrgNameById(smEducation.getCollege()));
			}else if(!StringUtil.isEmpty(smEducation.getAcademy())) {
				smEducation.setOrgId(smEducation.getAcademy());
				smEducation.setOrgName(getOrgNameById(smEducation.getAcademy()));
			}else if(!StringUtil.isEmpty(smEducation.getSchool())) {
				smEducation.setOrgId(smEducation.getSchool());
				smEducation.setOrgName(getOrgNameById(smEducation.getSchool()));
			}
		}
		return smEducations;
	}
	
	/**
	 * 获取机构名称
	* <p>Title: setOrgName</p>  
	* <p>Description: </p>  
	* @param orgId
	* @return
	 */
	public String getOrgNameById(String orgId) {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(orgId);
		return sysOrgService.get(sysOrg).getName();
	}
}