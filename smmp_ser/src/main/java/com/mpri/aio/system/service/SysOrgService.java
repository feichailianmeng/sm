package com.mpri.aio.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.schoolmate.utils.DatasCovert;
import com.mpri.aio.schoolmate.vo.FormSelectDatas;
import com.mpri.aio.system.mapper.SysOrgMapper;
import com.mpri.aio.system.model.SysOrg;

 /**   
 *  
 * @Description:  机构——Service
 * @Author:       SYP
 * @project 	  AIO 
 * @CreateDate:   Fri Aug 10 15:38:06 CST 2018
 * @Version:      v_1.0
 *    
 */
@Service
public class SysOrgService extends CrudService<SysOrgMapper, SysOrg>  {

	private static String ROOT_ORG = "root";
	
	/**
	 * FormSelectDatas 数据格式(住址)
	* <p>Title: getFormSelectDatas</p>  
	* <p>Description: </p>  
	* @param sysAreaList
	* @return
	 */
	public FormSelectDatas getAllFormSelectDatas(SysOrg sysOrg){
		List<FormSelectDatas> formSelectDatas = new ArrayList<FormSelectDatas>();
		List<SysOrg> sysOrgs = this.loadAllListBy(sysOrg);
		for(SysOrg org : sysOrgs) {
			formSelectDatas.add(new FormSelectDatas(org.getId(),org.getParentId(),org.getName())); 
		}
		FormSelectDatas res = DatasCovert.setRootFormSelectDatas(formSelectDatas, ROOT_ORG);
		return res;
	}
}