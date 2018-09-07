package com.mpri.aio.schoolmate.controller;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mpri.aio.common.response.RestResponse;
import com.mpri.aio.common.utils.DateUtils;
import com.mpri.aio.common.utils.FileUtils;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.service.SmSchoolmateTempService;
import com.mpri.aio.schoolmate.utils.ExcelImportUtils;

/**
* 校友数据级文件上传导入导出
* @author lzq
* @date 2018年8月29日 - 上午11:16:42
*/
@CrossOrigin
@RestController
@RequestMapping("/sys/schoolmate")
public class ExcelController
{
    @Autowired
    SmSchoolmateTempService smSchoolmateTempService;
    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);
    
    /**
     * 导入excel
     * 
     * @param response
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @PostMapping(value = "/importExcle")
    public RestResponse<String> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        String fileName = file.getOriginalFilename();
        String newFilName = String.valueOf(new Date().getTime()) + "." + fileName.substring(fileName.lastIndexOf(".") + 1); /* 更改文件名 */
        String resfillPath = DateUtils.getDate();
        String filePath = request.getSession().getServletContext().getRealPath(resfillPath + "/");

        // 判断文件是否为空
        if (file == null)
        {
            return RestResponse.getInstance(-1, "文件不能为空！！", resfillPath + fileName);
        }
        
        // 验证文件名是否合格
        if (!ExcelImportUtils.validateExcel(fileName))
        {
            return RestResponse.getInstance(-1, "文件必须是excel格式！", resfillPath + fileName);
        }
        // 进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size = file.getSize();
        if (StringUtils.isEmpty(fileName) || size == 0)
        {
            return RestResponse.getInstance(-1, "文件不能为空！", resfillPath + fileName);
        }
        // 批量导入
        try
        {
            InputStream is = file.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(is);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(buf);
            
            smSchoolmateTempService.createSchoolmate(xssfWorkbook);
            
            buf.close();
            
            // 导入完成删除多余的文件
            FileUtils.delFile(filePath + newFilName);
            return RestResponse.getInstance(200, "导入成功", resfillPath + "/" + newFilName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.info("ExcelController:uploadImg--导入校友数据读取文件时出错");
        }
        return RestResponse.getInstance(-1, "导入成功", resfillPath + fileName);
    }

    /**
     * 导出excel
     * @param response
     * @throws Exception
     */
     @CrossOrigin
     @GetMapping(value = "/exportExcle")
     public void exportExcle1(HttpServletResponse response, SmSchoolmate smSchoolmate)
     {
         
         smSchoolmate.setFlag("NORMAL");
         smSchoolmateTempService.exportExcle(response,smSchoolmate);
         
     }
}
