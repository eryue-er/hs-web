package org.hswebframework.web.crud.generator;

import org.hswebframework.ezorm.core.DefaultValueGenerator;
import org.hswebframework.ezorm.core.RuntimeDefaultValue;
import org.hswebframework.web.id.IDGenerator;

public class MD5Generator implements DefaultValueGenerator {
    @Override
    public String getSortId() {
        return "md5";
    }

    @Override
    public RuntimeDefaultValue generate() {
        return IDGenerator.MD5::generate;
    }

    @Override
    public String getName() {
        return "MD5";
    }
}
