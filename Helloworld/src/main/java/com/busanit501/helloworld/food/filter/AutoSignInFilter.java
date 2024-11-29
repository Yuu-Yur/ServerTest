package com.busanit501.helloworld.food.filter;

import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebFilter(urlPatterns = "/food/signIn")
public class AutoSignInFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Cookie findCookie = findCookie(req.getCookies(), "autoSI");
        if (findCookie != null && findCookie.getValue() != null) {
            String uuid = findCookie.getValue();
            try {
                MemberDTO user = MemberService.INSTANCE.getAutoInfo(uuid);//쿠키의 uuid를 가진 멤버 가져옴
                if (user != null) {
                    session.setAttribute("user", user);//세션에 추가
                    res.sendRedirect("/food/main");
                } else chain.doFilter(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else chain.doFilter(request, response);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie foundCookie = null;
        log.info(cookies);
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    foundCookie = cookie;
                    break;
                }
            }
        }
        return foundCookie;
    }
}
