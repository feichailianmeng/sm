package com.mpri.aio.schoolmate.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.mpri.aio.base.mapper.CrudMapper;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;


 /**   
 *  
 * @Description:  校友中间表管理——DAO
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Tue Aug 28 16:24:33 CST 2018
 * @Version:      v_1.0
 *    
 */
@Mapper
public interface SmSchoolmateTempMapper extends CrudMapper<SmSchoolmateTemp>{

	
}