package com.mayur.SpringSecurityEx.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //in single line
         return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();
    }


    //custom users using database
    //change authentication provider (own custom authentication provider)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        
        //return object of Authentication Provider (AuthenticationProvider is a Interface)
        //DaoAuthenticationProvider implementing Authentication provider indirectly
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //DaoAuthenticationProvider ask two things passwordEncoder and UserDetailsService (username, pass, roles and other information)

        //verify password
        //BCrypt password
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        //for set own UserDetailsService we have to implement it beacuse it's interface then create our own class which implement UserDetailsService
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    //plain -> hash1 -> hash2
    //from plain text we get hash but from hash we can't get plain text
    //bcrypt for user register to store it in database
    //bcrypt for login password verification





}
