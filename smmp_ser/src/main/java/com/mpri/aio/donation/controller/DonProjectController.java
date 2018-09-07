package com.mpri.aio.donation.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.mpri.aio.donation.model.DonDonationFile;
import com.mpri.aio.donation.model.DonProject;
import com.mpri.aio.donation.service.DonDonationFileService;
import com.mpri.aio.donation.service.DonProjectService;

 /**   
 *  
 * @Description:  捐赠项目管理——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:22:22 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/donProject")
public class DonProjectController extends BaseController {
	
	@Autowired
	private DonProjectService donProjectService;
	
	@Autowired
	private DonDonationFileService donDonationFileService;
		
	/**
	 * 获取捐赠项目管理列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param donProject
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public PageInfo<DonProject> list(int pageNo,int pageSize,DonProject donProject) {
		PageIo<DonProject> pageInfo =  donProjectService.loadByPage(pageNo,pageSize,donProject);							
		return pageInfo;
	}
	
	
	/**
	 * 增加或者更新捐赠项目管理
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param donProject
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(HttpServletRequest request,@Validated DonProject donProject,String files,@Validated DonDonationFile donDonationFile){
		donProjectService.save(donProject);
		donDonationFileService.deleteByProject(donProject);
		if(files.length()>0)
		{
			donDonationFileService.saveProjectFiles(donProject.getId(), files.split(";"));
		}
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！","");
	}	
	
	/**
	 * 删除捐赠项目管理（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param donProject
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(DonProject donProject) {
		donProjectService.delete(donProject);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	/**
	 * 获取捐赠项目管理
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param donProject
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<DonProject> get(DonProject donProject) {
		return new RestResponse<DonProject>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", donProjectService.get(donProject));
	}
		
}