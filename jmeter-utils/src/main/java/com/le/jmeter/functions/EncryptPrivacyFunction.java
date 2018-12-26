package com.le.jmeter.functions;

import com.le.jmeter.utils.PrivacyMaskUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangwanli on 2017/10/31.
 */
public class EncryptPrivacyFunction extends AbstractFunction {
    private static final Logger log = LoggerFactory.getLogger(EncryptPrivacyFunction.class);

    // Function参数
    private Key key;
    private String unencrypted;


    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) {
        return PrivacyMaskUtils.encryptPrivacy(key, unencrypted);
    }

    /**
     * 获取参数
     * @param collection 参数值
     */
    @Override
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection, 2);

        CompoundVariable keyVar = collection.iterator().next();
        String keyBase64Str = keyVar.execute();
        log.debug("key = {}", keyBase64Str);
        key = PrivacyMaskUtils.createKey(keyBase64Str);

        CompoundVariable unencryptedVar = collection.iterator().next();
        String unencryptedStr = unencryptedVar.execute();
        log.debug("unencryptedVar = {}", unencryptedVar);
        unencrypted = unencryptedStr;
    }

    /**
     * 显示在JMeter的函数名
     */
    @Override
    public String getReferenceKey() {
        return "EncryptPrivacy";
    }

    /**
     * 显示在JMeter的参数描述, 有几个参数写几个
     */
    @Override
    public List<String> getArgumentDesc() {
        return Arrays.asList("keyBase64", "unencrypted");
    }

}
