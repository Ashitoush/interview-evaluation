package com.ashitoush.interviewevaluation.config;

import com.ashitoush.interviewevaluation.exception.AppException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;

        if (token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            try {
                username = jwtHelper.getUsernameFromToken(token);
            } catch (ExpiredJwtException | MalformedJwtException exception) {
                throw new AppException(exception.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

            if (userDetails != null && jwtHelper.validateToken(token, userDetails.getUsername())) {
                List<GrantedAuthority> authorities = jwtHelper.getAuthoritiesClaimFromToken(token);

                Authentication authentication = jwtHelper.getAuthentication(username, authorities, request);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}