package com.mpri.aio.schoolmate.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.service.SmAddressService;

 /**   
 *  
 * @Description:  校友管理-校友地址管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 20 10:21:32 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smAddress")
public class SmAddressController extends BaseController {
	
	@Autowired
	private SmAddressService smAddressService;
		
	/**
	 * 获取校友管理-校友校园经历表列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smAddress
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/loadAllListBy")
	public RestResponse<List<SmAddress>> list(SmAddress smAddress) {
		smAddress.setType(SmAddress.NO_NATION_PLACE);
		List<SmAddress> smAddresses =  smAddressService.loadAllListBy(smAddress);							
		return new RestResponse<List<SmAddress>>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smAddresses);
	}
	
	
	/**
	 * 增加或者更新校友管理-校友校园经历表
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smAddress
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<SmAddress> save(@RequestBody SmAddress smAddress){
		smAddress.setType(SmAddress.NO_NATION_PLACE);
		smAddressService.save(smAddress);	
		return new RestResponse<SmAddress>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", smAddress);
	}	
	
	
	
	/**
	 * 删除校友管理-校友通讯地址（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smAddress
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmAddress smAddress) {
		smAddressService.delete(smAddress);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取校友管理-校友校园经历表
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smAddress
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public SmAddress get(SmAddress smAddress) {
		return smAddressService.get(smAddress);
	}
		
}