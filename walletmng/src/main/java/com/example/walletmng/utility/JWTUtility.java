package com.example.walletmng.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTUtility implements Serializable {

    private static final long serialVersionUID=234234523523L;

    public static final long JWT_TOKEN_VALIDITY= 400*60*60;

    @Value("${jwt.secret}")
    private String secretKey;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token,Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token , Function<Claims,T>claimResolver)
    {
        final Claims claims=getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    // for retrieving any information from token we will need the secret key

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user

    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object>claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    //
    private String doGenerateToken(Map<String,Object>claims,String subject)
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512,secretKey).compact();
    }

    //validate token
    public Boolean validateToken(String token,UserDetails userDetails)
    {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&&! isTokenExpired(token));
    }
}
