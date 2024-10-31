package com.mayur.SpringSecurityEx.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration to mark this class as configuration
// @EnableWebSecurity change the default flow and tell to go with this configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.csrf(csrf -> csrf.disable()); //disable csrf

//        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); //all request required to authenticated

//        http.formLogin(Customizer.withDefaults()); //for form login working

//        http.httpBasic(Customizer.withDefaults()); //rest access for postman

        //make http stateless
//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //make http stateless new session for every request (new session id for every request)

        //anonymous class
//        Customizer<CsrfConfigurer<HttpSecurity>> custCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> customizer) {
//                customizer.disable();
//            }
//        };

        //this required object of Customizer<CsrfConfigurer<HttpSecurity>>
//        http.csrf(custCsrf);


        //in single line
         return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .build();


//        return http.build(); //returns the object of security filter chain
    }
}
