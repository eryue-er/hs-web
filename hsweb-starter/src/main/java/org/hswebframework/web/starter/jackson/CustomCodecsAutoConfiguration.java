package org.hswebframework.web.starter.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hswebframework.web.api.crud.entity.EntityFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.CodecConfigurer;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(JacksonAutoConfiguration.class)
public class CustomCodecsAutoConfiguration {

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(ObjectMapper.class)
	static class JacksonDecoderConfiguration {

		@Bean
		@Order(1)
		@ConditionalOnBean(ObjectMapper.class)
		CodecCustomizer jacksonDecoderCustomizer(EntityFactory entityFactory, ObjectMapper objectMapper) {
			objectMapper.setTypeFactory(new CustomTypeFactory(entityFactory));
			return (configurer) -> {
				CodecConfigurer.DefaultCodecs defaults = configurer.defaultCodecs();
				defaults.jackson2JsonDecoder(new CustomJackson2JsonDecoder(objectMapper));
			};
		}

	}


}
