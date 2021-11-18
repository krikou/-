package com.example.demo.util;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit;


/**
 * @author nan.gai
 */
public class JwtUtil {

    /** 日志 */
    private  static final Logger log = (Logger) LoggerFactory.getLogger(JwtUtil.class);
    /** 密钥 */
    private static final String SECRET_KEY = "0123456789_0123456789_0123456789";
    /** 加密方式 */
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    /** 签发人 */
    private static final String ISSUER = "zyq";
    /** 过期时间：1天（单位：毫秒） */
    private static final long TOKEN_EXPIRE_TIME =  24 * 60 * 60 * 1000;
    /** 加密数据（键） */
    private static final String USER_ID = "userId";

    /**
     *  测试主方法
     */
    /**public static void main(String[] args) {
        String token = getToken("123456789");
        System.out.println("加密后的 token 为：" + token);
        String userId = decodedToken(token);
        System.out.println("解密后的 userId 为：" + userId);
    }
     */

    /**
     * 加密 token
     */
    public static String getterToken(String userId) {
        // 签发时间
        Date now = new Date();
        return JWT.create()
                // 签发人
                .withIssuer(ISSUER)
                // 签发时间
                .withIssuedAt(now)
                // 过期时间
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                .withClaim(USER_ID, userId)
                .sign(ALGORITHM);
    }

    /**
     * 解析 token
     * @return
     */
    public static String decodedToken(String token) {
        DecodedJWT jwt;
       try {
            jwt = JWT.decode(token);
        } catch (Exception e) {
            log.warn("异常token:{}", token);
           return null;
        }
        if (new Date().getTime() > jwt.getExpiresAt().getTime()) {
            log.warn("token 已过期:{}", token);
            return null;
        }
        return jwt.getClaim(USER_ID).asString();
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) {return false;}
        try {
            Jwts.parser().setSigningKey(USER_ID).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) {return false;}
            Jwts.parser().setSigningKey(USER_ID).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    }



