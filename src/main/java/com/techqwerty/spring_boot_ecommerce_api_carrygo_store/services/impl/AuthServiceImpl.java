package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.impl;

import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.JWTAuthResponse;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.LoginDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.dtos.RegisterDto;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.Role;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.entities.User;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions.EcommerceAPIException;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.RoleRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.repositories.UserRepository;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.security.JwtTokenProvider;
import com.techqwerty.spring_boot_ecommerce_api_carrygo_store.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public JWTAuthResponse login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        jwtAuthResponse.setId(user.getId());
        jwtAuthResponse.setUsername(user.getUsername());
        jwtAuthResponse.setEmail(user.getEmail());
        jwtAuthResponse.setFirstName(user.getFirstName());
        return jwtAuthResponse;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new EcommerceAPIException("Username is already exists!.", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new EcommerceAPIException("Email is already exists!.", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhone(registerDto.getPhone());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // set the new user created role to ROLE_USER
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        // save the new user and the
        userRepository.save(user);

        return "User registered successfully!.";
    }
}
