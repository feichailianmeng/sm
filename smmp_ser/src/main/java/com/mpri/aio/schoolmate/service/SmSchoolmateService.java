package com.mpri.aio.schoolmate.service;

import java.util.Date;
import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.mpri.aio.base.service.CrudService;
import com.mpri.aio.common.utils.IdGen;
import com.mpri.aio.schoolmate.mapper.SmSchoolmateMapper;
import com.mpri.aio.schoolmate.model.SmAddress;
import com.mpri.aio.schoolmate.model.SmContact;
import com.mpri.aio.schoolmate.model.SmEducation;
import com.mpri.aio.schoolmate.model.SmProfession;
import com.mpri.aio.schoolmate.model.SmSchoolmate;
import com.mpri.aio.schoolmate.model.SmSchoolmateTemp;
import com.mpri.aio.system.model.SysUser;
import com.mpri.aio.system.service.SysUserService;

/**
 * 
 * @Description: 校友管理-校友表——Service
 * @Author: LZQ
 * @project AIO
 * @CreateDate: Mon Aug 20 10:32:38 CST 2018
 * @Version: v_1.0
 * 
 */
@Service
public class SmSchoolmateService extends CrudService<SmSchoolmateMapper, SmSchoolmate>{

	/*默认机构*/
	public static final String DEFAULT_ORG ="ROOT";
	/*默认用户类型*/
	public static final String DEFAULT_USER_TYPE ="NORMAL";
	/*默认密码*/
	public static final String DEFAULT_PWD = "123456";
	/*默认身份证号*/
	public static final String DEFAULT_CARDTYPE = "IDCARD";
	
	/*默认国家*/
	public static final String DEFAULT_COUNTRY = "1";
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SmAddressService smAddressService;
	
    @Autowired
    SmSchoolmateService smSchoolmateService;
    @Autowired
    SmSchoolmateTempService smSchoolmateTempService;
    @Autowired
    SmEducationService smEducationService;
    @Autowired
    SmProfessionService smProfessionService;
    @Autowired
    SmContactService  smContactService;
	
	/**
	 * 保存校友及相关联信息
	 * 
	 * 
	 */
	@Transactional(readOnly = false)
	public void saveSmAndAddrAndUser(SmSchoolmate schoolmate) {
		SysUser sysUser = this.saveSysUser(schoolmate);
		SmAddress smAddress = schoolmate.getSmAddress();
		
		//新增籍贯
		if(StringUtil.isEmpty(schoolmate.getId())) {
			smAddress.setSysUserId(sysUser.getId());
			smAddress.setUsername(schoolmate.getUsername());
			smAddress.setCountry(DEFAULT_COUNTRY);
			smAddress.setType(SmAddress.IS_NATION_PLACE);
			//保存校友
			schoolmate.setSysUserId(sysUser.getId());
			smAddressService.save(smAddress);
		}else { //修改
			smAddress.setSysUserId(sysUser.getId());
			smAddress.setType(SmAddress.IS_NATION_PLACE);
			SmAddress oldsmAddress = smAddressService.getNationBySysUser(smAddress);
			oldsmAddress.setProvince(smAddress.getProvince());
			oldsmAddress.setCity(smAddress.getCity());
			oldsmAddress.setDistrict(smAddress.getDistrict());
			smAddressService.save(oldsmAddress);
		}
		
		this.save(schoolmate);
	}
	
	
	public SysUser saveSysUser(SmSchoolmate schoolmate) {
		SysUser sysUser = new SysUser();
		//编辑
		if(!StringUtil.isEmpty(schoolmate.getSysUserId())) {
			sysUser.setId(schoolmate.getSysUserId());
			sysUser = sysUserService.get(sysUser);
		}else{
			sysUser.setSafecode(IdGen.uuid());
			//保存用户

			if(DEFAULT_CARDTYPE.equalsIgnoreCase(schoolmate.getCardType())) {
				sysUser.setPassword(initPwd(schoolmate.getCardNum()));
			}else {
				sysUser.setPassword(initPwd(null));
			}
			ByteSource salt = ByteSource.Util.bytes(sysUser.getSafecode());
			//加盐炒三次safecode=salt
			String result = new Md5Hash(sysUser.getPassword(),salt,3).toString();
			sysUser.setPassword(result);
			sysUser.setCreateDate(new Date());		
			sysUser.setType(DEFAULT_USER_TYPE);
			sysUser.setOrgId(DEFAULT_ORG);
			sysUser.setUsername(schoolmate.getUsername());		
		};
		sysUser.setName(schoolmate.getName());
		sysUserService.save(sysUser);
		return sysUser;
	}
	
	
	/**
	 * 初始化密码(冗余代码)
	* <p>Title: initPwd</p>  
	* <p>Description: </p>  
	* @param idCard
	* @return
	 */
	private String initPwd(String idCard) {
		if( null != idCard && idCard.length() > 6 ) {
			return idCard.substring(idCard.length()-6);
		}else {
			return DEFAULT_PWD;
		}
	}




