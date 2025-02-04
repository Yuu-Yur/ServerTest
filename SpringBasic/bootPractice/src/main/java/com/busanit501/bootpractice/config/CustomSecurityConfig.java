package com.busanit501.bootpractice.config;

import com.busanit501.bootpractice.security.handler.Custom403Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // security 설정 on
@EnableMethodSecurity() // 권한별 설정 추가, food/read 에서 확인, prePostEnabled = true 이 parameter 로 들어갔는데 빠짐
public class CustomSecurityConfig {
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    @Bean // 여기서 인증, 인가 관련 구체적 설정
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("=======securityConfig=======");
        // 로그인 방식 설정 -> form 로그인으로
        http.formLogin(formLogin -> formLogin.loginPage("/member/login"));
        // 로그인 성공 시 이동하는 url
        http.formLogin(formLogin -> formLogin.defaultSuccessUrl("/food/list", true));
        // csrf 설정, 기본은 on 인데 끄고 작업, 사용하려면 웹에서 서버로 csrf 토큰을 생성해서 전송해야함
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        // 허용 관련 목록, 위에서부터 우선순위 desc
        http.authorizeHttpRequests(
                authorizeRequests -> {
                    authorizeRequests.requestMatchers("/css/**", "/js/**", "/images/**","/member/login").permitAll();
                    authorizeRequests.requestMatchers("/food/list").authenticated();
                    authorizeRequests.requestMatchers("/food/update", "/food/delete").hasRole("ADMIN");
                    authorizeRequests.anyRequest().authenticated();
                }
        );

        // 로그아웃 설정
        http.logout(logout -> logout.logoutUrl("/member/logout").logoutSuccessUrl("/member/login?logout"));

        // 자동 로그인
        http.rememberMe(rememberMe ->
                rememberMe.key("12345678")
                        .tokenRepository(persistentTokenRepository())
                        .userDetailsService(userDetailsService)
                        .tokenValiditySeconds(60*60*24*30));


        return http.build();
    }

    // 토큰 설정
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    // 403 handler
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

    // 패스워드 암호화 해싱 해싱 툴 = BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // css, js 등 정적 자원 제외
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("=======webSecurityCustomizer======");
        // 정적 자원에 접근하는 것에 대해선 무시
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }
}
