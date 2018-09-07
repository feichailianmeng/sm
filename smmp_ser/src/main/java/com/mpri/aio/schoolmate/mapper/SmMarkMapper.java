package com.mpri.aio.schoolmate.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mpri.aio.base.mapper.CrudMapper;
import com.mpri.aio.schoolmate.model.SmMark;
import com.mpri.aio.schoolmate.model.SmSchoolmate;


 /**   
 *  
 * @Description:  校友标签管理——DAO
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Fri Aug 24 11:05:42 CST 2018
 * @Version:      v_1.0
 *    
 */
@Mapper
public interface SmMarkMapper extends CrudMapper<SmMark>{

	public List<SmMark> loadMarkByUser(SmSchoolmate schoolmate);
}