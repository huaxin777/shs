package com.sh.utils;

import com.sh.model.enums.CustomMapKey;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: BeanUtils
 * @Description:
 * @Version: 1.0.0
 * @Date: 2024/3/5 14:54
 * @Author: SH
 */
public class BeanUtils {
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            
            CustomMapKey customKeyAnnotation = field.getAnnotation(CustomMapKey.class);
            String key = customKeyAnnotation != null ? customKeyAnnotation.value() : field.getName();
            
            Object fieldValue = field.get(obj);
            map.put(key, fieldValue);
        }
        
        return map;
    }
    
    
    /**
     * 在类及其父类中查找指定字段，并返回该字段的反射对象。
     *
     * @param clazz       要查找的类
     * @param fieldName   要查找的字段名
     * @return            字段的反射对象，若未找到则抛出异常
     * @throws NoSuchFieldException 如果在整个继承链中都没有找到指定的字段
     */
    private static Field findFieldInHierarchy(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        
        throw new NoSuchFieldException("Field '" + fieldName + "' not found in hierarchy of " + clazz.getName());
    }
    
    /**
     * 获取指定字段的值
     *
     * @param obj          对象实例
     * @param fieldName    字段名
     * @return             字段的值
     * @throws NoSuchFieldException     如果找不到字段
     * @throws IllegalAccessException   如果没有访问权限
     */
    public static Object getFieldValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = findFieldInHierarchy(obj.getClass(), fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
