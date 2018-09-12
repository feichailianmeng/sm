package com.mpri.aio.schoolmate.aop;

import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.mpri.aio.schoolmate.model.SmSchoolmate;

@Aspect
@Component
public class InfoIntegrityAop {

	private static String SYSUSERID = "sysUserId";

	@Pointcut("@annotation(com.mpri.aio.schoolmate.aop.InfoIntegrity)")
	public void costTimePointCut() {
	}

	@Around("costTimePointCut()")
	public Object around(ProceedingJoinPoint point) {
		Object obj = null;
		System.out.println("@Around：执行目标方法之前...");
		try {
			obj = point.proceed();
			Object data = getObjFromRes(obj);
			
			System.err.println(getSysUserId(data));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 访问目标方法的参数：
		return obj;
	}

	private Object getObjFromRes(Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		Object data = null;
		Field[] field = obj.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			String name = field[j].getName();
			boolean accessFlag = field[j].isAccessible();
			// 修改访问控制权限
			field[j].setAccessible(true);
			if (name.equals("data")) {
				data = field[j].get(obj);
			}
			// 恢复访问控制权限
			field[j].setAccessible(accessFlag);
		}
		return data;
	}

	/**
	 * 获取用户id
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private String getSysUserId(Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		String sysUserId = null;
		Field[] field = obj.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			String name = field[j].getName();
			boolean accessFlag = field[j].isAccessible();
			// 修改访问控制权限
			field[j].setAccessible(true);
			if (name.equals(SYSUSERID)) {
				sysUserId = field[j].get(obj).toString();
			}
			// 恢复访问控制权限
			field[j].setAccessible(accessFlag);
		}
		return sysUserId;
	}
}
