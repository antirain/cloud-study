package com.hyf.cloud.util;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: crush
 * @Date: 2021-09-21 22:18
 * version 1.0
 */
public class JwtUtils {

    /**
     * 服务器端密钥
     */
    private static final String SECRET = "jwtsecretdemo";

    /**
     * 颁发者
     */
    private static final String ISS = "crush";


    /**
     * 这里创建用到的时间、用户名、应该是传入进来的，
     * 登录时选择是否记住我，过期时间应当是不一致的。
     * @return
     */
    public static String createJwt() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("demo", "可存储信息");
        map.put("demo2","可存储信息2");
        String jwt = Jwts.builder()
                .setClaims(map)
                // jwt所面向的用户
                .setSubject("username")
                //设置颁发者
                .setIssuer(ISS)
                // 定义在什么时间之前，该jwt都是不可用的.
                .setNotBefore(new Date())
                //签发时间
                .setIssuedAt(new Date())
                //设置 JWT 声明exp （到期）值
                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                //实际构建 JWT 并根据JWT 紧凑序列化 规则将其序列化为紧凑的、URL 安全的字符串。
                .compact();
        return jwt;
    }

    /**
     * 获取 Claims 实例
     * Claims ：一个 JWT声明集 。
     *   这最终是一个 JSON 映射，可以向其中添加任何值，但为了方便起见，JWT 标准名称作为类型安全的 getter 和 setter 提供。
     *   因为这个接口扩展了Map&lt;String, Object&gt; , 如果您想添加自己的属性，只需使用 map 方法，
     *   例如：
     *      claims.put("someKey", "someValue");
     *
     * @param jwt
     * @return
     */
    public static Claims getBody(String jwt) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();
    }


    /**
     * 判断 JWT 是否已过期
     *
     * @param jwt
     * @return
     */
    public static boolean isExpiration(String jwt) {
        return getBody(jwt)
                //返回 JWT exp （到期）时间戳，如果不存在则返回null 。
                .getExpiration()
                //测试此日期是否在指定日期之前。
                .before(new Date());
    }

    /**
     * Subject:获取 jwt 所面向的用户
     *
     * @param jwt
     * @return
     */
    public static String getSubject(String jwt) {
        return getBody(jwt).getSubject();
    }

    /**
     * Issuer:获取颁发者
     *
     * @param jwt
     * @return
     */
    public static String getIssuer(String jwt) {
        return getBody(jwt).getIssuer();
    }

    /**
     * getClaimsValue
     *
     * @param jwt
     * @return
     */
    public static String getClaimsValue(String jwt) {
        return (String) getBody(jwt).get("demo");
    }

    /**
     * getClaimsValue
     *
     * @param jwt
     * @return
     */
    public static String getClaimsValue2(String jwt) {
        return (String) getBody(jwt).get("demo2");
    }

    public static void main(String[] args) {
        String jwt = createJwt();
        System.out.println(jwt);
        System.out.println("jwt 是否已经过期："+isExpiration(jwt));
        System.out.println("Claims 中所存储信息："+getBody(jwt).toString());
        System.out.println("jwt 所面向的用户："+getSubject(jwt));
        System.out.println("jwt 颁发者："+getIssuer(jwt));
        System.out.println("通过键值,取出我们自己放进 Jwt 中的信息："+getClaimsValue(jwt));
        System.out.println("通过键值,取出我们自己放进 Jwt 中的信息2："+getClaimsValue2(jwt));
    }
}

