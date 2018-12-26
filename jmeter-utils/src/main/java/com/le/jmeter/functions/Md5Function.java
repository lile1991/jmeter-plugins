package com.le.jmeter.functions;

import com.le.jmeter.utils.MD5Util;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * MD5加密函数
 */
public class Md5Function extends AbstractFunction {
    private CompoundVariable parameter;
    private static final String charsetname = "UTF-8";

    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        return MD5Util.MD5Encode(parameter.execute(), charsetname).toUpperCase();
    }

    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection, 1);
        parameter = collection.iterator().next();
    }

    public String getReferenceKey() {
        return "MD5";
    }

    public List<String> getArgumentDesc() {
        return Arrays.asList("Encryption parameters");
    }
}
