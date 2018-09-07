package com.mpri.aio.schoolmate.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.mpri.aio.base.mapper.CrudMapper;
import com.mpri.aio.schoolmate.model.SmAssociation;


 /**   
 *  
 * @Description:  校友会管理——DAO
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Mon Aug 27 14:36:13 CST 2018
 * @Version:      v_1.0
 *    
 */
@Mapper
public interface SmAssociationMapper extends CrudMapper<SmAssociation>{

	
}