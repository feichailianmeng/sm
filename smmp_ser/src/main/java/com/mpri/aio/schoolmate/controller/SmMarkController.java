package com.mpri.aio.schoolmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.ResJson;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.schoolmate.model.SmMark;
import com.mpri.aio.schoolmate.service.SmMarkService;

 /**   
 *  
 * @Description:  校友标签管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Fri Aug 24 11:05:42 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smMark")
public class SmMarkController extends BaseController {
	
	@Autowired
	private SmMarkService smMarkService;
		
	/**
	 * 获取校友标签管理列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smMark
	* @return
	 */
	@CrossOrigin
	@GetMapping(value = "/list")
	public ResJson<SmMark> list(int pageNo,int pageSize,SmMark smMark) {
		ResJson<SmMark>rj = new ResJson<SmMark>();
		List<SmMark> list =  smMarkService.loadAllListBy(smMark);	
		rj.setData(list);
		return rj;
	}
	
	
	/**
	 * 增加或者更新校友标签管理
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smMark
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@Validated SmMark smMark){
		smMarkService.save(smMark);							
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}	
	
	/**
	 * 删除校友标签管理（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smMark
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmMark smMark) {
		smMarkService.delete(smMark);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取校友标签管理
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smMark
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<SmMark> get(SmMark smMark) {
		return new RestResponse<SmMark>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smMarkService.get(smMark));
	}
		
}