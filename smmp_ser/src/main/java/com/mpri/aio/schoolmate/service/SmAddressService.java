package com.mpri.aio.schoolmate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.schoolmate.mapper.SmAddressMapper;
import com.mpri.aio.schoolmate.model.SmAddress;

 /**   
 *  
 * @Description:  校友管理-校友地址管理——Service
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 20 10:21:32 CST 2018
 * @Version:      v_1.0
 *    
 */
@Service
public class SmAddressService extends CrudService<SmAddressMapper, SmAddress>  {
	
	@Autowired
	private SmAddressMapper mapper;

	/**
	 * 获取籍贯信息
	* <p>Title: getNationBySysUser</p>  
	* <p>Description: </p>  
	* @param smAddress
	* @return
	 */
	public SmAddress getNationBySysUser(SmAddress smAddress) {
		return mapper.getNationBySysUser(smAddress);
	}
}