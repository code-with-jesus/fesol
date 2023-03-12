package com.jcode.fesol.user.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jcode.fesol.user.model.UserAccount;
import com.jcode.fesol.user.model.UserProfile;

public class UserDetailsMapper {

    private UserDetailsMapper() {
        // Hide constructor
    }

    public static UserDetails build(UserAccount userAccount) {
        return new org.springframework.security.core.userdetails.User(userAccount.getUsername(),
            userAccount.getPassword(), getAuthorities(userAccount));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(UserAccount userAccount) {
        Set<UserProfile> profiles = userAccount.getUserProfiles();
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        profiles.forEach(profile -> authorities.add(new SimpleGrantedAuthority("ROLE_" + profile.getName())));
        return authorities;
    }
}
