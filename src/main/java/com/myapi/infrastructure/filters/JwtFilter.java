package com.myapi.infrastructure.filters;

import com.myapi.infrastructure.exception.LoginException;
import com.myapi.infrastructure.utils.JwtUtility;
import com.myapi.service.user.MyUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    final String PREFIX = "Bearer ";

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filter) throws ServletException, IOException, LoginException {
        String jwt = parseJwt(request);

        try {
            if (StringUtils.isNotEmpty(jwt) && jwtUtility.validateToken(jwt)) {
                String email = jwtUtility.getEmailFromToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
//            else {
//                LoginException exception = new LoginException();
//                exception.addMessage("No Token Found");
//                throw exception;
//            }
        } catch (LoginException e) {
            logger.error("Cannot set user authentication: {}", e);
            throw e;
        }

        filter.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith(PREFIX)) {
            return StringUtils.substringAfterLast(headerAuth, PREFIX);
        }

        return null;
    }
}
