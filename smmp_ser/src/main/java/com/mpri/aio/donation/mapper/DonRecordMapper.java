package com.mpri.aio.donation.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.mpri.aio.base.mapper.CrudMapper;
import com.mpri.aio.donation.model.DonRecord;


 /**   
 *  
 * @Description:  捐赠记录管理——DAO
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:09:37 CST 2018
 * @Version:      v_1.0
 *    
 */
@Mapper
public interface DonRecordMapper extends CrudMapper<DonRecord>{

	
}