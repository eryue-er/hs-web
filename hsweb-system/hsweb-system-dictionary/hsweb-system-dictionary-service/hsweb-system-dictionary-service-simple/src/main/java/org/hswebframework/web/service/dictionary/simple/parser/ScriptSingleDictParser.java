package org.hswebframework.web.service.dictionary.simple.parser;

import org.hswebframework.expands.script.engine.DynamicScriptEngine;
import org.hswebframework.expands.script.engine.DynamicScriptEngineFactory;
import org.hswebframework.web.ExpressionUtils;
import org.hswebframework.web.service.dictionary.parser.SingleDictParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODO 完成注释
 *
 * @author zhouhao
 */
public class ScriptSingleDictParser implements SingleDictParser {
    private String script;

    private String language;

    @Override
    public Optional<String> parse(String target, Object context) {
        Map<String, Object> vars = new HashMap<>();
        vars.put("context", context);
        DynamicScriptEngine engine = DynamicScriptEngineFactory.getEngine(language);
        String scriptId = String.valueOf(script.hashCode());
        try {
            if (!engine.compiled(scriptId)) {
                engine.compile(scriptId, language);
            }
            Object result = engine.execute(scriptId, vars).getIfSuccess();
            if (result == null) {
                return null;
            }
            return Optional.of(String.valueOf(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
