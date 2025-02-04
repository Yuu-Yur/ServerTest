package com.busanit501.bootpractice.security;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class customUserDetailsService implements UserDetailsService {
    // constructor 주입
    private PasswordEncoder passwordEncoder;
    customUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    // 시큐리티에서 로그인 작업 시 동작하는 메서드
    // username 을 받아서 db의 user db 와 대조 없으면 usernameNotFoundException throws, 있으면 UserDetails 반환
    // 앞단에서 username, password 고정으로 보내야함
    // 왜 UserDetails 로 반환하는지? -> 여러 메서드를 가지고 있음 예시) getAuthorities : 로그인 한 유저의 인증 정보를 가짐
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + "확인");
        UserDetails userDetails = User.builder()
                .username("momoa")
                .password(passwordEncoder.encode("1234"))
                .authorities("ROLE_USER")
                .build();
        return userDetails;
    }
}
