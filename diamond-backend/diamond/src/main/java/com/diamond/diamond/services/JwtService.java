// package com.diamond.diamond.services;

// import java.nio.charset.StandardCharsets;
// import java.security.Key;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.function.Function;

// import javax.crypto.SecretKey;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.io.Decoders;
// import io.jsonwebtoken.security.Keys;

// @Component
// public class JwtService {

//     // Server-side secret key used to validate the JWT
//     @Value("${jwt.secret}")
//     private String secretKey;
//     // How long until the access token expires
//     @Value("${jwt.access.expiration}")
//     private long accessTokenExpiration;
//     // How long until the refresh token expires
//     @Value("${jwt.refresh.expiration}")
//     private long refreshTokenExpiration;

//     public String generateAccessToken(UserDetails userDetails) {
//         // Initializing the claims map to be used for creating tokens
//         Map<String, Object> claims = new HashMap<>();

//         //claims.put("access", true); // for the access token
//         // Generating the token
//         return buildToken(claims, userDetails.getUsername(), accessTokenExpiration);
//     }

//     public String generateRefreshToken(UserDetails userDetails) {
//         // Initializing the claims map to be used for creating tokens
//         Map<String, Object> claims = new HashMap<>();

//         claims.put("refresh", true); // for the refresh token

//         // Generating the token
//         return buildToken(claims, userDetails.getUsername(), refreshTokenExpiration);
//     }

//     private String buildToken(Map<String, Object> claims, String subject, Long expiration) {
//         SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//         return Jwts
//                 .builder()
//                 .setClaims(claims)
//                 .setSubject(subject)
//                 .setIssuedAt(new Date()) // date the token was issued
//                 .setExpiration(new Date(System.currentTimeMillis() + expiration)) // date that the token will expire
//                 .signWith(key) // using the secret key to sign the JWT
//                 .compact();
//     }

//     public String extractUsername(String token) {
//         return extractClaims(token, Claims::getSubject);
//     }

//     public Date extractExpiration(String token) {
//         return extractClaims(token, Claims::getExpiration);
//     }

//     public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
//         Claims claims = extractAllClaims(token);
//         return claimsResolver.apply(claims);
//     }

//     private Claims extractAllClaims(String token) {
//         return Jwts
//                 .parserBuilder()
//                 .setSigningKey(getSignInKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean isTokenValid(String token, UserDetails userDetails) {
//         final String username = extractUsername(token);
//         return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
//     }

//     private boolean isTokenExpired(String token) {
//         return extractExpiration(token).before(new Date());
//     }

//     /* 
//      * Decodes the JWT secret key
//      */
//     private Key getSignInKey() {
//         byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//         return Keys.hmacShaKeyFor(keyBytes);
//     }

//     public long getAccessExpiration() {
//         return accessTokenExpiration;
//     }

//     public long getRefreshExpiration() {
//         return refreshTokenExpiration;
//     }
    
// }
