package com.xk.common.util;

import com.xk.common.base.Common;
import com.xk.common.util.dto.JwtUserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

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
        UUID userUuid = claims.get("userUuid", UUID.class);
        String userName = claims.get("userName", String.class);
        String email = claims.get("email", String.class);
        String cellPhone = claims.get("cellPhone", String.class);
        UUID roleUuid = claims.get("roleUuid", UUID.class);
        Boolean enable = claims.get("enable", Boolean.class);
        Boolean lock = claims.get("lock", Boolean.class);
        List<JwtUserDTO.SystemDTO> systemDTOs = claims.get("systemDTOs", List.class);
        return new JwtUserDTO(
                userUuid,
                userName,
                email,
                cellPhone,
                roleUuid,
                enable,
                lock,
                systemDTOs
        );
    }

    // 解析 Token 並提取 JwtUserDTO
    public static JwtUserDTO parseJwtUserFromToken(String token) {
        return extractClaim(token, XkJwtUtil::extractJwtUserFromClaims);
    }

    // 驗證Token
    public static boolean isTokenValid(String token, JwtUserDTO jwtUserDTO) {
        String userName = extractUsername(token);
        return (userName.equals(jwtUserDTO.getUsername())) && !isTokenExpired(token);
    }

    // 取得所有Claim
    public static Claims extractAllClaim(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // 產生Token Long id 
    public static String generateToken(JwtUserDTO jwtUserDTO) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userUuid", jwtUserDTO.getUserUuid());
        claims.put("account", jwtUserDTO.getAccount());
        claims.put("userName", jwtUserDTO.getUsername());
        claims.put("email", jwtUserDTO.getEmail());
        claims.put("cellPhone", jwtUserDTO.getCellPhone());
        claims.put("roleUuid", jwtUserDTO.getRoleUuid());
        claims.put("enable", jwtUserDTO.isEnable());
        claims.put("lock", jwtUserDTO.isLock());
        claims.put("systemDTOs", jwtUserDTO.getSystemDTOs());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(jwtUserDTO.getUserUuid()))
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