package com.mpri.aio.schoolmate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.mpri.aio.ApplicationTests;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;
import com.mpri.aio.schoolmate.service.SmAddressService;
import com.mpri.aio.schoolmate.service.SmSchoolmateService;
import com.mpri.aio.schoolmate.service.SmSchoolmateTempService;

public class SmAddressServiceTest extends ApplicationTests{

	@Autowired
	private SmAddressService smAddressService;
	@Autowired
	private SmSchoolmateService smSchoolmateService;
    @Autowired
    private SmSchoolmateTempService smSchoolmateTempService;	
	/* 去重的数据 */
	@Value("#{'${spring.business.smSchoolmateTemp.delDuplicatesStr}'.split(',')}")
	private List<String> compareStrs;
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
//	@Test
//	public void getInfoList() {
//		SmSchoolmate smSchoolmate = new SmSchoolmate();
//		smSchoolmate.setFlag("FLAG_NORMAL");
//		List<SmSchoolmate> schoolmates = smSchoolmateService.loadInfoAllListBy(smSchoolmate);
//		System.out.println(schoolmates.size());
//		super.outprint("java.util.List", schoolmates);
//	}
//    @Test
    public void del() {
        SmSchoolmateTemp smSchoolmateTemp = new SmSchoolmateTemp();
        smSchoolmateTemp.setCompareStrs(compareStrs);
//        smSchoolmateTemp.setFlag("FLAG_NORMAL");
        smSchoolmateTempService.delDuplicate(smSchoolmateTemp);
//    	System.out.println(compareStrs);
    }
    @Test
    public void sendMail() {
    	String toEmail = "735031272@qq.com";

        Map<String, Object> model = new HashMap<>();
        SmSchoolmateTemp smSchoolmateTemp = new SmSchoolmateTemp();
        smSchoolmateTemp.setName("王三锤");
        smSchoolmateTemp.setSchool("1314921");
        model.put("params", smSchoolmateTemp);
    	smSchoolmateTempService.cardIssue(toEmail, model);
    	System.out.println("发送结束");
    }
}
