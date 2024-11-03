package com.jwt.SpringSecurityJWT.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//class for generating jwt token
@Service
public class JWTService {

    //secret key required for generating jwt token
    private String secretKey = "";

    //generating key
    public JWTService() {
        try {
            //use key generator for key generation and pass algorithm (here HmacSHA256)
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            //generate key of type SecretKey
            SecretKey sk = keyGen.generateKey();
            //converting secrete key into string with encoding
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    //generating jwt token
    public String generateToken(String username) {
        //jwt token have subject, issue data, expiry etc.
        //map for storing claims data
        Map<String, Object> claims = new HashMap<>();

        //jwts builder -> user to build claims, and it's having information like username, issue, expiry etc.
        return Jwts.builder()
                .claims()
                .add(claims) //sign in token
                .subject(username) //set username with key
                .issuedAt(new Date(System.currentTimeMillis())) //token issue date (current date)
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //token expiry date (30 minutes)
                .and() //it applies method to jwts.builder()
                .signWith(getKey()) //required key for generating token
                .compact();

        //next time we're sending token with request not username and password
    }

    //generating key using secret key
    private SecretKey getKey() {
        //convert secret key into bytes
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        //hmacShaFor require bytes
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //extract username
    public String extractUserName(String token) {
        //for extract username we have to extract claims
        return extractClaim(token, Claims::getSubject);
    }

    //extract claim
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        //extracting claims
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().
                verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //validating token
    public boolean validateToken(String token, UserDetails userDetails) {
        //extract username
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
