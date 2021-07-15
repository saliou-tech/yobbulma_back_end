/*
package com.Yobulma.Yobulma.Security;


import com.Yobulma.Yobulma.Service.CustomUserDetailService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailService customUserDetailService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(CustomUserDetailService customUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customUserDetailService = customUserDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/yobulma/register").permitAll().anyRequest().authenticated()
        .and().addFilter(new AuthenticationFilter(authenticationManager()));
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder)  ;
    }

    public AuthenticationFilter getAuthenticationFillter() throws Exception{
        final  AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("yobulma/login");
        return  filter;

    }
}
*/
