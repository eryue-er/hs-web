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

package org.hswebframework.web.controller.authorization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hswebframework.web.BusinessException;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.WebUtil;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.listener.AuthorizationListenerDispatcher;
import org.hswebframework.web.authorization.listener.event.*;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.entity.authorization.UserEntity;
import org.hswebframework.web.logging.AccessLogger;
import org.hswebframework.web.service.authorization.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

/**
 * @author zhouhao
 */
@RestController
@RequestMapping("${hsweb.web.mappings.authorize:authorize}")
@AccessLogger("授权")
@Api(tags = "hsweb-authorization", description = "提供基本的授权功能")
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationListenerDispatcher authorizationListenerDispatcher;

    @GetMapping("/login-out")
    @AccessLogger("退出登录")
    @Authorize
    @ApiOperation("退出当前登录")
    public ResponseMessage exit(@ApiParam(hidden = true) Authentication authentication) {
        authorizationListenerDispatcher.doEvent(new AuthorizationExitEvent(authentication));
        return ok();
    }

    @PostMapping(value = "/login")
    @AccessLogger("授权")
    @ApiOperation("用户名密码登录")
    public ResponseMessage<String> authorize(@RequestParam @ApiParam("用户名") String username,
                                             @RequestParam @ApiParam("密码") String password,
                                             @ApiParam(hidden = true) HttpServletRequest request) {

        AuthorizationFailedEvent.Reason reason = AuthorizationFailedEvent.Reason.OTHER;
        Function<String, Object> parameterGetter = request::getParameter;
        try {
            AuthorizationDecodeEvent decodeEvent = new AuthorizationDecodeEvent(username, password, parameterGetter);
            authorizationListenerDispatcher.doEvent(decodeEvent);
            username = decodeEvent.getUsername();
            password = decodeEvent.getPassword();

            AuthorizationBeforeEvent beforeEvent = new AuthorizationBeforeEvent(username, password, parameterGetter);
            authorizationListenerDispatcher.doEvent(beforeEvent);
            UserEntity entity = userService.selectByUsername(username);
            if (entity == null) {
                reason = AuthorizationFailedEvent.Reason.USER_NOT_EXISTS;
                throw new NotFoundException("{user_not_exists}");
            }
            if (Boolean.FALSE.equals(entity.isEnabled())) {
                reason = AuthorizationFailedEvent.Reason.USER_DISABLED;
                throw new BusinessException("{user_is_disabled}", 400);
            }
            password = userService.encodePassword(password, entity.getSalt());
            if (!entity.getPassword().equals(password)) {
                reason = AuthorizationFailedEvent.Reason.PASSWORD_ERROR;
                throw new BusinessException("{password_error}", 400);
            }

            userService.updateLoginInfo(entity.getId(), WebUtil.getIpAddr(request), System.currentTimeMillis());
            // 验证通过
            Authentication authentication = userService.initUserAuthorization(entity.getId());
            AuthorizationSuccessEvent event = new AuthorizationSuccessEvent(authentication, parameterGetter);
            authorizationListenerDispatcher.doEvent(event);
            return ok(entity.getId());
        } catch (Exception e) {
            AuthorizationFailedEvent failedEvent = new AuthorizationFailedEvent(username, password, parameterGetter, reason);
            failedEvent.setException(e);
            authorizationListenerDispatcher.doEvent(failedEvent);
            throw e;
        }
    }

}
