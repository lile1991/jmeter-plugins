package com.le.jmeter.redis;

import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.testelement.TestStateListener;

/**
 * Created by Administrator on 2016/12/27 0027.
 */
public abstract class AbstractRedisTestElement extends AbstractTestElement implements TestStateListener {

    private transient String jedisPool;

    @Override
    public void testStarted() {

    }

    @Override
    public void testStarted(String s) {

    }

    @Override
    public void testEnded() {

    }

    @Override
    public void testEnded(String s) {

    }

    public String getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(String jedisPool) {
        this.jedisPool = jedisPool;
    }
}
