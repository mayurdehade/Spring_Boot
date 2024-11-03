package com.jwt.SpringSecurityJWT.config;


import com.jwt.SpringSecurityJWT.service.JWTService;
import com.jwt.SpringSecurityJWT.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//class for validate jwt token (create our own filter)
//for every request it checks only once (OncePerRequestFilter -> abstract class)
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    //abstract method
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // From client side we get -> Bearer token
        String authHeader = request.getHeader("Authorization"); //we only take header with Authorization (not full request)

        String token = null;
        String username = null;

        //checking filter is not empty and token starts with Bearer
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            //from authHeader get username and token
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = context.getBean(MyUserDetailService.class).loadUserByUsername(username);

            //verify the token data and database data is matching or not
            if(jwtService.validateToken(token, userDetails)) {
                //if it's matching then create new authentication object
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //set context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
