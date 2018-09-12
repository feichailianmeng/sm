package com.mpri.aio.schoolmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.PageIo;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.schoolmate.model.SmContact;
import com.mpri.aio.schoolmate.service.SmContactService;

 /**   
 *  
 * @Description:  校友管理-联系方式——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Tue Aug 21 11:21:24 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smContact")
public class SmContactController extends BaseController {
	
	@Autowired
	private SmContactService smContactService;
		
	/**
	 * 获取校友管理-荣誉列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smContact
	* @return
	 */
	@CrossOrigin
	@GetMapping(value = "/list")
	public PageInfo<SmContact> list(int pageNo,int pageSize,SmContact smContact) {
		PageIo<SmContact> pageInfo =  smContactService.loadByPage(pageNo,pageSize,smContact);							
		return pageInfo;
	}
	
	/**
	 * 获取校友管理-荣誉列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param smContact
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/loadAllListBy")
	public RestResponse<List<SmContact>> list(SmContact smContact) {
		List<SmContact>  smContacts=  smContactService.loadAllListBy(smContact);							
		return new RestResponse<List<SmContact>>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smContacts);
	}
//	/**
//	 * 增加或者更新校友管理-荣誉
//	* <p>Title: add</p>  
//	* <p>Description: </p>  
//	* @param smContact
//	* @return
//	 */
//	@CrossOrigin
//	@PostMapping(value = "/save")
//	public String save(@RequestBody SmContact [] smContact){
//		for( int i=0; i<smContact.length ; i++)
//		{
//		smContactService.save(smContact[i]);	
//		}
//		return SUCCESS;
//	}	
	
	/**
	 * 增加或者更新校友管理-荣誉
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smContact
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<SmContact> save( SmContact smContact){
		smContactService.save(smContact);
		
		return new RestResponse<SmContact>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", smContact);
	}	
	
	/**
	 * 删除校友管理-荣誉（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smContact
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmContact smContact) {
		smContactService.delete(smContact);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取校友管理-荣誉
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smContact
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public SmContact get(SmContact smContact) {
		return smContactService.get(smContact);
	}
		
}