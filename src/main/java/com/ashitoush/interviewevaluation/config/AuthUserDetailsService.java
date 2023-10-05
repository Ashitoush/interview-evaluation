package com.ashitoush.interviewevaluation.config;

import com.ashitoush.interviewevaluation.entity.User;
import com.ashitoush.interviewevaluation.exception.AppException;
import com.ashitoush.interviewevaluation.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username)
                .orElseThrow(() -> new AppException("Invalid Email"));
        return new AuthUserDetails(user);
    }
}
