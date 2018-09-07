package com.mpri.aio.donation.service;

import org.springframework.stereotype.Service;

import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.donation.mapper.DonDonationFileMapper;
import com.mpri.aio.donation.model.DonDonationFile;
import com.mpri.aio.donation.model.DonProject;

 /**   
 *  
 * @Description:  捐赠项目附件——Service
 * @Author:       LZQ
 * @project 	  AIO 
 * @CreateDate:   Wed Aug 29 15:34:58 CST 2018
 * @Version:      v_1.0
 *    
 */
@Service
public class DonDonationFileService extends CrudService<DonDonationFileMapper, DonDonationFile>  {

	public void saveProjectFiles(String projectid,String[] filesName)
	{
		for(int i=0;filesName!=null&&i<filesName.length;i++)
		{
			DonDonationFile file=new DonDonationFile();
			file.setFilepath(filesName[i]);
			file.setDonDonationId(projectid);
			file.setSequence(i);
			save(file);
		}
	}

	public void deleteByProject(DonProject donProject)
	{
		mapper.deleteByProjectId(donProject);
		
	}
}