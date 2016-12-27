package org.apache.jmeter.protocol.redis.config;

import org.apache.jmeter.config.ConfigElement;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testbeans.TestBeanHelper;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.jorphan.util.JOrphanUtils;
import org.apache.log.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by Administrator on 2016/12/26 0026.
 */
public class RedisPoolElement extends AbstractTestElement implements ConfigElement, TestStateListener, TestBean {

    private static final Logger log = LoggingManager.getLoggerForClass();

    private transient String jedisPool;

    private transient int maxTotal;
    private transient int maxIdle;
    private transient int minIdle;
    private transient int maxWaitMillis;
    private transient int minEvictableIdleTimeMillis;

    private transient String host;
    private transient int port;
    private transient int timeout;
    private transient String password;

    public RedisPoolElement() {
        this.setName("Redis Pool Configuration");
    }

    @Override
    public void addConfigElement(ConfigElement configElement) {

    }

    @Override
    public boolean expectsModification() {
        return false;
    }

    @Override
    public void testStarted() {
        this.setRunningVersion(true);
        TestBeanHelper.prepare(this);
        JMeterVariables variables = this.getThreadContext().getVariables();
        String poolName = this.getJedisPool();
        if(JOrphanUtils.isBlank(poolName)) {
            throw new IllegalArgumentException("Variable Name must not be empty for element:" + this.getName());
        } else {
            if(variables.getObject(poolName) != null) {
                log.error("JDBC data source already defined for: " + poolName);
            } else {
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(maxTotal);
                jedisPoolConfig.setMaxIdle(maxIdle);
                jedisPoolConfig.setMinIdle(minIdle);
                jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
                jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
                JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
                variables.putObject(poolName, jedisPool);
            }
        }
    }

    @Override
    public void testStarted(String host) {
        testStarted();
    }

    @Override
    public void testEnded() {

    }

    @Override
    public void testEnded(String s) {

    }

    public static Jedis getResource(String poolName) {
        Object poolObject =
                JMeterContextService.getContext().getVariables().getObject(poolName);
        if (poolObject == null) {
            throw new JedisException("No pool found named: '" + poolName + "', ensure Variable Name matches Variable Name of Redis Connection Configuration");
        } else {
            if(poolObject instanceof JedisPool) {
                JedisPool pool = (JedisPool) poolObject;
                return pool.getResource();
            } else {
                String errorMsg = "Found object stored under variable:'"+poolName
                        +"' with class:"+poolObject.getClass().getName()+" and value: '"+poolObject+" but it's not a JedisPool, check you're not already using this name as another variable";
                log.error(errorMsg);
                throw new JedisException(errorMsg);
            }
        }
    }


    public String getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(String jedisPool) {
        this.jedisPool = jedisPool;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
