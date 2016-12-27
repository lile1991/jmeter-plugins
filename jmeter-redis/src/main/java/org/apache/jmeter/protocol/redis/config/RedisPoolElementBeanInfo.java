package org.apache.jmeter.protocol.redis.config;

import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

import java.beans.PropertyDescriptor;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class RedisPoolElementBeanInfo extends BeanInfoSupport {
    private static final Logger log = LoggingManager.getLoggerForClass();

    public RedisPoolElementBeanInfo() {
        super(RedisPoolElement.class);

        this.createPropertyGroup("varName", new String[]{"jedisPool"});
        this.createPropertyGroup("pool", new String[]{"maxTotal", "maxIdle", "minIdle", "maxWaitMillis", "minEvictableIdleTimeMillis"});
        this.createPropertyGroup("redis", new String[]{"host", "port", "timeout", "password"});
        PropertyDescriptor p = this.property("jedisPool");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", "");
        p = this.property("maxTotal");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 50);
        p = this.property("maxIdle");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 200);
        p = this.property("minIdle");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 2);
        p = this.property("maxWaitMillis");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 15000);
        p = this.property("minEvictableIdleTimeMillis");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 300000);
        p = this.property("host");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", "127.0.0.1");
        p = this.property("port");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 6379);
        p = this.property("timeout");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", 3000);
        p = this.property("password");
        p.setValue("notUndefined", Boolean.TRUE);
        p.setValue("default", "");
    }
}
