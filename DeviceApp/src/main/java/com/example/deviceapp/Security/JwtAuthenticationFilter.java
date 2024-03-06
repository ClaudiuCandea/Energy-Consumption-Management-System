package com.example.deviceapp.Security;

import com.example.deviceapp.Services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response
            , FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader  = request.getHeader("Authorization");
        String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        if( SecurityContextHolder.getContext().getAuthentication() == null){
            if(jwtService.isTokenValid(jwt)){
                Claims claims = jwtService.extractAllClaims(jwt);
                if (claims != null && claims.containsKey("role")) {
                    String role = (String) claims.get("role");
                    if ("ADMIN".equals(role)) {
                        System.out.println("aici in if");
                        // Role is ADMIN, allow the request
                        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthenticationToken(
                                "jwtAuthentication", "ADMIN", AuthorityUtils.createAuthorityList("ADMIN")));
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
