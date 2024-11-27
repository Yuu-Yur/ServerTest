package com.busanit501.helloworld.food.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//톰캣 서버에서,
// 한글로 입력된 내용을, UTF8로 변환해서 보내기.
// 필터, 서버에 작업을 실행하기전에, 먼저 검사한다.
// 유효성 체크.
@WebFilter(urlPatterns = {"/*"}) // 하위로 들어오는 모든 url에 대해
@Log4j2
public class UTF8Filter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/ 하위로 들어오는 모든 url에 대해서 " +
                "한글 변환 필터링 동작중.");
        HttpServletRequest request = (HttpServletRequest) servletRequest; // 서블릿 리퀘스트 -> http 서블릿 리퀘스트 캐스팅
        request.setCharacterEncoding("utf-8"); // 인코딩을 utf-8 설정
        filterChain.doFilter(servletRequest, servletResponse); //리퀘스트, 리스폰스를 다음 서블릿이나 필터로 전달
    }
}
