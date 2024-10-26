package com.edigest.journalApp.config;

import com.edigest.journalApp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.websocket.Session;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //method chaining (.)
//        http
//                //This tells spring security to start authorizing the requests.
//                .authorizeRequests()
//                    //HTTP request matching with path /hello allowed for all users (permitted)
//                    .antMatchers("/hello").permitAll()
//                        //general matcher -> the request not matched by other matcher should be authenticated
//                        .anyRequest().authenticated()
//                //method to join several configuration (this applied to http)
//                .and()
//                //this enables form based authentication
//                .formLogin();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //check authentication for /journal
                .antMatchers("/journal/**", "/user/**").authenticated()
                //other and /journal all request does not need authentication
                .anyRequest().permitAll()
                .and()
                .httpBasic();

        //cross site request forgery
//        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        //convert password into hash
        return new BCryptPasswordEncoder();
    }
}
