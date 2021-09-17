package com.watercloud.webmagic.common.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTool {
    private static String SECRET;
    private static long EXPIRE_TIME;
    private static String ISSUER;
    @Value("${JWTConfig.SECRET}")
    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }
    @Value("${JWTConfig.EXPIRE_TIME}")
    public void setEXPIRE_TIME(long EXPIRE_TIME) {
        this.EXPIRE_TIME = EXPIRE_TIME;
    }
    @Value("${JWTConfig.ISSUER}")
    public void setISSUER(String ISSUER) {
        this.ISSUER = ISSUER;
    }

    //    JWT（Json Web Token）分为三部分：
//         1：Header:头部中主要包含两部分，token 类型和加密算法，如默认的： {typ: "jwt", alg: "HS256"}
//         2: Payload:负载就是存放有效信息的地方
//                iss: jwt签发者；
//                sub: jwt所面向的用户；
//                aud: 接收jwt的一方；
//                exp: jwt的过期时间，这个过期时间必须要大于签发时间；
//                nbf: 定义在什么时间之前，该jwt都是不可用的；
//                iat: jwt的签发时间；
//                jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击；
//         3: Signature: 这一部分指将 Header 和 Payload 通过密钥 secret 和加盐算法进行加密后生成的签名，
//             secret一般保存在服务端，所以加密的东西是无法解的，别人不知道这个密匙,它用来jwt的签发和验证，就像一把钥匙
//    三部分通过“.”连接，如：eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzE3MTc3MTEsInVzZXJuYW1lIjoiMTIzIn0.dfJIlCpcmamuJw79ap582Lp-JDcIT9RXKva_kUiigVA
//    默认加密方式 HS256

    /**
     * 创建token,30min后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
         //构建密匙信息
        return JWT.create()
                .withIssuer(ISSUER)//设置生成者
                .withIssuedAt(new Date())//设置创建的时间
                .withExpiresAt(date)//设置过期时间
                .withClaim("username", username)//设置自定义信息
                .sign(Algorithm.HMAC256(SECRET));//设置签名
    }

    /**
     * 校验加密的token
     * @param token 加密的token
     */
    public static Map<String,String> check(String token){
        Map<String,String> resultMap = new HashMap<>();
        try{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        } catch (SignatureVerificationException e) {
            log.error("无效签名！ 错误 ->", e);
            resultMap.put("msg","无效签名！");
            resultMap.put("isSuccess","0");
            return resultMap;
        } catch (TokenExpiredException e) {
            log.error("token过期！ 错误 ->", e);
            resultMap.put("msg","token过期！");
            resultMap.put("isSuccess","0");
            return resultMap;
        } catch (AlgorithmMismatchException e) {
            log.error("token算法不一致！ 错误 ->", e);
            resultMap.put("msg","token算法不一致！");
            resultMap.put("isSuccess","0");
            return resultMap;
        } catch (Exception e) {
            log.error("token无效！ 错误 ->", e);
            resultMap.put("msg","token无效！");
            resultMap.put("isSuccess","0");
            return resultMap;
        }
        resultMap.put("msg","验证通过！");
        resultMap.put("isSuccess","1");
        return resultMap;
    }

    /**
     * 解析token中负载部分的username
     * @param token 加密的token
     */
    public static String getTokenUsername(String token){
        return JWT.decode(token).getClaim("username").asString();
    }

}
