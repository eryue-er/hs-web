package org.hswebframework.web.authorization.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.hswebframework.web.Maps;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.basic.web.TokenResult;
import org.hswebframework.web.authorization.basic.web.UserTokenGenerator;
import org.hswebframework.web.id.IDGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouhao on 2017/8/30.
 */
public class JwtTokenGenarator implements UserTokenGenerator {

    private JwtConfig jwtConfig;

    public JwtTokenGenarator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String getSupportTokenType() {
        return "jwt";
    }

    private String createToken(){
        return IDGenerator.MD5.generate();
    }
    @Override
    public TokenResult generate(Authentication authentication) {
        String token = createToken();

        String jwtToken = createJWT(jwtConfig.getId(),token,jwtConfig.getTtl());

        String refreshToken = createJWT(jwtConfig.getId(),token,jwtConfig.getRefreshTtl());
        int timeout = jwtConfig.getTtl();

        return new TokenResult() {
            @Override
            public Map<String, Object> getResponse() {
                Map<String,Object> map = new HashMap<>();
                map.put("token",jwtToken);
                map.put("refreshToken",refreshToken);
                return map;
            }

            @Override
            public String getToken() {
                return token;
            }

            @Override
            public int getTimeout() {
                return timeout;
            }
        };
    }


    public String createJWT(String id, String subject, long ttlMillis){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date();
        SecretKey key = jwtConfig.generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
}
