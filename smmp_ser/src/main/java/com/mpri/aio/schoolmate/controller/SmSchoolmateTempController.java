package com.mpri.aio.schoolmate.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.mpri.aio.base.controller.BaseController;
import com.mpri.aio.common.exception.ExceptionResult;
import com.mpri.aio.common.page.PageIo;
import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.common.utils.DateUtils;
import com.mpri.aio.common.utils.FileUtils;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;
import com.mpri.aio.schoolmate.service.SmSchoolmateTempService;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * 
 * @Description: 校友中间表管理——Controller
 * @Author: LZQ
 * @project AIO
 * @CreateDate: Tue Aug 28 16:24:33 CST 2018
 * @Version: v_1.0
 * 
 */
@RestController
@RequestMapping("/sys/smSchoolmateTemp")
public class SmSchoolmateTempController extends BaseController {

	@Autowired
	private SmSchoolmateTempService smSchoolmateTempService;

	/**
	 * 获取校友中间表管理列表
	 * <p>
	 * Title: list
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param smSchoolmateTemp
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/list")
	public PageInfo<SmSchoolmateTemp> list(int pageNo, int pageSize, SmSchoolmateTemp smSchoolmateTemp) {
		PageIo<SmSchoolmateTemp> pageInfo = smSchoolmateTempService.loadByPage(pageNo, pageSize, smSchoolmateTemp);
		return pageInfo;
	}

	/**
	 * 增加或者更新校友中间表管理
	 * <p>
	 * Title: add
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param smSchoolmateTemp
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/save")
	public RestResponse<String> save(@RequestBody SmSchoolmateTemp smSchoolmateTemp) {
		smSchoolmateTempService.save(smSchoolmateTemp);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "保存成功！", "");
	}

	/**
	 * 删除校友中间表管理（逻辑删除）
	 * <p>
	 * Title: delete
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param smSchoolmateTemp
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/delete")
	public RestResponse<String> delete(SmSchoolmateTemp smSchoolmateTemp) {
		smSchoolmateTempService.delete(smSchoolmateTemp);
		return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "删除成功！", "");
	}

	/**
	 * 获取校友中间表管理
	 * <p>
	 * Title: get
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param smSchoolmateTemp
	 * @return
	 */
	@CrossOrigin
	@PostMapping(value = "/get")
	public RestResponse<SmSchoolmateTemp> get(SmSchoolmateTemp smSchoolmateTemp) {
		return new RestResponse<SmSchoolmateTemp>(ExceptionResult.REQUEST_SUCCESS, "获取成功！",
				smSchoolmateTempService.get(smSchoolmateTemp));
	}


	/**
	 * uncheck 导入
	 * <p>
	 * Title: downloadtemplate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param response
	 * @param request
	 */
	@CrossOrigin
	@PostMapping(value = "/importsmtempdata")
	public RestResponse<String> importsmtempdata(MultipartFile file,HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String newFilName = String.valueOf(new Date().getTime()) + "." + fileName.substring(fileName.lastIndexOf(".") + 1); /* 更改文件名 */
        String resfillPath = DateUtils.getDate();
        String filePath = request.getSession().getServletContext().getRealPath(resfillPath + "/");
		ImportParams importParams = new ImportParams();
		try {
			FileUtils.uploadFile(file.getBytes(), filePath, newFilName);
			List<SmSchoolmateTemp> objects = ExcelImportUtil.importExcel(new File(filePath+newFilName), SmSchoolmateTemp.class,
					importParams);
			smSchoolmateTempService.importSmTempData(objects);
			return new RestResponse<String>(ExceptionResult.REQUEST_SUCCESS, "导入成功！", "");
		} catch (Exception e) {
			e.printStackTrace();
			return new RestResponse<String>(ExceptionResult.SYS_ERROR, "导入失败！", "");
		}
	}

}