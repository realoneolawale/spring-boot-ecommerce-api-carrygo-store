package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    // inject dependencies
    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    // URL to skip authentication for
    private static final List<String> PUBLIC_URLS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/shopping/add-to-cart",
            "/api/products/**", // FOR TESTING
            "/api/shopping/update-cart/{productId}/{userId}/{action}",
            "/api/images/view/",
            "/swagger-ui/",
            "/v3/api-docs/");

    public JWTAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // URL not to process authentication for
        String path = request.getRequestURI();
        if (PUBLIC_URLS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        // get JWT token from http request
        String token = getTokenFromRequest(request);

        // validate token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // get username from token
            String username = jwtTokenProvider.getUsername(token);
            // load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

}

/*
 * @Component
 * public class JwtAuthenticationFilter extends OncePerRequestFilter {
 * 
 * private static final List<String> PUBLIC_URLS = List.of(
 * "/api/auth/login",
 * "/api/auth/register",
 * "/api/shopping/add-to-cart",
 * "/swagger-ui/",
 * "/v3/api-docs/"
 * );
 * 
 * @Override
 * protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response,
 * FilterChain filterChain)
 * throws ServletException, IOException {
 * 
 * 
 * // Skip public paths
 * String path = request.getRequestURI();
 * if (PUBLIC_URLS.stream().anyMatch(path::startsWith)) {
 * filterChain.doFilter(request, response);
 * return;
 * }
 * 
 * // 🔐 Your JWT extraction + validation logic here
 * // If invalid or missing -> throw exception -> triggers 401
 * 
 * filterChain.doFilter(request, response);
 * }
 * }
 */
