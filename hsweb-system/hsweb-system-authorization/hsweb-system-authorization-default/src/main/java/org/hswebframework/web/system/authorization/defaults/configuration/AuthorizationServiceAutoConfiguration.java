package org.hswebframework.web.system.authorization.defaults.configuration;

import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.authorization.ReactiveAuthenticationInitializeService;
import org.hswebframework.web.authorization.ReactiveAuthenticationManager;
import org.hswebframework.web.authorization.ReactiveAuthenticationManagerProvider;
import org.hswebframework.web.system.authorization.api.UserDimensionProvider;
import org.hswebframework.web.system.authorization.api.service.reactive.ReactiveUserService;
import org.hswebframework.web.system.authorization.defaults.service.DefaultReactiveAuthenticationInitializeService;
import org.hswebframework.web.system.authorization.defaults.service.DefaultReactiveAuthenticationManager;
import org.hswebframework.web.system.authorization.defaults.service.DefaultReactiveUserService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthorizationServiceAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    static class ReactiveAuthorizationServiceAutoConfiguration{
        @ConditionalOnBean(ReactiveRepository.class)
        @Bean
        public ReactiveUserService reactiveUserService() {
            return new DefaultReactiveUserService();
        }

        @Bean
        @ConditionalOnBean(ReactiveUserService.class)
        public ReactiveAuthenticationManagerProvider defaultReactiveAuthenticationManager() {
            return new DefaultReactiveAuthenticationManager();
        }

        @Bean
        @ConditionalOnBean(ReactiveUserService.class)
        public ReactiveAuthenticationInitializeService reactiveAuthenticationInitializeService() {
            return new DefaultReactiveAuthenticationInitializeService();
        }

        @Bean
        public UserDimensionProvider userPermissionDimensionProvider(){
            return new UserDimensionProvider();
        }
    }


}
