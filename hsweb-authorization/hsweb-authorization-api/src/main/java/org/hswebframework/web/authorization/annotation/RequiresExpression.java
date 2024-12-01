/*
 * Copyright 2016 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用表达式进行验证,默认支持spel,ognl表达式。
 *
 * @author zhouhao
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresExpression {

    /**
     * 表达式内容,表达式可以调用方法的参数值以及当前的用户信息和spring管理的bean
     * 例如:
     * <code>
     * <p>
     * &#064;ReuqestMapping<br>
     * &#064;RequiresExpression("#param!=null")<br>
     * public ResponseMessage requestHandle(String param){ <br/>
     * //...<br>
     * }<br>
     * </code>
     */
    String value();

    String language() default "spel";
}
