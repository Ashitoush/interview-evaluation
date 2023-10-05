package com.ashitoush.interviewevaluation.config;

import com.ashitoush.interviewevaluation.entity.Role;
import com.ashitoush.interviewevaluation.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class AuthUserDetails implements UserDetails {

    private Integer userId;
    private String email;
    private String password;
    private Boolean status;
    private List<GrantedAuthority> authorities;

    public AuthUserDetails(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getIsActive();
        this.authorities = getGrantedAuthority(user);
    }

    private List<GrantedAuthority> getGrantedAuthority(User user) {
        List<Role> roleList = user.getRoles();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roleList.forEach(
                role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()))
        );
        return grantedAuthorities;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}
