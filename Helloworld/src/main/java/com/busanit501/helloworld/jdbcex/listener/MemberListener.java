package com.busanit501.helloworld.jdbcex.listener;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Log4j2
public class MemberListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized");
// page -> request -> session -> application
// 갈수록 더 넓은곳에서 접근 가능
// 커넥션 풀 초기화, enum 인스턴스 할당 등등 누구나,전체가 접근가능한 공유저장소
// 스프링에서 이것을 사용하기에 미리 확인
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("appTest","helloworld");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed");
    }
}
