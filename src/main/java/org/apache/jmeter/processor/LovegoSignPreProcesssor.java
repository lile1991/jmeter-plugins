package org.apache.jmeter.processor;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.utils.MD5Util;

import java.util.*;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class LovegoSignPreProcesssor extends AbstractLovegoTestElement implements PreProcessor, TestBean {
    public void process() {
        StringBuilder sign = new StringBuilder();

        JMeterContext jmctx = JMeterContextService.getContext();
        Sampler sampler = jmctx.getCurrentSampler();
        if(sampler instanceof HTTPSamplerBase) {
            HTTPSamplerBase httpSampler = (HTTPSamplerBase) sampler;

            JMeterVariables vars = jmctx.getVariables();
            String salt = vars.get("salt");
            if(salt == null) {
                return;
            }

            Map<String, String> argumentMap = new TreeMap<String, String>();
            Arguments arguments = httpSampler.getArguments();
            argumentMap.putAll(arguments.getArgumentsAsMap());
            argumentMap.put("salt", salt);
            Set<Map.Entry<String, String>> entrySet = argumentMap.entrySet();

            sign.append(salt);
            for(Map.Entry<String, String> entry: entrySet) {
                sign.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sign.append(salt);
            httpSampler.addArgument("salt", salt);
            httpSampler.addArgument("sign", MD5Util.MD5Encode(sign.toString(), "UTF-8").toUpperCase());
            vars.put("signSource", sign.toString());
        }
    }
}
