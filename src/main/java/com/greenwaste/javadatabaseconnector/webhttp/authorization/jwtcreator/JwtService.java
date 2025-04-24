package com.greenwaste.javadatabaseconnector.webhttp.authorization.jwtcreator;

import com.greenwaste.javadatabaseconnector.model.User;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService() {
        Dotenv dotenv = Dotenv.configure().directory("src/main/resources/AmbientVariables").load();
        String base64Secret = dotenv.get("JWT_SECRET");
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Secret));
    }

    public String generateToken(User user) {
        return Jwts.builder().subject(user.getEmail()).claim("role", user.getRole()).claim("id", user.getId()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();
    }

    public boolean validateToken(String token, String email) {
        try {
            Claims claims = extractAllClaims(token);
            String extractedEmail = claims.getSubject();
            Date expiration = claims.getExpiration();
            return (email.equals(extractedEmail) && expiration.after(new Date()));
        } catch (Exception e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
}
