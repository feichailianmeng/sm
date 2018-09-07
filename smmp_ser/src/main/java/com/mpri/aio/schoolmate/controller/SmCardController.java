package com.mpri.aio.schoolmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.PageIo;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.schoolmate.model.SmCard;
import com.mpri.aio.schoolmate.service.SmCardService;

 /**   
 *  
 * @Description:  校友卡管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 22 13:57:05 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smCard")
public class SmCardController extends BaseController {
	
	@Autowired
	private SmCardService smCardService;
		
	/**
	 * 获取校友卡管理列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smCard
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public PageInfo<SmCard> list(int pageNo,int pageSize,SmCard smCard) {
		PageIo<SmCard> pageInfo =  smCardService.loadByPage(pageNo,pageSize,smCard);							
		return pageInfo;
	}
	
	
	/**
	 * 增加或者更新校友卡管理
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smCard
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@Validated SmCard smCard){
		smCardService.save(smCard);							
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}	
	
	/**
	 * 删除校友卡管理（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smCard
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmCard smCard) {
		smCardService.delete(smCard);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取校友卡管理
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smCard
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<SmCard> get(SmCard smCard) {
		return new RestResponse<SmCard>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smCardService.get(smCard));
	}
		
}