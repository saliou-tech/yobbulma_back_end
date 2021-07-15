package com.Yobulma.Yobulma.Configuration;

import com.Yobulma.Yobulma.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
               // .antMatchers("/api/client/login").permitAll()
                .antMatchers("/yobulma/**").permitAll()
                .antMatchers("/yobulma/login").permitAll()
                .antMatchers("/yobulma/register").permitAll()
               .antMatchers("/yobulma/allLivreur").permitAll()
                .antMatchers("/api/commande/**").authenticated();

             // .antMatchers("/api/products/**").hasAuthority("ADMIN")
        http    .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        http
                .addFilterBefore(new JwtTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);



       // http.csrf().disable().authorizeRequests().anyRequest().permitAll();
    }



/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        System.out.println("called1");
        http.csrf().disable().authorizeRequests()

                .anyRequest().authenticated()


                .antMatchers("/api/client/login").permitAll()
                .antMatchers("/api/client/register").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                // We filter the api/login requests
                .apply(new JwtConfigurer(jwtTokenProvider));
        http
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);


       // http.csrf().disable().authorizeRequests().anyRequest().permitAll();

    }
/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ressorce");
    }

 */

  @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized request");
    }


/*
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }


 */
    @Bean
    public UserDetailsService mongoUserDetails() {
        return (UserDetailsService) new CustomUserDetailService();
    }


















}
