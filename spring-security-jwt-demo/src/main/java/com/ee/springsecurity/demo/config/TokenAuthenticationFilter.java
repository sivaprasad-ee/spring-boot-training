package com.ee.springsecurity.demo.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenHelper tokenHelper;
    private final UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(
            TokenHelper tokenHelper, UserDetailsService userDetailsService) {
        super();
        this.tokenHelper = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain chain) throws IOException, ServletException {
        String authToken = tokenHelper.getToken(request);
        if (authToken != null) {
            String username = tokenHelper.getUsernameFromToken(authToken);
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (tokenHelper.validateToken(authToken, userDetails)) {
                    var authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
