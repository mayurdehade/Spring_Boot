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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration to mark this class as configuration
// @EnableWebSecurity change the default flow and tell to go with this configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

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

    //This code is for default
    //username and password authenticate feature (own for multiple users)
    //UserDetailsService (Interface)
//    @Bean
//    public UserDetailsService userDetailsService() {
//        //add deferent users
//        //return object of UserDetails
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("anju")
//                .password("1234")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("manju")
//                .password("5678")
//                .roles("USER")
//                .build();
//
//        //here we can pass multiple users
//        return new InMemoryUserDetailsManager(user1, user2);
//
//        //InMemoryUserDetailsManager implementing UserDetailsManager
//        //UserDetailsManager implementing UserDetailsService
//    }


    //custom users using database
    //change authentication provider (own custom authentication provider)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        //return object of Authentication Provider (AuthenticationProvider is a Interface)
        //DaoAuthenticationProvider implementing Authentication provider indirectly
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //DapAuthenticationProvider ask two things passwordEncoder and UserDetailsService (username, pass, roles and other information)
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        //for set own UserDetailsService we have to implement it beacuse it's interface then create our own class which implement UserDetailsService
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }







}
