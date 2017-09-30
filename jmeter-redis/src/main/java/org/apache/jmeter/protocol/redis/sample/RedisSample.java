package org.apache.jmeter.protocol.redis.sample;

import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.engine.util.ConfigMergabilityIndicator;
import org.apache.jmeter.protocol.redis.AbstractRedisTestElement;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/27 0027.
 *待实现
 */
public class RedisSample extends AbstractRedisTestElement implements Sampler, TestBean, ConfigMergabilityIndicator {
    private static final Set<String> APPLIABLE_CONFIG_CLASSES = new HashSet(Arrays.asList(new String[]{"org.apache.jmeter.config.gui.SimpleConfigGui"}));

    @Override
    public boolean applies(ConfigTestElement configElement) {
        String guiClass = configElement.getProperty("TestElement.gui_class").getStringValue();
        return APPLIABLE_CONFIG_CLASSES.contains(guiClass);
    }

    @Override
    public SampleResult sample(Entry entry) {
        return null;
    }

    private byte[] execute(Jedis jedis, SampleResult res) {
//        jedis.set()
        return new byte[0];
    }

    private void close(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }
}
