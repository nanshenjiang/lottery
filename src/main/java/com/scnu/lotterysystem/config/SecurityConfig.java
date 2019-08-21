package com.scnu.lotterysystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //启用安全认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //定义一个key
    private static final String KEY = "scnu";

    //注入UserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  //使用BCrypt加密
    }

    //实现方法authenticationProvider()，内含密码加密
    @Bean
    public AuthenticationProvider authenticationProvider(){
        //DaoAuthenticationProvider用于从UserDetailsService中取出认证信息
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder); //密码加密
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/asserts/**","/login.html").permitAll()  //静态资源可以访问
                .antMatchers("/h2-console/**").permitAll() // h2控制台都可以访问
                .antMatchers("/admin/**").hasRole("ADMIN")  //管理页需要admin角色才可以访问
                .antMatchers("/setter/**").hasRole("USER")
                .and()
                .formLogin()  //基于form表单的访问形式
                .loginPage("/login").defaultSuccessUrl("/dispath").failureUrl("/login-error")  //设置登录页,成功后访问的页面和访问错误页
                .and().rememberMe().key(KEY)  //remember-me的设置
                .and().exceptionHandling().accessDeniedPage("/403");  //账号密码错误进入403界面
        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求
    }

    /* *
    * 认证信息从数据库从获取
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);   //使用数据库存储的信息
        auth.authenticationProvider(authenticationProvider());   //密码加密使用BCrypt加密算法
    }
}
