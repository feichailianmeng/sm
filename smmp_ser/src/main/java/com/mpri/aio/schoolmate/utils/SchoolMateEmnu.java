package com.mpri.aio.schoolmate.utils;

import java.util.Date;

/**
* desc
* @author lzq
* @date 2018年8月29日 - 下午2:51:42
*/
public enum SchoolMateEmnu
{
     name("姓名"), sex("性别"), 
      birthday("出生日期"), 
     cardNum("证件号码"), cardType("证件类型"), 
     type("类型"), nativePlace("籍贯"), 
     nation("民族"), address("所在地"), 
     school("学校"), college("学院"), 
     academy("书院"), series("所在系"), 
     specialty("专业"), smclass("班级"), 
     degree("学历"), degreetype("教育类型"), 
     studentid("学号"), schoollen("学制"), 
     startdate("入学时间"), enddate("毕业时间"), 
     email("电子邮箱"), workplace("工作单位"), phone("电话");

 // 定义私有变量

    private String nCode;

    // 构造函数，枚举类型只能为私有

    private SchoolMateEmnu(String _nCode)
    {

        this.nCode = _nCode;

    }

    @Override

    public String toString()
    {

        return String.valueOf(this.nCode);

    }

}
