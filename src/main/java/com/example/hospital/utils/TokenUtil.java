package com.example.hospital.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUtil {
    private static final String SECRET_KEY = "your-secret-keysaddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaa";
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final long EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000; // 24 hours

    public static String generateToken(String subject) {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SIGNATURE_ALGORITHM.getJcaName());

        long currentTimeMs = System.currentTimeMillis();
        Date expirationDate = new Date(currentTimeMs + EXPIRATION_TIME_MS);

        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();

        return token;
    }

    public static boolean verifyToken(String token, String expectedSubject) {
        Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SIGNATURE_ALGORITHM.getJcaName());

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Verify the subject
            String actualSubject = claims.getSubject();
            if (!expectedSubject.equals(actualSubject)) {
                return false; // Subject mismatch
            }

            // Perform additional token validation or access claims information


            return true;
        } catch (Exception e) {
            // Token is invalid or expired
            return false;
        }
    }

}
