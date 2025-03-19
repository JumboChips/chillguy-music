package com.jumbochips.chillguy.user.principal;

import com.jumbochips.chillguy.security.oauth.OAuth2UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private final OAuth2UserInfo oAuth2UserInfo;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(OAuth2UserInfo userInfo, Collection<? extends GrantedAuthority> authorities) {
        this.oAuth2UserInfo = userInfo;
        this.authorities = authorities;
    }

    public String getEmail() {
        return oAuth2UserInfo.getEmail();
    }

    public String getName() {
        return oAuth2UserInfo.getName();
    }

    public String getProfileImageUrl() {
        return oAuth2UserInfo.getProfileImageUrl();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // OAuth2 인증이므로 비밀번호 없음
    }

    @Override
    public String getUsername() {
        return getEmail();
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
        return true;
    }
}
