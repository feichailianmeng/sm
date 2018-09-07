package com.mpri.aio.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.schoolmate.utils.DatasCovert;
import com.mpri.aio.schoolmate.vo.FormSelectDatas;
import com.mpri.aio.system.mapper.SysAreaMapper;
import com.mpri.aio.system.model.SysArea;


/**
* .地区CRUD实现
* @author lzq
* @date 2018年8月1日
*/
@Service
public class SysAreaService extends CrudService<SysAreaMapper, SysArea>  {	

	private static String ROOT_AREA = "root";
	
	/**
	 * 根据code获取对象
	 * @return 
	 */
	public SysArea getSysAreaByCode(String code) {
		SysArea area = new SysArea();
		area.setCode(code);
		return mapper.getSysAreaByCode(area);
	}
	
	/**
	 * .根据父获取所有子
	 * @param sysArea
	 * @return
	 */
	public List<SysArea> loadChildrenByParent(SysArea sysArea){
		 List<SysArea> list =mapper.loadChildrenByParent(sysArea);
		 return list;
		
	}
	
	/**
	 * FormSelectDatas 数据格式(籍贯)
	* <p>Title: getFormSelectDatas</p>  
	* <p>Description: </p>  
	* @param sysAreaList
	* @return
	 */
	public FormSelectDatas getFormSelectDatas(SysArea sysArea){
		List<FormSelectDatas> formSelectDatas = new ArrayList<FormSelectDatas>();
		List<SysArea> sysAreaList = this.loadAllListBy(sysArea);
		for(SysArea Area : sysAreaList) {
			formSelectDatas.add(new FormSelectDatas(Area.getId(),Area.getParentId(),Area.getName())); 
		}
		FormSelectDatas res = DatasCovert.setRootFormSelectData(formSelectDatas, ROOT_AREA);
		return res;
	}
	
	/**
	 * FormSelectDatas 数据格式(住址)
	* <p>Title: getFormSelectDatas</p>  
	* <p>Description: </p>  
	* @param sysAreaList
	* @return
	 */
	public FormSelectDatas getAllFormSelectDatas(SysArea sysArea){
		List<FormSelectDatas> formSelectDatas = new ArrayList<FormSelectDatas>();
		List<SysArea> sysAreaList = this.loadAllListBy(sysArea);
		for(SysArea Area : sysAreaList) {
			formSelectDatas.add(new FormSelectDatas(Area.getId(),Area.getParentId(),Area.getName())); 
		}
		FormSelectDatas res = DatasCovert.setRootFormSelectDatas(formSelectDatas, ROOT_AREA);
		return res;
	}
}