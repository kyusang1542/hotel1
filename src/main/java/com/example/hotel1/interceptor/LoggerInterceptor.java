package com.example.hotel1.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    // 컨트롤러 실행 전에 log 작업이 시작됨을 알림
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.debug("============== START ==============");
        log.debug(" Request URI \t:  " + request.getRequestURI());

        return true;
    }

    @Override
    // 컨트롤러 수행 후 결과를 뷰로 보내기 전에 log 작업이 끝났음을 알림
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception{
        log.debug("============== END ==============\n");
    }
}
