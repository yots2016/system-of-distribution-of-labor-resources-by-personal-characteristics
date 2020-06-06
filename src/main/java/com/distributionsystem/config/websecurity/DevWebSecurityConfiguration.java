package com.distributionsystem.config.websecurity;

import com.distributionsystem.config.MyAccessDeniedHandler;
import com.distributionsystem.service.UserService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile({"herokudev", "herokuprod", "h2dev", "psdev"})
@EnableWebSecurity
@Configuration
public class DevWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MyAccessDeniedHandler myAccessDeniedHandler;

    private final UserService userService;

    public DevWebSecurityConfiguration(MyAccessDeniedHandler myAccessDeniedHandler,
                                       //TODO 08.05.2020 Maybe redesign is needed because of circular dependency
                                       @Lazy UserService userService) {
        this.myAccessDeniedHandler = myAccessDeniedHandler;
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .disable()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
//                        "/",
                        "/login",
                        "/registration",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**",
                        "h2-console")
                .permitAll()
                .antMatchers("/projects/delete/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/projects")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
