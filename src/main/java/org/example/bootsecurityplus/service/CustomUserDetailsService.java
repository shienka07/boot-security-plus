package org.example.bootsecurityplus.service;

import org.example.bootsecurityplus.model.domain.SecurityUser;
import org.example.bootsecurityplus.model.mapper.SecurityUserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SecurityUserMapper securityUserMapper;

    public CustomUserDetailsService(SecurityUserMapper securityUserMapper) {
        this.securityUserMapper = securityUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUser user = securityUserMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.username(), user.password(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_%s".formatted(user.role()))));
    }
}
