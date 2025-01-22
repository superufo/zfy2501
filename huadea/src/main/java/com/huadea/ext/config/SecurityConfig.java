package com.huadea.ext.config;

import com.huadea.ext.commons.AjaxResult;
import com.huadea.ext.service.IModuleService;
import com.huadea.ext.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    IModuleService moduleService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserServiceImpl userService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserServiceImpl userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        log.info("AuthenticationProvider -------------------------------");
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public HuadeaAuthorizationManager huadeaAuthorizationManager() throws Exception {
        return new HuadeaAuthorizationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    /**
     * 这个就是 Spring Security 过滤器链，Spring Security 中的所有功能都是通过这个链来提供的
     *
     * @return
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HuadeaAuthorizationManager huadeaAuthorizationManager = huadeaAuthorizationManager();

         //return http
         return http.authenticationProvider(authenticationProvider())
         .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // 禁用表单登录  禁用HTTP基本认证
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))

        //这个实际上就是在默认的过滤器基础之上进行构建
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        //requests.requestMatchers( "/user/reg", "/user/login", "/user/test","/swagger-ui/**").permitAll()  会被access覆盖
        .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/auth/add", "/user/reg", "/auth/test", "/user/login", "/user/test").permitAll()
                        // 静态资源，可匿名访问
                        .requestMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                        .requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                        .anyRequest().access(huadeaAuthorizationManager))
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(new UserAuthenticationEntryPoint());
                    exception.accessDeniedHandler(accessDeniedHandlerHandler());
                })
        .build();
    }


    private static AccessDeniedHandler accessDeniedHandlerHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            AjaxResult ajax = AjaxResult.error(400, "无访问权限");

            response.getWriter().println(ajax);
        };
    }

}
