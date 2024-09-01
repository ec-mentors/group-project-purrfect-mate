package purrfectmate.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetails;
import purrfectmate.data.entity.Human;


import java.util.Collection;

import java.util.Set;

public class UserPrincipal implements UserDetails {

    private final Human human;

    public UserPrincipal(Human human) {
        this.human = human;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<String> authorities = human.getAuthorities();
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }

    @Override
    public String getPassword() {
        return human.getPassword();
    }

    @Override
    public String getUsername() {
        return human.getUsername();
    }

    public Long getUserId() {
        return human.getId();
    }

    public String getLocation() {
        return human.getLocation();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
