package com.sh.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BeanToMap
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/11/4 11:17
 * @Author: SH
 */
public class BeanToMap {
	/**
	 * 将一个bean生成map对象
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if(obj == null)  {
			return null;
		}
		if(obj instanceof Map){
			return (Map<String, Object>) obj;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter!=null ? getter.invoke(obj) : null;
			if (value instanceof Date) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put(key, sf.format(value));
			}else{
				map.put(key, value);
			}
			
		}
		return map;
	}
}
