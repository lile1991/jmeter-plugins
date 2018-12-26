package com.le.jmeter.redis;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.TestBean;

import java.beans.PropertyDescriptor;

/**
 * Created by Administrator on 2016/12/27 0027.
 */
public class RedisTestElementBeanInfoSupport extends BeanInfoSupport {
    protected RedisTestElementBeanInfoSupport(Class<? extends TestBean> beanClass) {
        super(beanClass);

        createPropertyGroup("varName",
                new String[]{"jedisPool" });

        PropertyDescriptor p = property("jedisPool");
        p.setValue(NOT_UNDEFINED, Boolean.TRUE);
        p.setValue(DEFAULT, "");
    }
}
