package com.virtual_wallet.user.service;

import com.virtual_wallet.user.entity.UserEntity;
import com.virtual_wallet.user.repository.UserRepository;
import com.virtual_wallet.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userFound = userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        userFound.getRoles()
                        .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName()))));

        userFound.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(
                userFound.getEmail(),
                userFound.getPassword(),
                userFound.isEnabled(),
                userFound.isAccountNonExpired(),
                userFound.isCredentialsNonExpired(),
                userFound.isAccountNonLocked(),
                authorities
        );
    }
}
