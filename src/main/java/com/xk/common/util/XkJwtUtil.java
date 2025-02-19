package com.xk.common.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.xk.common.base.Common;
import com.xk.common.util.dto.JwtUserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class XkJwtUtil implements InitializingBean {

    @Value("${jwt.secret}")
    private String secret;

    private static Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public static String extractUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null == authentication ? null : authentication.getName();
    }

    // 取得使用者名稱
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 取得指定Claim參數
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaim(token);
        return claimsResolver.apply(claims);
    }

    // 提取 JwtUserDTO 的具名方法
    public static JwtUserDTO extractJwtUserFromClaims(Claims claims) {
        return new JwtUserDTO((String) claims.get("username"));
    }

    // 解析 Token 並提取 JwtUserDTO
    public static JwtUserDTO parseJwtUserFromToken(String token) {
        return extractClaim(token, XkJwtUtil::extractJwtUserFromClaims);
    }

    // 產生Token
    public static String generateToken(UUID UserUuid){
        return generateToken(new HashMap<>(), UserUuid);
    }
 // 產生Token
    public static String generateToken(Long Userid){
        return generateToken(new HashMap<>(), Userid);
    }

    // 驗證Token
    public static boolean isTokenValid(String token, JwtUserDTO jwtUserDTO) {
        String userName = extractUsername(token);
        return (userName.equals(jwtUserDTO.getUsername())) && !isTokenExpired(token);
    }

    // 取得所有Claim
    private static Claims extractAllClaim(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 產生Token
    private static String generateToken(Map<String, Object> extraClaims, UUID UserUuid) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(UserUuid.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Common.JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }
    
    // 產生Token Long id 
    private static String generateToken(Map<String, Object> extraClaims, Long Userid) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(Userid.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Common.JWT_EXPIRATION))
                .signWith(key)
                .compact();
    }

    // 驗證Token是否過期
    private static boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // 取得Token過期時間
    private static Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

}