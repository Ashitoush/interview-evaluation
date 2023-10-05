package com.ashitoush.interviewevaluation.config;

import com.ashitoush.interviewevaluation.response.ApiResponse;
import com.ashitoush.interviewevaluation.response.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtHelper {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public AuthResponse generateToken(AuthUserDetails authUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        setCustomClaims(claims, authUserDetails);

        AuthResponse authResponse = new AuthResponse();

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(authUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 10 *  60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        authResponse.setAccessToken(token);

        token = Jwts.builder()
                .setClaims(claims)
                .setSubject(authUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        authResponse.setRefreshToken(token);
        return authResponse;
    }

    private void setCustomClaims(Map<String, Object> claims, AuthUserDetails authUserDetails) {
        List<String> authorities = new ArrayList<>();

        for (GrantedAuthority authority : authUserDetails.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        claims.put("User Id", authUserDetails.getUserId());
        claims.put("Role", authorities);
    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public List<GrantedAuthority> getAuthoritiesClaimFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        List<String> authorities = (List<String>) claims.get("Role");
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        Date expirationDate = getClaimsFromToken(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public Boolean validateToken(String token, String username) {
        String usernameFromToken = getClaimsFromToken(token, Claims::getSubject);

        return usernameFromToken != null && usernameFromToken.equals(username) && !isTokenExpired(token);
    }

    public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

}
