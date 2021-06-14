package com.yao.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack Yao on 2021/6/14 11:59 上午
 */
public class CustomBeanUtils {
    /*找所有空的屬性*/
    public static String[] getNullPropertyNames(Object source){
        /*包裝*/
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        /*包裝好後找空屬性*/
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullpropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd: pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null){
                nullpropertyNames.add(propertyName);
            }
        }
        return nullpropertyNames.toArray(new String[nullpropertyNames.size()]);
    }
}
