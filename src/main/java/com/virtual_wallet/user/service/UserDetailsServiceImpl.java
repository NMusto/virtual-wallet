package com.virtual_wallet.user.service;

import com.virtual_wallet.security.dto.AuthRequest;
import com.virtual_wallet.security.dto.AuthResponse;
import com.virtual_wallet.user.entity.UserEntity;
import com.virtual_wallet.user.repository.UserRepository;
import com.virtual_wallet.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public AuthResponse loginUser(AuthRequest authRequest) {

        String email = authRequest.email();
        String password = authRequest.password();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(email, "Login successful", accessToken, true);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Email or Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }
}
