package com.virtual_wallet.auth.service;

import com.virtual_wallet.auth.dto.AuthRequest;
import com.virtual_wallet.auth.dto.AuthResponse;
import com.virtual_wallet.user.entity.UserEntity;
import com.virtual_wallet.user.repository.UserRepository;
import com.virtual_wallet.user.service.UserDetailsServiceImpl;
import com.virtual_wallet.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse loginUser(AuthRequest authRequest) {

        String email = authRequest.email();
        String password = authRequest.password();

        Authentication authentication = authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity user = userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String accessToken = jwtUtils.createToken(authentication, user.getId());

        return new AuthResponse(email, "Login successful", accessToken, true);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Email or Password");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                password,
                userDetails.getAuthorities()
        );
    }
}
