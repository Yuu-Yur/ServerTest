package com.busanit501.helloworld.jdbcex.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/member/main/myPage")
@Log4j2
public class SignInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("마이 페이지 이하로 접근 시 로그인 확인 필터");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        HttpSession session = req.getSession();

        if (session.isNew()) {
            log.info("세션 생성");
            if (req.getParameter("mno") != null) {
                session.setAttribute("mno", req.getParameter("mno"));
                resp.sendRedirect("/member/signIn");
            } else resp.sendRedirect("/member/main");
            return;
        } else if (session.getAttribute("signInInfo") == null) {
            log.info("세션은 이미 있지만 로그인 실패");
            session.setAttribute("mno", req.getParameter("mno"));
            resp.sendRedirect("/member/signIn");
            return;
        } else if (session.getAttribute("signInInfo") != null) {
            log.info("로그인 성공");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
