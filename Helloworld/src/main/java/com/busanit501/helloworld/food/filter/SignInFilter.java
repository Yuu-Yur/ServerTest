package com.busanit501.helloworld.food.filter;




// 사실 필터 쓰는 곳이 메뉴 등록 register 하나밖에 없지만 연습삼아서
// 로그인 페이지를 main에 묶어둬서 main으로 무한 리다이렉트하는 문제가 있음
// 로그인을 메인 컨트롤러 밖으로 빼면 해결
// 연습목적은 달성했으므로 주석

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/food/reg")
public class SignInFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.isNew()){
            // 처음 열렸다면 회원가입
            resp.sendRedirect("/food/main?button=signUp");
            return;
        }
        else if (session.getAttribute("user") == null) {
            // 처음 열린게 아니라면 로그인
            session.setAttribute("fromReg", true);
            resp.sendRedirect("/food/signIn");
            return;
        } else {
            // 로그인이 되어 있다면 req,resp를 그대로 넘겨줌
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
