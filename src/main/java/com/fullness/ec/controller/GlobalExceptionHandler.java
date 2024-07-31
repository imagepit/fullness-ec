package com.fullness.ec.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * アプリケーション全体の例外制御クラス
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    /**
     * ロガーオブジェクト
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 例外補足時のハンドラーメソッド
     * @param request HTTPリクエストオブジェクト
     * @param response HTTPレスポンスオブジェクト
     * @param handler ??
     * @param ex 例外オブジェクト
     * @return Spring MVCモデルとビューオブジェクト
     */
    @Override
    public ModelAndView resolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ){
        ex.printStackTrace();
        // StringWriter sw = new StringWriter();
        // PrintWriter pw = new PrintWriter(sw);
        // ex.printStackTrace(pw);
        // pw.flush();
        // String str = sw.toString();
        // logger.error(str);
        ModelAndView model = new ModelAndView();
        model.addObject("message",ex.getMessage());
        // URL毎にエラーページを振り分ける
        if(request.getRequestURI().startsWith("/admin/")){
            model.setViewName("/backend/error");
        }else{
            model.setViewName("/error");
        }
        return model;
    }
}
