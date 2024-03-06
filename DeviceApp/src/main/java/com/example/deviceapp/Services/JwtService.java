package com.example.deviceapp.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }


    public boolean isTokenValid(String token){
       try{
           Jws<Claims> claims = Jwts.parserBuilder()
                   .setSigningKey(Decoders.BASE64.decode(SECRET_KEY))
                   .build()
                   .parseClaimsJws(token);
           if(!isTokenExpired(token)){
               System.out.println("in if valid");
               return true;
           }
           else {
               System.out.println("in if invalid 1");
               return false;
           }
       }
       catch (Exception e){
           System.out.println(e.getMessage());
           System.out.println("in if invalid 2");
           return false;
       }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
