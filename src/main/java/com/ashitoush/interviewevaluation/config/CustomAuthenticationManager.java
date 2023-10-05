package com.ashitoush.interviewevaluation.config;

import com.ashitoush.interviewevaluation.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final AuthUserDetailsService authUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final CustomMessageSource customMessageSource;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

        if (userDetails != null) {
            if (isPasswordValid(userDetails, password)) {
                Authentication authResult = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                return authResult;
            } else {
                throw new AppException("Wrong Password!!!");
            }
        } else {
            throw new AppException("Wrong Username!!!");
        }
    }

    private boolean isPasswordValid(UserDetails userDetails, String password) {
        return passwordEncoder.encode(password).equals(userDetails.getPassword());
    }
}
