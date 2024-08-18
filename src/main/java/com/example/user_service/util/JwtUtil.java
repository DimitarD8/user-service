package com.example.user_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String secretKey = "MYSXKZMO1BLFS5BW9P3X1LEL5LXZFXSXNSLXW2HS5F9U1NZBX5V6F9K1BWZFLP1LS5P6ZZ9ZFT1LZX9RFLP1XKZ5XSZ5LNXZL9F6SF9LXS5KX9XPL";

    public JwtUtil(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        int jwtExpirationInMillis = 3600000;
        kafkaTemplate.send("secretKeyTopic", secretKey);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()) // Use plain string bytes
                .compact();
    }

    private byte[] getSecretKeyBytes() {
        // Use Base64 encoding to get the bytes of the secret key
        return Base64.getDecoder().decode(secretKey);
    }
}
