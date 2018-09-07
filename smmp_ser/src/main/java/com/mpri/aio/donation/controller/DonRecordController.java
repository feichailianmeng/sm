package com.mpri.aio.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.mpri.aio.donation.model.DonRecord;
import com.mpri.aio.donation.service.DonRecordService;

 /**   
 *  
 * @Description:  捐赠记录管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:09:37 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/donRecord")
public class DonRecordController extends BaseController {
	
	@Autowired
	private DonRecordService donRecordService;
		
	/**
	 * 获取捐赠记录管理列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param donRecord
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public PageInfo<DonRecord> list(int pageNo,int pageSize,DonRecord donRecord) {
		PageIo<DonRecord> pageInfo =  donRecordService.loadByPage(pageNo,pageSize,donRecord);							
		return pageInfo;
	}
	
	
	/**
	 * 增加或者更新捐赠记录管理
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param donRecord
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@Validated DonRecord donRecord){
		donRecordService.save(donRecord);							
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}	
	
	/**
	 * 删除捐赠记录管理（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param donRecord
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(DonRecord donRecord) {
		donRecordService.delete(donRecord);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取捐赠记录管理
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param donRecord
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<DonRecord> get(DonRecord donRecord) {
		return new RestResponse<DonRecord>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", donRecordService.get(donRecord));
	}
		
}