package org.hswebframework.web.authorization.starter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.hswebframework.web.authorization.basic.web.AuthorizedToken;
import org.hswebframework.web.authorization.basic.web.ParsedToken;
import org.hswebframework.web.authorization.basic.web.UserTokenParser;
import org.hswebframework.web.authorization.token.UserToken;
import org.hswebframework.web.authorization.token.UserTokenManager;
import org.hswebframework.web.entity.authorization.UserEntity;
import org.hswebframework.web.service.authorization.UserService;

import javax.servlet.http.HttpServletRequest;

public class BasicAuthorizationTokenParser implements UserTokenParser {

    private UserService userService;

    private UserTokenManager userTokenManager;

    public BasicAuthorizationTokenParser(UserService userService, UserTokenManager userTokenManager) {
        this.userService = userService;
        this.userTokenManager = userTokenManager;
    }

    @Override
    public ParsedToken parseToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            return null;
        }
        if (authorization.contains(" ")) {
            String[] info = authorization.split("[ ]");
            if (info[0].equalsIgnoreCase("Basic")) {
                authorization = info[1];
            }
        }
        try {
            String usernameAndPassword = new String(Base64.decodeBase64(authorization));
            UserToken token = userTokenManager.getByToken(usernameAndPassword);
            if (token != null && token.isEffective()) {
                return new ParsedToken() {
                    @Override
                    public String getToken() {
                        return usernameAndPassword;
                    }

                    @Override
                    public String getType() {
                        return "basic";
                    }
                };
            }
            if (usernameAndPassword.contains(":")) {
                String[] arr = usernameAndPassword.split("[:]");
                UserEntity user = userService.selectByUserNameAndPassword(arr[0], arr[1]);
                if (user != null) {
                    return new AuthorizedToken() {
                        @Override
                        public String getUserId() {
                            return user.getId();
                        }

                        @Override
                        public String getToken() {
                            return usernameAndPassword;
                        }

                        @Override
                        public String getType() {
                            return "basic";
                        }

                        @Override
                        public long getMaxInactiveInterval() {
                            //10分钟有效期
                            return 10_60_1000;
                        }
                    };
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
