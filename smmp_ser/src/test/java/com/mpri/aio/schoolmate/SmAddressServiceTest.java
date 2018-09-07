package com.mpri.aio.schoolmate;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mpri.aio.ApplicationTests;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.service.SmAddressService;
import com.mpri.aio.schoolmate.service.SmSchoolmateService;
import com.mpri.aio.system.model.SysArea;
import com.mpri.aio.system.service.SysAreaService;

public class SmAddressServiceTest extends ApplicationTests{

	@Autowired
	private SmAddressService smAddressService;
	@Autowired
	private SmSchoolmateService smSchoolmateService;
	
//	
//	@Test
//	public void getNationBySysUserTest() {
//		SmAddress smAddress = new SmAddress();
//		smAddress.setType("IS_NATION_PLACE");
//		smAddress.setSysUserId("67fb5eaf2ee74f2d9ae7d54c7fdaac62");
//		smAddress = smAddressService.getNationBySysUser(smAddress);
//		System.err.println(smAddress.getCity());
//		super.outprint("com.mpri.aio.schoolmate.model.SmAddress", smAddress);
//	}
	@Test
	public void getInfoList() {
		SmSchoolmate smSchoolmate = new SmSchoolmate();
		smSchoolmate.setFlag("FLAG_NORMAL");
		List<SmSchoolmate> schoolmates = smSchoolmateService.loadInfoAllListBy(smSchoolmate);
		System.out.println(schoolmates.size());
		super.outprint("java.util.List", schoolmates);
	}
}
