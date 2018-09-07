package com.mpri.aio.donation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.ResJson;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.donation.model.DonDonationFile;
import com.mpri.aio.donation.service.DonDonationFileService;

 /**   
 *  
 * @Description:  捐赠项目附件——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:34:58 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/donDonationFile")
public class DonDonationFileController extends BaseController {
	
	@Autowired
	private DonDonationFileService donDonationFileService;
		
	/**
	 * 获取捐赠项目附件列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param donDonationFile
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public ResJson<DonDonationFile> list(DonDonationFile donDonationFile) {
		ResJson<DonDonationFile>rj = new ResJson<DonDonationFile>();
		List<DonDonationFile> list =  donDonationFileService.loadAllListBy(donDonationFile);	
		rj.setData(list);
		return rj;
	}
	
	
	/**
	 * 增加或者更新捐赠项目附件
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param donDonationFile
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@Validated DonDonationFile donDonationFile){
		donDonationFileService.save(donDonationFile);							
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}	
	
	/**
	 * 删除捐赠项目附件（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param donDonationFile
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(DonDonationFile donDonationFile) {
		donDonationFileService.delete(donDonationFile);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取捐赠项目附件
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param donDonationFile
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<DonDonationFile> get(DonDonationFile donDonationFile) {
		return new RestResponse<DonDonationFile>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", donDonationFileService.get(donDonationFile));
	}
		
}