package org.hswebframework.web.authorization.token;

import org.hswebframework.web.authorization.Authentication;
import reactor.core.publisher.Mono;

/**
 * @author zhouhao
 * @since 1.0
 */
public interface ThirdPartAuthenticationManager {

    /**
     * @return 支持的tokenType
     */
    String getTokenType();

    /**
     * 根据用户ID获取权限信息
     *
     * @param userId 用户ID
     * @return 权限信息
     */
    Mono<Authentication> getByUserId(String userId);

}
