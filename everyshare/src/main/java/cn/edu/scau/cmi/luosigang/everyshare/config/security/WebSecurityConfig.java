package cn.edu.scau.cmi.luosigang.everyshare.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());//自定义AuthenticationProvider
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);//关闭用户名未找到异常转换，使返回的异常信息更加准确
        provider.setUserDetailsService(userDetailsService);//自定义userDetailsService（从数据库查找用户信息并封装）
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//关闭csrf过滤器
        http.formLogin().loginProcessingUrl("/api/user/login")//启用Spring Security默认的登录服务
                .successHandler(authenticationSuccessHandler)//认证成功处理器
                .failureHandler(authenticationFailureHandler)//认证失败处理器
                .permitAll();
        http.logout().logoutUrl("/api/user/logout")//启用Spring Security默认的退出登录服务
                .logoutSuccessHandler(logoutSuccessHandler)//退出登录成功处理器
                .deleteCookies("JSESSIONID");//删除JSESSIONID
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)//未认证处理器
                .accessDeniedHandler(accessDeniedHandler);//拒绝访问（无权限）处理器
        http.authorizeRequests()//请求权限认证
                .antMatchers("/api/user/login","/api/user/register/**","/api/user/current","/api/goods/get/**","/api/category/**","/api/picture/**","/swagger**/**","/webjars/**","/v2/**","/doc.html").permitAll()//无条件允许任何访问
                .antMatchers("/api/goods/save").hasRole("BUSINESS")//商户可访问
                .antMatchers("/**").authenticated();//认证过可访问
    }
}
