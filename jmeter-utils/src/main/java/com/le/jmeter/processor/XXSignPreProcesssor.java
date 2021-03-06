package com.le.jmeter.processor;

import com.le.jmeter.utils.MD5Util;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.processor.PreProcessor;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerBase;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * http前置处理器
 * 用于测试定制接口， 把所有参数hash排序并加密后放到salt
 */
public class XXSignPreProcesssor extends AbstractTestElement implements PreProcessor, TestBean {
    public static final String SALT = "salt";

    public void process() {
        StringBuilder sign = new StringBuilder();

        JMeterContext jmctx = JMeterContextService.getContext();
        Sampler sampler = jmctx.getCurrentSampler();
        if(sampler instanceof HTTPSamplerBase) {
            HTTPSamplerBase httpSampler = (HTTPSamplerBase) sampler;

            JMeterVariables vars = jmctx.getVariables();
            String salt = vars.get("salt");

            Arguments arguments = httpSampler.getArguments();
            Map<String, String> argumentsAsMap = arguments.getArgumentsAsMap();
            {
                String argumentSalt = argumentsAsMap.get(SALT);
                if(argumentSalt != null && argumentSalt.trim().length() > 0) {
                    salt = argumentSalt;
                } else {
                    httpSampler.addArgument("salt", salt);
                }
            }

            Map<String, String> argumentMap = new TreeMap<>();
            argumentMap.putAll(arguments.getArgumentsAsMap());
            argumentMap.put(SALT, salt);

            Set<Map.Entry<String, String>> entrySet = argumentMap.entrySet();

            sign.append(salt);
            for(Map.Entry<String, String> entry: entrySet) {
                if(entry.getValue() != null && entry.getValue().length() > 0) {
                    sign.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
            }
            sign.append(salt);
            httpSampler.addArgument("sign", MD5Util.MD5Encode(sign.toString(), "UTF-8").toUpperCase());
            vars.put("signSource", sign.toString());
        }
    }

    @Override
    public void setComment(String comment) {
        super.setComment("接口参数自动加密工具");
    }

    @Override
    public void setName(String name) {
        super.setName("xxx Sign Pre Processor");
    }
}
