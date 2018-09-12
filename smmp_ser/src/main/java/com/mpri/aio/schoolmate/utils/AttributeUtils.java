package com.mpri.aio.schoolmate.utils;

import java.lang.reflect.Field;

/**
* desc
* @author lzq
* @date 2018年8月29日 - 上午11:16:42
*/
public class AttributeUtils<T> {
    
    

	/**
	 * 给类中的属性赋值
	 */
	public void setAttributeValue(T t, String name, String value) 
	{
		//根据属性名给属性赋值
		Field f = null;
        try
        {
            f = t.getClass().getDeclaredField(name);
            f.setAccessible(true);
            String type = f.getType().toString();//得到此属性的类型
              if (type.endsWith("String")) 
              {
                  f.set(t,value) ;        //给属性设值
               }else if(type.endsWith("int") || type.endsWith("Integer"))
               {
                  f.set(t, Integer.valueOf(value)) ;       //给属性设值
               }else
               {
                  System.out.println(f.getType()+"\t");
               }
        }
        catch (NoSuchFieldException | SecurityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
}