	/**
	 *      
	* <p>Title: mager</p>  
	* <p>Description: 数据转换  合并</p>  
	* @param smSchoolmate
	* @param smSchoolmateTemp
	 */
    public void mager(SmSchoolmate smSchoolmate, SmSchoolmateTemp smSchoolmateTemp){

        SysUser sysuser = null;
        smSchoolmate = get(smSchoolmate);
        smSchoolmateTemp = smSchoolmateTempService.get(smSchoolmateTemp);
        if (null == smSchoolmate || null == smSchoolmate.getId() || smSchoolmate.getId().isEmpty())
        {
            // 创建用户
            sysuser = new SysUser();
            sysuser.setName(smSchoolmateTemp.getName());
            sysuser.setEmail(smSchoolmateTemp.getEmail());
            sysuser.setMobile(smSchoolmateTemp.getPhone());
            sysUserService.save(sysuser);
            smSchoolmate = new SmSchoolmate();
        }
        else
        {
            sysuser.setId(smSchoolmate.getSysUserId());
            sysuser = sysUserService.get(sysuser);
        }

        // 同步到校友表
        smSchoolmate.setSysUserId(sysuser.getId());
        smSchoolmate.setUsername(sysuser.getUsername());
        smSchoolmate.setName(smSchoolmateTemp.getName());
        smSchoolmate.setSex(smSchoolmateTemp.getSex());
        smSchoolmate.setBirthday(smSchoolmateTemp.getBirthday());
        smSchoolmate.setCardNum(smSchoolmateTemp.getCardNum());
        smSchoolmate.setCardType(smSchoolmateTemp.getCardType());
        smSchoolmate.setType(smSchoolmateTemp.getType());
        smSchoolmate.setNation(smSchoolmateTemp.getNation());
        smSchoolmateService.save(smSchoolmate);
        // 同步教育经历
        SmEducation smEducation = new SmEducation();
        smEducation.setSysUserId(sysuser.getId());
        smEducation.setSysUserUsername(sysuser.getUsername());
        smEducation.setSchool(smSchoolmateTemp.getSchool());
        smEducation.setCollege(smSchoolmateTemp.getCollege());
        smEducation.setSeries(smSchoolmateTemp.getSeries());
        smEducation.setSpecialty(smSchoolmateTemp.getSpecialty());
        smEducation.setClassName(smSchoolmateTemp.getSmclass());
        smEducation.setDegree(smSchoolmateTemp.getDegree());
        smEducation.setDegreetype(smSchoolmateTemp.getDegreetype());
        smEducation.setStudentid(smSchoolmateTemp.getStudentid());
        smEducation.setSchoollen(smSchoolmateTemp.getSchoollen());
        // 保存
        smEducationService.save(smEducation);
        // 同步联系方式
        String emailStr = smSchoolmateTemp.getEmail();
        if (!emailStr.isEmpty() && !"".equals(emailStr))
        {
            SmContact smContact = new SmContact();
            smContact.setSysUserId(sysuser.getId());
            smContact.setUsername(sysuser.getUsername());
            smContact.setType("email");
            smContact.setContact(emailStr);
            // 保存
            smContactService.save(smContact);
        }
        String phoneStr = smSchoolmateTemp.getPhone();
        if (!phoneStr.isEmpty() && !"".equals(phoneStr))
        {
            SmContact smContact = new SmContact();
            smContact.setSysUserId(sysuser.getId());
            smContact.setUsername(sysuser.getUsername());
            smContact.setType("phone");
            smContact.setContact(phoneStr);
            // 保存
            smContactService.save(smContact);
        }

        // 同步工作经历
        SmProfession smProfession = new SmProfession();
        smProfession.setSysUserId(sysuser.getId());
        smProfession.setUsername(sysuser.getUsername());
        smProfession.setWorkplace(smSchoolmateTemp.getWorkplace());
        // 保存
        smProfessionService.save(smProfession);
    }

    /**
     * 
    * <p>Title: commper</p>  
    * <p>Description: 比较</p>
     */
    public void commper(/* SmSchoolmateInfo smSchoolmateInfo */){

        
    }

    @Transactional
	public void saveUserMark(SmSchoolmate smSchoolmate)
	{
		mapper.deleteUserMark(smSchoolmate);
		mapper.insertUserMark(smSchoolmate);
	}
	
    public List<SmSchoolmate> loadInfoAllListBy(SmSchoolmate smSchoolmate){
    	return mapper.loadInfoAllListBy(smSchoolmate);
    }
}