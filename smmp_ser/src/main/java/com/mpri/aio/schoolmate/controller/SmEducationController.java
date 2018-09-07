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
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.schoolmate.service.SmEducationService;

 /**   
 *  
 * @Description:  校友管理-教育经历——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 20 10:53:23 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smEducation")
public class SmEducationController extends BaseController {
	
	@Autowired
	private SmEducationService smEducationService;
		
	
	/**
	 * 增加或者更新校友管理-校友表
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smEducation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<SmEducation> save(@RequestBody SmEducation smEducation){		
		
		smEducationService.saveOrgs(smEducation);
									
		return new RestResponse<SmEducation>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", smEducation);
	}	
	
	/**
	 * 删除校友管理-校友表（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smEducation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmEducation smEducation) {
		smEducationService.delete(smEducation);
		System.out.println(smEducation);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	
	
	/**
	 * 获取校友管理-校友表
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smEducation
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/loadAllListBy")
	public RestResponse<List<SmEducation>> loadAllListBy(SmEducation smEducation) {
		List<SmEducation> smEducations =  smEducationService.loadAllListBy(smEducation);
		smEducations = smEducationService.setAllOrgId(smEducations);	
		return new RestResponse<List<SmEducation>>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", smEducations);
	}
}