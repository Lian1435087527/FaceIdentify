package com.exampl.demo.Services;

import java.util.Date;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

public class JwtUtil {
	 
final static String base64EncodedSecretKey = "你的私钥";//私钥
final static long TOKEN_EXP = 1000 * 60 * 10;//过期时间,测试使用十分钟
 
 
 
//public static String getToken(String userName) {
 
/*return Jwts.builder()
 
.setSubject(userName)
 
.claim("roles", "user")
 
.setIssuedAt(new Date())
 
.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
 
//.signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
 
//.compact();
 
//}
 
}
