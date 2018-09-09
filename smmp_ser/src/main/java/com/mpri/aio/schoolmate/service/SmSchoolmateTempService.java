package com.mpri.aio.schoolmate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.common.page.PageIo;
import com.mpri.aio.schoolmate.mapper.SmSchoolmateTempMapper;
import com.mpri.aio.schoolmate.model.ExcelMode;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;
import com.mpri.aio.schoolmate.utils.AttributeUtils;
import com.mpri.aio.schoolmate.utils.ExportExcelUtils;
import com.mpri.aio.system.utils.MailUtil;

import freemarker.template.Template;

 /**   
 *  
 * @Description:  校友中间表管理——Service
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Tue Aug 28 16:24:33 CST 2018
 * @Version:      v_1.0
 *    
 */
@Service
public class SmSchoolmateTempService extends CrudService<SmSchoolmateTempMapper, SmSchoolmateTemp>  {

    
    @Autowired
    private SmSchoolmateService smSchoolmateService;
    
    @Autowired
    private MailUtil mailUtil;
	
    private static Logger logger = LoggerFactory.getLogger(SmSchoolmateTempService.class);
    
    public void exportExcle(HttpServletResponse response,SmSchoolmate smSchoolmate)
    {
        int startNum = 0;
        int size = 100;
        int pageSize = 0;
        // 获取导出数据
        List<List<Object>> rows = new ArrayList();
        // 将导出的数据放到列表里面
        do
        {
            PageIo<SmSchoolmate> pageIo = smSchoolmateService.loadByPage(startNum, size, smSchoolmate);
            List<SmSchoolmate> list = pageIo.getList();
            pageSize = pageIo.getSize();
            for (int i = 0; i < pageSize; i++)
            {
                SmSchoolmate item = list.get(i);
                List<Object> row = new ArrayList();
                row.add(item.getName());
                row.add(item.getId());
                row.add(item.getUsername());
                rows.add(row);
            }
        }
        while (pageSize == size);

        ExcelMode data = new ExcelMode();
        // 设置文件名(校友数据)
        String fileName = "校友数据";
        data.setName(fileName);

        // 设置表头的信息,可以动态获取
        List<String> titles = new ArrayList();
        titles.add("姓名");
        titles.add("id");
        titles.add("账号");
        data.setTitles(titles);
        data.setRows(rows);
        
        // 浏览器导出
        try
        {
            ExportExcelUtils.exportExcel(response, fileName + ".xlsx", data);
        }
        catch (Exception e)
        {
            logger.info("SmSchoolmateTempService:exportExcle--校友数据导出出错");
        }
    }

    
    /**
     * 导入数据
     * @param wb  校友excle 数据
     */
    /* public SmSchoolmate createSchoolmate(Workbook wb) */
    public void createSchoolmate(XSSFWorkbook wb)
    {
        // 存放表头字段对应 类的属性名
        Map<Integer, String> titlMap = new HashMap<Integer, String>();
        // 一个列对象
        XSSFCell xssfcell = null;
        // 一个行对象
        XSSFRow xssfRow = null;
        // 总列数
        int celNum = 0;
        // 总行数
        int sheetNum = 0;
        Map map = setSchoolMateMap();
        AttributeUtils<SmSchoolmateTemp> attributeUtils = new AttributeUtils();
        // 那到第一个页签的页面，判断是否有内容
        XSSFSheet xssfSheet = wb.getSheetAt(0);
        if (null == xssfSheet)
        {
            return;
        }
        sheetNum = xssfSheet.getLastRowNum();
       
        for (int numSheet = 0; numSheet < sheetNum; numSheet++)
        {
            // 表头不为空，开始处理数据(第一行的数据)
            if (numSheet == 0)
            {
                xssfRow = xssfSheet.getRow(numSheet);
                celNum = xssfRow.getLastCellNum();
                // 遍历表头，拿到对应的列及其对象的属性名
                for (int c = 0; c < celNum; c++)
                {
                    xssfcell = xssfRow.getCell((short) c);
                    String strCel = xssfcell.toString().replaceAll("\\s*", "").replaceAll("_", "");
                    
                    System.out.println("strCel:"+strCel);
                    titlMap.put(c, strCel);
                }
              
            }  // 读取非第一行的正文数据，将每一行的数据存储到对应属性的对象中
            else
            {
                xssfRow = xssfSheet.getRow(numSheet);
                celNum = xssfRow.getLastCellNum();
                SmSchoolmateTemp smSchoolmateTemp = new SmSchoolmateTemp();
                // 读取数据，根据表头放入对应的字段中，保存到数据库中
                for (int i = 0; i < celNum; i++)
                {
                    xssfcell = xssfRow.getCell((short) i);
                    // cell值（属性值）=========得到该列的值，转为String类型，以便于对象中存储
                    String strCel =  String.valueOf(xssfcell.toString());
                    // 属性名==========根据该列序号，在map中对应的key中查找该序号下对应的属性名
                    String titlName = titlMap.get(i).toLowerCase();
                    titlName = map.get(titlName).toString();
                    // 给类赋值
                   attributeUtils.setAttributeValue(smSchoolmateTemp, titlName, strCel);
                }
                //保存
                save(smSchoolmateTemp);
            }
        }
    }
    
    private Map setSchoolMateMap()
    {
        Map map = new HashMap<String, String>();
        map.put("姓名","name");
        map.put("性别","sex");
        map.put("出生日期","birthday");
        map.put("证件号码", "cardNum");
        map.put("证件类型", "cardType");
        map.put("类型", "type");
        map.put("籍贯", "nativePlace");
        map.put("民族", "nation");
        map.put("所在地", "address");
        map.put("学校", "school");
        map.put("学院", "college");
        map.put("书院", "academy");
        map.put("所在系", "series");
        map.put("专业", "specialty");
        map.put("班级", "smclass");
        map.put("学历", "degree");
        map.put("教育类型", "degreetype");
        map.put("学号", "studentid");
        map.put("入学时间", "startdate");
        map.put("电子邮箱", "email");
        map.put("工作单位", "workplace");
        map.put("电话", "phone");
        return map;
    }

    
    /**
     * ss 循环导入
    * <p>Title: importSmTempData</p>  
    * <p>Description: </p>
     */
    @Transactional(readOnly = false)
    public void importSmTempData(List<SmSchoolmateTemp> objects) {
    	for (SmSchoolmateTemp smSchoolmateTemp : objects) {
			this.save(smSchoolmateTemp);
		}
    }
    
	    
    
    /**
     * <p>Description: 数据去重 </p>
     * @param smSchoolmateTemp
     */
    @Transactional(readOnly = false)
    public void delDuplicate(SmSchoolmateTemp smSchoolmateTemp) {
        mapper.delDuplicate(smSchoolmateTemp);
    };
    
    
    /**
     * 邮件发送
     * @param toEmail
     * @param map
     */
    public void cardIssue(String toEmail,Map<String, Object> map) {
    	try {
    		Template emailTemplate =MailUtil.getFtlTemplete("email.ftl");
    		mailUtil.send(toEmail, emailTemplate, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}