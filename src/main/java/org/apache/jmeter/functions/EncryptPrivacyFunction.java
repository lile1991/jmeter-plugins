package org.apache.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.utils.PrivacyMaskUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangwanli on 2017/10/31.
 */
public class EncryptPrivacyFunction extends AbstractFunction {
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
        key = PrivacyMaskUtils.createKey(keyVar.execute());

        CompoundVariable unencryptedVar = collection.iterator().next();
        unencrypted = unencryptedVar.execute();
    }

    /**
     * 显示在JMeter的函数名
     */
    @Override
    public String getReferenceKey() {
        return "__EncryptPrivacy";
    }

    /**
     * 显示在JMeter的参数描述, 有几个参数写几个
     */
    @Override
    public List<String> getArgumentDesc() {
        return Arrays.asList("key", "unencrypted");
    }

}
