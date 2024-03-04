package com.cda25.springboot.square_games.security;

import com.cda25.springboot.square_games.services.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtTokenAuthenticationFilter(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, java.io.IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;


        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            log.info("User found.");
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                log.info("Successfully authenticated.");
            } else {
                log.error("JWT Token expired. Log-in again.");
            }
        } else {
            log.info("Please authenticate.");
        }
        filterChain.doFilter(request, response);
    }

}