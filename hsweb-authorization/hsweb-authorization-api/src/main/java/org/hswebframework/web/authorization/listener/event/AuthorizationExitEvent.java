/*
 *  Copyright 2016 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package org.hswebframework.web.authorization.listener.event;

import org.hswebframework.web.authorization.Authorization;

/**
 * 退出登录事件
 *
 * @author zhouhao
 */
public class AuthorizationExitEvent implements AuthorizationEvent {
    private Authorization authorization;

    public AuthorizationExitEvent(Authorization authorization) {
        this.authorization = authorization;
    }

    public Authorization getAuthorization() {
        return authorization;
    }
}
