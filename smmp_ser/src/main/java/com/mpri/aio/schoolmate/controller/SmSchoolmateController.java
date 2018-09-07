package com.mpri.aio.schoolmate.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.PageIo;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.model.SmSchoolmateInfo;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;
import com.mpri.aio.schoolmate.service.SmAddressService;
import com.mpri.aio.schoolmate.service.SmEducationService;
import com.mpri.aio.schoolmate.service.SmMarkService;
import com.mpri.aio.schoolmate.service.SmSchoolmateService;
import com.mpri.aio.schoolmate.utils.ExcelUtil;
import com.mpri.aio.schoolmate.utils.SchoolmateExportHandler;
import com.mpri.aio.schoolmate.utils.SchoolmateInfoHander;

 /**   
 *  
 * @Description:  校友管理-校友表——Controller
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 20 10:32:38 CST 2018
 * @Version:      v_1.0
 *    
 */
@RestController
@RequestMapping("/sys/smSchoolmate")
public class SmSchoolmateController extends BaseController {
	
	@Autowired
	private SmSchoolmateService smSchoolmateService;
	@Autowired
	private SmAddressService smAddressService;	
	@Autowired
	private SmMarkService smMarkService;	
	@Autowired
	private SmEducationService smEducationService; 
	
	/**
	 * 获取校友管理-校友表列表
	* <p>Title: list</p>  
	* <p>Description: </p>  
	* @param pageNo
	* @param pageSize
	* @param smSchoolmate
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public PageInfo<SmSchoolmate> list(int pageNo,int pageSize, SmSchoolmateInfo smSchoolmateInfo) {
		SmSchoolmate smSchoolmate = SchoolmateInfoHander.covertSmData(smSchoolmateInfo);
		PageIo<SmSchoolmate> pageInfo =  smSchoolmateService.loadByPage(pageNo,pageSize,smSchoolmate);							
		return pageInfo;
	}
	
	/**
	 * 增加或者更新校友管理-校友表
	* <p>Title: add</p>  
	* <p>Description: </p>  
	* @param smSchoolmate
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<SmSchoolmate> save(@RequestBody SmSchoolmate smSchoolmate){
		smSchoolmateService.saveSmAndAddrAndUser(smSchoolmate);
		return new RestResponse<SmSchoolmate>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", smSchoolmate);
	}	
	
	/**
	 * 更新校友标签
	* @param smSchoolmate
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/saveSchoolmateMark")
	public RestResponse<SmSchoolmate> saveSchoolmateMark(@RequestBody SmSchoolmate smSchoolmate){
		smSchoolmateService.saveUserMark(smSchoolmate);
		return new RestResponse<SmSchoolmate>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", smSchoolmate);
	}
	
	/**
	 * 删除校友管理-校友表（逻辑删除）
	* <p>Title: delete</p>  
	* <p>Description: </p>  
	* @param smSchoolmate
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmSchoolmate smSchoolmate) {
		smSchoolmateService.delete(smSchoolmate);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}
	
	
	
	
	/**
	 * 获取校友管理-校友表
	* <p>Title: get</p>  
	* <p>Description: </p>  
	* @param smSchoolmate
	* @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<SmSchoolmate> get(SmSchoolmate smSchoolmate) {
		SmSchoolmate res = smSchoolmateService.get(smSchoolmate);
		SmAddress smAddress = new SmAddress();
		smAddress.setType(SmAddress.IS_NATION_PLACE);
		smAddress.setSysUserId(res.getSysUserId());
		smAddress = smAddressService.getNationBySysUser(smAddress);
		res.setSmAddress(smAddress);
		
		SmEducation smEducation = new SmEducation();
		smEducation.setSysUserId(res.getSysUserId());
		smEducation =smEducationService.get(smEducation);
		res.setSmEducation(smEducation);
		
		res.setMarkList(smMarkService.getMarkByUser(res));
		return new RestResponse<SmSchoolmate>(ExceptionResult.REQUEST_SUCCESS, "获取成功！", res);
	}
	
	/**
     * 导出
    * <p>Title: get</p>  
    * <p>Description: </p>  
    * @param smSchoolmate
    * @return
     */
    @CrossOrigin
    @PostMapping(value = "/exportsmDatas")
    public void exportsmDatas(HttpServletResponse response, SmSchoolmateInfo smSchoolmateInfo) {
    	SmSchoolmate smSchoolmate = SchoolmateInfoHander.covertSmData(smSchoolmateInfo);;
    	List<SmSchoolmate> list = smSchoolmateService.loadInfoAllListBy(smSchoolmate);
    	List<SmSchoolmate> exportlist = SchoolmateExportHandler.smhander(list);
        //导出操作
        try {
			ExcelUtil.exportExcel(exportlist,"校友信息","sheet1",SmSchoolmate.class,"xiaoyou.xls",response);
		} catch (Exception e) {
//			return  new RestResponse<String>(ExceptionResult.SYS_ERROR, "导出失败！",e.getMessage()); 
		}
//        return  new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "导出成功！", "");
    }
	
	
	/**
     * 校友比较-校友表
    * <p>Title: get</p>  
    * <p>Description: </p>  
    * @param smSchoolmate
    * @return
     */
    @CrossOrigin
    @PostMapping(value = "/commper")
    public void commper() {
        smSchoolmateService.commper();
    }
    
    /**
     * 校友合并-校友表
    * <p>Title: get</p>  
    * <p>Description: </p>  
    * @param smSchoolmate
    * @return
     */
    @CrossOrigin
    @PostMapping(value = "/mager")
    public void mager(String schoolmateId, String smSchoolmateTempId) {
        SmSchoolmate schoolmate = new SmSchoolmate();
        SmSchoolmateTemp  smSchoolmateTemp  = new SmSchoolmateTemp();
        schoolmate.setId(schoolmateId);
        smSchoolmateTemp.setId(smSchoolmateTempId);
        smSchoolmateService.mager(schoolmate, smSchoolmateTemp);
    }
	
}