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
import com.mpri.aio.schoolmate.model.SmAssociation;
import com.mpri.aio.schoolmate.service.SmAssociationService;

 /**   
 *  
 * @Description:  校友会管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 27 14:36:13 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smAssociation")
public class SmAssociationController extends BaseController {
	
	@Autowired
	private SmAssociationService smAssociationService;
		
	/**
	 * 获取校友会管理列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smAssociation
	* @return
	 */
	@CrossOrigin
	@GetMapping(value = "/list")
	public ResJson<SmAssociation> list(int pageNo,int pageSize,SmAssociation smAssociation) {
		ResJson<SmAssociation>rj = new ResJson<SmAssociation>();
		List<SmAssociation> list =  smAssociationService.loadAllListBy(smAssociation);	
		rj.setData(list);
		return rj;
	}
	
	
	/**
	 * 增加或者更新校友会管理
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smAssociation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@Validated SmAssociation smAssociation){
		smAssociationService.save(smAssociation);							
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}	
	
	/**
	 * 删除校友会管理（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smAssociation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmAssociation smAssociation) {
		smAssociationService.delete(smAssociation);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取校友会管理
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smAssociation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<SmAssociation> get(SmAssociation smAssociation) {
		return new RestResponse<SmAssociation>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smAssociationService.get(smAssociation));
	}
		
}