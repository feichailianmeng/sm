package com.mpri.aio.donation.mapper;
import org.apache.ibatis.annotations.Mapper;

import com.mpri.aio.base.mapper.CrudMapper;
import com.mpri.aio.donation.model.DonDonationFile;
import com.mpri.aio.donation.model.DonProject;


 /**   
 *  
 * @Description:  捐赠项目附件——DAO
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:34:58 CST 2018
 * @Version:      v_1.0
 *    
 */
@Mapper
public interface DonDonationFileMapper extends CrudMapper<DonDonationFile>{

	void deleteByProjectId(DonProject donProject);

	
}