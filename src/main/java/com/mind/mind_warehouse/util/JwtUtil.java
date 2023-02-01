package com.mind.mind_warehouse.util;

import com.mind.mind_warehouse.vo.JwtInfo;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class JwtUtil {
    public static Properties properties=new Properties();
    static {
        InputStream in = JwtUtil.class.getClassLoader().getResourceAsStream("jwt.properties");

        try {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createToken(JwtInfo jwtInfo){
        String token = null;
        try {
            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setHeaderParam("typ",properties.getProperty("typ"));
            jwtBuilder.setHeaderParam("alg",properties.getProperty("alg"));
            jwtBuilder.setIssuer(properties.getProperty("issuer"));
            String expiration = properties.getProperty("expiration");
            jwtBuilder.setExpiration(new Date(new Date().getTime()+Long.parseLong(expiration)));
            jwtBuilder.setSubject(properties.getProperty("subject"));
            jwtBuilder.claim(properties.getProperty("userName"),jwtInfo.getUsername());
            jwtBuilder.signWith(SignatureAlgorithm.HS256,properties.getProperty("secret"));
            token = jwtBuilder.compact();
            return token;
        } catch (NumberFormatException e) {
            throw new RuntimeException("生成Token出错！");
        }
    }

    /**
     * 解析JWT的token
     * @param token
     * @return
     */

    public static Claims parseToken(String token){
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(properties.getProperty("secret")).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return body;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw  new ExpiredJwtException(null,null,"token已经过期！",e);
        }catch (SignatureException e1){
            e1.printStackTrace();
            throw new SignatureException("密钥不正确！",e1);
        }catch (RuntimeException e2){
            e2.printStackTrace();
            throw new RuntimeException("解析token出错！",e2);
        }
    }

    public static String getUsername(String token){
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(properties.getProperty("secret")).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String userName = (String) body.get(properties.getProperty("userName"));
            return userName;
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw  new ExpiredJwtException(null,null,"token已经过期！",e);
        }catch (SignatureException e1){
            e1.printStackTrace();
            throw new SignatureException("密钥不正确！",e1);
        }catch (RuntimeException e2){
            e2.printStackTrace();
            throw new RuntimeException("解析token出错！",e2);
        }
    }
}
